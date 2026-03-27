
public class Elettronico extends Prodotto implements Scontabile {

    protected int garanziaMesi;

    public Elettronico(String nome, double prezzo, int garanziaMesi) {
        super(nome, prezzo);
        this.garanziaMesi = garanziaMesi;
    }

    public int getGaranziaMesi() {
        return garanziaMesi;
    }

    @Override
    public String toString() {
        return " nome : " + super.nome + " costo : " + super.prezzo + " La garanzia dura: " + this.garanziaMesi + " mesi";

    }

    @Override
    public void applicaSconto(int percentuale) {
        int sconto = (int) (prezzo / 100) * percentuale;
        prezzo = prezzo - sconto;
    }

    @Override
    public double getPrezzoAttuale() {
        return this.prezzo;
    }
}
