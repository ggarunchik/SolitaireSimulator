package playingcards;

public class InvalidCardException extends Exception {
    public InvalidCardException() {
    }

    public InvalidCardException(String message) {
        super(message);
    }

    public InvalidCardException(Throwable cause) {
        super(cause);
    }

    public InvalidCardException(String message, Throwable cause) {
        super(message, cause);
    }
}
