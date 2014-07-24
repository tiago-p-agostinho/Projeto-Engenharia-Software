package pt.ist.rest.exception;

public class ManagerHasNoAccessException extends RestException{
	private static final long serialVersionUID = 1L;
	
	private String managerUsername;
	
	public  ManagerHasNoAccessException (String managerUsername){
		this.managerUsername=managerUsername;
	}
	public  ManagerHasNoAccessException() {}
	
	public String getManagerUsername(){
		return this.managerUsername;
	}

	public String toString(){
		return "The manager with username: "+managerUsername+" has no access to this restaurant,";
	}
	
}
