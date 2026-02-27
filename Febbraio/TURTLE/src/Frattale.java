
import java.util.Scanner;

public class Frattale extends TurtleScreen {

    private static int lunghezza;
    private static int dettagli;
    int nLati = 3;
    private Turtle t;

    public Frattale() {
        super(1920, 1080);
    }

    public void creaTriangoloDiSierpiński(int lunghezza) {

    }

    @Override
    public void setup() {
        noLoop();
        int n = 5; // i triangoli che deve fare
        t = createTurtle();
        double angolo = 360 / nLati;
        lunghezza *= 50;
        int avanti = lunghezza;
        int metàLato = lunghezza/2;
        int distanza = avanti;
        double circumraggio = 0.5 * avanti * (1 / Math.sin(Math.PI / nLati));
        double apotema = circumraggio * Math.cos(Math.PI / nLati);
        t.penUp();
        t.goTo(-Math.sqrt(Math.pow(circumraggio, 2) - Math.pow(apotema, 2)), -apotema);
        t.penDown();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < nLati; j++) {
                t.forward(avanti);
                t.left(angolo);
            }
            avanti += distanza;
            t.penUp();
            t.goTo(t.position()[0] - Math.sqrt(Math.pow(circumraggio, 2) - Math.pow(apotema, 2)), t.position()[1] - apotema);
            t.penDown();
            t.penUp();
            t.goTo(metàLato, apotema);
            t.penDown();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("dimmi la lunghezza del lato tuo frattale segreto");
        lunghezza = sc.nextInt();
        System.out.println("dimmi il numero di dettagli che vuoi da 1 a 10 ");
        dettagli = sc.nextInt();
        Frattale tortellino = new Frattale();
        tortellino.run();
    }
}
