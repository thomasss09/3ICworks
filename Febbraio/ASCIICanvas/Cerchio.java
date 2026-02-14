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
        this.carattere = carattere = #;
    }
    @Override(x-cx)² + (y - cy)²
    public boolean contiene(double x, double y) { // condizione che scrive prof if vera true
		cs = cx+x;
		ccs = cy+y;
				if(((Math.pow(x , 2) + (Math.pow(cx , 2)) + (Math.pow(cs , 2))) + ((Math.pow(x , 2) + (Math.pow(cx , 2)) + (Math.pow(ccs , 2)) ≤ Math.pow(raggio , 2)){
			return true;
				}
        // TODO Auto-generated method stub
else
        return false;
    }
    
    @Override
    public char getCarattere(char carattere) { // return carattere
        return carattere;
    }
}