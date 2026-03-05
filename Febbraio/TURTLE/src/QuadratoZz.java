

import java.awt.Color;
import java.awt.Font;

/**
 * Base per il giochino. Un quadrato si muove su una griglia
 *
 * Questo esempio mostra come:
 * - Creare una griglia 
 * - Gestire il movimento con i tasti WASD 
 * - Disegnare un quadrato che si muove di cella in cella
 * - Mostrare del testo
 * 
 * Controlli:
 * - WASD: Muovi il quadrato
 * - R: Riporta il quadrato al centro
 */
public class QuadratoZz extends TurtleScreen {

    // Dimensioni della griglia
    private static final int GRID_SIZE = 16;        // Numero di celle per lato
    private static final int CELL_SIZE = 30;        // Dimensione di ogni cella in pixel
    private static final int CANVAS_SIZE = GRID_SIZE * CELL_SIZE;
    private int[][] celle = new int[16][16];

    // Tartarughe per il disegno
    private Turtle quadratoBlu;     // Tartaruga per disegnare il quadrato blu
    private Turtle quadratoRosso;   // Tartaruga per disegnare il quadrato rosso
    private Turtle sciaBlu;
    private Turtle sciaRosso;
    private Turtle griglia;      // Tartaruga per disegnare la griglia
    private Turtle testo;        // Tartaruga per il testo

    // Posizione del quadrato sulla griglia (in coordinate di cella)
    private int posXBlu;
    private int posYBlu;
    private int posXRosso;
    private int posYRosso;

    // Direzione corrente del movimento
    private int dxBlu = 0;
    private int dyBlu = 0;
    private int dxRosso = 0;
    private int dyRosso = 0;

    // Contatore frame per controllare la velocita' del movimento
    private int frameCount = 0;
    private final int frameCountFinale = 600;

    // Punteggi
    private int[] punteggi = new int[2];

    public QuadratoZz() {
        super(CANVAS_SIZE, CANVAS_SIZE);
    }

    @Override
    public void setup() {
        title("Quadratoz");
        bgcolor(new Color(30, 30, 50));

        // Inizializza la tartaruga per il quadrato blu
        quadratoBlu = createTurtle();
        quadratoBlu.hideTurtle();
        quadratoBlu.speed(0);

        // Inizializza la tartaruga per il quadrato rosso
        quadratoRosso = createTurtle();
        quadratoRosso.hideTurtle();
        quadratoRosso.speed(0);

        // Inizializzo la tartaruga per la scia blu
        sciaBlu = createTurtle();
        sciaBlu.hideTurtle();
        sciaBlu.speed(0);

        // Inizializzo la tartaruga per la scia rosso
        sciaRosso = createTurtle();
        sciaRosso.hideTurtle();
        sciaRosso.speed(0);

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
        posXBlu = GRID_SIZE / 2 - 1;
        posYBlu = GRID_SIZE / 2;
        posXRosso = GRID_SIZE / 2;
        posYRosso = GRID_SIZE / 2;
    }

    @Override
    public void loop() {
        if (frameCount >= frameCountFinale) {
            disegnaPunteggioFinale();
            return;
        }
        // Gestisci l'input della tastiera
        handleInput();

        // Incrementa il contatore dei frame
        frameCount++;

        calcolaPunteggio();

        // Muovi il quadrato ogni 8 frame (circa 7-8 volte al secondo)... 
        // Cambia per renderlo più veloce/lento
        if (frameCount % 8 == 0) {
            muoviQuadrato();
        }

        // Disegna tutto ogni frame
        render();
    }

