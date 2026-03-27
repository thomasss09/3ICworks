
public class ProdottoAlimentare extends Prodotto {

    protected double calorie;
    protected boolean richiedeRefrigerazione;

    public ProdottoAlimentare(String nome, double prezzo, double calorie, boolean richiedeRefrigerazione) {
        super(nome, prezzo);
        this.calorie = calorie;
        this.richiedeRefrigerazione = richiedeRefrigerazione;
    }

    public double getCalorie() {
        return calorie;
    }

    public void setCalorie(double calorie) {
        this.calorie = calorie;
    }

    public boolean isRichiedeRefrigerazione() {
        return richiedeRefrigerazione;
    }

    public void setRichiedeRefrigerazione(boolean richiedeRefrigerazione) {
        this.richiedeRefrigerazione = richiedeRefrigerazione;
    }

    @Override
    public String toString() {
        return " nome : " + super.nome + " costa: " + super.prezzo + " consuma " + this.calorie + " calorie" + " La refrigerazione è :" + this.richiedeRefrigerazione;

    }
}
