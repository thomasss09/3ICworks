
public class Digitale extends Prodotto implements Scontabile, Spedibile {

    @Override
    public double calcolaSpese() {
        return 0.0;
    }

    @Override
    public String corriere() {
        return "download diretto";
    }

    public Digitale(String nome, double prezzo) {
        super(nome, prezzo);
    }

    @Override
    public String toString() {
        return " nome (digitale): " + nome + " prezzo :" + prezzo;
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
