import java.util.ArrayList;
import java.util.List;

public class GestionePlayers {
    
    /* lista giocatori */
    public List<PlayerServer> players;

    /* costruttore */
    public GestionePlayers(){
        players=new ArrayList<>();
    }

    /* aggiunge giocatore alla lista */
    public boolean aggiungiPlayer(PlayerServer p){
        if(players.size() == 2 && Gioco.gioco){
            return false;
        }

        else{
            players.add(p);
            return true;
        }
    }


}
