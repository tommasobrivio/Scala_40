import java.io.BufferedReader;
import java.io.IOException;

/**
 * Carta
 */
public class Carta {

    public char cover;  /* cover della carta (o rossa o blu) */
    public char rank;   /* valore scritto sulla carta */
    public char type;   /* seme della carta */
    public int value;   /* valore effettivo della carta */

    /* costruttore default */
    public Carta(){}

    /* costruttore */
    public Carta(char cover, char rank, char type, int value){

        this.cover=cover;
        this.rank=rank;
        this.type=type;
        this.value=value;
    }
    
    /* metodo che controlla se è un intero (se non lo è sarà o un asso o una figura) */
    public boolean isInt(Object o){
        return o instanceof Integer;
    }

    /* in base alla carta gli associa il loro valore */
    public void associaValore(){
        if(isInt(this.rank)){
            this.value=this.rank-'0';
        }
        else if(this.rank=='A'){
            this.value=1;
        }
        else if(this.rank=='J' || this.rank=='Q' || this.rank=='K'){
            this.value=10;
        }
    }

    // Metodo per caricare i dati del dipendente da un file CSV
    public boolean loadFromFileCSV(BufferedReader reader) throws IOException {
        String line = reader.readLine();
        if (line != null && !line.equals("")) { // se la linea esiste e non è una linea vuota
            String[] parts = line.split(";");
            this.cover=parts[0].charAt(0);
            this.rank=parts[1].charAt(0);
            this.type=parts[2].charAt(0);    
            return true;
        }
        return false;
    }

    public int getValue(){
        return value;
    }
}