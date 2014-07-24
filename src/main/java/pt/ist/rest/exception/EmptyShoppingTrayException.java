package pt.ist.rest.exception;

public class EmptyShoppingTrayException extends RestException{

	private static final long serialVersionUID = 1L;
	
	public String toString(){
		return "The shopping tray has no items in it.";
	}
}
