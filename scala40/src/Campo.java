import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Campo extends JFrame{

    private JPanel gamePanel;

    public Campo() {

        setTitle("Scala 40");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gamePanel= new JPanel();
        gamePanel = new JPanel();
        gamePanel.setLayout(new BorderLayout());

        // Crea e aggiungi componenti GUI (es. carte, bottoni, ecc.) al pannello del gioco..

        

        add(gamePanel);

        setVisible(true);
    }
}
