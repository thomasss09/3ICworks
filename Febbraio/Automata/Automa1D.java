
public class Automa1D {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    private static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    private String stringaIni;
    public String strisciaString = "";
    protected int regola = 0;

    public Automa1D(int larghezza, int regola) {
        this.stringaIni = creaStriscia(larghezza);
        this.regola = regola;
    }

    private String creaStriscia(int larghezza) {  // striscia iniziale //00001000
        String strisciaIni = "";
        for (int i = 0; i < larghezza; i++) {
            if (i == larghezza / 2) {
                strisciaIni += "1";
            } else {
                strisciaIni += 0;
            }
        }
        return strisciaIni;
    }

    public String calcolaStato(char sinistra, char centro, char destra) {
        /*che regola vuoi 110 => 110 in binario 01101110 poi prendo il num e lo assegno alle triplette */
        String[] celle = {"111", "110", "101", "100", "011", "010", "001", "000"};
        String tripletta = "" + sinistra + centro + destra;
        String string = Integer.toBinaryString(this.regola);    // porto la regola in binario
        while (string.length() < 8) {                           // raggiungo gli 8 bit
            string = "0" + string;                             // aggiungo 0 davanti la stringa
        }
        for (int i = 0; i < celle.length; i++) {               // guardo tutte le combinazioni
            if (tripletta.equals(celle[i])) {                  // guardo se qualcuna è uguale alla nostra tripletta
                strisciaString = "" + string.charAt(i);    // se fosse così come trovo la tripletta giusta leggo il numero binario della regola e lo aggiung a strisciaIni
                break;                                         // come troviamo il bit giusto esco con break (1-0)
            }
        }
        return strisciaString;
    }
    // calcolo lo stato in next stato  110 ==> 01101111 guardo la cella 111 calcolo lo stato e poi faccio anche per 110 e così via fino a 000

    public void nextStato() {
        String nuovoStato = "";
        stringaIni = "0" + stringaIni + "0";
        for (int i = 1; i < stringaIni.length() - 1; i++) {
            nuovoStato = nuovoStato + calcolaStato(stringaIni.charAt(i - 1), stringaIni.charAt(i), stringaIni.charAt(i + 1));  // stringaIni.charAt(i - 1) valore sx prima di i
        }
        stringaIni = nuovoStato;
    }

    public void stampa() throws InterruptedException {
        for (int i = 0; i < stringaIni.length(); i++) {
            System.out.print(stringaIni.charAt(i) == '1' ? ANSI_BLACK_BACKGROUND + " " + ANSI_RESET : ANSI_GREEN_BACKGROUND + " " + ANSI_RESET);
            //fa il delay
        }
        System.out.println();
        Thread.sleep(500);
    }

    public static void main(String[] args) throws InterruptedException {
        Automa1D ac = new Automa1D(170, 210);
        for (int i = 0; i < 1000; i++) {
            ac.stampa();
            ac.nextStato();
        }
    }
}
