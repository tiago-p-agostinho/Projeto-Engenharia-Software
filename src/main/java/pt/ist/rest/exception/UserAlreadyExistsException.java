package pt.ist.rest.exception;

public class UserAlreadyExistsException extends RestException{
	private static final long serialVersionUID = 1L;
	private String userName;
	
	public UserAlreadyExistsException(String userName){
		this.userName=userName;
	}
	public UserAlreadyExistsException() {}
	
	public String getUserName(){
		return this.userName;
	}

}
