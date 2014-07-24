package pt.ist.rest.service;

//import pt.ist.service .EmptyTheTrayService;
import pt.ist.rest.exception.*;
import pt.ist.rest.service.dto.*;
import org.junit.Test;

public class EsvaziarTabuleiroComprasServiceTest extends RestServiceTestCase{
	
	private final static String EXISTING_USER_NAME = "Bart";
	private final static String EXISTING_CLIENT_ADDRESS = "DressRosa";
	private final static String EXISTING_CLIENT_NAME = "Bartholomeu Supernova";
	private final static String EXISTING_PRATO = "Chicken Tika";
	private final static String EXISTING_RESTAURANT_NAME = "Konichi Place";
	private final static String EXISTING_MAIL = "barrister@ko.com";
	private final static String EXISTING_CLIENT_PASS = "b3rt";
	private final static String NON_EXISTING_USER_NAME = "Jorge Jesus";


	
	@Override
	protected void setUp(){
		super.setUp();	//cleans the db
		adicionaRestaurante(EXISTING_RESTAURANT_NAME, EXISTING_CLIENT_ADDRESS);
		adicionaCliente(EXISTING_CLIENT_NAME,
						EXISTING_CLIENT_ADDRESS,
						EXISTING_USER_NAME,
						EXISTING_MAIL,
						EXISTING_CLIENT_PASS);
		addPrato(EXISTING_RESTAURANT_NAME, EXISTING_PRATO,34, 45,3);
		addItem(EXISTING_RESTAURANT_NAME,
				EXISTING_USER_NAME,
				EXISTING_PRATO,
				2); 
	}
	@Test(expected=ClientNotFoundException.class)
	public void testClienteExists(){
		Boolean exceptionThrown = false;
		ClienteDto clienteDto = new ClienteDto(NON_EXISTING_USER_NAME,
				   							   EXISTING_CLIENT_PASS,
				   							   EXISTING_CLIENT_NAME,
				   							   EXISTING_CLIENT_ADDRESS,
				   							   EXISTING_MAIL);
		try{
			new EsvaziarTabuleiroComprasService(clienteDto).execute();
			fail("Client Not Found Exception not thrown");
		}catch (ClientNotFoundException e){
			exceptionThrown = true;
		}
		assertTrue("Exception should have been thrown",exceptionThrown);
	}
	public void testTabuleiroVazio(){
		
		ClienteDto clienteDto = new ClienteDto(EXISTING_USER_NAME,
											   EXISTING_CLIENT_PASS,
											   EXISTING_CLIENT_NAME,
											   EXISTING_CLIENT_ADDRESS,
											   EXISTING_MAIL);
		try{
			new EsvaziarTabuleiroComprasService(clienteDto).execute();
			
		}catch(ClientNotFoundException e){
			fail("The client exists, but was not found");
		}
		assertTrue("The tray still exists",getNumberOfItems(EXISTING_USER_NAME)==0);
	
		
		
	}

} 