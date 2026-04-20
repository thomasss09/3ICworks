package ProvaVerificaAlgoritmi;

public class IndiciNonValidiException extends RuntimeException {
    public IndiciNonValidiException(String messaggio){
        super(messaggio);
    }

    public IndiciNonValidiException(String message, Throwable cause) {
        super(message, cause);
    }
}