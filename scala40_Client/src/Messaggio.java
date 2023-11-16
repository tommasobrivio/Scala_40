import java.io.Serializable;

public class Messaggio implements Serializable {

    private Object mess; // messaggio da inviare al server

    // Costruttore
    public Messaggio(Object o) {
        mess = o;
    }

    // Metodo per ottenere il messaggio
    public Object getMessaggio() {
        return mess;
    }

    // Metodo per impostare un nuovo messaggio
    public void setMessaggio(Object nuovoMessaggio) {
        mess = nuovoMessaggio;
    }

    // Metodo per verificare se il messaggio è vuoto (null)
    public boolean isMessaggioVuoto() {
        return mess == null;
    }

    // Metodo per stampare le informazioni del messaggio
    public void stampaInfoMessaggio() {
        System.out.println("Contenuto del messaggio: " + mess);
    }
}