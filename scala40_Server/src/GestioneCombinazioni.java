import java.util.ArrayList;
import java.util.List;

public class GestioneCombinazioni {

    /* lista di combinazioni */
    List<Combinazione> combinazioni;
    
    /*costruttore */
    public GestioneCombinazioni(){
        combinazioni=new ArrayList<>();
    }

    /* controlla di che tipo è la combinazione, ritorna true se ha settato o era già settato, false se la combinazione non va bene */
    public boolean checkTipoCombinazione(Combinazione c){
        if(c.isSetTipo())
            return true;

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
                return true;
            }
        }
        else{
            //controlla per il seme per vedere se è una scala
            char type= c.combinazione.get(0).type;

            for(int i=1;i<c.combinazione.size();i++){
                if(c.combinazione.get(i).type!=type){
                    trovato=false;
                    break;
                }
            }

            if(trovato){
                c.setTipo("scala");
                return true;
            }
        }

        return false;
    }

    /* controlla se la carta passata può attaccarsi a una combinazione presente sul campo */
    public boolean checkPushCombinazione(Carta carta){

        for(Combinazione c : combinazioni){
            if(c.isSetTipo()){
                if(c.tipo.equals("tris")){

                    if(carta.rank==c.combinazione.get(0).rank){

                        if(c.combinazione.size()<4){
                            
                            if(!c.combinazione.stream().anyMatch(existingCard -> existingCard.type == carta.type))
                                return true;
                            
                        }
                    }
                }
                else{
                    if(c.combinazione.size()<13){
                        c.ordinaScala();
                        if(carta.type==c.combinazione.get(0).type){

                            if(c.combinazione.get(0).rank=='A'){

                                if(carta.rank=='A' && c.combinazione.get(c.combinazione.size()-1).rank=='K')
                                    return true;
                                
                                else if(carta.value+1 == c.combinazione.get(0).value)
                                    return true;
                                
                                else if(carta.value-1 == c.combinazione.get(c.combinazione.size()-1).value)
                                    return true;
                                
                            }
                            else if(c.combinazione.get(c.combinazione.size()-1).rank=='A'){

                                if(carta.value+1 == c.combinazione.get(0).value)
                                        return true;
                                    
                                else if(carta.value-1 == c.combinazione.get(c.combinazione.size()-1).value)
                                    return true;
                                
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    /* serializza le combinazioni */
    public String serialize(){
        String output="";
        for (Combinazione c : combinazioni) {
            output += c.serialize();
        }
        return output;
    }
}
