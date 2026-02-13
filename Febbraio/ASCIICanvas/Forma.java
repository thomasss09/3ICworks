public interface Forma {
    // Il punto (x, y) è contenuto in questa forma?
    boolean contiene(double x, double y);
    // Che carattere devo usare per disegnare?
    char getCarattere();
    }
