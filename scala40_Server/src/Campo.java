public class Campo {
    
    public GestioneCombinazioni gestioneCombinazioni;   /* combinazioni presenti sul campo */

    public GestioneMazzo gestioneMazzo;     /* mazzo e scarti sul campo */

    /*costruttore */
    public Campo(){
        gestioneCombinazioni=new GestioneCombinazioni();
        gestioneMazzo= new GestioneMazzo();
    }


    /* serializza il campo di gioco */
    public String serializeAll(){

        String out = "combinazioni;" + gestioneCombinazioni.combinazioni.size()+";";

        if(gestioneCombinazioni.combinazioni.size()!=0){
            out+=gestioneCombinazioni.serialize();
        }

        if(gestioneMazzo.mazzo.size()!=0){
            out+=gestioneMazzo.serialize();
        }
        else{
            gestioneMazzo.mischiaScarti();
            out+=gestioneMazzo.serialize();
        }

        return out;

    }

}
