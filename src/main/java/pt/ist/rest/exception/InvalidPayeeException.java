package pt.ist.rest.exception;

public class InvalidPayeeException extends Exception {
    public InvalidPayeeException(String payee) {
	super("Invalid payee : " + payee);
    }
}
