package pt.ist.rest.service;



import pt.ist.rest.domain.Cliente;
import pt.ist.rest.domain.Rest;
import pt.ist.rest.exception.ClientNotFoundException;
import pt.ist.rest.exception.ArgumentosInvalidosException;
import pt.ist.rest.service.dto.ClienteDto;
import pt.ist.fenixframework.FenixFramework;


public class VerificaPassClienteService extends RestService {
	
	private ClienteDto userDto;
	
	public VerificaPassClienteService(ClienteDto userDto){
		this.userDto = userDto;
	}

	public final void dispatch() throws ClientNotFoundException, ArgumentosInvalidosException{
		
		Rest rest = FenixFramework.getRoot();
		
	final Cliente cliente = rest.procuraClientePorNome(userDto.getUser());
	
	if(cliente == null)
		throw new ClientNotFoundException(userDto.getUser());
	
	if(!(cliente.getPassword().equals(userDto.getPass()))){
	   	throw new ArgumentosInvalidosException();
	}
  }
}
