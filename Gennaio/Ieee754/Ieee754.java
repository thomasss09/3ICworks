package Ieee754;
import java.util.Scanner;

public class Ieee754 {

    public static void iEEE754Final(float num) {
        int bias = 127;
        String con = "";                         //  Serve essenzialmente a prendere il numero binario della parte con la virgola
        String input = String.valueOf(num);
        String[] split = input.split("[,\\.]");
        String parteIntera = split[0];
        String parteDecimale = split[1];
        Float nIntero = Float.valueOf(parteIntera);
        Float nDecimale = Float.valueOf(parteDecimale);

        //  TODO divido la parte intera e con la virgola 
        String sResto = "";
        while (nIntero > 0) {       // numero binario parte intera 

            Float nResto = nIntero % 2;
            sResto = nResto + sResto;
            nIntero = nIntero / 2;
            System.out.println(sResto); //TODO s
        }
        Float numeroIntero = Float.valueOf(sResto);  // faccio tornare da stringa in numero la parte decimale ;
        //TODO tempo di agire anche sulla parte con la virgola
        while (nDecimale != 1) {
            int contaUni = 0;
            int contaZeri = 0;
            while (nDecimale < 1) {
                nDecimale = nDecimale * 2;
                String s = String.valueOf(nDecimale);
                if (s.charAt(0) == 1) {
                    con = 1 + "";
                } else {
                    con = 0 + "";
                }
            }
            nDecimale = nDecimale - 1;
            System.out.println(con);  // TODO S
        }
        // TODO  sia parte che decimale che intera sono state messe in binario ora è tempo di unirle nella mantissa
        String virgi = String.valueOf(numeroIntero);
        String mantissa = "";
        mantissa = virgi + "," + con;
        // TODO fatta la mantissa è ora di fare la polarizzazione che con singola precisione (32 bit) il bias è 127

        String[] v1 = mantissa.split("[,\\.]");
        String part1 = v1[0];
        String part2 = v1[1];
        String vf = part1 + part2;
        int lunghezzaPrimaParte = part1.length();
        int esponente = bias + (lunghezzaPrimaParte);
        String xResto = "";
        while(esponente != 0){
            int resto = esponente % 2;
            xResto = resto + xResto;
            esponente = esponente / 2;
        }
        // TODO mettiamo insieme tutto
        char[] arr = new char[23];
        arr = vf.toCharArray();
        if(vf.length() < 23){
            for (int i = arr.length; i == 23 ; i++) {          //faccio raggiungere la mantissa a 23 bit 
                arr[i] = '0';
            }
        }
        String manti = String.valueOf(arr);
        String esp = xResto;
        String finale = "1" + manti + esp ;
        System.out.println(finale);
    
    }

    public static void main(String[] args) {
        float num;
        Scanner input = new Scanner(System.in);
        System.out.println("Sono qui per tradurre il numero che mi darai con lo standard IEEE754 , la prego di inserire un numero.");
        num = input.nextFloat();
        input.close();
    }
}
