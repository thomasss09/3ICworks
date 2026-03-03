package Ieee754;

import java.util.Scanner;

public class IEE754 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        float numero;
        System.out.println("inserisci un numero da convertire in IEEE754");
        numero = scanner.nextFloat();
        int numeroIntero = parteIntera(numero);
        float numeroNonIntero = parteDecimale(numero);
        int[] binarioIntero = conversioneBinariaIntero(numeroIntero);
        int[] binarioDecimale = conversioneBinariaDecimale(numeroNonIntero, binarioIntero);
        int[] binarioEsponente = calcolaEsponente(binarioIntero);
        String binarioCompleto = numeroCompletoBinario(binarioEsponente, binarioDecimale, binarioIntero, numero);
        conversioneEsadecimale(binarioCompleto);

    }

    public static int parteIntera(float numero) {
        int numeroTroncato;
        numeroTroncato = (int) numero;
        System.out.println("ecco la parte intera del numero: " + numeroTroncato);
        return numeroTroncato;
    }

    public static float parteDecimale(float numero) {
        float numeroDecimale;
        numeroDecimale = numero - (int) numero;
        if (numeroDecimale < 0) {
            numeroDecimale = numeroDecimale * - 1;
        }
        System.out.println("ecco la parte decimale del numero: " + numeroDecimale);
        return numeroDecimale;
    }

    public static int[] conversioneBinariaIntero(int numero) {
        int temp = numero;
        int contatore = 0;

        while (temp > 0) {
            temp /= 2;
            contatore++;
        }

        int[] binario = new int[contatore];
        int indice = contatore - 1;

        while (numero > 0) {
            binario[indice] = numero % 2;
            numero /= 2;
            indice--;
        }
        for (int i = 0; i < binario.length; i++) {
            System.out.print(binario[i]);
        }
        System.out.println();
        return binario;
    }

    public static int[] conversioneBinariaDecimale(float numeroNonIntero, int[] binario) {
        int numeroDaSottrarre = binario.length;
        int[] lunghezzaMantissa = new int[23 - numeroDaSottrarre];
        for (int i = 0; i < lunghezzaMantissa.length; i++) {
            numeroNonIntero = numeroNonIntero * 2;
            if (numeroNonIntero >= 1) {
                numeroNonIntero -= 1;
                lunghezzaMantissa[i] = 1;
            } else if (numeroNonIntero < 1) {
                lunghezzaMantissa[i] = 0;
            }
        }
        for (int j = 0; j < lunghezzaMantissa.length; j++) {
            System.out.print(lunghezzaMantissa[j]);
        }
        System.out.println();
        return lunghezzaMantissa;
    }

    public static int[] calcolaEsponente(int[] binarioIntero) {
        int esponente = 127;
        int contoPosizioni = binarioIntero.length - 1;
        int esponentePolarizzato = esponente + contoPosizioni;
        int[] calcolo = conversioneBinariaIntero(esponentePolarizzato);
        return calcolo;
    }

    public static String numeroCompletoBinario(int[] binarioEsponente, int[] binarioDecimale, int[] binarioIntero, float numero) {
        String completo = "";
        if (numero >= 0) {
            completo += 0;
        } else {
            completo += 1;
        }
        for (int i = 0; i < binarioEsponente.length; i++) {
            completo += binarioEsponente[i];
        }
        for (int i = 1; i < binarioIntero.length; i++) {
            completo += binarioIntero[i];
        }
        for (int i = 0; i < binarioDecimale.length; i++) {
            completo += binarioDecimale[i];
        }
        System.out.println(completo);
        return completo;
    }

    public static String conversioneEsadecimale(String rappresentazione) {
        String esadecimale = "";
        int bitiniziale;
        String[] nNumeri = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};
        for (int i = 0; i < 8; i++) {
            bitiniziale = 4 * i;
            String stringa = rappresentazione.substring(bitiniziale, bitiniziale + 4);
            esadecimale += nNumeri[Integer.parseInt(stringa, 2)];
        }
        System.out.println("Numero esadecimale: 0x" + esadecimale);
        return "0x" + esadecimale;
    }
}
