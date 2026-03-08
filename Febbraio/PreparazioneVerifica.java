
import java.util.Arrays;

public class PreparazioneVerifica {

    public static int binarySearch(int[] arr, int target) { // funziona
        int min;
        int lancio = 0;
        int max = arr.length - 1;
        while (lancio != target) {
            min = arr[0];
            lancio = (min + max) / 2;
            if (lancio == target) {
                return lancio;
            } else {
                if (lancio > target) {
                    max = lancio - 1;
                } else {
                    if (lancio < target) {
                        min = lancio + 1;
                    }
                }
            }
        }
        return lancio;
    }

    public static void bubbleSort(int[] arr) { // funziona ma era meglio fare con n+1    n<n-1
        int nCurrent;
        int nPrec;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 1; j < arr.length; j++) {
                nCurrent = j;
                nPrec = j - 1;
                if (arr[nCurrent] < arr[nPrec]) {
                    int temp = arr[nCurrent];
                    arr[nCurrent] = arr[nPrec];
                    arr[nPrec] = temp;
                }
            }
        }
    }

    public static void insertionSort(int[] arr) { // n < n-1 // funziona
        int nPrec;
        int nCurrent;
        for (int i = 1; i < arr.length; i++) {
            nPrec = i - 1;
            nCurrent = arr[i];
            while (nPrec >= 0 && nCurrent < arr[nPrec]) {
                arr[nPrec + 1] = arr[nPrec];
                nPrec--;
            }
            arr[nPrec + 1] = nCurrent;
        }
    }

    public static void selectionSort(int[] arr) { // se min>j j == new min e dopo scambio funziona
        int min;
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
    // tempo = circa 40min

    public static int[] mergeSort(int[] arr) { // funziona 
        if (arr.length <= 1) {
            return arr;
        }
        int[] arr1 = mergeSort(Arrays.copyOfRange(arr, 0, arr.length / 2)); // inizio array incluso fine esclusa /2 perchè vogliamo solo la prima parte dell' array 
        int[] arr2 = mergeSort(Arrays.copyOfRange(arr, arr.length / 2, arr.length)); // 2 parte dell'array divide per 2 così parte giusto e va fino ad array.length perchè la fine è esclusa
        return merge(arr1, arr2);
    }
//int[] arr1 = mergeSort(Arrays.copyOfRange(arr , 0 , arr.length /2 )); prima parte 50% motivo /2
// int[] arr2 = mergeSort(Arrays.copyOfRange(arr , arr.legnth /2 , arr.length)); 50% in poi
// return merge(arr1, arr2);

    private static int[] merge(int[] arr1, int[] arr2) {
        int[] risultato = new int[arr1.length + arr2.length];
        int idx1 = 0;
        int idx2 = 0;
        int idxris = 0;
        while (idx1 < arr1.length && idx2 < arr2.length) {
            if (arr1[idx1] > arr2[idx2]) {
                risultato[idxris++] = arr2[idx2++];
            } else {
                risultato[idxris++] = arr1[idx1++];
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
        // System.out.println(binarySearch(array, 5));
        // bubbleSort(array);
        // array = mergeSort(array);
        //insertionSort(arr);
        // selectionSort(array);
        //int[] arr = {6,23,76,1,7,3,9,15,900,35};
        //arr = mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }
//tempo 1 ora ma merge sort copiato
}