    /**
     * Gestisce l'input da tastiera per cambiare direzione.
     */
    private void handleInput() {
        String key = getLastKey();
        if (key == null) return;

        switch (key) {
            // Movimento verso l'alto
            case "w":
            // case "up": per la freccia
                dxBlu = 0;
                dyBlu = 1;
                break;

            // Movimento verso il basso
            case "s":
            // case "down": per la freccia
                dxBlu = 0;
                dyBlu = -1;
                break;

            // Movimento verso sinistra
            case "a":
            // case "left": per la freccia
                dxBlu = -1;
                dyBlu = 0;
                break;

            // Movimento verso destra
            case "d":
            // case "right": per la freccia
                dxBlu = 1;
                dyBlu = 0;
                break;

            // Movimento verso l'alto
            case "up":
            // case "up": per la freccia
                dxRosso = 0;
                dyRosso = 1;
                break;

            // Movimento verso il basso
            case "down":
            // case "down": per la freccia
                dxRosso = 0;
                dyRosso = -1;
                break;

            // Movimento verso sinistra
            case "left":
            // case "left": per la freccia
                dxRosso = -1;
                dyRosso = 0;
                break;

            // Movimento verso destra
            case "right":
            // case "right": per la freccia
                dxRosso = 1;
                dyRosso = 0;
                break;
        }
    }
    /**
     * Muove il quadrato nella direzione corrente.
     * Il quadrato si ferma ai bordi della griglia.
     */
    private void muoviQuadrato() {
        // Calcola la nuova posizione
        int nuovaXBlu = posXBlu + dxBlu;
        int nuovaYBlu = posYBlu + dyBlu;
        int nuovaXRosso = posXRosso + dxRosso;
        int nuovaYRosso = posYRosso + dyRosso;

        // Controlla i bordi della griglia
        if (nuovaXBlu >= 0 && nuovaXBlu < GRID_SIZE) {
            posXBlu = nuovaXBlu;
        }
        if (nuovaYBlu >= 0 && nuovaYBlu < GRID_SIZE) {
            posYBlu = nuovaYBlu;
        }
        if (nuovaXRosso >= 0 && nuovaXRosso < GRID_SIZE) {
            posXRosso = nuovaXRosso;
        }
        if (nuovaYRosso >= 0 && nuovaYRosso < GRID_SIZE) {
            posYRosso = nuovaYRosso;
        }

        celle[posXBlu][posYBlu] = 1;
        celle[posXRosso][posYRosso] = 2;
    }

    private void calcolaPunteggio(){
        int puteggioBlu = 0;
        int punteggioRosso = 0;
        for (int i = 0; i < celle.length; i++) {
            for (int j = 0; j < celle[i].length; j++) {
                if (celle[i][j] == 1) puteggioBlu++;
                if (celle[i][j] == 2) punteggioRosso++;
            }
        }
        punteggi[0] = puteggioBlu;
        punteggi[1] = punteggioRosso;
    }

