
public class Rettangolo implements Forma {

    private int larghezza;
    private int lunghezza;
    private char carattere;
    
    public Rettangolo(int larghezza, int lunghezza, char carattere) {
        this.larghezza = larghezza;
        this.lunghezza = lunghezza;
        this.carattere = carattere;
    }

    @Override
    public boolean contiene(double x, double y) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public char getCarattere() {
        // TODO Auto-generated method stub
        return 0;
    }

}
