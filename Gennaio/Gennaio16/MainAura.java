
import java.util.Arrays;
import java.util.Scanner;

public class MainAura {

    public static int[] creaArray(int n, int max, int min) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = (int) (Math.random() * (max - min) + min);
        }
        return arr;
    }

    public static void bubbleSorrrt(int[] lOLLO, int l_AURA) {  // tutto
        int blurrato;
        boolean ci = false;
        for (int i = 0; i < l_AURA - 1; i++) {
            for (int j = 0; j < l_AURA - 1; j++) {
                if (lOLLO[j] > lOLLO[j + 1]) {
                    blurrato = lOLLO[j];
                    lOLLO[j] = lOLLO[j + 1];
                    lOLLO[j + 1] = blurrato;
                    ci = true;
                }
            }
            if (ci == false) {
                break;
            }

        }
        System.out.println(Arrays.toString(lOLLO));
    }

    public static void bubbleSorrrtlobotomizzato(int[] lOLLO, int l_AURA) {  // solo i
        int blurrato;
        for (int i = 0; i < l_AURA - 1; i++) {
            for (int j = 0; j < l_AURA - i - 1; j++) {
                if (lOLLO[j] > lOLLO[j + 1]) {
                    blurrato = lOLLO[j];
                    lOLLO[j] = lOLLO[j + 1];
                    lOLLO[j + 1] = blurrato;

                }
            }
        }
        System.out.println(Arrays.toString(lOLLO));
    }

    public static void bubbleSorrrtlobotomizzato1(int[] lOLLO, int l_AURA) {   // solo cicciio
        int blurrato;
        boolean ci = false;
        for (int i = 0; i < l_AURA - 1; i++) {
            for (int j = 0; j < l_AURA - i - 1; j++) {
                if (lOLLO[j] > lOLLO[j + 1]) {
                    blurrato = lOLLO[j];
                    lOLLO[j] = lOLLO[j + 1];
                    lOLLO[j + 1] = blurrato;
                    ci = true;
                }
            }
            if (ci == false) {
                break;
            }
        }
        System.out.println(Arrays.toString(lOLLO));
    }

    public static void laGaraDelMarzone(int[] pesh, int l_AURA) {
        long startTime = System.nanoTime();
        bubbleSorrrt(pesh, l_AURA);
        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        int elementi = l_AURA;
        System.out.println("elementi: " + elementi + "          " + "Tempo: " + duration + " ns");
    }
    public static void laGaraDelMarzonemid(int[] pesh, int l_AURA) {
        long startTime = System.nanoTime();
        bubbleSorrrtlobotomizzato1(pesh, l_AURA);
        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        int elementi = l_AURA;
        System.out.println("elementi: " + elementi + "          " + "Tempo: " + duration + " ns");
    }
    public static void laGaraDelMarzonefinale(int[] pesh, int l_AURA) {
        long startTime = System.nanoTime();
        bubbleSorrrtlobotomizzato(pesh, l_AURA);
        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        int elementi = l_AURA;
        System.out.println("elementi: " + elementi + "          " + "Tempo: " + duration + " ns");
    }

    public static void main(String[] args) {
        Scanner marzone = new Scanner(System.in);
        System.out.println("dimmi 3 numeri , IL NUMERO DI ELEMENTI ,un massimo e un minimo  ");
        int l_AURA = marzone.nextInt();
        int auraMASSIMA = marzone.nextInt();
        int menoAURA = marzone.nextInt();
        int[] pesh = creaArray(l_AURA, auraMASSIMA, menoAURA);
        System.out.println(Arrays.toString(pesh));
        bubbleSorrrt(pesh, l_AURA);
        bubbleSorrrtlobotomizzato(pesh, l_AURA);
        bubbleSorrrtlobotomizzato1(pesh, l_AURA);
        laGaraDelMarzone(pesh, l_AURA);
        laGaraDelMarzonemid(pesh, l_AURA);
        laGaraDelMarzonefinale(pesh, l_AURA);


    }
}
