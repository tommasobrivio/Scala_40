import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class PlayerServer {

    public String nome;    /* nome inserito dal giocatore */
    public boolean pronto; /* indica se è pronto a giocatore */
    public List<Carta> carteMano;   /* carte che ha in mano */
    private Socket socket;  /* socket personale del giocatore */
    public List<Carta> combinazioni; /* combinazioni che il giocatore ha in campo */
    public boolean aperto; /* true se ha giocato i primi punti */

    /* costruttore */
    public PlayerServer(Socket socket){
        this.socket=socket;
        carteMano=new ArrayList<>();
        aperto=false;
    }

    /* setta pronto */
    public void setPronto(boolean b){
        pronto=b;
    }

    
}
