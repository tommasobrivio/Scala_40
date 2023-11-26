/* per testare la connessione TCP con il client_demo sono stati commentati tutte le istruzioni che includevano il secondo player
 * 
 * problemi:
 *      - nel momento che deve leggere un messaggio da client pare che non salvi il contenuto nel parametro mess della classe Messaggio
 */


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


       //  Socket connP2 = server.accettaConnessione();    /* socket player 2 */

        /* mentre la connessione non esiste */
        //while(connP2==null)

            /* accetta connessione */
        //    connP2 = server.accettaConnessione();

        /* creazione player 2 */
        //PlayerServer p2 = new PlayerServer("player1",connP1);

        /* creazione oggetto Gioco */
        Gioco gioco= new Gioco(p1/* ,p2*/, server);

        gioco.start();
        /* mentre il thread gioco esiste */
        while (Gioco.gioco) {
            
        }

        if(!server.serverSocket.isClosed())
            server.close();
    }
}
