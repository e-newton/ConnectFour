package exceptions;

public class FilledColumnException extends Exception {
    public FilledColumnException() {
        super("Selected Column is full");
    }

}
