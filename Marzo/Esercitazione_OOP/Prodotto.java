
public abstract class Prodotto {

    protected String nome;
    protected double prezzo;

    public Prodotto(String nome, double prezzo) {
        this.nome = nome;
        this.prezzo = prezzo;
    }

    public void applicaIVA(double aliquota) {
        this.prezzo += (this.prezzo * 0.22);
    }

    @Override
    public abstract String toString();

    

}
