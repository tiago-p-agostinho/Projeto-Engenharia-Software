package pt.ist.rest.exception;

public class ManagerNotFoundException extends RestException{
	
	private static final long serialVersionUID = 1L;
	private String username;
	private String restaurantName;
	
	public ManagerNotFoundException (String username,String restaurantName){
		this.username=username;
		this.restaurantName = restaurantName;
	}
	public ManagerNotFoundException(String username){
		this.username=username;
		this.restaurantName = null;
	}
	public ManagerNotFoundException() {}

	public String getManagerUsername(){
		return this.username;
	}
	public String getRestaurantName(){
		return this.restaurantName;
	}

	public String toString(){
		final boolean isRestaurantSpecific = (restaurantName != null); 
		if (isRestaurantSpecific)
			return "The manager with username: "+this.getManagerUsername() 
								+" was not found in restaurant: "+getRestaurantName();
		return "The manager with username: "+this.getManagerUsername() 
								+" was not found";
	}
	
}
