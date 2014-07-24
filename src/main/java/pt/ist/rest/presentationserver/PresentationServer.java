package pt.ist.rest.presentationserver;

import java.util.ArrayList;
import java.util.List;

import pt.ist.fenixframework.Config;
import pt.ist.fenixframework.FenixFramework;
import jvstm.Atomic;
import pt.ist.rest.domain.*;
import pt.ist.rest.exception.*;
import pt.ist.rest.service.*;
import pt.ist.rest.service.dto.*;
import pt.ist.chequerefeicao.*;
import pt.ist.chequerefeicao.CheckAlreadyUsedException;
import pt.ist.chequerefeicao.InvalidCheckException;
import pt.ist.chequerefeicao.InvalidPayeeException;


public class PresentationServer {

    public final static String DBUSER = "rest";
	public final static String DBPASS = "r3st";
	public final static String DBURL = "//localhost:3306/restdb";
	public final static String DMLPATH = "src/main/dml/rest.dml";
	
	
	
    public static void main(String[] args) {
    	FenixFramework.initialize(new Config() {{
                dbAlias = DBURL;
                dbUsername = DBUSER;
                dbPassword = DBPASS;
    		    domainModelPath = DMLPATH;
    		    rootClass=Rest.class;
    	}});
		
    	
    	
        registaBarrigaCheia();
		imprimeUtilizadores();
		imprimeRestaurantes();
		adicionaBitoque();	
		escreveClassificacao();
		gostarPratos1();
		gostarPratos2();
		escreveClassificacao2();
		adicionaPratoTabuleiro1();
		adicionaPratoTabuleiro2();
		verificaTabuleiro1();
		procuraPrato();
	//	pagaCompra();
		verificaTabuleiro1();

   }
	
	@Atomic
	public static void registaBarrigaCheia() {			
		Rest rest = FenixFramework.getRoot();
		Restaurante restaurante = new Restaurante("Barriga Cheia","Porto,Portugal");
		try{
			rest.addRestaurante(restaurante, "pp");
		}catch(RestaurantAlreadyExistsException e){
			System.out.println(e.toString());
		}catch(ManagerNotFoundException e){
			System.out.println(e.toString());
		}catch(BusyManagerException e){
			System.out.println(e.toString());
		}
		
	}
   
    @Atomic
	public static void imprimeUtilizadores(){
		Rest rest = FenixFramework.getRoot();
		rest.imprimeUtilizadores();	
	}
	
	
    public static void imprimeRestaurantes(){

    	ListaRestaurantesService sr = new ListaRestaurantesService();

    	try{
    	sr.execute();
    	RestaurantPresenter.show(sr.getResult()); 
    	}catch (NoRestaurantsException e){
    		System.out.println(e.toString());
    		
    	}catch (RestaurantHasNoDishesException e){
    		System.out.println(e.toString());
    	}
    }
	
    
	
    @Atomic
	public static void adicionaBitoque(){
		Rest rest = FenixFramework.getRoot();
		
		try{
			rest.adicionaPratoRestaurante("pp", "Barriga Cheia", 
											new Prato("Bitoque",new Integer(14) ,new Integer(14),rest.incrementaIDPrato()));
		}catch (RestaurantNotFoundException e){
			System.out.println(e.toString());

		}catch (DishesNumberExceedException e){
			System.out.println(e.toString());
			
		}catch (ManagerNotFoundException e){
			System.out.println(e.toString());
			
		}catch (ManagerHasNoAccessException e){
			System.out.println(e.toString());
		}
	}
	
	@Atomic
	public static void escreveClassificacao(){
		Rest rest = FenixFramework.getRoot();
		final int classificacaoCheia;
		final int classificacaoFeliz;
		
		try{
			classificacaoCheia = rest.calculaClassificacao("Barriga Cheia");
			classificacaoFeliz = rest.calculaClassificacao("Barriga Feliz");
			
			System.out.println("Classificacao do restaurante barrigaCheia:"+classificacaoCheia);
			System.out.println("Classificacao do restaurante barrigaFeliz:"+classificacaoFeliz);
       }catch (RestaurantNotFoundException e){
			System.out.println(e.toString());
	   }
	
	}
	
	@Atomic
	public static void escreveClassificacao2(){
		Rest rest = FenixFramework.getRoot();
		final int classificacaoCheia;
		final int classificacaoFeliz;
		
		try{
			classificacaoCheia = rest.calculaClassificacao("Barriga Cheia");
			classificacaoFeliz = rest.calculaClassificacao("Barriga Feliz");
			
			System.out.println("Classificacao do restaurante barrigaCheia:"+classificacaoCheia);
			System.out.println("Classificacao do restaurante barrigaFeliz:"+classificacaoFeliz);
       }catch (RestaurantNotFoundException e){
			System.out.println(e.toString());
	   }  
    
	
	}
	
