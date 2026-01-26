
import java.util.Arrays;

public class Giocatore {

    private String nomeGiocatore = "";
    private static Carta[] mazzo = new Carta[50];
    private Carta[] carteInMano = new Carta[10];
    private Carta[] carteSulCampo = new Carta[5];
    private int vita;

    public Giocatore(String nomeGiocatore, Carta[] carteSulCampo, int vita) {
        this.nomeGiocatore = nomeGiocatore;
        this.mazzo = mazzo;
        this.carteInMano = carteInMano;
        this.carteSulCampo = carteSulCampo;
        this.vita = vita;

        for (int i = 0; i < mazzo.length; i++) {

            mazzo[i] = new Carta();
        }
        for (int j = 0; j < 5; j++) {

            carteInMano[j] = mazzo[j];
        }
    }

    public void pescaCarta(Carta[] mazzo) {
        if (controllaMano() == true) {
            for (int i = 0; i < mazzo.length; i++) {
                if (mazzo[i] != null) {
                    for (int j = 0; j < carteInMano.length; j++) {
                        if (carteInMano[j] == null) {
                            carteInMano[j] = mazzo[i];
                            mazzo[i] = null;
                            System.out.println("la carta che hai pescato è " + carteInMano[i]);
                            return;
                        }
                    }
                }
            }
        }
    }

    public void evocaCarta(Carta[] carteSulCampo, Carta[] carteInMano) {
        if(controllaMano() == true ){
            
        }
    }

    public boolean controllaMano() {
        for (int i = 0; i < carteInMano.length; i++) {
            if (carteInMano == null) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Giocatore gg = new Giocatore(null, null, 0);
        System.out.println(Arrays.toString(mazzo));

    }

}
