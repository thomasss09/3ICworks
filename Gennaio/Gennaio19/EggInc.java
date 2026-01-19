
public class EggInc {

    public static int ricercaLineare(EggGame gioco, int numeroPiani) {

        for (int i = 0; i < numeroPiani; i++) {
            if (gioco.lanciaUovo(i).equals("SPLAT")) {
                //System.out.println(" il tuo uovo sui spiaccica al terreno dal " + i + " piano");
                return i;
            }
        }
        return 0;
    }

    public static int ricercaBinariaUova(EggGame gioco, int numeroPiani) {
        int max = numeroPiani;
        int min = 0;
        int lancio = (numeroPiani + 1) / 2;
        int dif = 0;
        while (min < max) {
            lancio = (min + max + 1) / 2;
            if (gioco.lanciaUovo(lancio).equals("SAFE")) {
                min = lancio;
            } else {
                max = lancio - 1;
            }
        }
        //System.out.println("l'uovo è intero al piano " + min);
        return min;
    }

    public static void confrontaRicerche() {
        int piani = 10;
        int contli;
        int contbin;
        for (int i = 0; i < 5; i++) {
            EggGame rub = new EggGame(piani);
            ricercaLineare(rub, piani);
            contli = rub.getContatoreNlanci();
            rub.resetContatore();
            ricercaBinariaUova(rub, piani);
            contbin = rub.getContatoreNlanci();
            System.out.println(" piano : " + piani + " Lineare :" + contli + " Binaria :" + contbin);
            piani*=10;
        }

    }

    public static void main(String[] args) {
        EggGame eggInc = new EggGame(30);
        System.out.println(ricercaLineare(eggInc, 30));
        ricercaBinariaUova(eggInc, 100);
        confrontaRicerche();
    }
}
