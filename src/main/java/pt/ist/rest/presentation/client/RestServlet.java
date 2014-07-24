package pt.ist.rest.presentation.client;


import java.util.List;

import pt.ist.rest.exception.*;
import pt.ist.rest.service.dto.*;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("service")
public interface RestServlet extends RemoteService {
	
	void initServer(String serverType);
	
	void login(ClienteDto user) throws ArgumentosInvalidosException, ClientNotFoundException;
	
	List<RestauranteSimpleDto> listaRestaurantes();
	
	List<PratoDto> listaMenu(RestauranteSimpleDto r);
	
	void adicionaItem(ClienteDto c,PratoSimpleDto d, RestauranteSimpleDto r, int quuantidade) 
			throws ClientNotFoundException,RestaurantNotFoundException,DishNotFoundException;
	
	TabuleiroDto listaTabuleiro(ClienteDto c) throws EmptyShoppingTrayException;
	
	
	void adicionaCheques(ChequesDto cheques)
			throws pt.ist.rest.exception.InvalidCheckException, pt.ist.rest.exception.CheckAlreadyUsedException, ClientNotFoundException; 

	FaturaDto efectuaPagamento(ClienteDto cliente)
			throws NegativeBalanceException, EmptyShoppingTrayException, ClientNotFoundException,
					EmissorInexistenteException, ClienteInexistenteException, FaturaInvalidaException,
					TotaisIncoerentesException;
					
	PratosDto procuraPrato(PratoSimpleDto p);
	ClienteDto mostraDadosCliente(ClienteDto userDto);
}
