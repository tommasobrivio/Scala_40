import java.util.ArrayList;
import java.util.List;

public class Messaggio {
    public String mess, input, output;
    
    
    /* costruttore che salva il messagio da inviare */
    public Messaggio(String m){
        mess=m;
    }

    /* costruttore che salva il messaggio e il contenuto input */
    public Messaggio(String m, String input){
        mess=m;
        this.input=input;
    }


    /* metodo per deserializzare i messaggi se sono delle liste di carte */
    public List<Carta> deSerializeCarte(){
        List<Carta> carte=new ArrayList<>();
        String[] dati= input.split(";");

        if(dati[0].equals("combinazione")){
            for(int i=0;i<Integer.parseInt(dati[1]);i++){
                String[] datiCarta=dati[2+i].split(",");

                Carta c=new Carta(datiCarta[0].charAt(0), datiCarta[1].charAt(0), datiCarta[2].charAt(0), Integer.parseInt(datiCarta[3]));

                carte.add(c);
            }
        }

        return carte;

    }

    /* setta l' output da inviare */
    public void setOutput(String s){
        output= mess+s;
    }
    
}
