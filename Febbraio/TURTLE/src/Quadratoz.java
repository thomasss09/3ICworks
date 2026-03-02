
import java.awt.Color;
import java.awt.Font;

/**
 * Base per il giochino. Un quadrato si muove su una griglia
 *
 * Questo esempio mostra come: - Creare una griglia - Gestire il movimento con i
 * tasti WASD - Disegnare un quadrato che si muove di cella in cella - Mostrare
 * del testo
 *
 * Controlli: - WASD: Muovi il quadrato - R: Riporta il quadrato al centro
 */
public class Quadratoz extends TurtleScreen {

    // Dimensioni della griglia
    private static final int GRID_SIZE = 15;        // Numero di celle per lato
    private static final int CELL_SIZE = 30;        // Dimensione di ogni cella in pixel
    private static final int CANVAS_SIZE = GRID_SIZE * CELL_SIZE;
    Striscia[][] colori = new Striscia[GRID_SIZE][GRID_SIZE];
    // Tartarughe per il disegno
    private Turtle quadrato;  // Tartaruga per disegnare il quadrato
    private Turtle quadratoAvversario;  // Tartaruga per disegnare il quadrato avversario 
    private Turtle griglia;      // Tartaruga per disegnare la griglia
    private Turtle testo;   // Tartaruga per il testo

    // Posizione del quadrato sulla griglia (in coordinate di cella)
    private int posX;
    private int posY;
    private int posX1;
    private int posY1;

    // Direzione corrente del movimento
    private int dx = 0;
    private int dy = 0;
    private int dx1 = 0;
    private int dy1 = 0;

    // Contatore frame per controllare la velocita' del movimento
    private int frameCount = 0;

    public Quadratoz() {
        super(CANVAS_SIZE, CANVAS_SIZE);
    }

    @Override
    public void setup() {
        title("Quadratoz");
        bgcolor(new Color(30, 30, 50));

        // Inizializza la tartaruga per il quadrato
        quadrato = createTurtle();
        quadrato.hideTurtle();
        quadrato.speed(0);
        // same for the red one 
        quadratoAvversario = createTurtle();
        quadratoAvversario.hideTurtle();
        quadratoAvversario.speed(0);

        // Inizializza la tartaruga per la griglia
        griglia = createTurtle();
        griglia.hideTurtle();
        griglia.speed(0);

        // Inizializza la tartaruga per il testo
        testo = createTurtle();
        testo.hideTurtle();
        testo.speed(0);
        testo.penUp();

        // Posiziona il quadrato al centro della griglia
        posX = GRID_SIZE / 2;
        posY = GRID_SIZE / 2;
        // quadrato avversario
        posX1 = GRID_SIZE / 2;
        posY1 = GRID_SIZE / 2;

    }

    @Override
    public void loop() {
        // Gestisci l'input della tastiera
        handleInput();

        // Incrementa il contatore dei frame
        frameCount++;

        // Muovi il quadrato ogni 8 frame (circa 7-8 volte al secondo)... 
        // Cambia per renderlo più veloce/lento
        if (frameCount % 8 == 0) {
            muoviQuadrato();
            muoviQuadratoAvversario();
        }

        // Disegna tutto ogni frame
        render();
    }

    /**
     * Gestisce l'input da tastiera per cambiare direzione.
     */
    private void handleInput() {
        String key = getLastKey();
        if (key == null) {
            return;
        }

        switch (key) {
            // Movimento verso l'alto
            case "w":
                // case "up": per la freccia
                dx = 0;
                dy = 1;
                break;
            case "up":
                // case "up": per la freccia
                dx1 = 0;
                dy1 = 1;
                break;
            // Movimento verso il basso
            case "s":
                // case "down": per la freccia
                dx = 0;
                dy = -1;
                break;
            case "down":
                // case "down": per la freccia
                dx1 = 0;
                dy1 = -1;
                break;
            // Movimento verso sinistra
            case "a":
                // case "left": per la freccia
                dx = -1;
                dy = 0;
                break;
            case "left":
                // case "left": per la freccia
                dx1 = -1;
                dy1 = 0;
                break;
            // Movimento verso destra
            case "d":
                // case "right": per la freccia
                dx = 1;
                dy = 0;
                break;
            case "right":
                // case "right": per la freccia
                dx1 = 1;
                dy1 = 0;
                break;
            // Reset: riporta al centro e ferma il movimento
            case "r":
                posX = GRID_SIZE / 2;
                posY = GRID_SIZE / 2;
                dx = 0;
                dy = 0;
                posX1 = GRID_SIZE / 2;
                posY1 = GRID_SIZE / 2;
                dx1 = 0;
                dy1 = 0;
                break;
        }
    }

    /**
     * Muove il quadrato nella direzione corrente. Il quadrato si ferma ai bordi
     * della griglia.
     */
    private void muoviQuadrato() {
        // Calcola la nuova posizione
        int nuovaX = posX + dx;
        int nuovaY = posY + dy;

        // Controlla i bordi della griglia
        if (nuovaX >= 0 && nuovaX < GRID_SIZE) {
            posX = nuovaX;
        }
        if (nuovaY >= 0 && nuovaY < GRID_SIZE) {
            posY = nuovaY;
        }
    }

    private void muoviQuadratoAvversario() {
        // Calcola la nuova posizione
        int nuovaX1 = posX1 + dx1;
        int nuovaY1 = posY1 + dy1;

        // Controlla i bordi della griglia
        if (nuovaX1 >= 0 && nuovaX1 < GRID_SIZE) {
            posX1 = nuovaX1;
        }
        if (nuovaY1 >= 0 && nuovaY1 < GRID_SIZE) {
            posY1 = nuovaY1;
        }
    }

