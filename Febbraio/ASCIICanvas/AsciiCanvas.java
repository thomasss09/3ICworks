
public class AsciiCanvas {

    private Forma[] forme;
    private int numeroForme;
    private int larghezza; // Numero di "colonne"
    private int altezza; // Numero di "righe"
		 public String[][] matr;

    public AsciiCanvas(Forma[] forme, int numeroForme, int larghezza, int altezza , String[][] matr) {
        this.forme = forme;
        this.numeroForme = numeroForme;
        this.larghezza = larghezza;
        this.altezza = altezza;
					this.matr = matr[altezza][larghezza];
    }

    public void aggiungiForma(Forma f) { // aggiungo uan forma all 'array'
        this.forme[numeroForme++] = f;
    }

    public void rimuoviForma(int indice) {
        try {
            this.forme[indice] = null;
        } catch (Exception e) {
            System.out.println("index out of bounds");
            return;
        }
        for (int i = 0; i < forme.length-1; i++) {
            forme[i] = forme[i+1];
            forme[i+1] = null;
        }
    }
public Stringa[][] crea matrice(String[][] matr){
int.valueOf(matr);
	for(int i ; i < ){}
}

    public void disegna() { // fare un metodo crea matice che usu il confrontyo il valore x e y della matrie con queòllo della forma da disegnare
        // TODO implementa
    }
}
