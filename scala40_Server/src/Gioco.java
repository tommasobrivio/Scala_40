import java.io.IOException;

public class Gioco extends Thread{

    /* attributo statico per indicare se il gioco è in corso */
    static public boolean gioco;

    ServerTCP server;
    PlayerServer p1, p2, inGioco;

    Campo campo;

    Messaggio messaggio;

    public Gioco(PlayerServer p1, PlayerServer p2){
        this.p1=p1;
        this.p2=p2;
        this.inGioco=p1;
        campo=new Campo();
    }
    
    public void run(){
        gioco=true;

        messaggio=new Messaggio("gioco iniziato", null, null);
        
        try {
            server.sendAll(messaggio);
            
            campo.gestioneMazzo.loadFromFileCSV();

            campo.gestioneMazzo.distribuisciCarte(p1, p2);

            
        } catch (IOException e) {
            e.printStackTrace();
        }

        while(gioco && p1.isAlive() && p2.isAlive()){
            gestioneRichieste();
        }

        gioco=false;

        messaggio=new Messaggio("gioco finito", null, null);
        try {
            server.sendAll(messaggio);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void gestioneRichieste(){
        inGioco.start();
    }
}
