import java.util.ArrayList;
import java.util.List;

public class Messaggio {
    public String mess, input, output;
    
    

    public Messaggio(String m){
        mess=m;
    }

    public Messaggio(String m, String input){
        mess=m;
        this.input=input;
    }


    public void deSerialize(){
        List<Carta> carte=new ArrayList<>();
        String[] dati= input.split(";");

        if(dati[0].equals("combinazione")){
            for(int i=0;i<Integer.parseInt(dati[1]);i++){
                String[] datiCarta=dati[2+i].split(",");

                Carta c=new Carta(datiCarta[0].charAt(0), datiCarta[1].charAt(0), datiCarta[2].charAt(0), Integer.parseInt(datiCarta[3]));

                carte.add(c);
            }
        }

    }

    public void setOutput(String s){
        output=s;
    }
    
}
