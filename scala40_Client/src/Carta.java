public class Carta {
    
    public char cover;  /* cover della carta (o rossa o blu) */
    public char rank;   /* valore scritto sulla carta */
    public char type;   /* seme della carta */
    public int value;   /* valore effettivo della carta */

    public Carta(char cover, char rank, char type, int value) {
        this.cover = cover;
        this.rank = rank;
        this.type = type;
        this.value = value;
    }


    // Metodo per visualizzare le informazioni della carta
    public void mostraInfo() {
        System.out.println("Cover: " + cover);
        System.out.println("Rank: " + rank);
        System.out.println("Type: " + type);
        System.out.println("Value: " + value);
    }

    // Metodo per confrontare due carte
    public boolean confrontaCarta(Carta altraCarta) {
        return this.rank == altraCarta.rank && this.type == altraCarta.type;
    }
}
