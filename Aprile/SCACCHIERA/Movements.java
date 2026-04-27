public abstract class Movements {
protected int posAttuale;
protected int posPrec;
int[] posizione = new int[2];
public abstract void muovi(char direzione , int caselle)throws MoviementoNonValidoException;
}
