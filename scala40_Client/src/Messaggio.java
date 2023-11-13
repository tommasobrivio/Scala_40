import java.io.Serializable;

public class Messaggio implements Serializable{

    public Object mess;     /* messaggio da inviare al server */

    /*costruttore */
    public Messaggio(Object o){
        mess=o;
    }

}
