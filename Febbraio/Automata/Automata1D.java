
class Automa1D {

    private int[] striscia;
    private int regola;

    public Automa1D(int larghezza, int regola) {
        this.striscia = new int[larghezza];
        this.regola = regola;
        striscia[larghezza / 2] = 1; // solo una cella accesa al centro.Ci serve per // vedere se si "propaga"
    }

    public int calcolaStato(int sinistra, int centro, int destra) {
        // TODO Completa qui : in base a sinistra, centro, destra e la "regola"
        // devi calcolare il nuovo stato

        return 0;
    }

    public void nextStato() {
        int[] nuovoStato = new int[striscia.length];
        //TODO Completa qui:
        // per ogni cella, prendi i vicini e calcola il nuovo stato usando "calcolaStato"
        striscia = nuovoStato;
    }

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
    }
}
