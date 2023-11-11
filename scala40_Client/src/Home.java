import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Home extends JFrame{

    private BufferedImage backgroundImage;
    private JTextField inputField;
    private JButton playButton;
    private JLabel messageLabel;
    private JLabel imageLabel;
    private String root= "C:\\Users\\tomma\\OneDrive\\Desktop\\Scuola\\tecnologia\\Scala40\\scala40_Client\\";

    public Home() throws IOException {
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
        
        //indica la possibilità di impostare manualmente la posizione degli elementi
        overlayPanel.setLayout(null);

        // Crea e aggiungi componenti GUI (es. carte, bottoni, ecc.) al pannello del gioco..

        //immagine titolo
        this.imageLabel= new JLabel();
        ImageIcon image=new ImageIcon(ImageIO.read(new File(this.root+"img/titolo.jpg")));
        this.imageLabel.setIcon(image);

        //textbox
        this.inputField = new JTextField(20);

        //bottone
        this.playButton = new JButton("GO");

        //label
        this.messageLabel= new JLabel("Input your name:");
        this.messageLabel.setForeground(Color.white);
        this.messageLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        
        // Setta le posizioni e dimensioni manualmente
        this.imageLabel.setBounds(150,(int) (screenHeight * 0.08), 1000, 70);
        this.messageLabel.setBounds(150,(int) (screenHeight * 0.2), 600, 30);
        this.inputField.setBounds((int) (screenWidth * 0.2), (int) (screenHeight * 0.2), 200, 40);
        this.playButton.setBounds((int) (screenWidth * 0.35), (int) (screenHeight * 0.21),80,30);

        //aggiunge elementi al pannello
        overlayPanel.add(this.imageLabel);
        overlayPanel.add(this.messageLabel);
        overlayPanel.add(this.inputField);
        overlayPanel.add(this.playButton);

        // funzione che verifica quando viene premuto il pulsante
        playButton.addActionListener(e -> {
            String inputText = inputField.getText();
            if(inputText!="" || inputText!=null){
                //passa alla prossima finestra
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
            else{
                JLabel tmp= new JLabel("You must input something in the textbox!!");
                tmp.setForeground(Color.white);
                tmp.setFont(new Font("Arial", Font.PLAIN, 14));
                tmp.setBounds(20,(int) (screenHeight * 0.3), 600, 30);
                overlayPanel.add(tmp);
                inputField.setText("");
            }
            
        });

        //aggiunge il pannello alla finestra
        add(overlayPanel);

        //rende visibile il pannello
        setVisible(true);
    }
}