    private void render() {
        // Disegna la griglia di sfondo
        disegnaGriglia();

        // Disegna scia
        disegnaScia();

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

    /**
     * Disegna il quadrato nella posizione corrente.
     */
    private void disegnaQuadrato() {
        // Converti le coordinate della griglia in coordinate del canvas
        double xBlu = posXBlu * CELL_SIZE - CANVAS_SIZE / 2.0 + CELL_SIZE / 2.0;
        double yBlu = posYBlu * CELL_SIZE - CANVAS_SIZE / 2.0 + CELL_SIZE / 2.0;
        double xRosso = posXRosso * CELL_SIZE - CANVAS_SIZE / 2.0 + CELL_SIZE / 2.0;
        double yRosso = posYRosso * CELL_SIZE - CANVAS_SIZE / 2.0 + CELL_SIZE / 2.0;

        // Imposta i colori
        quadratoBlu.setPenColor(Color.WHITE);
        quadratoBlu.setFillColor(new Color(0, 0, 255));
        quadratoRosso.setPenColor(Color.WHITE);
        quadratoRosso.setFillColor(new Color(255, 0, 0));


        // Posiziona la tartaruga nell'angolo in basso a sinistra della cella
        quadratoBlu.penUp();
        quadratoBlu.goTo(xBlu - CELL_SIZE / 2.0 + 2, yBlu - CELL_SIZE / 2.0 + 2);
        quadratoBlu.penDown();
        quadratoRosso.penUp();
        quadratoRosso.goTo(xRosso - CELL_SIZE / 2.0 + 2, yRosso - CELL_SIZE / 2.0 + 2);
        quadratoRosso.penDown();

        // Disegna il quadrato con riempimento
        quadratoBlu.beginFill();
        quadratoRosso.beginFill();
        for (int i = 0; i < 4; i++) {
            quadratoBlu.forward(CELL_SIZE - 4);
            quadratoBlu.left(90);
            quadratoRosso.forward(CELL_SIZE - 4);
            quadratoRosso.left(90);
        }
        quadratoBlu.endFill();
        quadratoRosso.endFill();
    }

    private void disegnaScia() {
        sciaBlu.setPenColor(Color.WHITE);
        sciaBlu.setFillColor(new Color(73, 39, 242));
        sciaRosso.setPenColor(Color.WHITE);
        sciaRosso.setFillColor(new Color(255, 77, 77));
        for (int i = 0; i < celle.length; i++) {
            for (int j = 0; j < celle[i].length; j++) {
                double xSciaBlu = i * CELL_SIZE - CANVAS_SIZE / 2.0 + CELL_SIZE / 2.0;
                double ySciaBlu = j * CELL_SIZE - CANVAS_SIZE / 2.0 + CELL_SIZE / 2.0;
                double xSciaRosso = i * CELL_SIZE - CANVAS_SIZE / 2.0 + CELL_SIZE / 2.0;
                double ySciaRosso = j * CELL_SIZE - CANVAS_SIZE / 2.0 + CELL_SIZE / 2.0;
                if (celle[i][j] == 1){
                    sciaBlu.penUp();
                    sciaBlu.goTo(xSciaBlu - CELL_SIZE / 2.0 + 2, ySciaBlu - CELL_SIZE / 2.0 + 2);
                    sciaBlu.penDown();
                    sciaBlu.beginFill();
                    for (int k = 0; k < 4; k++) {
                        sciaBlu.forward(CELL_SIZE - 4);
                        sciaBlu.left(90);
                    }
                    sciaBlu.endFill();
                }
                if (celle[i][j] == 2){
                    sciaRosso.penUp();
                    sciaRosso.goTo(xSciaRosso - CELL_SIZE / 2.0 + 2, ySciaRosso - CELL_SIZE / 2.0 + 2);
                    sciaRosso.penDown();
                    sciaRosso.beginFill();
                    for (int k = 0; k < 4; k++) {
                        sciaRosso.forward(CELL_SIZE - 4);
                        sciaRosso.left(90);
                    }
                    sciaRosso.endFill();
                }
            }
        }
    }

    private void disegnaTesto() {
        testo.setPenColor(Color.WHITE); // Usiamo una tartaruga apposita

        // Creiamo un "Font" da usare per scrivere
        Font f = new Font("Arial", Font.PLAIN, 12);
        // Istruzioni in alto

        testo.goTo(-CANVAS_SIZE / 2 + 50, CANVAS_SIZE / 2 - 20);
        testo.write("WASD: Muovi", "center", f);
        testo.goTo(CANVAS_SIZE / 2 - 60, CANVAS_SIZE / 2 - 20);
        testo.write("FRECCE: Muovi", "center", f);

        // Posizione corrente in basso
        testo.goTo(-CANVAS_SIZE / 2 + 60, -CANVAS_SIZE / 2 + 10);
        String posizioneBlu = "Punteggio P1: " + punteggi[0];
        testo.write(posizioneBlu, "center", f);
        testo.goTo(CANVAS_SIZE / 2 - 60, -CANVAS_SIZE / 2 + 10);
        String posizioneRosso = "Punteggio P2: " + punteggi[1];
        testo.write(posizioneRosso, "center", f);

        // Tempo rimanente in alto
        testo.goTo(0, CANVAS_SIZE / 2 - 20);
        String tempo = "Tempo rimanente: " + ((frameCountFinale - frameCount) / 60) + " secondi!";
        testo.write(tempo, "center", f);
    }

    public void disegnaPunteggioFinale() {
            testo.setPenColor(Color.WHITE); // Usiamo una tartaruga apposita
    
            // Creiamo un "Font" da usare per scrivere
            Font f = new Font("Arial", Font.BOLD, 24);
            String risultato;
            if (punteggi[0] > punteggi[1]) {
                risultato = "Vince il Blu! Punteggio: " + punteggi[0] + " a " + punteggi[1] + "!";
            } else if (punteggi[1] > punteggi[0]) {
                risultato = "Vince il Rosso! Punteggio: " + punteggi[1] + " a " + punteggi[0] + "!";
            } else {
                risultato = "Pareggio! Punteggio: " + punteggi[0] + " a " + punteggi[1] + "!";
            }
            testo.goTo(0, 0);
            testo.write(risultato, "center", f);
    }

    public static void main(String[] args) {
        TurtleScreen app = new QuadratoZz();
        app.run();
    }
}