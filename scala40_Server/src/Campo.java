public class Campo {
    
    public GestioneCombinazioni gestioneCombinazioni;

    public GestioneMazzo gestioneMazzo;

    public Campo(){
        gestioneCombinazioni=new GestioneCombinazioni();
        gestioneMazzo= new GestioneMazzo();
    }


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

    public String serializeMazzo(){
        String out="";
        
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
