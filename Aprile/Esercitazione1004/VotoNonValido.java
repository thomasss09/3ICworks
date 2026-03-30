
public class VotoNonValido extends RuntimeException {

    public VotoNonValido(String message) {
        super(message);
    }

    public VotoNonValido(String message, Throwable cause) {
        super(message, cause);
    }
}
