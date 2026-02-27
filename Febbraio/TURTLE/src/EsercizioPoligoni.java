
import java.awt.Color;
import java.util.Scanner;
public class EsercizioPoligoni extends TurtleScreen {

    private Turtle t;
      int nLati;
    
    int quantiPoligoni;
    public EsercizioPoligoni(int quantiLati, int quantiPoligoni) {
        super(1920, 1080);
        this.nLati = quantiLati;
        this.quantiPoligoni = quantiPoligoni;
    }

    public void creaPoligoni(int n, int k) {
        double gradi = 360;
        int x = 0;
        int y = -350;
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
            lunghezza /= 2;
            x = x - (lunghezza / 2);
            y = y + lunghezza;
            t.goTo(x, y + 10);
        }
    }

    @Override
    public void setup() {
        noLoop();
        t = createTurtle();
        double angolo = 360 / nLati;
        int avanti = 360 / nLati;
        int distanza = avanti;
        double circumraggio = 0.5 * avanti * (1 / Math.sin(Math.PI / nLati));
        double apotema = circumraggio * Math.cos(Math.PI / nLati);
        t.penUp();
        t.goTo(-Math.sqrt(Math.pow(circumraggio, 2) - Math.pow(apotema, 2)), -apotema);
        t.penDown();
        for (int i = 0; i < quantiPoligoni; i++) {
            for (int j = 0; j < nLati; j++) {
                t.forward(avanti);
                t.left(angolo);
            }
            avanti += distanza;
            t.penUp();
            t.goTo(t.position()[0] - Math.sqrt(Math.pow(circumraggio, 2) - Math.pow(apotema, 2)), t.position()[1] - apotema);
            t.penDown();
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Dimmi il numero di lati: ");
        int n = sc.nextInt();
        System.out.print("Dimmi quanti poligoni disegnare: ");
        int k = sc.nextInt();

        EsercizioPoligoni tortellino = new EsercizioPoligoni(n, k);
        tortellino.run();
    }
}
