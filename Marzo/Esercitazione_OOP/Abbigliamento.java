
public class Abbigliamento extends Prodotto implements Scontabile {

    protected String taglia;

    public Abbigliamento(String nome, double prezzo, String taglia) {
        super(nome, prezzo);
        this.taglia = taglia;
    }

    public String getTaglia() {
        return taglia;
    }

    @Override

    public String toString() {
        return " nome : " + super.nome + " costa: " + super.prezzo + " taglia: " + this.taglia;

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
