import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class GestionePlayers {
    
    /* lista giocatori */
    public List<Socket> connPlayers;


    /* costruttore */
    public GestionePlayers(){
        connPlayers=new ArrayList<>();
    }

    /* aggiunge giocatore alla lista */
    public boolean aggiungiPlayer(Socket p){
        //if(Gioco.gioco){
            for(Socket socket : connPlayers){
                if(socket.equals(p)){
                    return false;
                }
            }
            connPlayers.add(p);
            return true;
        //}
        //return false;
    }

    /* chiude tutte le connessioni */
    public void chiudiConnessioni() throws IOException{
        for(Socket socket : connPlayers){
            socket.close();
        }
    }


}
