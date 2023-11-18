import java.net.Socket;

public class App {
    public static void main(String[] args) throws Exception {
        

        ServerTCP server= new ServerTCP(666);   /* oggetto per gestire le comunicazioni */

        Socket connP1 = server.accettaConnessione();    /* socket player 1 */

        /* mentre la connessione non esiste */
        while(connP1==null)

            /* accetta connessione */
            connP1 = server.accettaConnessione();

        /* creazione player 1 */
        PlayerServer p1 = new PlayerServer("player1",connP1);


        Socket connP2 = server.accettaConnessione();    /* socket player 2 */

        /* mentre la connessione non esiste */
        while(connP2==null)

            /* accetta connessione */
            connP2 = server.accettaConnessione();

        /* creazione player 2 */
        PlayerServer p2 = new PlayerServer("player1",connP1);

        /* creazione oggetto Gioco */
        Gioco gioco= new Gioco(p1,p2);

        /* mentre il thread gioco esiste */
        while (gioco.isAlive()) {
            
        }

        if(!server.serverSocket.isClosed())
            server.close();
    }
}
