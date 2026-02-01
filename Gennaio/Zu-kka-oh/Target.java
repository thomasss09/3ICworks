//FINITO

public enum Target {
    ATK_FORTE, ATK_DEBOLE, DEF_FORTE, DEF_DEBOLE, HP_ALTO, HP_BASSO;

    public static Target randTarget() {
        int rand = (int) (Math.random() * Target.values().length);
        return Target.values()[rand];
    }
}
