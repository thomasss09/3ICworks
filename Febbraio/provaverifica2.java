
import java.util.Arrays;
// da fare ricerca binaria, bubble sort , insertion sort , selection sort , merge sort 

public class provaverifica2 {

    public static int ricercaBinaria(int[] arr, int target) {
        int lancio = 0;
        int min = 0;
        int max = arr.length;
        while (lancio != target) {
            lancio = (max + min) / 2;
            if (lancio == target) {
                return lancio;
            }
            if (lancio > target) {
                max = lancio - 1;
            } else {
                if (lancio < target) {
                    min = lancio + 1;
                }
            }
        }
        return lancio;
    }

    public static void bubbleSort(int[] arr) { //n<n-1
        int Nattuale;
        int Nprecedente;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 1; j < arr.length; j++) {
                Nattuale = j;
                Nprecedente = j - 1;
                if (arr[Nattuale] < arr[Nprecedente]) {
                    int swap = arr[Nattuale];
                    arr[Nattuale] = arr[Nprecedente];
                    Nprecedente = swap;
                }
            }

        }
    }

    public static void insertionSort(int[] arr) { //n<n-1
        int nPrec;
        int nCurr;
        for (int i = 1; i < arr.length; i++) {
            nPrec = i - 1;
            nCurr = arr[i];
            while (nPrec >= 0 && nCurr < arr[nPrec]) {
                arr[nPrec + 1] = arr[nPrec];
                nPrec--;
            }
            arr[nPrec + 1] = nCurr;
        }
    }

    public static void selectionSort(int[] arr) { //min e  j
        int min = 0;
        for (int i = 0; i < arr.length; i++) {
            min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            int swap = arr[min];
            arr[min] = arr[i];
            arr[i] = swap;
        }
    }

    public static int[] mergeSort(int[] arr) {
        if (arr.length <= 1) {
            return arr;
        }

        int[] arr1 = mergeSort(Arrays.copyOfRange(arr, 0, arr.length / 2));
        int[] arr2 = mergeSort(Arrays.copyOfRange(arr, arr.length / 2, arr.length));

        return merge(arr1, arr2);
    }

    public static int[] merge(int[] arr1, int[] arr2) {
        int[] risultato = new int[arr1.length + arr2.length];
        int idx1 = 0;
        int idx2 = 0;
        int idxris = 0;
        while (idx1 < arr1.length && idx2 < arr2.length) {
            if (arr1[idx1] < arr2[idx2]) {
                risultato[idxris++] = arr1[idx1++];
            } else {
                risultato[idxris++] = arr2[idx2++];
            }
        }
        while (idx1 < arr1.length) {
            risultato[idxris++] = arr1[idx1++];
        }

        while (idx2 < arr2.length) {
            risultato[idxris++] = arr2[idx2++];
        }
        return risultato;
    }

    public static void main(String[] args) {
        int[] arr = {9, 6, 3, 4, 5, 2, 7, 10, 1, 8};
        // System.out.println(ricercaBinaria(arr, 5));
        // bubbleSort(array);
        // array = mergeSort(array);
        //insertionSort(arr);
        selectionSort(arr);
        //int[] arr = {6,23,76,1,7,3,9,15,900,35};
        //arr = mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
