import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientTCP {
    public Socket socket;
    public DataInputStream is;
    public DataOutputStream os;

    
    public ClientTCP() throws UnknownHostException, IOException{

        //creazione socket comunicazione
        socket=new Socket(/*IP*/"", /*porta*/0);

        is=new DataInputStream(socket.getInputStream());

        os=new DataOutputStream(socket.getOutputStream());
    }

    public void send(Object object) throws IOException{

        //invia messaggio al server
        os.writeUTF((String) object);
    }

    public Object receive() throws IOException{

        //legge e ritorna messaggio dal server
        return is.readUTF();
    }


}
