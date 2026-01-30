
public class Partita {
//TODO DA FAREEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE
    private Giocatore giocatore1;
    private Giocatore giocatore2;
    private int turno;
    private boolean primoTurno;

    
    public Partita(Giocatore giocatore1, Giocatore giocatore2, int turno, boolean primoTurno) {
        this.giocatore1 = giocatore1;
        this.giocatore2 = giocatore2;
        this.turno = turno;
        this.primoTurno = primoTurno;
    }

    public Partita(String nome1, String nome2) {
        this.giocatore1 = new Giocatore(nome1);
        giocatore2 = new Giocatore(nome2);
        turno = 0;
        primoTurno = true;
    }
// Gioca 1 solo turno. Se `turno` è pari, tocca al giocatore1,
// altrimenti tocca al giocatore2

    public void turno() {
    }
// Controlla se la partita è finita

    public boolean isFinita() {   // TODO DA FARE
        return true;
    }
// Ritorna il vincitore

    public Giocatore getVincitore() {   //TODO DA FARE
        return giocatore1;
    }
// Simula l'intera partita (vedi dopo)

    public void gioca() {
    }
}
