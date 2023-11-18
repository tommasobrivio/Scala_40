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

        
        
        try {
            server.sendAll("gioco iniziato;");
            
            campo.gestioneMazzo.loadFromFileCSV();

            campo.gestioneMazzo.distribuisciCarte(p1, p2);

            messaggio=new Messaggio("mazzo e scarti;");

            messaggio.setOutput(campo.serializeMazzo());

            
        } catch (IOException e) {
            e.printStackTrace();
        }

        while(gioco && p1.isAlive() && p2.isAlive()){
            gestioneRichieste();
        }

        gioco=false;

        try {
            server.sendAll("gioco finito;");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void gestioneRichieste(){
        inGioco.start();

        
    }
}
