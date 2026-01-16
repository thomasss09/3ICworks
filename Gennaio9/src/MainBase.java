import java.util.Scanner;

public class MainBase {
    public static void main(String[] args) throws Exception {
        Scanner SC = new Scanner(System.in);
        System.out.println(" dammi la coordinata x ");
        int x = SC.nextInt();
        System.out.println(" dammi la coordinata y ");
        int y = SC.nextInt();
        Punto p = new Punto(x , y);
        System.out.println(p.toString());
        
        System.out.println(" dammi la coordinata x1 ");
        int x1 = SC.nextInt();
        System.out.println(" dammi la coordinata y1 ");
        int y1 = SC.nextInt();
        Punto b = new Punto(x1 , y1);
        System.out.println(b.toString());

        System.out.println(" dammi la direzione ");
        String dir = SC.next();
        Direzione direzione = Direzione.valueOf(dir);
        
        System.out.println(" dammi la distanza ");
        int distanza = SC.nextInt();

        
        System.out.println(p.toString());
        p.trasla(direzione, distanza);
        System.out.println(p.toString());
        p.distanza(p, b);
        SC.close();
    }
}
