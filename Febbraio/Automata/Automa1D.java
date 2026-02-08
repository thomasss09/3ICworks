
public class Automa1D {

    private int[] striscia;
    private String strisciaTemp = ""; //0001000
    private int regola;

    public Automa1D(int larghezza, int regola) {
        this.striscia = new int[larghezza];
        this.regola = regola;
        striscia[larghezza / 2] = 1; // solo una cella accesa al centro.Ci serve per // vedere se si "propaga"

    }

    public String calcolaStato(int sinistra, int centro, int destra) {
        String[] stato = {"111", "110", "101", "100", "011", "010", "001", "000"};
        // TODO Completa qui : in base a sinistra, centro, destra e la "regola"
        // devi calcolare il nuovo stato
        int n = 8; // numero lunghezza miaStriscia
        String nuovaRiga = "";

        for (int i = 0; i < n; i++) {
            String tripla; //prendo 3 bit
            if (i == 0) {
                // bordo sinistro
                tripla = "0" + strisciaTemp.substring(0, 2);

            } else if (i == n - 1) {
                // bordo destro
                tripla = strisciaTemp.substring(n - 2, n) + "0";

            } else {
                // bordo normale
                tripla = strisciaTemp.substring(i - 1, i + 2);
            }

            
            System.out.println("i = " + i + " " + tripla);
        }

        return nuovaRiga; 
    }

    public void nextStato() {
        int[] nuovoStato = new int[striscia.length];
        //TODO Completa qui:
        // per ogni cella, prendi i vicini e calcola il nuovo stato usando "calcolaStato"
        striscia = nuovoStato;
    }
/* 
    public void stampa() {
        for (int c : striscia) {
            System.out.print(c = 1 ? "█" : " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Automa1D ac = new Automa1D(61, 110);
        for (int i = 0; i < 30; i++) {
            ac.stampa();
            ac.nextStato();
        }
    }*/
}
