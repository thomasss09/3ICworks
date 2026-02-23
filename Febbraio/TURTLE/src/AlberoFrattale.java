     // Importa la libreria turtle
import java.awt.Color;

/**
 * Demo che disegna un albero frattale usando la ricorsione
 */
public class AlberoFrattale extends TurtleScreen {  
    private Turtle t; 

    public AlberoFrattale() {
        super(800, 600); 
    }

    @Override
    public void setup() {                        // Metodo chiamato una volta all'avvio
        noLoop();                                // Disegna 1 volta e poi si ferma
        title("Albero Frattale");                // Imposta il titolo della finestra
        bgcolor(new Color(50, 50, 100));       // Colore di sfondo

        t = createTurtle(); // Crea una nuova tartaruga
        t.speed(1); // Velocità 1 = disegna veloce
        t.hideTurtle(); // Nasconde la tartaruga
        t.setPenSize(2); // Spessore iniziale della penna

        // Posiziona la tartaruga in basso al centro
        t.penUp();                       
        t.goTo(0, -250);                 
        t.penDown();                  
        t.setHeading(90);                

        // Inizia a disegnare l'albero
        disegnaRamo(100, 10);
    }

    private void disegnaRamo(double lunghezza, int profondita) {
        // Caso base: ferma la ricorsione se è troppo piccolo il segmento
        // o se ho finito la "profondità"
        if (profondita == 0 || lunghezza < 2) {
            return;
        }

        // Calcola il colore del ramo (più verde verso le punte)
        float componenteVerde = Math.min(1.0f, 0.2f + (10 - profondita) * 0.08f);
        float componenteRosso = Math.max(0.1f, 0.4f - profondita * 0.03f);
        t.setPenColor(new Color(componenteRosso, componenteVerde, 0.1f));

        // I rami più profondi sono più spessi
        t.setPenSize(profondita+1);

        // Disegna il ramo corrente
        t.forward(lunghezza);            

        // Salva la posizione e direzione corrente
        double[] posizione = t.position(); // Ottiene [x, y] correnti
        double direzione = t.heading(); // Ottiene l'angolo corrente

        // Disegna il ramo sinistro
        t.left(30);                         
        disegnaRamo(lunghezza * 0.7, profondita - 1); 

        // Torna alla posizione salvata
        t.penUp();                           // Alza la penna
        t.goTo(posizione[0], posizione[1]);  // Torna alla biforcazione
        t.setHeading(direzione);             // Ripristina la direzione
        t.penDown();                         // Abbassa la penna

        // Disegna il ramo destro
        t.right(30);                         
        disegnaRamo(lunghezza * 0.7, profondita - 1); 

        // Torna alla posizione salvata in precedenza
        t.penUp();
        t.goTo(posizione[0], posizione[1]);
        t.setHeading(direzione);
        t.penDown();
    }

    public static void main(String[] args) {
        TurtleScreen app = new AlberoFrattale();  // Crea l'applicazione
        app.run();                                 // Avvia l'esecuzione
    }
}
