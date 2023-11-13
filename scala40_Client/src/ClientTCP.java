import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientTCP {
    public Socket socket;
    public ObjectOutputStream oOutput;
    public ObjectInputStream oInput;


    public ClientTCP() throws UnknownHostException, IOException{

        //creazione socket comunicazione
        socket=new Socket(/*IP*/"127.0.0.1", /*porta*/666);

        oInput=new ObjectInputStream(socket.getInputStream());
        
        oOutput=new ObjectOutputStream(socket.getOutputStream());
        
        
    }

    public void send(Object object) throws IOException{

        //invia messaggio al server
        oOutput.writeObject(object);
    }

    public Object receive() throws IOException, ClassNotFoundException{

        //legge e ritorna messaggio dal server
        return oInput.readObject();
    }


}
