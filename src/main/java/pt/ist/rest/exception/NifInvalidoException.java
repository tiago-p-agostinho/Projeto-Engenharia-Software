package pt.ist.rest.exception;

public class NifInvalidoException extends RestException{

private static final long serialVersionUID = 1L;
private String user;	

	public NifInvalidoException(String user){
		this.user = user;
	}
	public NifInvalidoException() {}
	
	public String toString(){
		return "Nif Invalido do " + user;
	}
}
