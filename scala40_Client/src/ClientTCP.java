import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientTCP {

    public Socket socket;   /* socket per comunicare col server */
    public ObjectOutputStream oOutput;  /* oggetto per inviare dati al server */
    public ObjectInputStream oInput;    /* oggetto per leggere messaggi del server */


    /* costruttore default */
    public ClientTCP() throws UnknownHostException, IOException{

        //creazione socket comunicazione
        socket=new Socket(/*IP*/"127.0.0.1", /*porta*/666);

        /* creazione oggetto input */
        oInput=new ObjectInputStream(socket.getInputStream());
        
        /* creazione oggetto output */
        oOutput=new ObjectOutputStream(socket.getOutputStream());
        
    }

    /* metodo per inviare oggetti al server */
    public void send(Object object) throws IOException{

        //invia messaggio al server
        oOutput.writeObject(object);
    }

    /* metodo per leggere oggetti dal server */
    public Object receive() throws IOException, ClassNotFoundException{

        //legge e ritorna messaggio dal server
        return oInput.readObject();
    }

    public void terminaConnection() throws IOException{
        try{
            this.oInput.close();
            this.oOutput.close();
            this.socket.close();
        }catch(IOException e){
            System.err.println("Errore: " + e.getMessage());
            throw e;
        }
    }


}
