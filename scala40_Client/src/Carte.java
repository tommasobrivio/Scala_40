import java.util.ArrayList;
import java.util.List;

public class Carte 
{
    public List<Carta> lista;

    public Carte()
    {
        this.lista = new ArrayList<Carta>();
    }

    public void addCarta(Carta c)
    {
        this.lista.add(c);
    }

    public void ClearList()
    {
        this.lista.clear();
    }

    public int size()
    {
        return this.lista.size();
    }
}

