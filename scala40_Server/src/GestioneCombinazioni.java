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
    public void checkCombinazione(Combinazione c){
        if(c.isSetTipo())
            return;

        boolean trovato=true;

        if(c.combinazione.size()<=4){
            //controlla per il rank per vedere se è un tris o poker
            char rank= c.combinazione.get(0).rank;
            
            for(int i=1;i<c.combinazione.size();i++){
                if(c.combinazione.get(i).rank!=rank){
                    trovato=false;
                    break;
                }
            }

            if(trovato){
                c.setTipo("tris");
            }
        }
        else{
            
        }
        
        
    }
}
