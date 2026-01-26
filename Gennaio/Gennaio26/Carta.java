
public class Carta {

    private String nome = "";
    private int hp;
    private int atk;
    private int def;

    public Carta() {
        this.nome = sceltaNome();
        this.hp = (int) (Math.random() * 50);
        this.atk = (int) (Math.random() * 50);
        this.def = (int) (Math.random() * 50);
    }

    private static String[] prefissi = {
        "Drago", "Lupo", "Tigre", "Serpente", "Aquila", "Golem", "Spirito", "Ombra",
        "Fuoco", "Ghiaccio", "Tuono", "Terra", "Vento", "Luce", "Oscuro"
    };
    private static String[] suffissi = {
        "Antico", "Furioso", "Mistico", "Selvaggio", "Celeste", "Infernale", "Glaciale",
        "Tonante", "Sacro", "Maledetto", "Supremo", "Eterno", "Fatale", "Divino", "Spettrale"
    };

    public static String sceltaNome() {
        int nPrefissi = (int) (Math.random() * prefissi.length);
        int nSuffissi = (int) (Math.random() * suffissi.length);
        return prefissi[nPrefissi] + " " + suffissi[nSuffissi];
    }

    public int getPuntiTotali() {
        int puntiTotali = hp + atk + def;
        return puntiTotali;
    }

    @Override
    public String toString() {
        return nome + ": hp=" + hp + "atk=" + atk + "def=" + def ;
    }
    
}
