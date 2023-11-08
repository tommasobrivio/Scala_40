import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class sceltaOpzioni extends JFrame{

    private JPanel gamePanel;

    public void controllaInput(String s){
        int n= Integer.parseInt(s);
        if(n<2 || n>4){
            // Mostra un messaggio di saluto
            JOptionPane.showMessageDialog(null, "PLAYERS MUST BE BETWEEN 2 AND 4");
        }
    }

    public sceltaOpzioni() {

        setTitle("Scala 40");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gamePanel= new JPanel();
        gamePanel = new JPanel();
        gamePanel.setLayout(new BorderLayout());

        // Crea e aggiungi componenti GUI (es. carte, bottoni, ecc.) al pannello del gioco..

        // Crea un'etichetta
        JLabel labelGiocatori = new JLabel("Input the number of players(2-4):");
        // Crea un campo di testo
        JTextField textFieldGiocatori = new JTextField(1);
        // Crea un pulsante
        JButton button = new JButton("GO");

        // Aggiungi un gestore di eventi al pulsante
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = textFieldGiocatori.getText();
                controllaInput(s);
            }
        });

        add(gamePanel);

        setVisible(true);
    }
}
