package pt.ist.rest.exception;

public class CheckAlreadyUsedException extends RestException {
    public CheckAlreadyUsedException(String checkNumber) {
	super( checkNumber);
    }
    public CheckAlreadyUsedException() {}
}
