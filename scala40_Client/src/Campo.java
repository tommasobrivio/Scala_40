import javax.imageio.ImageIO;
import javax.swing.*;    
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Campo extends JFrame{

    private BufferedImage backgroundImage;
    private ClientTCP clientTCP=new ClientTCP();
    private String root= "C:\\Users\\tomma\\OneDrive\\Desktop\\Scuola\\tecnologia\\Scala40\\scala40_Client\\";

    public Campo(String players) throws IOException {

        //titolo della finestra
        setTitle("Scala 40");

        //sfondo finestra
        this.backgroundImage = ImageIO.read(new File(this.root+"img/sfondoCampo.jpg"));

        // calcolo le coordinate in base alle percentuali dello schermo
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;

        //calcolo dimensioni
        setSize((int) (screenWidth * 1), (int) (screenHeight * 1));

        //controlla che si chiuda la finestra quando clicco sulla X
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //crea pannello dove inserire i componenti
        JPanel overlayPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // disegno l'immagine di sfondo
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };

        

        // Crea e aggiungi componenti GUI (es. carte, bottoni, ecc.) al pannello del gioco..

        

        add(overlayPanel);

        setVisible(true);
    }

    
}
