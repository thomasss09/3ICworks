
public class RicercaArray {

    public static int RicercaBinaria(int[] arr, int target) {
        int max = arr.length - 1;
        int min = 0;

        int dif = 0;
        while (min <= max) {
            int lancio = (min + max) / 2;
            if (arr[lancio] == target) {
                return lancio;
            } else {
                if (arr[lancio] < target) {
                    min = lancio + 1;
                } else {
                    max = lancio - 1;
                }
            }
        }
        return -1;
    }

    public static int[] crearray(int n) {
        int[] arr = new int[n];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        return arr;
    }

    public static int ricercaLineare(int[] arr, int target) {

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {

                return i;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int bin = RicercaBinaria(crearray(30), 10);
        System.out.println(" il risultato è " + bin);
        int lin = ricercaLineare(crearray(30), 10);
        System.out.println(" il risultato è " + lin);
    }
    // Ritorna l'indice dell'elemento, o -1 se non trovato
}
