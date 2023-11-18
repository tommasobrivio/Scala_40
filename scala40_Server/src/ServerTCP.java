import java.io.*;
import java.net.*;

public class ServerTCP {
    
    public ServerSocket serverSocket;
    public GestionePlayers gestionePlayers;
    private Messaggio messaggio;

    public ServerTCP(int porta) throws IOException{
        // Crea un socket server che ascolta sulla porta "porta"
        this.serverSocket = new ServerSocket(porta);
        gestionePlayers=new GestionePlayers();
    }
        

    public Socket accettaConnessione() throws IOException{
        //accetta la connessione del client
        Socket socket = this.serverSocket.accept();

        if(gestionePlayers.aggiungiPlayer(socket)){
            
            send("connessione accettata;", socket);
        }
        return socket;
    }

    public void chiudiConnessione() throws IOException{

        //chiude la connessione
        serverSocket.close();
    }

    public void send(String messaggio, Socket socket) throws IOException{
        PrintWriter output= new PrintWriter(socket.getOutputStream());
        output.println(messaggio);
    }
    
    public void sendAll(String messaggio) throws IOException{
        for(Socket socket : gestionePlayers.connPlayers){
            send(messaggio, socket);
        }
    }

    public void close() throws IOException{
        gestionePlayers.chiudiConnessioni();

        if(!serverSocket.isClosed())
            serverSocket.close();
    }
}



