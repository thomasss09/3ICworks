
public abstract class Item extends HeldItem implements Medicine {

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
    @Override
    public boolean equipaggiabile() {
        if (this.equipaggiabile) {
            System.out.println("c'è già qualcosa equipaggiato");
            return true;
        } else {
            System.out.println("si può equipaggiare");
            return false;
        }
    }

    @Override
    public void equipaggia(Item equipaggia) {
        this.item = equipaggia;
        this.equipaggiabile = true;
    }

    @Override
    public void disequipaggia() {
        this.item = null;
        this.equipaggiabile = false;
    }



}
