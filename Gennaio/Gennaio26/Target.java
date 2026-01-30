
public enum Target {
    ATK_FORTE, ATK_DEBOLE, DEF_FORTE, DEF_DEBOLE, HP_ALTO, HP_BASSO;

    public static Target creaTarget() {
        int rand = (int) (Math.random() * (6 + 0) - 0);
        return  Target.values()[rand];
    }
}
