//SPERO FINITO
public class Giocatore {

    private int vita = 3;
    private static Carta[] mazzo;
    private Carta[] carteInMano;
    private Carta[] carteSulCampo = new Carta[5];
    private String nomeGiocatore;
    Target target;

    public Giocatore(String nomeGiocatore) {
        this.vita = 3;
        this.mazzo = new Carta[50];
        this.carteInMano = new Carta[10];
        this.carteSulCampo = new Carta[5];
        this.nomeGiocatore = nomeGiocatore;
        this.target = Target.randTarget();

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
    public Carta[] ordinaCarteSulCampo(Carta[] carteSulCampo) {
        Carta sostituto;                                                                    //variabile in più che prende posto momentaneo di arr[j+1] o arr[j]
        boolean sos = false;
        int n = carteSulCampo.length;
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
        return carteSulCampo;
    }

    public String getNomeGiocatore() {
        return nomeGiocatore;
    }

    public static Carta[] getMazzo() {
        return mazzo;
    }

    public Carta[] getCarteInMano() {
        return carteInMano;
    }

    public Carta[] getCarteSulCampo() {
        return carteSulCampo;
    }

    public int getVita() {
        return vita;
    }

    public void battlePhase(Carta[] carteSulCampo) {
        int min = 1050000;
        int max = 0;
        int idx = 0;
        int cont = 0;
        int danno = 0;

        for (int i = 0; i < carteSulCampo.length; i++) {
            this.carteSulCampo = ordinaCarteSulCampo(carteSulCampo);
            if (this.carteSulCampo[i] == null) {
                if (cont == 5) {
                    this.vita -= 1;
                }
            } else {
            }
            switch (this.getCarteSulCampo()[i].getTarget()) {
                /*case ATK_FORTE:
                    for (int j = 0; j < carteSulCampo.length; j++) {                                        //cerchiamo la carta attacco con valore maggiore
                        if (max < carteSulCampo[j].getDef()) {
                            max = carteSulCampo[j].getDef();                                                //cartasulcampo[j] diventa il nuovo max
                        }
                    }
                    danno = carteSulCampo[i].getDef() - carteSulCampo[i].getAtk();                          //calcoliamo il danno da infliggere
                    if (danno == 0) {                                                                       // se il danno è 0 mettiamo un danno minimo
                        danno = Extension.dannoMin();
                    }
                    carteSulCampo[idx].setHp(carteSulCampo[idx].getHp() - danno);                           // attacchiamo
                    if (carteSulCampo[idx].getHp() <= 0) {
                        carteSulCampo[idx] = null;
                    }
                    break;

                case ATK_DEBOLE:
                    for (int j = 0; j < carteSulCampo.length; j++) {                                        //cerchiamo la carta difesa con valore minore
                        if (min > carteSulCampo[j].getDef()) {
                            min = carteSulCampo[j].getDef();
                            idx = j;
                        }
                    }
                    danno = carteSulCampo[i].getDef() - carteSulCampo[i].getAtk();                          //calcoliamo il danno
                    if (danno == 0) {
                        danno = Extension.dannoMin();                                                       //attribuiamo il danno minimo
                    }
                    carteSulCampo[idx].setHp(carteSulCampo[idx].getHp() - danno);                           //infiggiamo del danno alla carta con def minimo
                    if (carteSulCampo[idx].getHp() <= 0) {                                                  // se la carta è di numero neg la togliamo 
                        carteSulCampo[idx] = null;
                    }

                    break;
                case DEF_FORTE:
                    for (int j = 0; j < carteSulCampo.length; j++) {
                        if (max < carteSulCampo[j].getDef()) {
                            max = carteSulCampo[j].getDef();
                        }
                    }
                    danno = carteSulCampo[i].getDef() - carteSulCampo[i].getAtk();
                    if (danno == 0) {
                        danno = Extension.dannoMin();
                    }
                    carteSulCampo[idx].setHp(carteSulCampo[idx].getHp() - danno);
                    if (carteSulCampo[idx].getHp() <= 0) {
                        carteSulCampo[idx] = null;
                    }
                    break;

                case DEF_DEBOLE:
                    for (int j = 0; j < carteSulCampo.length; j++) {
                        if (min > carteSulCampo[j].getDef()) {
                            min = carteSulCampo[j].getDef();
                            idx = j;
                        }
                    }
                    danno = carteSulCampo[i].getDef() - carteSulCampo[i].getAtk();
                    if (danno == 0) {
                        danno = Extension.dannoMin();
                    }
                    carteSulCampo[idx].setHp(carteSulCampo[idx].getHp() - danno);
                    if (carteSulCampo[idx].getHp() <= 0) {
                        carteSulCampo[idx] = null;
                    }

                    break;

                case HP_ALTO:
                    for (int j = 0; j < carteSulCampo.length; j++) {
                        if (max < carteSulCampo[j].getDef()) {
                            max = carteSulCampo[j].getDef();
                        }
                    }
                    danno = carteSulCampo[i].getDef() - carteSulCampo[i].getAtk();
                    if (danno == 0) {
                        danno = Extension.dannoMin();
                    }
                    carteSulCampo[idx].setHp(carteSulCampo[idx].getHp() - danno);
                    if (carteSulCampo[idx].getHp() <= 0) {
                        carteSulCampo[idx] = null;
                    }
                    break;

                case HP_BASSO:
                    for (int j = 0; j < carteSulCampo.length; j++) {
                        if (min > carteSulCampo[j].getDef()) {
                            min = carteSulCampo[j].getDef();
                            idx = j;
                        }
                    }
                    danno = carteSulCampo[i].getDef() - carteSulCampo[i].getAtk();
                    if (danno == 0) {
                        danno = Extension.dannoMin();
                    }
                    carteSulCampo[idx].setHp(carteSulCampo[idx].getHp() - danno);
                    if (carteSulCampo[idx].getHp() <= 0) {
                        carteSulCampo[idx] = null;
                    }

                    break; */
                case ATK_FORTE:

                    danno = carteSulCampo[i].getDef() - carteSulCampo[i].getAtk();                          //calcoliamo il danno da infliggere
                    if (danno == 0) {                                                                       // se il danno è 0 mettiamo un danno minimo
                        danno = Extension.dannoMin();
                    }

                    for (int j = 0; j < carteSulCampo.length; j++) {                                        //cerchiamo la carta attacco con valore maggiore
                        if (carteSulCampo[j] != null && max < carteSulCampo[j].getAtk()) {
                            max = carteSulCampo[j].getAtk();                                                //cartasulcampo[j] diventa il nuovo max
                            idx = j;
                        }
                    }

                    carteSulCampo[idx].setHp(carteSulCampo[idx].getHp() - danno);                           // attacchiamo
                    if (carteSulCampo[idx].getHp() <= 0) {
                        carteSulCampo[idx] = null;
                    }

                    break;

                case ATK_DEBOLE:

                    danno = carteSulCampo[i].getDef() - carteSulCampo[i].getAtk();                          //calcoliamo il danno
                    if (danno == 0) {
                        danno = Extension.dannoMin();                                                       //attribuiamo il danno minimo
                    }

                    for (int j = 0; j < carteSulCampo.length; j++) {                                        //cerchiamo la carta difesa con valore minore
                        if (carteSulCampo[j] != null && min > carteSulCampo[j].getAtk()) {
                            min = carteSulCampo[j].getAtk();
                            idx = j;
                        }
                    }

                    carteSulCampo[idx].setHp(carteSulCampo[idx].getHp() - danno);                           //infiggiamo del danno alla carta con def minimo
                    if (carteSulCampo[idx].getHp() <= 0) {                                                  // se la carta è di numero neg la togliamo 
                        carteSulCampo[idx] = null;
                    }

                    break;

                case DEF_FORTE:

                    danno = carteSulCampo[i].getDef() - carteSulCampo[i].getAtk();                          //calcoliamo il danno da infliggere
                    if (danno == 0) {                                                                       // se il danno è 0 mettiamo un danno minimo
                        danno = Extension.dannoMin();
                    }

                    for (int j = 0; j < carteSulCampo.length; j++) {                                        //cerchiamo la carta attacco con valore maggiore
                        if (carteSulCampo[j] != null && max < carteSulCampo[j].getAtk()) {
                            max = carteSulCampo[j].getAtk();                                                //cartasulcampo[j] diventa il nuovo max
                            idx = j;
                        }
                    }

                    carteSulCampo[idx].setHp(carteSulCampo[idx].getHp() - danno);                           // attacchiamo
                    if (carteSulCampo[idx].getHp() <= 0) {
                        carteSulCampo[idx] = null;
                    }

                    break;

                case DEF_DEBOLE:

                    danno = carteSulCampo[i].getDef() - carteSulCampo[i].getAtk();                          //calcoliamo il danno
                    if (danno == 0) {
                        danno = Extension.dannoMin();                                                       //attribuiamo il danno minimo
                    }

                    for (int j = 0; j < carteSulCampo.length; j++) {                                        //cerchiamo la carta difesa con valore minore
                        if (carteSulCampo[j] != null && min > carteSulCampo[j].getAtk()) {
                            min = carteSulCampo[j].getAtk();
                            idx = j;
                        }
                    }

                    carteSulCampo[idx].setHp(carteSulCampo[idx].getHp() - danno);                           //infiggiamo del danno alla carta con def minimo
                    if (carteSulCampo[idx].getHp() <= 0) {                                                  // se la carta è di numero neg la togliamo 
                        carteSulCampo[idx] = null;
                    }

                    break;
                case HP_ALTO:

                    danno = carteSulCampo[i].getDef() - carteSulCampo[i].getAtk();                          //calcoliamo il danno da infliggere
                    if (danno == 0) {                                                                       // se il danno è 0 mettiamo un danno minimo
                        danno = Extension.dannoMin();
                    }

                    for (int j = 0; j < carteSulCampo.length; j++) {                                        //cerchiamo la carta attacco con valore maggiore
                        if (carteSulCampo[j] != null && max < carteSulCampo[j].getAtk()) {
                            max = carteSulCampo[j].getAtk();                                                //cartasulcampo[j] diventa il nuovo max
                            idx = j;
                        }
                    }

                    carteSulCampo[idx].setHp(carteSulCampo[idx].getHp() - danno);                           // attacchiamo
                    if (carteSulCampo[idx].getHp() <= 0) {
                        carteSulCampo[idx] = null;
                    }

                    break;

                case HP_BASSO:

                    danno = carteSulCampo[i].getDef() - carteSulCampo[i].getAtk();                          //calcoliamo il danno
                    if (danno == 0) {
                        danno = Extension.dannoMin();                                                       //attribuiamo il danno minimo
                    }

                    for (int j = 0; j < carteSulCampo.length; j++) {                                        //cerchiamo la carta difesa con valore minore
                        if (carteSulCampo[j] != null && min > carteSulCampo[j].getAtk()) {
                            min = carteSulCampo[j].getAtk();
                            idx = j;
                        }
                    }

                    carteSulCampo[idx].setHp(carteSulCampo[idx].getHp() - danno);                           //infiggiamo del danno alla carta con def minimo
                    if (carteSulCampo[idx].getHp() <= 0) {                                                  // se la carta è di numero neg la togliamo 
                        carteSulCampo[idx] = null;
                    }

                    break;

            }
        }

    }

}
