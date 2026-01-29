
public class Partita {

    private Giocatore giocatore1;
    private Giocatore giocatore2;
    private int turno;
    private boolean primoTurno;

    public Partita(String nome1, String nome2) {
        giocatore1 = new Giocatore(nome1);
        giocatore2 = new Giocatore(nome2);
        turno = 0;
        primoTurno = true;
    }
// Gioca 1 solo turno. Se `turno` è pari, tocca al giocatore1,
// altrimenti tocca al giocatore2

    public void turno() {
    }
// Controlla se la partita è finita

    public boolean isFinita() {
    }
// Ritorna il vincitore

    public Giocatore getVincitore() {
    }
// Simula l'intera partita (vedi dopo)

    public void gioca() {
    }
}
