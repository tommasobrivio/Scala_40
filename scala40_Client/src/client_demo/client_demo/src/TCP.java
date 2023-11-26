import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TCP extends Thread{

    private Socket socket;      /* socket per comunicare */
    private String ipServer;    /* indirizzo ip del server */
    private int portServer;     /* porta di comunicazione */
    private BufferedReader bufferedReader;  /* buffer per leggere dati dal server */
    private PrintWriter printWriter;        /* writer per inviare dati al server */

    public TCP(String ip, int port) throws IOException{
        ipServer=ip;
        portServer=port;
        socket= new Socket(ipServer, portServer);
        bufferedReader= new BufferedReader(new InputStreamReader(socket.getInputStream()));
        printWriter = new PrintWriter(socket.getOutputStream(), true);
    }

    public void run(){
        
             try {
                while(socket.isConnected()){
             // legge i messaggi dal server
                String receivedMessage = bufferedReader.readLine();
                if (receivedMessage != null) {
                    // Process the received message
                    handleReceivedMessage(receivedMessage);
                } else {
                    // The server has closed the connection
                    break;
                }
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        
    }

    private void handleReceivedMessage(String message) {
        System.out.println("Messaggio: " + message);
    }

    public boolean connesso(){
        if(socket.isConnected())
            return true;
        else
            return false;
    }

    public void send(String s){
        printWriter.println(s);
    }

    public String read() throws IOException{
        return bufferedReader.readLine();
    }
}
