
import java.util.Arrays;

public class Giocatore {

    private String nomeGiocatore = "";
    private static Carta[] mazzo = new Carta[50];
    private Carta[] carteInMano = new Carta[10];
    private Carta[] carteSulCampo = new Carta[5];
    private int vita = 3;   //se subisci 3 attacchi diretti hai perso

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

    // metodo che pesca una carta dal mazzo 
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
    //evoca carts ==> prendere una carta dalla mano e metterla sul campo da gioco 

    public void evocaCarta(Carta[] carteSulCampo, Carta[] carteInMano) {
        if (controllaMano() == true) {
            for (int i = 0; i < carteInMano.length; i++) {
                for (int j = 0; j < carteSulCampo.length; j++) {
                    if (carteSulCampo[j] == null) {
                        carteSulCampo[j] = carteInMano[i];
                        carteInMano[i] = null;
                    }
                }
            }
        }
    }
// controlliamo se abbiamo carte in mano 

    public boolean controllaMano() {
        for (int i = 0; i < carteInMano.length; i++) {
            if (carteInMano == null) {
                return true;
            }
        }
        return false;
    }

    //ordiniamo le carte sul campo per punti 
    public void ordinaCarteSulCampo(Carta[] carteSulCampo, int n) {
        Carta sostituto; //variabile in più che prende posto momentaneo di arr[j+1] o arr[j]
        boolean sos = false;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1; j++) {
                if (carteSulCampo[j].getPuntiTotali() > carteSulCampo[j + 1].getPuntiTotali()) {
                    sostituto = carteSulCampo[j];
                    carteSulCampo[j] = carteSulCampo[j + 1];
                    carteSulCampo[j + 1] = sostituto;
                    sos = true;
                }
            }
            if (sos == false) {
                break;
            }

        }
        System.out.println(Arrays.toString(carteSulCampo));
    }

    public void attacco(Giocatore Attaccante, Giocatore Bersaglio) {
        Carta A = new Carta();
        Carta B = new Carta();

    }
//1. Ordina le carte sul campo di A per punti totali crescenti  

    public void battlePhase(Giocatore A) {

    }

}
