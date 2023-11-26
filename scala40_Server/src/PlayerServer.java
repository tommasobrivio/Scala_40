import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class PlayerServer extends Thread{

    public String nome;    /* nome inserito dal giocatore */
    public List<Carta> carteMano;   /* carte che ha in mano */
    public Socket socket;  /* socket personale del giocatore */
    public GestioneCombinazioni combinazioni; /* combinazioni che il giocatore ha in campo */
    public boolean aperto; /* true se ha giocato i primi punti */
    public Messaggio messaggio;     /* messaggio per salvare quello che riceve dal client */
    

    /* costruttore */
    public PlayerServer(String nome, Socket socket){
        this.nome=nome;
        this.socket=socket;
        carteMano=new ArrayList<>();
        aperto=false;
    }

    /* metodo run dei thread */
    public void run(){
        try{
            /* oggetto BufferedReader per leggere messaggio del client */
            BufferedReader input= new BufferedReader(new InputStreamReader(socket.getInputStream()));

            /* mentre la connessione non è chiusa */
            while(!this.socket.isClosed()){
                System.out.println(input.readLine());
                messaggio=new Messaggio(input.readLine());
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
}
