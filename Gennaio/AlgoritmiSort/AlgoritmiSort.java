
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
        int nPrecedente = 0;
        int nCorrente;
        for (int i = 1; i < n; i++) {
            nCorrente = arr[i];
            nPrecedente = i - 1;
            while (nPrecedente >= 0 && nCorrente < arr[nPrecedente]) {
                arr[nPrecedente + 1] = arr[i];
                nPrecedente--;
            }
            arr[nPrecedente + 1] = nCorrente;
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

    public static int[] MergeSort(int[] arr1, int[] arr2) {
        int[] risultato = new int[arr1.length + arr2.length];
        int idx1 = 0;
        int idx2 = 0;
        int idxRis = 0;
            while (idx1 < arr1.length && idx2 < arr2.length) {
                if (arr1[idx1] > arr2[idx2]) {
                    risultato[idxRis] = arr2[idx2];
                    idx2++;
                } else {
                    risultato[idxRis] = arr1[idx1];
                    idx1++;
                }
                idxRis++;
            }
            if (idx1 < arr1.length) {
                while (idx1 < arr1.length) {
                    risultato[idxRis] = arr1[idx1];
                    idxRis++;
                    idx1++;
                }
            }
            if (idx2 < arr2.length) {
                while (idx2 < arr2.length) {
                    risultato[idxRis] = arr2[idx2];
                    idxRis++;
                    idx2++;
                }
            }
            return risultato;
            
        }

    

    public static void main(String[] args) {
        int[] arr = {5, 3, 8, 4};
        int[] arr1 = {1, 7, 9, 6};
        int[] risultato = MergeSort(arr, arr1);
        System.out.println(Arrays.toString(risultato));
    }
}
