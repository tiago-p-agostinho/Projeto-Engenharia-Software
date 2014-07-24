package pt.ist.rest.exception;

public class ClientAlreadyExistsException extends RestException{
	
	private static final long serialVersionUID = 1L;
	private String clientName;
	
	public ClientAlreadyExistsException (String clientName){
		this.clientName=clientName;
		
	}
	public ClientAlreadyExistsException() {}
	
	public String getClientName(){
		return this.clientName;
	}

	public String toString(){
		return "The client username: " + this.getClientName() +  " is already taken";
	}
	
}
