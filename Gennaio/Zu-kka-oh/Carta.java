//FINITO

public class Carta {

    protected String nome;
    protected int hp;
    protected int atk;
    protected int def;
    Target target;

    public Carta() {
        this.nome = sceltaNome();
        this.hp = (int) (Math.random() * 50);
        this.atk = (int) (Math.random() * 50);
        this.def = (int) (Math.random() * 50);
        this.target = Target.randTarget();

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
        return nome + ": hp=" + hp + "atk=" + atk + "def=" + def;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getHp() {
        return this.hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAtk() {
        return this.atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getDef() {
        return this.def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public static String[] getPrefissi() {
        return prefissi;
    }

    public static void setPrefissi(String[] prefissi) {
        Carta.prefissi = prefissi;
    }

    public static String[] getSuffissi() {
        return suffissi;
    }

    public static void setSuffissi(String[] suffissi) {
        Carta.suffissi = suffissi;
    }

    // GET TARGET è NULL
    public Target getTarget() {
        return this.target;
    }

    public void setTarget(Target target) {
        this.target = target;
    }

}
