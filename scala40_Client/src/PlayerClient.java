import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

public class PlayerClient {

    public String nome;    /* nome inserito dal giocatore */
    public boolean pronto; /* indica se il giocatore è pronto a giocare */
    public List<Carta> carteMano;  /* carte che possiede il giocatore */
    private Socket socket;  /* socket personale del giocatore */

    /* costruttore */
    public PlayerClient(String s) throws UnknownHostException, IOException{
        nome=s;
        pronto=false;
        socket=new Socket("127.0.0.1", 666);
    }
}
