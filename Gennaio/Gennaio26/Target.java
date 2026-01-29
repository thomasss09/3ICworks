
public enum Target {
    ATK_FORTE, ATK_DEBOLE, DEF_FORTE, DEF_DEBOLE, HP_ALTO, HP_BASSO;

    public static int creaTarget() {
        int rand = (int) (Math.random() * (6 + 0) - 0);
        Target random = Target.values()[rand];
         
        Target s = Target.values()[rand];
        /*switch (random) {
            /*case : {
                random = ATK_FORTE;
                break;
            }

            case ATK_DEBOLE:
                break;

            case DEF_FORTE:
                break;

            case DEF_DEBOLE:
                break;

            case HP_ALTO:
                break;

            case HP_BASSO:
                break;

            default:
                throw new IllegalStateException("Stato non valido: " + rand);
        }*/

        System.out.println("il Target scelto è ");
        return 0;
    }
}
