
import java.awt.Color;

/**
 * Disegna una spirale con i colori che cambiano gradualmente
 */
public class SpiraleDemo extends TurtleScreen {  
    private Turtle t;  // La nostra tartaruga

    public SpiraleDemo() {
        super(800, 600);  // Crea una finestra di 800x600 pixel
    }

    @Override
    public void setup() {      // Metodo chiamato una volta all'avvio
        noLoop();              // Disabilita l'animazione continua
        title("Spirale Colorata");  // Imposta il titolo della finestra
        bgcolor(Color.GRAY);   // Imposta il colore di sfondo a grigio

        t = createTurtle();    // Crea una nuova tartaruga
        t.speed(1);            // Velocità molto alta (1=veloce, 255=lento)
        t.setPenSize(2);       // Imposta lo spessore della penna a 2 pixel

        int iterations = 360;  // Numero di segmenti della spirale
        for (int i = 0; i < iterations; i++) {
            // Calcola un colore diverso per ogni segmento (arcobaleno)
            float hue = (float) i / iterations;  // Valore tra 0 e 1
            Color color = Color.getHSBColor(hue, 1.0f, 1.0f);  // Crea colore HSB (Cerca come funziona HSB rispetto a RGB)

            t.setPenColor(color);   // Imposta il colore della penna
            t.forward(i * 0.5);     // Avanza di una distanza crescente (crea la spirale)
            t.left(59);             // Ruota a sinistra di 59 gradi
        }

        t.hideTurtle();  // Nasconde la tartaruga alla fine
    }

    public static void main(String[] args) {
        TurtleScreen app = new SpiraleDemo();  // Crea l'applicazione
        app.run();  // Avvia l'esecuzione: chiama setup() e mostra la finestra
    }
}
