package pt.ist.rest.exception;

public class ArgumentosInvalidosException extends RestException  {

private static final long serialVersionUID = 1L;
	
	public String toString(){
		return "Password incorrecta, escreva outra vez";
	}
}
