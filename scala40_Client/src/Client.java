import java.io.IOException;
import javax.swing.SwingUtilities;

public class Client {
    public static void main(String[] args) throws IOException {
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                
                try {
                    new Home();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                
            }
        });


    }
}
