package pt.ist.rest.exception;

public class BusyManagerException extends RestException {
	
	private static final long serialVersionUID = 1L;

	private String name;

	public BusyManagerException() {}
	
	public BusyManagerException(String name) {
		this.name = name;
	}
	
	public String getManagerName() {
		return this.name;
	}
	
	public String toString(){
		return "The Manager " + this.getManagerName() + " is busy at the moment. Try again later!" ;
	}

}
