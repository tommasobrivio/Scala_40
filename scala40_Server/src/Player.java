import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Player {

    private String nome;    /* nome inserito dal giocatore */
    private boolean pronto; /* indica se è pronto a giocatore */
    public List<Carta> carteMano;   /* carte che ha in mano */
    private Socket socket;  /* socket personale del giocatore */

    /* costruttore */
    public Player(Socket socket){
        this.socket=socket;
        carteMano=new ArrayList<>();
    }

    /* setta pronto */
    public void setPronto(boolean b){
        pronto=b;
    }

    
}
