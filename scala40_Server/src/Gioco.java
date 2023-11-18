import java.io.IOException;

public class Gioco extends Thread{

    /* attributo statico per indicare se il gioco è in corso */
    static public boolean gioco;

    ServerTCP server;   /* oggetto serverTCP per la comunicazione */
    PlayerServer p1, p2;   /* oggetti player all' interno del gioco */
    PlayerServer inGioco;   /* player che sta giocando in un turno */
    Campo campo;    /* campo con le varie combinazioni di carte */

    Messaggio messaggio;    /* oggetto messaggio per gestire i contenuti inviati e ricevuti */

    /* costruttore */
    public Gioco(PlayerServer p1, PlayerServer p2){
        this.p1=p1;
        this.p2=p2;
        this.inGioco=p1;
        campo=new Campo();
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
            messaggio.setOutput(campo.serializeMazzo());

            /* invia il mazzo ai giocatori */
            server.sendAll(messaggio.output);

        } catch (IOException e) {
            e.printStackTrace();
        }

        /* mentre si sta giocando */
        while(gioco && p1.isAlive() && p2.isAlive()){

            /* gestisce i messaggi tra client server */
            gestioneRichieste();
        }

        /* gioco finito */
        gioco=false;

        try {
            /* invia che il gioco è finito ai giocatori */
            server.sendAll("gioco finito;");

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /* gestisce le richieste */
    private void gestioneRichieste(){
        inGioco.start();

        
    }
}
