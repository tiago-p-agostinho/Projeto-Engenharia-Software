package pt.ist.rest.service;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.rest.exception.*;
import pt.ist.rest.domain.*;
import pt.ist.rest.service.dto.ClienteDto;


public class RegistaPagamentoTabuleiroComprasService extends RestService {

	final private ClienteDto clienteDto;

	public RegistaPagamentoTabuleiroComprasService(ClienteDto cliente) {
		   this.clienteDto = cliente;
	}

	public final void dispatch() throws ClientNotFoundException, 
										NegativeBalanceException, 
										EmptyShoppingTrayException{

		Rest rest = FenixFramework.getRoot();

		final Cliente cliente = rest.procuraClientePorNome(this.clienteDto.getUser());
		cliente.confirmaCompra();
	}

}

		


	
	





