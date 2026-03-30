
public abstract class Item {

    protected final String NOME;
    protected final String DESCRIZIONE;
    protected final int STACK;
    protected int quantità;
    public Item(String nome, String descrizione, int stack, int quantità) {
        this.NOME = nome;
        this.DESCRIZIONE = descrizione;
        this.STACK = stack;
        this.quantità = quantità;
    }
    @Override
    public String toString() {
        return "Item [NOME=" + NOME +", quantità=" + quantità + "]";
    }
    


}
