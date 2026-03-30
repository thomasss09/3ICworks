
public class Pokemon extends HeldItem {

    private int hp;
    private int atk;
    private int def;
    private final String STATUS;

    public Pokemon(int hp, int atk, int def, String status) {
        this.hp = hp;
        this.atk = atk;
        this.def = def;
        this.STATUS = status;
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
