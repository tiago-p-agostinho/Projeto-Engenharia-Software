package pt.ist.rest.exception;

public class RestaurantHasNoDishesException extends RestException{
	private static final long serialVersionUID = 1L;
	
	private String restaurantName;
	
	public RestaurantHasNoDishesException(String restaurantName){ 
		this.restaurantName = restaurantName;
	}
	public RestaurantHasNoDishesException() {}

	public String getRestaurantName(){
		return this.restaurantName;
	}

	public String toString(){
		return "The restaurant: " + this.getRestaurantName() + " has no dishes.";
	}
	
}
