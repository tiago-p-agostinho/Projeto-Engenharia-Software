package pt.ist.rest.service;

import java.util.List;

import pt.ist.chequerefeicao.CheckAlreadyUsedException;
import pt.ist.chequerefeicao.ChequeRefeicao;
import pt.ist.chequerefeicao.InvalidCheckException;
import pt.ist.chequerefeicao.InvalidPayeeException;
import pt.ist.registofatura.RegistoFatura;
import pt.ist.registofatura.RegistoFaturaLocal;
import pt.ist.registofatura.ws.ClienteInexistente_Exception;
import pt.ist.registofatura.ws.EmissorInexistente_Exception;
import pt.ist.registofatura.ws.Fatura;
import pt.ist.registofatura.ws.FaturaInvalida_Exception;
import pt.ist.registofatura.ws.Serie;
import pt.ist.registofatura.ws.TotaisIncoerentes_Exception;
import pt.ist.rest.exception.ClientNotFoundException;
import pt.ist.rest.exception.ClienteInexistenteException;
import pt.ist.rest.exception.EmissorInexistenteException;
import pt.ist.rest.exception.EmptyShoppingTrayException;
import pt.ist.rest.exception.FaturaInvalidaException;
import pt.ist.rest.exception.NegativeBalanceException;
import pt.ist.rest.exception.TotaisIncoerentesException;
import pt.ist.rest.service.dto.ChequesDto;
import pt.ist.rest.service.dto.ClienteDto;
import pt.ist.rest.service.dto.FaturaDto;
import pt.ist.rest.service.dto.PagamentoDto;

public class EfectuaPagamento{

	//RegistoFatura
		private Serie serie = null;
		private int NIF_PORTAL = 1212;
		private int nrSequencia;
		RegistoFaturaLocal registo = new RegistoFaturaLocal();
	
   public void init(){	
		RegistoFatura.setRegistoFatura(registo);
	}
	
	public void adicionaCheques(ChequesDto cheques) throws pt.ist.rest.exception.InvalidCheckException,
	pt.ist.rest.exception.CheckAlreadyUsedException,ClientNotFoundException {
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
	

	public void efectuaPagamento(ClienteDto cliente, Fatura fatura) throws NegativeBalanceException, 
																EmptyShoppingTrayException, 
																ClientNotFoundException,
																EmissorInexistenteException,
																ClienteInexistenteException,
																FaturaInvalidaException,
																TotaisIncoerentesException{


	     try{
	    	 
			RegistoFatura.comunicarFatura(fatura);
			
		}catch (ClienteInexistente_Exception e){
			throw new ClienteInexistenteException();
		}catch (EmissorInexistente_Exception e){
			throw new EmissorInexistenteException();
		}catch (FaturaInvalida_Exception e){
			System.out.println("Expcao invalid");
			throw new FaturaInvalidaException();
		}catch (TotaisIncoerentes_Exception e){
			throw new TotaisIncoerentesException();
		}
		
	}



}

