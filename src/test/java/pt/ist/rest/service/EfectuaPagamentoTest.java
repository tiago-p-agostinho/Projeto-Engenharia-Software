package pt.ist.rest.service;

import java.util.ArrayList;
import java.util.List;

import mockit.Expectations;
import mockit.Mock;
import mockit.Mocked;

import org.junit.Test;

import pt.ist.chequerefeicao.CheckAlreadyUsedException;
import pt.ist.chequerefeicao.ChequeRefeicao;
import pt.ist.chequerefeicao.InvalidCheckException;
import pt.ist.chequerefeicao.InvalidPayeeException;
import pt.ist.registofatura.*;
import pt.ist.registofatura.ws.ClienteInexistente_Exception;
import pt.ist.registofatura.ws.EmissorInexistente_Exception;
import pt.ist.registofatura.ws.Fatura;
import pt.ist.registofatura.ws.FaturaInvalida_Exception;
import pt.ist.registofatura.ws.Serie;
import pt.ist.registofatura.ws.TotaisIncoerentes;
import pt.ist.registofatura.ws.TotaisIncoerentes_Exception;
import pt.ist.rest.exception.ClienteInexistenteException;
import pt.ist.rest.exception.EmissorInexistenteException;
import pt.ist.rest.exception.FaturaInvalidaException;
import pt.ist.rest.exception.TotaisIncoerentesException;
import pt.ist.rest.service.dto.ChequesDto;
import pt.ist.rest.service.dto.ClienteDto;
import pt.ist.rest.service.dto.PagamentoDto;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;


public class EfectuaPagamentoTest{
       
	    ClienteDto cliente = new ClienteDto("zeze",null);
       	
	    @Mocked 
		private ChequeRefeicao cheque;
		
	    EfectuaPagamento pagamento = new EfectuaPagamento();
	   
		
	    @Test
		public void RealizaPagamentoTestInvalidCheque()throws InvalidCheckException,CheckAlreadyUsedException{
		
		
		final List<String> cheques = new ArrayList<String>();
	    boolean exceptionThrown = false;
        cheques.add("A");
		ChequesDto chequesdto = new ChequesDto(cliente,cheques);
				  
		new Expectations() {{ 
	    	  ChequeRefeicao.cashChecks("zeze", cheques);
			  result = new InvalidCheckException(cheques.get(0));
        }};
		
        
       try{ 
          pagamento.adicionaCheques(chequesdto);
       
       } catch(pt.ist.rest.exception.CheckAlreadyUsedException e ){
    	   System.out.println("Could not make valid registry of checks! " + e);
       } catch(pt.ist.rest.exception.InvalidCheckException e){
    	   exceptionThrown = true;
       } 
       
     //Assert
		assertTrue(exceptionThrown);

     }
	    
	    @Mocked
	    RegistoFatura fatura;
	    
	    @Test
	    public void RealizaPagamentoFaturaInvalida() throws TotaisIncoerentes_Exception, ClienteInexistente_Exception, EmissorInexistente_Exception, FaturaInvalida_Exception{
	    	
	    	
	        boolean ExceptionThrown = false;
	    	final TotaisIncoerentes t = new TotaisIncoerentes();
	    	final Fatura fatura = new Fatura();			
	    	pagamento.init();
	    	
	    	
	    	
	    	new Expectations() {{
	    		  RegistoFatura.comunicarFatura(fatura);
				  result = new TotaisIncoerentes_Exception("smt",t);
	        }};
	    	
	        
	        try{
	        	pagamento.efectuaPagamento(cliente, fatura);
	        } catch(TotaisIncoerentesException e){
	        	ExceptionThrown = true;
	        } catch (ClienteInexistenteException e){
	        	System.out.println("Cliente inexistente");
	        } catch (EmissorInexistenteException e){
	        	System.out.println("EmissorInexistente");
	        } catch (FaturaInvalidaException e){
	        	System.out.println("Fatura Invalida");
	        }
	        
	        assertTrue(ExceptionThrown);
	   }
}	    