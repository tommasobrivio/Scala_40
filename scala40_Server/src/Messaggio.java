import java.io.Serializable;
import java.util.List;

public class Messaggio implements Serializable{
    public String mess;
    public List<Carta> carte;
    public Campo campo;

    public Messaggio(String m, List<Carta> list, Campo cam){
        mess=m;
        carte=list;
        campo=cam;
    }


    public void deSerialize(Object o){
        
    }

    public Object serialize(){

        String out=mess+";";

        if(carte!=null){
            out+=carte.size()+";";

            for(Carta c : carte){
                out+=c.cover+","+c.rank+","+c.type+","+c.value+";";
            }
        }
        else if(campo!=null){
            out+=campo.serialize();
        }
        else 
            return out;

        return out;
    }

    
}
