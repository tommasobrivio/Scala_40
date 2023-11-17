public class Campo {
    
    public GestioneCombinazioni gestioneCombinazioni;

    public GestioneMazzo gestioneMazzo;

    public Campo(){
        gestioneCombinazioni=new GestioneCombinazioni();
        gestioneMazzo= new GestioneMazzo();
    }
}
