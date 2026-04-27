
public class Pedone extends Movements {

    Scacchiera scacchi = new Scacchiera(0, 0);

    @Override
    public void muovi(char direzione, int caselle) throws MoviementoNonValidoException { // caselle = di quanto si sposta 
        if (direzione != 'w' || direzione != 's' || direzione != 'a' || direzione != 'd') {
            throw new MoviementoNonValidoException("direzione non valida ");
        }
        switch (direzione) {
            case 'w':
                if (posizione[0] + caselle > 0 && this.posizione[0] < scacchi.rig) {
                    posizione[0] += caselle;
                }
                break;
            case 'a':
                if (posizione[0] + caselle > 0 && this.posizione[0] < scacchi.rig) {
                    posizione[0] += caselle;
                }
                break;
            case 's':
                if (posizione[0] + caselle > 0 && this.posizione[0] < scacchi.rig) {
                    posizione[0] += caselle;
                }
                break;
            case 'd':
                if (posizione[0] + caselle > 0 && this.posizione[0] < scacchi.rig) {
                    posizione[0] += caselle;
                }
            default:
                break;
        }
    }

}
