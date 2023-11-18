import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class GestioneMazzo {

    private final static String FILE_MAZZO = "mazzoIniziale.csv"; /* nome file dove si trova il mazzo intero */

    public List<Carta> mazzo; /* mazzo di carte */

    public List<Carta> scarti; /* scarti */

    /* costruttore */
    public GestioneMazzo() {
        mazzo = new ArrayList<>();
        scarti = new ArrayList<>();
    }

    /* carica le carte dal file */
    public void loadFromFileCSV() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_MAZZO))) {
            Carta carta = new Carta();
            while (carta.loadFromFileCSV(reader)) { // mentre la linea esiste e non è una linea vuota
                mazzo.add(carta);
                carta.associaValore();
                carta = new Carta();
            }
        }
        /* mischia il mazzo pronto per giocare */
        mischia();
    }

    /* metodo per distribuire le carte */
    public void distribuisciCarte(PlayerServer p1, PlayerServer p2) {
        for (int i = 0; i < 13; i++) {
            p1.carteMano.add(popIndex(mazzo));
            p2.carteMano.add(popIndex(mazzo));
        }
        /* prima carta da mostrare nella pila scarti */
        scarti.add(popIndex(mazzo));
    }

    /* mischia il mazzo */
    public void mischia() {
        Collections.shuffle(mazzo);
    }

    /* prende l' ultima carta nella lista */
    public Carta popIndex(List<Carta> list) {
        return list.remove(list.size() - 1);
    }

    /*
     * quando mazzo finisce aggiunge la pila degli scarti, tranne una carta, al
     * mazzo e mischia
     */
    public void mischiaScarti() {

        Carta temp = popIndex(scarti);

        for (int i = 0; i < scarti.size(); i++) {
            mazzo.add(popIndex(scarti));
        }

        scarti.add(temp);

        mischia();
    }

    public String serialize() {
        String output="mazzo;"+mazzo.size()+";";
        
        for (Carta c : mazzo) {
            output += c.cover + "," + c.rank + "," + c.type + "," + c.value + ";";
        }
    
        output="scarti;"+scarti.size()+";";
        for (Carta c : scarti) {
            output += c.cover + "," + c.rank + "," + c.type + "," + c.value + ";";
        }
        return output;
        

    }
}
