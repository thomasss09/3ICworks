
public class Main {

    public static void main(String[] args) {
          /*// nel main:
        ProdottoAlimentare a = new ProdottoAlimentare("Pasta", 1.50, 350, false);
        System.out.println(a); // "Pasta (350 kcal, no refrigerazione): €1.5"
        Elettronico e = new Elettronico("Laptop", 800.0, 24);
        System.out.println(e); // "Laptop (garanzia 24 mesi): €800.0"
        Abbigliamento ab = new Abbigliamento("Giacca", 120.0, "L");
        System.out.println(ab); // "Giacca (taglia L): €120.0"
        e.applicaIVA(0.22);
        System.out.println(e); // "Laptop (garanzia 24 mesi): €976.0" */

        /*
        // nel main:
        Elettronico e = new Elettronico("Laptop", 1000.0, 24);
        System.out.println(e.getPrezzoAttuale()); // 1000.0
        e.applicaSconto(20);
        System.out.println(e.getPrezzoAttuale()); // 800.0
        Digitale d = new Digitale("Suite Office", 149.0);
        System.out.println(d); // "Suite Office (digitale): €149.0"
        System.out.println(d.calcolaSpese()); // 0.0
        System.out.println(d.corriere()); // "download diretto"
         */ // nel main:
         
        Prodotto[] catalogo = {
            new ProdottoAlimentare("Pasta", 1.50, 350, false),
            new Elettronico("Laptop", 999.0, 24),
            new Digitale("Antivirus", 29.99)
        };
        Magazzino.stampaInventario(catalogo);
        Magazzino.scontaTutto(catalogo, 10);
        Object[] ordini = {
            new Digitale("Ebook", 9.99),
            new Elettronico("Mouse", 30.0, 12),
            new Digitale("Gioco", 19.99)
        };
        Magazzino.stampaSpedizioni(ordini);
    }
}
