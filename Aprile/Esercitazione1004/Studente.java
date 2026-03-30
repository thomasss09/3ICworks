
import java.util.ArrayList;
import java.util.Arrays;

public class Studente {

    private final String nome = "";
    private String classe;
    private ArrayList<Double> voti = new ArrayList<>();
    private ArrayList<Double> accettabili = new ArrayList<>(Arrays.asList(0.00, 0.25, 0.50, 0.75, 0.90));

    public Studente(String classe, ArrayList<Double> voti) {
        this.classe = classe;
        this.voti = voti;
    }

    public void aggiungiVoto(double voto) {  //0.00, 0.25, 0.50, 0.75, 0.90

        if (voto < 0) {
            throw new VotoNonValido("voto non valido troppo basso");
        }
        if (voto > 10) {
            throw new VotoNonValido("voto non valido troppo alto");
        }
        if (!accettabili.contains(voto - Math.floor(voto))) {
            throw new VotoNonValido("voto non valido");
        }
        voti.add(voto);
        System.out.println("vot aggiunto con successo");
    }

    public void registraVotoString(String stringaVoto) {
        if (!Character.isDigit(stringaVoto.charAt(0))) {
            throw new FormatoVotoStringaErrato(" non si usano le lettere dislessico");
        }
        if (stringaVoto.length() > 3) {
            throw new FormatoVotoStringaErrato("a scuola non prendi 600 gang");
        }
        if (stringaVoto.charAt(0) == '1' && stringaVoto.charAt(1) == '0') {
            if (stringaVoto.length() == 2) {
                aggiungiVoto(10);
            } else if (stringaVoto.charAt(stringaVoto.length()) == '-') {
                aggiungiVoto(9.90);
            } else {
                throw new FormatoVotoStringaErrato();
            }
            if (stringaVoto.charAt(stringaVoto.length()) == '+') {
                Double.parseDouble("" + stringaVoto.charAt(0));
            }
            if (stringaVoto.charAt(stringaVoto.length()) == '-') {
                Double.parseDouble("" + stringaVoto.charAt(0));
            }
            if (stringaVoto.charAt(stringaVoto.length()) == '1⁄2') {
                Double.parseDouble("" + stringaVoto.charAt(0));
            }
            if (stringaVoto.charAt(stringaVoto.length()) == '/') {
                Double.parseDouble("" + stringaVoto.charAt(0));
            }
        }
    }
}
