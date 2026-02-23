
import java.awt.Color;
import java.util.Scanner;

public class EsercizioPoligoni extends TurtleScreen {
    private Turtle t;
    public EsercizioPoligoni() {
        super(1200, 1080);
    }

    
    public void creaPoligoni(int n, int k) {
        double gradi = 360;

        int x = 0;
        int y = -300;
        gradi = (double) (gradi / n);
        int lunghezza = 250;
        for (int j = 0; j < k; j++) {
            for (int i = 0; i < n; i++) {
                t.left(gradi);
                t.penDown();
                t.setColor(Color.BLACK);
                t.forward(lunghezza);
                t.penUp();
            }
            lunghezza/=2;
            x = x - (lunghezza/2);
            y = y+lunghezza;
            t.goTo(x,y+10);
        }
    }
    @Override
    public void setup() {                        // Metodo chiamato una volta all'avvio
        noLoop();                                // Disegna 1 volta e poi si ferma
        title("poligoni");                // Imposta il titolo della finestra
        bgcolor(new Color(50, 50, 100));       // Colore di sfondo
        Scanner sc = new Scanner(System.in);
        System.out.println("dimmi il n di lati del poliggg da 3 a  12");
        int n = sc.nextInt(); // numero di lati del poligono da  3 a 12
        System.out.println("dimmi il num di polig da disegnare");
        int k = sc.nextInt();   // poligoni da disegnare
        t = createTurtle(); // Crea una nuova tartaruga
        t.speed(1); // Velocità 1 = disegna veloce
        t.hideTurtle(); // Nasconde la tartaruga
        t.setPenSize(6); // Spessore iniziale della penna

        // Posiziona la tartaruga in basso al centro
        t.penUp();                       
        t.goTo(0, -300
            
        );                 
        t.penDown();                  
        t.setHeading(0);                
        // Inizia a disegnare il peris
        creaPoligoni(n, k);
    }

    public static void main(String[] args) {
      
        TurtleScreen app = new EsercizioPoligoni();  // Crea l'applicazione
        app.run();                                 // Avvia l'esecuzione
    }
}