	public static void adicionaPratoTabuleiro1(){
		AddItemService sr = new AddItemService(new ClienteDto("zeze","z3z3"),new PratoSimpleDto("Canja de Galinha"),
				                               new RestauranteSimpleDto("Barriga Feliz") ,3);
		
		try{
			sr.execute();
		}catch (RestaurantNotFoundException e){
			System.out.println(e.toString());
				
		}catch (DishNotFoundException e){
			System.out.println(e.toString());
			
		}catch (ClientNotFoundException e){
			System.out.println(e.toString());
		}
	}
	
	public static void adicionaPratoTabuleiro2(){
		AddItemService sr = new AddItemService(new ClienteDto("zeze","z3z3"),new PratoSimpleDto("Bacalhau com batatas"),
                                               new RestauranteSimpleDto("Barriga Feliz") ,2);
		try{
			sr.execute();
		}catch (RestaurantNotFoundException e){
			System.out.println(e.toString());
				
		}catch (DishNotFoundException e){
			System.out.println(e.toString());
			
		}catch (ClientNotFoundException e){
			System.out.println(e.toString());
		}
	}


	public static void verificaTabuleiro1(){
		
	 ListaTabuleiroService service = new ListaTabuleiroService(new ClienteDto("zeze",null));
	 service.execute();
	 TabuleiroPresenter.show(service.getResult());
	
	}
	
	public static void gostarPratos1(){
		
		GostarPratoService sr = new GostarPratoService(new RestauranteSimpleDto("Barriga Feliz"), new PratoSimpleDto("Bacalhau com batatas"), new ClienteDto("zeze","z3z3"));
		GostarPratoService sr1 = new GostarPratoService(new RestauranteSimpleDto("Barriga Feliz"),new PratoSimpleDto("Bitoque"), new ClienteDto("zeze","z3z3"));
		GostarPratoService sr2 = new GostarPratoService(new RestauranteSimpleDto("Barriga Feliz"),new PratoSimpleDto("Canja de Galinha"), new ClienteDto("zeze","z3z3"));

		try{
		sr.execute();
		sr1.execute();
		sr2.execute();
		}catch (RestaurantNotFoundException e){
			System.out.println(e.toString());
				
		}catch (DishNotFoundException e){
			System.out.println(e.toString());
			
		}catch (ClientAlreadyLikesDishException e){
			System.out.println(e.toString());
			
		}catch (ClientNotFoundException e){
			System.out.println(e.toString());
			
		}catch (LikesNumberExceedException e){
			System.out.println(e.toString());
		}
	} 
	

	public static void gostarPratos2(){
		GostarPratoService sr = new GostarPratoService(new RestauranteSimpleDto("Barriga Feliz"), new PratoSimpleDto("Canja de Galinha"), new ClienteDto("mariazinha","****"));
		
		try{
		sr.execute();
		}catch (RestaurantNotFoundException e){
			System.out.println(e.toString());
				
		}catch (DishNotFoundException e){
			System.out.println(e.toString());
			
		}catch (ClientAlreadyLikesDishException e){
			System.out.println(e.toString());
			
		}catch (ClientNotFoundException e){
			System.out.println(e.toString());
			
		}catch (LikesNumberExceedException e){
			System.out.println(e.toString());
		}
	} 
	
	
	public static void procuraPrato(){
		
		ProcuraPratoService sr = new ProcuraPratoPorTipoService(new PratoSimpleDto("a"));
		sr.execute();
		
		
		for(PratoDeRestauranteDto p: sr.getResult().getPratoRestaurante()){
			System.out.println(p.getAtributo() + " ");
		}
	}
	
	
	
	public static void pagaCompra(){
		String nomeCliente = "zeze";
		String cheque = "19";
		List<String> cheques = new ArrayList<String>();
		cheques.add(cheque);
		pagamentoDeCompra(nomeCliente,cheques);
	}
	
	
	
	public static void pagamentoDeCompra(String nomeCliente, List<String> cheques){
	
		
		try {
		    int valorCheques = ChequeRefeicao.cashChecks(nomeCliente, cheques);
		
		   	ClienteDto cliente = new ClienteDto(nomeCliente,null);
		    new ActualizaSaldoService(new ChequesDto(cliente,cheques)).execute();
		    new RegistaPagamentoTabuleiroComprasService(cliente).execute();
		     
		} catch (InvalidCheckException ice) {
		    System.out.println("Could not make valid registry of checks! " + ice);
		} catch (CheckAlreadyUsedException cae) {
		    System.out.println("Could not make valid registry of checks!" + cae);
		} catch (ClientNotFoundException e){
			System.out.println(e.toString());
		} catch (NegativeBalanceException e){
			System.out.println(e.toString());
		}
		
	}
	
}



    			
 

