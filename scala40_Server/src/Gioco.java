import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Gioco extends Thread{

    /* attributo statico per indicare se il gioco è in corso */
    static public boolean gioco;

    public ServerTCP server;   /* oggetto serverTCP per la comunicazione */
    public PlayerServer p1, p2;   /* oggetti player all' interno del gioco */
    public PlayerServer inGioco;   /* player che sta giocando in un turno */
    public Campo campo;    /* campo con le varie combinazioni di carte */

    public List<Combinazione> perAprire;


    public Messaggio messaggio;    /* oggetto messaggio per gestire i contenuti inviati e ricevuti */

    /* costruttore */
    public Gioco(PlayerServer p1, PlayerServer p2){
        this.p1=p1;
        this.p2=p2;
        this.inGioco=p1;
        campo=new Campo();
        perAprire=new ArrayList<>();
    }
    
    public void run(){
        /* inizia il gioco */
        gioco=true;

        try {
            server.sendAll("gioco iniziato;");

            /* carica il mazzo */
            campo.gestioneMazzo.loadFromFileCSV();

            /* distribuisce le carte */
            campo.gestioneMazzo.distribuisciCarte(p1, p2);

            /* crea messaggio e setta il contenuto da inviare */
            messaggio=new Messaggio("mazzo e scarti;");
            messaggio.setOutput(campo.gestioneMazzo.serialize());

            /* invia il mazzo ai giocatori */
            server.sendAll(messaggio.output);

        } catch (IOException e) {
            e.printStackTrace();
        }

        /* mentre si sta giocando */
        while(gioco && p1.isAlive() && p2.isAlive()){

            /* gestisce i messaggi tra client server */
            try {
                gestioneRichieste();
            } catch (IOException e) {
                
                e.printStackTrace();
            }
        }

        

        try {
            /* invia che il gioco è finito ai giocatori */
            server.sendAll("gioco finito;");

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /* gestisce le richieste */
    private void gestioneRichieste() throws IOException{


        /* starta il metodo run del playerServer e riceve i dati */
        inGioco.start();

        /* divide i dati */
        String[] dati=inGioco.messaggio.mess.split(";");

        /* se il primo dato non è richiesta client manda il messaggio di errore */
        if(!dati[0].equals("richiesta client")){
            server.send("messaggio errato;", inGioco.socket);
            return;
        }
            
        switch (dati[1]) {

            /* se la richiesta è di pescare dal mazzo */
            case "pescaMazzo":

                /* prende la prima carta del mazzo */
                Carta pesca = campo.gestioneMazzo.popIndex(campo.gestioneMazzo.mazzo);

                /* invia la carta */
                server.send(pesca.serialize(), inGioco.socket);

                break;

            /* se la richiesta è di pescare dagli scarti */
            case "pescaScarto":

                /* solo se ha aperto può prendere la carta degli scarti */
                if(inGioco.aperto){
                    /* prende la prima carta degli scarti */
                    pesca = campo.gestioneMazzo.popIndex(campo.gestioneMazzo.scarti);

                    /* invia la carta */
                    server.send(pesca.serialize(), inGioco.socket);

                }
                else{
                    /* messaggio errore */
                    server.send("non hai aperto", inGioco.socket);
                }
                
                break;

            /* se la richiesta è gioca */
            case "gioca":

                /* deserializza la combinazione di carte ricevuta */
                List<Carta> temp= inGioco.messaggio.deSerializeCarte();

                /* crea l' oggetto combinazione */
                Combinazione c = new Combinazione(temp);

                /* se la combinazione non supera i controlli di validazione */
                if(!campo.gestioneCombinazioni.checkTipoCombinazione(c)){

                    /* messaggio d' errore */
                    server.send("combinazione non corretta;", inGioco.socket);

                    /* interrompe il metodo */
                    break;
                }

                /* se non ha ancora aperto */
                if(!inGioco.aperto){
                    /* aggiunge la combinazione temporaneamente alla lista perAprire */
                    perAprire.add(c);
                    
                    server.send("gioca o passa;", inGioco.socket);
                }

                else{
                    /* aggiunge la combinazione al mazzo */
                    campo.gestioneCombinazioni.combinazioni.add(c);

                    server.send("richiesta eseguita;", inGioco.socket);
                
                    /* invia ai giocatori il campo */
                    server.sendAll("campo;"+campo.serializeAll());
                }


                break;

            case "passa":
                
            /* se non ha ancora aperto */
                if(!inGioco.aperto){

                    /* controlla che abbia giocato almeno 40 */
                    int somma=0;
                    for(Combinazione combinazione : perAprire){
                        for(Carta carta : combinazione.combinazione){
                            somma+=carta.value;
                        }
                    }

                    /* se non sono 40 */
                    if(somma<40){
                        server.send("punti insufficienti;", inGioco.socket);
                        /* ripulisce la lista temporanea */
                        perAprire.clear();
                        break;
                    }

                    /* altrimenti */
                    else{
                        /* ha aperto */
                        inGioco.aperto=true;

                        /* controlli riguardo lo scarto */
                        String[] datiCarta=dati[1].split(",");

                        Carta scarto=new Carta(datiCarta[0].charAt(0), datiCarta[1].charAt(0), datiCarta[2].charAt(0), Integer.parseInt(datiCarta[3]));

                        /* se la carta scartata attacca */
                        if(campo.gestioneCombinazioni.checkPushCombinazione(scarto) != null){

                            /* messaggio d'errore */
                            server.send("cambia scarto;", inGioco.socket);
                            break;
                        }
                        
                        /* se passa i controlli aggiunge la carta agli scarti */
                        campo.gestioneMazzo.scarti.add(scarto);

                        /* messaggio successo */
                        server.send("richiesta eseguita;", inGioco.socket);

                        /* se il mazzo è vuoto */
                        if(campo.gestioneMazzo.mazzo.size()==0){
                            /* mischia gli scarti nel mazzo tranne l' ultima carta */
                            campo.gestioneMazzo.mischiaScarti();
                            
                        }

                        /* invia il campo */
                        server.sendAll("campo;"+campo.serializeAll());

                        /*cambia il giocatore corrente */
                        inGioco = (inGioco.nome==p1.nome) ? p2 : p1;

                    }
                }

                /* altrimenti se ha aperto */
                else{

                    /* prende dati carta scartata */
                    String[] datiCarta=dati[1].split(",");

                    Carta scarto=new Carta(datiCarta[0].charAt(0), datiCarta[1].charAt(0), datiCarta[2].charAt(0), Integer.parseInt(datiCarta[3]));

                    /* controlla se può attaccare */
                    if(campo.gestioneCombinazioni.checkPushCombinazione(scarto) != null){

                        server.send("cambia scarto;", inGioco.socket);
                        break;
                    }

                    /* aggiunge agli scarti */
                    campo.gestioneMazzo.scarti.add(scarto);

                    server.send("richiesta eseguita;", inGioco.socket);

                    /* se il mazzo è vuoto */
                    if(campo.gestioneMazzo.mazzo.size()==0){
                        /*mischia gli scarti nel mazzo */
                        campo.gestioneMazzo.mischiaScarti();
                        
                    }

                    /*invia il campo */
                    server.sendAll("campo;"+campo.serializeAll());

                    /*cambia giocatore corrente */
                    inGioco = (inGioco.nome==p1.nome) ? p2 : p1;
                }

                break;

            case "attacca":
                
                /*prende dati carta da attaccare */
                String[] datiCarta=dati[1].split(",");

                Carta attacca=new Carta(datiCarta[0].charAt(0), datiCarta[1].charAt(0), datiCarta[2].charAt(0), Integer.parseInt(datiCarta[3]));

                /* se la carta può attaccare */
                if(campo.gestioneCombinazioni.checkPushCombinazione(attacca) != null){

                    /*aggiunge la carta alla combinazione */
                    campo.gestioneCombinazioni.checkPushCombinazione(attacca).combinazione.add(attacca);

                    server.send("richiesta eseguita;", inGioco.socket);

                    /*invia il campo aggiornato */
                    server.sendAll("campo;"+campo.serializeAll());

                }
                else{
                    /*messaggio d'errore */
                    server.send("cambia carta;", inGioco.socket);
                }
                
                break;
        
            case "disconnetti":
                inGioco.socket.close();

                if(inGioco==p1)
                    server.send("p1 disconnesso", p2.socket);

                else
                    server.send("p2 disconnesso", p1.socket);

                gioco=false;

                break;
            default:
                break;
        }
        
    }
}
