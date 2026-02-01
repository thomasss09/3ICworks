//FINITO

public class Extension {

    public static int dannoMin() {
        return 1;
    }

    public static void selectionSort(Carta[] carteInMano) {

        for (int i = 0; i < carteInMano.length - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < carteInMano.length; j++) {
                if (carteInMano[j].getHp() < carteInMano[minIdx].getHp()) {
                    minIdx = j;
                }
            }
            // scambio carte
            Carta temp = carteInMano[minIdx];
            carteInMano[minIdx] = carteInMano[i];
            carteInMano[i] = temp;
        }
    }
}
