import javax.swing.SwingUtilities;

public class Server {
    public static void main(String[] args) throws Exception {
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Campo();
            }
        });


    }
}
