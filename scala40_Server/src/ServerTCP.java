import java.io.*;
import java.net.*;

public class ServerTCP {
    
    public ServerSocket serverSocket;   /* oggetto serverSocket per la comunicazione */
    public GestionePlayers gestionePlayers;     /* contiene la lista delle socket dei giocatori */

    public ServerTCP(int porta) throws IOException{
        // Crea un socket server che ascolta sulla porta "porta"
        this.serverSocket = new ServerSocket(porta);
        gestionePlayers=new GestionePlayers();
    }
        

    public Socket accettaConnessione() throws IOException{
        //accetta la connessione del client
        Socket socket = this.serverSocket.accept();

        if(gestionePlayers.aggiungiPlayer(socket)){
            
            /* se riesce ad aggiungere invia conferma */
            send("connessione accettata;", socket);
        }
        return socket;
    }

    public void chiudiConnessione() throws IOException{

        //chiude la connessione
        serverSocket.close();
    }

    /* invia il messaggio a un client specifico */
    public void send(String messaggio, Socket socket) throws IOException{
        PrintWriter output= new PrintWriter(socket.getOutputStream());
        output.println(messaggio);
    }
    
    /* invia il messaggio a tutti i client */
    public void sendAll(String messaggio) throws IOException{
        for(Socket socket : gestionePlayers.connPlayers){
            send(messaggio, socket);
        }
    }

    /* chiude la connessione */
    public void close() throws IOException{
        gestionePlayers.chiudiConnessioni();

        if(!serverSocket.isClosed())
            serverSocket.close();
    }
}



