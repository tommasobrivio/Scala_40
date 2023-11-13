import java.util.ArrayList;
import java.util.List;

public class GestionePlayers {
    
    /* lista giocatori */
    public List<Player> players;

    /* costruttore */
    public GestionePlayers(){
        players=new ArrayList<>();
    }

    /* aggiunge giocatore alla lista */
    public boolean aggiungiPlayer(Player p){
        if(players.size() == 2 && Gioco.gioco){
            return false;
        }

        else{
            players.add(p);
            return true;
        }
    }


}
