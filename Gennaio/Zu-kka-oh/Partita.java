
public class Partita extends Extension {

    private Giocatore giocatore1;
    private Giocatore giocatore2;
    private int turno;

    public Partita(String nome1, String nome2) {
        this.giocatore1 = new Giocatore(nome1);
        this.giocatore2 = new Giocatore(nome2);
        turno = 0;
    }
// Gioca 1 solo turno. Se `turno` è pari, tocca al giocatore1,

    public void setupIniziale() {
        giocatore1.evocaCarta();
        giocatore2.evocaCarta();
    }
    public void turno() {
        if (this.turno % 2 == 0) {
            System.out.println("turno di " + giocatore1.nomeGiocatore);
            giocatore1.evocaCarta();
            giocatore1.battlePhase(giocatore2.nomeGiocatore); //inzia la battaglia
            giocatore1.pescaCarta(); // pesca na cart
        } else {
            System.out.println("turno di " + giocatore2.nomeGiocatore);
            giocatore2.battlePhase(giocatore2.nomeGiocatore);
            giocatore2.evocaCarta();
            giocatore2.pescaCarta();
        }
    }
// Controlla se la partita è finita

    public boolean isFinita() {
        if (giocatore1.getVita() <= 0 || giocatore2.getVita() <= 0) {
            return true;
        }
        return false;
    }
// Ritorna il vincitore

    public Giocatore getVincitore() {
        if (giocatore1.getVita() <= 0 && giocatore2.getVita() > 0) {
            return giocatore2;
        } else if (giocatore2.getVita() <= 0 && giocatore1.getVita() > 0) {
            return giocatore1;
        }
        return null;
    }

// Simula l'intera partita (vedi dopo)
    public void gioca() {

        System.out.println(" La partita è inziata , giocherà per primo " + this.giocatore1.getNomeGiocatore() + " buona fortuna");
        setupIniziale();
        while (!isFinita()) {
            turno();
            turno++;
        }
        System.out.println("Il vincitore di questa partita è " + getVincitore().getNomeGiocatore() + " bravo !!!");
    }
    
}
