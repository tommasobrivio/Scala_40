import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ThreadClient extends Thread{
    
    private Socket socket;
    private ServerTCP server;
    private boolean gioco;
    private ObjectInputStream oInput;

    public ThreadClient() throws IOException{
        socket=new Socket();
        oInput=new ObjectInputStream(socket.getInputStream());
    }

    public void run(){
        while(this.gioco){
            try {
                oInput.readObject();
                
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
