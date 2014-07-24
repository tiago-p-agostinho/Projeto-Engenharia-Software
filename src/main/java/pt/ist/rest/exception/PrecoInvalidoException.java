package pt.ist.rest.exception;

public class PrecoInvalidoException extends RestException {

	private static final long serialVersionUID = 1L;
	
	public String toString(){
		return "Preco acima do limite";
	}


}
