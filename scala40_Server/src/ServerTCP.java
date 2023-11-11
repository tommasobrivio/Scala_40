import java.io.*;
import java.net.*;

public class ServerTCP {
    
    private ServerSocket serverSocket;

    public ServerTCP(int porta) throws IOException{
        // Crea un socket server che ascolta sulla porta "porta"
        this.serverSocket = new ServerSocket(porta);
    }
        

    public Socket accettaConnessione() throws IOException{
        //accetta la connessione del client
        return this.serverSocket.accept();
    }

    public void chiudiConnessione() throws IOException{

        //chiude la connessione
        serverSocket.close();
    }

    
}



