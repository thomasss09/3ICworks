
import java.util.Random;

public class EggGame {

    private int pianoCritico;
    private int numeroPiani;
    private int contatoreNlanci;
    private boolean soluzioneRivelata;

    public EggGame(int numeroPiani) {
        this.numeroPiani = numeroPiani;
        Random random = new Random();
        this.pianoCritico = random.nextInt(numeroPiani + 1); // tra 0 e numeroPiani inclusi
        this.contatoreNlanci = 0;
        this.soluzioneRivelata = false;
    }

    public String lanciaUovo(int piano) {
        if (piano > numeroPiani || piano < 0 || soluzioneRivelata) {
            return "ERRORE";
        }
        contatoreNlanci++;
        if (piano <= pianoCritico) {
            return "SAFE";
        }

        return "SPLAT";
    }

    public int getContatoreNlanci() {
        return contatoreNlanci;
    }

    public void resetContatore() {
        contatoreNlanci = 0;
    }

    // Metodo solo per verifica (non usare durante la ricerca, i prossimi lanci daranno 'E')
    public int getPianoCritico() {
        soluzioneRivelata = true;
        return pianoCritico;
    }
}
