import java.util.Arrays;

public class AlgoritmiSort {

    public static int[] creaArray(int n, int max, int min) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = (int) (Math.random() * (max - min) + min);
        }
        return arr;
    }

    public static void insertionSort(int[] arr, int n) {
        int j = 0;
        int elemento;
        for (int i = 1; i < n; i++) {
            elemento = arr[i];
            j = i - 1;
            while (j >= 0 && elemento < arr[j]) {
                arr[j + 1] = arr[i];
                j--;
            }
            arr[j + 1] = elemento;
        }
    }

    /*
-faccio la ricerca lineare per trovare l'elemento più piccolo
-scambio l'elemento che ho trovato con l'elemento i-esimo
-ripeto i passaggi precedenti per i che va da 0 a N */
    public static void selectionSort(int[] arr, int n) {
        int[] aruba = arr;
        int min = arr[0];
        int ind = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j2 = 0; j2 < arr.length; j2++) {
                if (min > arr[i]) {
                    min = arr[i];
                    ind = j2;
                }
            }
            aruba[ind] = aruba[i];
            aruba[i] = min;
        }
    }
    public static void bubbleSorrrt(int[] arr, int n) {  // tutto
        int sostituto; //variabile in più che prende posto momentaneo di arr[j+1] o arr[j]
        boolean sos = false;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    sostituto = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = sostituto;
                    sos = true;
                }
            }
            if (sos == false) {
                break;
            }

        }
        System.out.println(Arrays.toString(arr));
    }
    public static void main(String[] args) {

    }
}
