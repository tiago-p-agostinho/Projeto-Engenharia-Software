package pt.ist.rest.service;


import jvstm.Atomic;

public abstract class RestService{

	@Atomic
	public void execute() { 
		dispatch();
	}
	
	public abstract void dispatch();
}