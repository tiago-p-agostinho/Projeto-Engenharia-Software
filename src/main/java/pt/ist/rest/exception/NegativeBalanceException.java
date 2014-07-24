package pt.ist.rest.exception;

public class NegativeBalanceException extends RestException{

	private static final long serialVersionUID = 1L;

	private int balance;
	
	public NegativeBalanceException(int currentBalance){
		this.balance = currentBalance;
	}
	public NegativeBalanceException() {}
	
	public int getBalance(){
		return this.balance;
	}
	
	public String toString(){
		return "The client only has "+this.getBalance()+" in his account." ;
	}
}
