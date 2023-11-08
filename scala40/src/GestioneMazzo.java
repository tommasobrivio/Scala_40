import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GestioneMazzo {
    private final static String FILE_MAZZO="mazzoIniziale.csv";

    public List<Carta> mazzo;

    public GestioneMazzo(String file){
        mazzo= new ArrayList<>();
    }

    public void loadFromFileCSV() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_MAZZO))) {
            Carta carta = new Carta();
            while (carta.loadFromFileCSV(reader)) { // mentre la linea esiste e non è una linea vuota
                mazzo.add(carta);
                carta = new Carta();
            }
        }
    }
    
}
