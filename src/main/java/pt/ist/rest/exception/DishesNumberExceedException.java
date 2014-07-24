package pt.ist.rest.exception;

public class DishesNumberExceedException extends RestException {
	
	private static final long serialVersionUID = 1L;

	private int dishesNumber;

	public DishesNumberExceedException(int dishesNumber) {
		this.dishesNumber = dishesNumber;
	}
	public DishesNumberExceedException() {}
	
	public int getDishesNumber() {
		return this.dishesNumber;
	}

	public String toString(){
		return "The max number of dishes in this restaurant is " + this.getDishesNumber() + "!";
	}
}
