import java.net.Socket;

public class App {
    public static void main(String[] args) throws Exception {
        

        ServerTCP server= new ServerTCP(666);

        Socket connP1 = server.accettaConnessione();

        while(connP1==null)
            connP1 = server.accettaConnessione();

        PlayerServer p1 = new PlayerServer("player1",connP1);

        Socket connP2 = server.accettaConnessione();

        while(connP2==null)
            connP2 = server.accettaConnessione();

        PlayerServer p2 = new PlayerServer("player1",connP1);


        Gioco gioco= new Gioco(p1,p2);

        while (gioco.isAlive()) {
            
        }

        if(!server.serverSocket.isClosed())
            server.close();
    }
}
