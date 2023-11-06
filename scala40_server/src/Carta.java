/**
 * Carta
 */
public class Carta {

    public char back;
    public char rank;
    public char type;
    public int value;
    

    public boolean isInt(Object o){
        return o instanceof Integer;
    }

    public void associaValore(){
        if(isInt(this.rank)){
            this.value=this.rank-'0';
        }
        else if(this.rank=='A'){
            this.value=1;
        }
        else if(this.rank=='J' || this.rank=='Q' || this.rank=='K'){
            this.value=10;
        }
        
    }
}