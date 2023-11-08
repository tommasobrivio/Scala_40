import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class sceltaGiocatori extends JFrame{

    private BufferedImage backgroundImage;
    private JTextField inputField;
    private JButton playButton;
    private JLabel messageLabel;
    private JLabel imageLabel;


    public void controllaInput(String s){
        int n= Integer.parseInt(s);
        if(n<2 || n>4){
            // Mostra un messaggio di saluto
            JOptionPane.showMessageDialog(null, "PLAYERS MUST BE BETWEEN 2 AND 4");
        }
    }

    public sceltaGiocatori() {

        setTitle("Scala 40");
        setIconImage(new ImageIcon("img/favicon.ico").getImage());

        // calcolo le coordinate in base alle percentuali dello schermo
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;

        setSize((int) (screenWidth * 1), (int) (screenHeight * 1));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JPanel overlayPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // disegno l'immagine di sfondo
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        
        overlayPanel.setLayout(null);
        // Crea e aggiungi componenti GUI (es. carte, bottoni, ecc.) al pannello del gioco..

        this.inputField = new JTextField(20);
        this.playButton = new JButton("GO");
        this.messageLabel= new JLabel("Input the number of players (2-4):");

        
        // Setta le posizioni e dimensioni manualmente
        this.messageLabel.setBounds(20,(int) (screenHeight * 0.2), 600, 30);
        this.inputField.setBounds((int) (screenWidth * 0.30), (int) (screenHeight * 0.2), 200, 40);
        this.playButton.setBounds((int) (screenWidth * 0.48), (int) (screenHeight * 0.2),100,30);

        overlayPanel.add(this.messageLabel);
        overlayPanel.add(this.inputField);
        overlayPanel.add(this.playButton);

        // Aggiungi un gestore di eventi al pulsante
        /*button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = textFieldGiocatori.getText();
                controllaInput(s);
            }
        });*/

        add(overlayPanel);

        setVisible(true);
    }
}
