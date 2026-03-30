
public abstract class HeldItem {
    protected boolean equipaggiabile;
    protected Item item;

    public HeldItem() {
        this.equipaggiabile = false;
    }
    public abstract boolean equipaggiabile();
    public abstract void equipaggia(Item equipaggia);
    public abstract void disequipaggia();


    

    
}
