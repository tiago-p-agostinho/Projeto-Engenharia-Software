package pt.ist.rest.service;

import pt.ist.rest.domain.Cliente;
import pt.ist.rest.domain.Rest;
import pt.ist.rest.exception.ArgumentosInvalidosException;
import pt.ist.rest.exception.ClientNotFoundException;
import pt.ist.rest.service.dto.ClienteDto;
import pt.ist.fenixframework.FenixFramework;

public class ApresentaDadosClienteService extends RestService{
	
	private ClienteDto userDto;
	
	public ApresentaDadosClienteService(ClienteDto userDto){
		this.userDto = userDto;
	}

	@Override
	public void dispatch() {
		Rest rest = FenixFramework.getRoot();
		
		final Cliente cliente = rest.procuraClientePorNome(userDto.getUser());
		
		if(cliente == null)
			throw new ClientNotFoundException(userDto.getUser());
		
		userDto = new ClienteDto(cliente.getUsername(),null,  cliente.getNome(), cliente.getMorada(), cliente.getEmail());
				
	}
	
	public ClienteDto getResult(){
		return this.userDto;
	}

}