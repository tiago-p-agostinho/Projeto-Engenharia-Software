package pt.ist.rest.exception;

public class ClientNotFoundException extends RestException{
	private static final long serialVersionUID = 1L;
	
	private  String clientName;
	
	public ClientNotFoundException (String clientName){
		this.clientName=clientName;
	}
	public ClientNotFoundException () {}

	public String getClientName(){
		return this.clientName;
	}

	public String toString(){
		return "The client with username: " + this.getClientName() +  " was not found";
	}
	
}
