import java.io.*;
import java.net.*;

public class Connessione {
    public static void main(String[] args) {
        try {
            // Crea un socket server che ascolta sulla porta 12345
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Server in ascolto sulla porta 12345...");

            while (true) {
                // Accetta una connessione da un client
                Socket clientSocket = serverSocket.accept();
                System.out.println("Connessione accettata da " + clientSocket.getInetAddress());

                // Crea flussi di input e output per la comunicazione con il client
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                // Legge la stringa inviata dal client
                String inputLine = in.readLine();

                // Converte la stringa in maiuscolo
                String response = inputLine.toUpperCase();

                // Invia la stringa in maiuscolo al client
                out.println(response);

                // Chiude la connessione con il client
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


