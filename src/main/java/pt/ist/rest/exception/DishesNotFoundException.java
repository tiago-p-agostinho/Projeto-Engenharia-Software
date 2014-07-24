package pt.ist.rest.exception;

/**
 * Quanto nao for encontrado nenhum prato em todos os restaurantes com
 * o identificador fornecido.
 * 
 *	@see pt.ist.rest.service.ProcuraPratoPorTipoService
 *	@see pt.ist.rest.service.ProcuraPratoService
 */

public class DishesNotFoundException extends RestException {
	
	private static final long serialVersionUID = 1L;
	
	private String identifier;

	public DishesNotFoundException(String id){
		this.identifier = id;
	}
	public DishesNotFoundException() {}
	public String getIdentifier(){
		return this.identifier;
	}
	
	public String toString(){
		return "No '"+this.getIdentifier()+"' dishes found.";
	}
}