    private void render() {
        // Disegna la griglia di sfondo
        disegnaGriglia();

        // Disegna il quadrato "evidenziato"
        disegnaQuadrato();

        // Disegna le istruzioni
        disegnaTesto();
    }

    /**
     * Disegna una griglia leggera di sfondo.
     */
    private void disegnaGriglia() {
        griglia.setPenColor(new Color(60, 60, 80));
        griglia.setPenSize(1);

        // Linee verticali
        for (int i = 0; i <= GRID_SIZE; i++) {
            double x = i * CELL_SIZE - CANVAS_SIZE / 2.0;
            griglia.penUp();
            griglia.goTo(x, -CANVAS_SIZE / 2.0);
            griglia.penDown();
            griglia.goTo(x, CANVAS_SIZE / 2.0);
        }

        // Linee orizzontali
        for (int i = 0; i <= GRID_SIZE; i++) {
            double y = i * CELL_SIZE - CANVAS_SIZE / 2.0;
            griglia.penUp();
            griglia.goTo(-CANVAS_SIZE / 2.0, y);
            griglia.penDown();
            griglia.goTo(CANVAS_SIZE / 2.0, y);
        }
    }

    private void aggiornamentoArray() {
        if (colori[posX][posY] == Striscia.VUOTO || colori[posX][posY] == Striscia.RED) {
            colori[posX][posY] = Striscia.BLUE;
        }
        if (colori[posX1][posY1] == Striscia.VUOTO || colori[posX1][posY1] == Striscia.RED) {
            colori[posX1][posY1] = Striscia.BLUE;
        }
    }

    private void disegnaSfondo() {
        Turtle filler = createTurtle();
        double x = posX * CELL_SIZE - CANVAS_SIZE / 2.0 + CELL_SIZE / 2.0;
        double y = posY * CELL_SIZE - CANVAS_SIZE / 2.0 + CELL_SIZE / 2.0;
        // double x1 = posX1 * CELL_SIZE - CANVAS_SIZE / 2.0 + CELL_SIZE / 2.0;
        // double y1 = posY1 * CELL_SIZE - CANVAS_SIZE / 2.0 + CELL_SIZE / 2.0;
        filler.penUp();
        // quadratoAvversario.penUp();
        filler.goTo(x - CELL_SIZE / 2.0 + 2, y - CELL_SIZE / 2.0 + 2);
        // quadratoAvversario.goTo(x1 - CELL_SIZE / 2.0 + 2, y1 - CELL_SIZE / 2.0 + 2);
        filler.penDown();
        //quadratoAvversario.penDown();
        filler.beginFill();
        for (int i = 0; i < 4; i++) {
            filler.forward(CELL_SIZE - 4);
            filler.left(90);
        }
        filler.endFill();
    }

    private void creazione() {
        for (int i = 0; i < colori.length; i++) {
            for (int j = 0; j < colori.length; j++) {
                colori[i][j] = Striscia.VUOTO;
            }
        }
    }

    /**
     * Disegna il quadrato nella posizione corrente.
     */
    private void disegnaQuadrato() {
        // Converti le coordinate della griglia in coordinate del canvas
        double x = posX * CELL_SIZE - CANVAS_SIZE / 2.0 + CELL_SIZE / 2.0;
        double y = posY * CELL_SIZE - CANVAS_SIZE / 2.0 + CELL_SIZE / 2.0;
        double x1 = posX1 * CELL_SIZE - CANVAS_SIZE / 2.0 + CELL_SIZE / 2.0;
        double y1 = posY1 * CELL_SIZE - CANVAS_SIZE / 2.0 + CELL_SIZE / 2.0;
        // Imposta i colori
        quadrato.setPenColor(Color.WHITE);
        quadratoAvversario.setPenColor(Color.WHITE);
        quadrato.setFillColor(new Color(100, 150, 255));
        quadratoAvversario.setFillColor(new Color(255, 000, 000));

        // Posiziona la tartaruga nell'angolo in basso a sinistra della cella
        quadrato.penUp();
        quadratoAvversario.penUp();
        quadrato.goTo(x - CELL_SIZE / 2.0 + 2, y - CELL_SIZE / 2.0 + 2);
        quadratoAvversario.goTo(x1 - CELL_SIZE / 2.0 + 2, y1 - CELL_SIZE / 2.0 + 2);
        quadrato.penDown();
        quadratoAvversario.penDown();

        // Disegna il quadrato con riempimento
        quadrato.beginFill();
        for (int i = 0; i < 4; i++) {
            quadrato.forward(CELL_SIZE - 4);
            quadrato.left(90);
        }
        quadrato.endFill();
        //disegnaSfondo();
        // quadrato rosso
        quadratoAvversario.beginFill();
        for (int i = 0; i < 4; i++) {
            quadratoAvversario.forward(CELL_SIZE - 4);
            quadratoAvversario.left(90);
        }
        quadratoAvversario.endFill();
       
    }

    private void disegnaTesto() {
        testo.setPenColor(Color.WHITE); // Usiamo una tartaruga apposita

        // Creiamo un "Font" da usare per scrivere
        Font f = new Font("Arial", Font.PLAIN, 12);
        // Istruzioni in alto
        double timeGamhz = 900;
        testo.goTo(0, CANVAS_SIZE / 2.0 - 20);
        testo.write(timeGamhz - frameCount + "", "center", f);
        // mettere a posto il tempo

        // Posizione corrente in basso
        testo.goTo(0, -CANVAS_SIZE / 2.0 + 8);
        String posizione = "Posizione: ( " + posX + " " + posY + ")";
        testo.write(posizione, "center", f);
    }

    public static void main(String[] args) {
        TurtleScreen app = new Quadratoz();
        app.run();
    }
}
