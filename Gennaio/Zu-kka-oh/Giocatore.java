//SPERO FINITO

public class Giocatore {

    protected String nomeGiocatore;
    private int vita;
    private Carta[] mazzo;
    private Carta[] carteInMano;
    private Carta[] carteSulCampo = new Carta[5];
    Target target;

    public Giocatore(String nomeGiocatore) {
        this.nomeGiocatore = nomeGiocatore;
        this.vita = 3;
        this.mazzo = new Carta[50];
        this.carteInMano = new Carta[10];
        this.carteSulCampo = new Carta[5];
        this.target = Target.randTarget();

        for (int i = 0; i < mazzo.length; i++) {
            mazzo[i] = new Carta();
        }
        for (int j = 0; j < 5; j++) {
            pescaCarta();
        }
    }

    // metodo che pesca una carta dal mazzo 
    public void pescaCarta() {
        if (controllaMano() < 10) {
            for (int i = 0; i < mazzo.length; i++) {
                if (mazzo[i] != null) {
                    for (int j = 0; j < carteInMano.length; j++) {
                        if (carteInMano[j] == null) {
                            carteInMano[j] = mazzo[i];
                            mazzo[i] = null;
                            System.out.println("la carta che hai pescato è " + carteInMano[j]);
                            return;
                        }
                    }
                }
            }
        }
    }
    //evoca carts ==> prendere una carta dalla mano e metterla sul campo da gioco 

    public void evocaCarta() {
        if (controllaMano() > 0) { // controlla mano restituisce un int di quante carte abbiamo in mano
            for (int i = 0; i < carteInMano.length; i++) {
                if (carteInMano[i] != null) { // se c'è la carta con idx i
                    for (int j = 0; j < carteSulCampo.length; j++) { //guardo tutto  carte sul campo
                        if (carteSulCampo[j] == null) {  // se c'è uno spazio vuoto
                            carteSulCampo[j] = carteInMano[i]; //lo assegno
                            carteInMano[i] = null;
                            break;  // serve perchè se ho tutto l'array vuoto potrebbe evocare la carta più volte
                        }
                    }
                }
            }
        }
    }
// controlliamo se abbiamo carte in mano 

    public int controllaMano() {
        int contaCarte = 0;
        for (int i = 0; i < carteInMano.length; i++) {
            if (carteInMano[i] != null) {
                contaCarte++;
            }
        }
        return contaCarte;  // se è false la mano è vuota
    }

    //ordiniamo le carte sul campo per punti 
    public Carta[] ordinaCarteSulCampo() { // Bubble sort
        Carta sostituto;
        boolean sos;
        int n = this.carteSulCampo.length;

        for (int i = 0; i < n - 1; i++) {
            sos = false;     // serve per dire che lo scambio tra carte è avvenuto così il ciclo sa se ha ordinato tutte le carte o meno
            for (int j = 0; j < n - 1; j++) {
                if (this.carteSulCampo[j] != null && this.carteSulCampo[j + 1] != null) { // mi dava quasi sempre null perciò ho aggiunto controlli
                    if (this.carteSulCampo[j].getPuntiTotali() > this.carteSulCampo[j + 1].getPuntiTotali()) {
                        sostituto = this.carteSulCampo[j];
                        this.carteSulCampo[j] = this.carteSulCampo[j + 1];
                        this.carteSulCampo[j + 1] = sostituto;
                        sos = true;
                    }
                }
            }
            if (!sos) {
                break;
            }
        }
        return this.carteSulCampo;
    }

    public String getNomeGiocatore() {
        return this.nomeGiocatore;
    }

    public Carta[] getMazzo() {
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

    public void battlePhase(String nome) {
        int min = 1050000;
        int max = 0;
        int idx = 0;
        int cont = 0;
        int danno = 0;

        for (int i = 0; i < carteSulCampo.length; i++) {
            this.carteSulCampo = ordinaCarteSulCampo();
            cont++;
            if (this.carteSulCampo[i] == null  || carteSulCampo[i] == null) {
                evocaCarta();
                if (cont == 5) {
                    this.vita -= 1;
                }
            }

            for (int j = 0; j < carteSulCampo.length; j++) {
                if (carteSulCampo[j] != null) {
                    Target t = carteSulCampo[j].getTarget();
                    if (t != null) {
                        this.getCarteSulCampo()[j].getTarget();
                    } else {
                        System.out.println("sono nulla");
                    }
                }
            }
            System.out.println("indice " + i + " lunghezza array carte sul campo " + this.getCarteSulCampo().length);
            if (this.getCarteSulCampo()[i].getTarget() != null && carteSulCampo[i] != null || this.carteSulCampo[i] == null) {
                System.out.println("SONO NULL");
                System.out.println("CARTE SUL CAMPO " + this.getCarteSulCampo()[i].getTarget());
                switch (this.getCarteSulCampo()[i].getTarget()) {
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

}
