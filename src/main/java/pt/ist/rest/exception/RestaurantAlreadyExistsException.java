package pt.ist.rest.exception;

public class RestaurantAlreadyExistsException extends RestException{
	private static final long serialVersionUID = 1L;
	private String restaurantName;
	
	public RestaurantAlreadyExistsException(String restaurantName){
		this.restaurantName=restaurantName;
	}
	public RestaurantAlreadyExistsException() {}

	public String getRestaurantName(){
		return this.restaurantName;
	}
	@Override
	public String toString(){
		return "The restaurant with name: "+getRestaurantName()+" already exists";
	}
}
