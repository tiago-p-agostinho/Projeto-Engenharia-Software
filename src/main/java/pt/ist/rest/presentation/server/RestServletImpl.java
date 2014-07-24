package pt.ist.rest.presentation.server;


import java.util.List;

import pt.ist.registofatura.*;
import pt.ist.registofatura.ws.*;
import pt.ist.chequerefeicao.ChequeRefeicao;
import pt.ist.chequerefeicao.ChequeRefeicaoLocal;
import pt.ist.rest.DatabaseBootstrap;
import pt.ist.rest.exception.*;
import pt.ist.rest.presentation.client.RestServlet;
import pt.ist.rest.presentation.shared.FieldVerifier;
import pt.ist.rest.service.*;
import pt.ist.rest.service.dto.*;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class RestServletImpl extends RemoteServiceServlet implements
        RestServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String localServerType = "ES-only";
	private static final String remoteServerType = "ES+SD";

	//RegistoFatura
	private Serie serie = null;
	private int nrSequencia;

	@Override
	public void initServer(String serverType){
		DatabaseBootstrap.init();
		if (serverType.equals(localServerType)){
			ChequeRefeicao.setCheque(new ChequeRefeicaoLocal());
			RegistoFatura.setRegistoFatura(new RegistoFaturaLocal());
		}
			
			
	};
	
	@Override
	  public void login(ClienteDto cliente) throws ArgumentosInvalidosException, ClientNotFoundException {
	    VerificaPassClienteService service = new VerificaPassClienteService(cliente);
	    service.execute();
	  }
	
	@Override
	public List<RestauranteSimpleDto> listaRestaurantes() {
		ListaRestaurantesService service = new ListaRestaurantesService();
		service.execute();
		return service.getResult().getRestaurantes();
	  }

	@Override
	public List<PratoDto> listaMenu(RestauranteSimpleDto r) {
		ListaMenuService service = new ListaMenuService(r);
		service.execute();
		return service.getResult().getPratos();
	    }
	
	@Override
	public void adicionaItem(ClienteDto c, PratoSimpleDto p, RestauranteSimpleDto r, int quantidade)
			throws ClientNotFoundException,RestaurantNotFoundException,DishNotFoundException {
		AddItemService service = new AddItemService(c,p,r,quantidade);
		service.execute();
	}

	@Override
	public TabuleiroDto listaTabuleiro(ClienteDto c)
		throws EmptyShoppingTrayException{
		ListaTabuleiroService service = new ListaTabuleiroService(c);
		service.execute();
		return service.getResult();
		
	}
		
	@Override
	public PratosDto procuraPrato(PratoSimpleDto p) throws DishNotFoundException{
		ProcuraPratoService service;
		if (p.customCheck())
			service = new ProcuraPratoSubstringService(p);
		else
			service = new ProcuraPratoPorTipoService(p);

		service.execute();
		return service.getResult();
		
	}

	@Override
 	public void adicionaCheques(ChequesDto cheques) throws pt.ist.rest.exception.InvalidCheckException,
 														pt.ist.rest.exception.CheckAlreadyUsedException, 
 														ClientNotFoundException {
 		cheques.valor = 0;
 		try{
 			cheques.valor  = ChequeRefeicao.cashChecks(cheques.clienteDto.getUser(),cheques.cheques);
 
 		} catch (pt.ist.chequerefeicao.InvalidCheckException ice) {
			throw new pt.ist.rest.exception.InvalidCheckException(ice.toString());
		} catch (pt.ist.chequerefeicao.CheckAlreadyUsedException cae) {
			throw new pt.ist.rest.exception.CheckAlreadyUsedException(cae.toString());
		}
 		new ActualizaSaldoService(cheques).execute();
 	}
 	@Override
 	public ClienteDto mostraDadosCliente(ClienteDto userDto){
		ApresentaDadosClienteService service = new ApresentaDadosClienteService(userDto);
		service.execute();
		return service.getResult();
	}

 	
 	@Override
 	public FaturaDto efectuaPagamento(ClienteDto cliente) throws NegativeBalanceException, 
 															EmptyShoppingTrayException, 
 															ClientNotFoundException,
 															EmissorInexistenteException,
 															ClienteInexistenteException,
 															FaturaInvalidaException,
 															TotaisIncoerentesException{
		boolean serieInvalida = (serie==null) || (nrSequencia > 4);

		try{

			if (serieInvalida){
				nrSequencia=1;
				serie = RegistoFatura.pedirSerie(1234);
			}

		}catch(EmissorInexistente_Exception e){
			throw new EmissorInexistenteException();
		}

		//modifica o saldo consoante a compra
		
		RegistaPagamentoTabuleiroComprasService paga = new RegistaPagamentoTabuleiroComprasService(cliente);
		paga.execute();		

		//passa a fatura e fecha a compra
		AdicionaFaturaService fatura = new AdicionaFaturaService(cliente,serie,nrSequencia);
		fatura.execute();
 		nrSequencia++;
 		
 		//Comunica a fatura acabada ao RegistoFatura
 		try{
 			RegistoFatura.comunicarFatura(fatura.getResult());
 		}catch (ClienteInexistente_Exception e){
 			throw new ClienteInexistenteException();
 		}catch (EmissorInexistente_Exception e){
 			throw new EmissorInexistenteException();
 		}catch (FaturaInvalida_Exception e){
 			throw new FaturaInvalidaException();
 		}catch (TotaisIncoerentes_Exception e){
 			throw new TotaisIncoerentesException();
 		}
 		return new FaturaDto(fatura.getResult().getNumSerie(),fatura.getResult().getNumSeqFatura());
 	}



} 
