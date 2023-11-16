import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Home extends JFrame {

    private BufferedImage backgroundImage;
    private JTextField inputField;
    private JButton playButton;
    private JLabel messageLabel;
    private JLabel imageLabel;
    private String root = "C:\\Users\\tomma\\OneDrive\\Desktop\\Scuola\\tecnologia\\Scala40\\scala40_Client\\";

    public Home() throws IOException {
        // Titolo della finestra
        setTitle("Scala 40");

        // Sfondo finestra
        this.backgroundImage = ImageIO.read(new File(this.root + "img/sfondoCampo.jpg"));

        // Calcolo le coordinate in base alle percentuali dello schermo
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;

        // Calcolo dimensioni
        setSize((int) (screenWidth * 0.7), (int) (screenHeight * 0.7));

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

        // Indica la possibilità di impostare manualmente la posizione degli elementi
        overlayPanel.setLayout(null);

        // Crea e aggiungi componenti GUI al pannello del gioco

        // Immagine titolo
        this.imageLabel = new JLabel();
        ImageIcon image = new ImageIcon(ImageIO.read(new File(this.root + "img/titolo.jpg")));
        this.imageLabel.setIcon(image);

        // Textbox
        this.inputField = new JTextField(20);

        // Bottone
        this.playButton = new JButton("GO");

        // Label
        this.messageLabel = new JLabel("Input your name:");
        this.messageLabel.setForeground(Color.white);
        this.messageLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        // Setta le posizioni e dimensioni manualmente
        this.imageLabel.setBounds(150, 30, 500, 70);
        this.messageLabel.setBounds(150, 180, 300, 30);
        this.inputField.setBounds(200, 220, 200, 40);
        this.playButton.setBounds(300, 250, 80, 30);

        // Aggiunge elementi al pannello
        overlayPanel.add(this.imageLabel);
        overlayPanel.add(this.messageLabel);
        overlayPanel.add(this.inputField);
        overlayPanel.add(this.playButton);

        // Funzione che verifica quando viene premuto il pulsante
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = inputField.getText();
                if (inputText.isEmpty()) {
                    JLabel tmp = new JLabel("You must input something in the textbox!!");
                    tmp.setForeground(Color.white);
                    tmp.setFont(new Font("Arial", Font.PLAIN, 14));
                    tmp.setBounds(20, 300, 600, 30);
                    overlayPanel.add(tmp);
                    inputField.setText("");
                } else {
                    // Passa alla prossima finestra
                    dispose();
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                new Campo(inputText);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        });

        // Aggiunge il pannello alla finestra
        add(overlayPanel);

        // Rende visibile il pannello
        setVisible(true);
    }
}
