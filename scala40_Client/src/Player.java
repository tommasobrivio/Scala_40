import java.net.Socket;
import java.util.List;

public class Player {

    private String nome;    /* nome inserito dal giocatore */
    private boolean pronto; /* indica se il giocatore è pronto a giocare */
    private List<Carta> carteMano;  /* carte che possiede il giocatore */
    private Socket socket;  /* socket personale del giocatore */

    /* costruttore */
    public Player(String s){
        nome=s;
        pronto=false;
    }
}
