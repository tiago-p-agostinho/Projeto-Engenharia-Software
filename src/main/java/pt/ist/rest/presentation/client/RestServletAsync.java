package pt.ist.rest.presentation.client;

import java.util.List;
import pt.ist.rest.exception.*;
import pt.ist.rest.service.dto.*;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface RestServletAsync {
	
	void initServer(String serverType, AsyncCallback<Void> callback);
	 
	void login(ClienteDto user, AsyncCallback<Void> callback)  throws ArgumentosInvalidosException, ClientNotFoundException;
	 
	void listaRestaurantes(AsyncCallback<List<RestauranteSimpleDto>> callback);
	 
	void listaMenu(RestauranteSimpleDto r, AsyncCallback<List<PratoDto>> callback);
	 
	void adicionaItem(ClienteDto c,PratoSimpleDto d, RestauranteSimpleDto r, int quantidade,AsyncCallback<Void> callback) 
			throws ClientNotFoundException,RestaurantNotFoundException,DishNotFoundException;
	 
	void listaTabuleiro(ClienteDto c, AsyncCallback<TabuleiroDto> callback)
			throws EmptyShoppingTrayException;
	
	void adicionaCheques(ChequesDto cheques,AsyncCallback<Void> callback)
			throws pt.ist.rest.exception.InvalidCheckException ,pt.ist.rest.exception.CheckAlreadyUsedException, ClientNotFoundException; 

	void efectuaPagamento(ClienteDto cliente, AsyncCallback<FaturaDto> callback)
			throws NegativeBalanceException, EmptyShoppingTrayException, ClientNotFoundException,
					EmissorInexistenteException, ClienteInexistenteException, FaturaInvalidaException,
					TotaisIncoerentesException;

	void procuraPrato(PratoSimpleDto p, AsyncCallback<PratosDto> asyncCallback);		
	void mostraDadosCliente(ClienteDto userDto, AsyncCallback<ClienteDto> callback); 
}	
