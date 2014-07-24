package pt.ist.rest.exception;

public class InvalidCheckException extends RestException {
    public InvalidCheckException(String checkNumber) {
	super(checkNumber);
    }
    public InvalidCheckException () {}
}
