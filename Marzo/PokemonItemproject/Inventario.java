
import java.util.ArrayList;

public class Inventario {

    ArrayList<Item> ogg = new ArrayList<>();

    public void addItem(Item item) {
        for (int i = 0; i < ogg.size(); i++) {
            if (ogg.get(i) == item) {
                if (ogg.get(i) instanceof Item l) {
                    if (l.quantità++ > l.STACK) {
                        ogg.add(0, item);
                    } else {
                        l.quantità++;
                        break;
                    }
                }
            }
        }
        ogg.add(0, item);
    }

    public void removeItem(Item item) {
        ogg.remove(item);
    }

    public void checkItem(Item item) {
        for (int i = 0; i < ogg.size(); i++) {
            if (ogg.get(i) == item) {
                System.out.println(ogg.get(0).toString());

            }
        }
        System.out.println("not here :/");
    }

}
