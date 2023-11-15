import java.util.ArrayList;
import java.util.List;

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

    /* aggiunge carta a una combinazione */
    public void pushCarta(Carta c){
        
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
}
