package pt.ist.rest.exception;

public abstract class RestException extends RuntimeException implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	public RestException(String s) {super(s);}
	public RestException() {}

}
