import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class PlayerServer extends Thread{

    public String nome;    /* nome inserito dal giocatore */
    public List<Carta> carteMano;   /* carte che ha in mano */
    private Socket socket;  /* socket personale del giocatore */
    public List<Carta> combinazioni; /* combinazioni che il giocatore ha in campo */
    public boolean aperto; /* true se ha giocato i primi punti */
    public Messaggio messaggio;

    /* costruttore */
    public PlayerServer(String nome, Socket socket){
        this.nome=nome;
        this.socket=socket;
        carteMano=new ArrayList<>();
        aperto=false;
    }

    public void run(){
        try{
            ObjectInputStream input= new ObjectInputStream(socket.getInputStream());

            while(!this.socket.isClosed()){
                
                messaggio=new Messaggio(nome, input) 
            }
        }
    }
    
}
