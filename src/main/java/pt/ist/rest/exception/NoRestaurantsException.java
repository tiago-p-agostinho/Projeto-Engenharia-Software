package pt.ist.rest.exception;

public class NoRestaurantsException extends RestException {
	private static final long serialVersionUID = 1L;
	
	public String toString(){
		return "No restaurants found";
	}
}
