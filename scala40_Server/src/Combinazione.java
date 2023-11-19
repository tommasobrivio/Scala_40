import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;

public class Combinazione {
    
    /* combinazione di carte da giocare */
    public List<Carta> combinazione;

    //tris se hanno stesso rank, scala se hanno stesso seme
    public String tipo;

    /* costruttore */
    public Combinazione(){
        combinazione=new ArrayList<>();
        tipo="";
    }

    /* costruttore */
    public Combinazione(List<Carta> tmp){
        combinazione=tmp;
    }

    /* setta il tipo della combinazione */
    public void setTipo(String s){
        tipo=s;
    }

    /* controlla se il tipo è stato settato */
    public boolean isSetTipo(){
        if(!tipo.equals("") && tipo!=null){
            return true;
        }
        return false;
    }

    /* ordina una scala in ordine crescente */
    public boolean ordinaScala(){

        try{
            Collections.sort(combinazione, Comparator.comparingInt(Carta::getValue));
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /* serializza una combinazione */
    public String serialize(){
        String output="";
        for (Carta c : combinazione) {
            output += c.cover + "," + c.rank + "," + c.type + "," + c.value + ";";
        }
        return output;
    }
}
