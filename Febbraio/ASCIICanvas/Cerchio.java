public class Cerchio implements Forma {
    private double centroX;
    private double centroY;
    private double raggio;
    private char carattere; // da stampare
    // TODO completa implementando il costruttore e i metodi di "Forma"
    public Cerchio(double centroX, double centroY, double raggio, char carattere) {
        this.centroX = centroX;
        this.centroY = centroY;
        this.raggio = raggio;
        this.carattere = carattere;
    }
    @Override
    public boolean contiene(double x, double y) { // condizione che scrive prof if vera true
        // TODO Auto-generated method stub
        return false;
    }
    
    @Override
    public char getCarattere() { // return carattere
        // TODO Auto-generated method stub
        return 0;
    }
    
    }