import java.util.List;

public class User {
    public String nome;
    public boolean pronto;
    public List<Carta> carteMano;

    public User(String s){
        nome=s;
        pronto=false;
    }
}
