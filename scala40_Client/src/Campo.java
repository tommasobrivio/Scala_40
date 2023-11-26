import javax.imageio.ImageIO;
import javax.swing.*;    
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Campo extends JFrame {

    private JButton deckButton;          // Bottone per selezionare il mazzo
    private JButton discardsButton;      // Bottone per selezionare il mazzo degli scarti
    private BufferedImage backgroundImage; // Immagine sfondo
    private ClientTCP clientTCP;           // Oggetto per comunicare col server
    private String root = "C:\\Users\\tomma\\OneDrive\\Desktop\\Scuola\\tecnologia\\Scala40\\scala40_Client\\"; // Root dove sono salvate le immagini

    // Costruttore che crea la finestra
    public Campo(String playerName) throws IOException {
        PlayerClient player = new PlayerClient(playerName);
        inviaConnessione(player);

        // Titolo della finestra
        setTitle("Scala 40");

        // Sfondo finestra
        this.backgroundImage = ImageIO.read(new File(this.root + "img/sfondoCampo.jpg"));

        // Calcolo le coordinate in base alle percentuali dello schermo
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;

        // Calcolo dimensioni
        setSize((int) (screenWidth * 1), (int) (screenHeight * 1));

        // Controlla che si chiuda la finestra quando clicco sulla X
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crea pannello dove inserire i componenti
        JPanel overlayPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Disegno l'immagine di sfondo
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };

        // Crea bottoni
        deckButton = new JButton("Selezione Mazzo");
        discardsButton = new JButton("Selezione Scarti");

        // Aggiunge azioni ai bottoni se necessario

        // Aggiunge gli elementi al pannello
        overlayPanel.add(deckButton);
        overlayPanel.add(discardsButton);

        // Aggiunge il pannello alla finestra
        add(overlayPanel);

        // Rende visibile il pannello
        setVisible(true);
    }

    // Invia un messaggio al server
    public void inviaConnessione(PlayerClient player) throws IOException {
        Messaggio m = new Messaggio(player.nome);
        this.clientTCP.send(m.mess);
    }
}