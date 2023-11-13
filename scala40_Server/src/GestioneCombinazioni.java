import java.util.ArrayList;
import java.util.List;

public class GestioneCombinazioni {

    /* lista di combinazioni */
    List<Combinazione> combinazioni;
    
    /*costruttore */
    public GestioneCombinazioni(){
        combinazioni=new ArrayList<>();
    }

    /* controlla di che tipo è la combinazione */
    public void checkCombinazione(List<Carta> combinazione){
        
        if(combinazione.size()<5){
            char rank= combinazione.get(0).rank;
            for(int i=1;i<combinazione.size();i++){
                if(combinazione.get(i).rank!=rank){
                    break;
                }
            }
        }
    }
}
