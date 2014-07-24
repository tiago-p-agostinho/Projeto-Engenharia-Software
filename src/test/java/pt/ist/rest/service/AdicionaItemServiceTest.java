package pt.ist.rest.service;

import  pt.ist.rest.service.AddItemService;
import  pt.ist.rest.service.dto.*;
import  pt.ist.rest.exception.*;

public class AdicionaItemServiceTest extends RestServiceTestCase{
	
	
	private static String EXISTING_USER_NAME = "Bart";
	private static String EXISTING_CLIENT_ADDRESS = "DressRosa";
	private static String EXISTING_CLIENT_NAME = "Bartholomeu Supernova";
	private static String EXISTING_PRATO = "Chicken Tika";
	private static String EXISTING_RESTAURANT_NAME = "Konichi Place";
	private static String EXISTING_MAIL = "barrister@ko.com";
	private static String EXISTING_CLIENT_PASS = "b3rt";
	private static Integer EXISTING_CLIENT_NIF = 1234;
    private static String NON_EXISTING_USER_NAME = "Capitain Kirk";
    private static String NON_EXISTING_RESTAURANT_NAME = "Darth Hood";
	private static String NON_EXISTING_PRATO = "Prawn Saag";
	
      
	public AdicionaItemServiceTest(String msg) {
		super(msg);
	    }
		
	    public AdicionaItemServiceTest() {
		super();
	    }
	   
	    @Override
	    public void setUp() {
		super.setUp();
		adicionaCliente(EXISTING_CLIENT_NAME,EXISTING_CLIENT_ADDRESS,EXISTING_USER_NAME,EXISTING_MAIL,EXISTING_CLIENT_PASS,EXISTING_CLIENT_NIF);
		adicionaRestaurante(EXISTING_RESTAURANT_NAME, EXISTING_CLIENT_ADDRESS);
		addPrato(EXISTING_RESTAURANT_NAME, EXISTING_PRATO,34, 45,3);
		
		
	    }
	   
	public void testCreateNewInexistentItem() {
		// Arrange
	PratoSimpleDto
	dto = new PratoSimpleDto(EXISTING_PRATO);
	RestauranteSimpleDto restauranteDto = new RestauranteSimpleDto(EXISTING_RESTAURANT_NAME);
	ClienteDto clienteDto = new ClienteDto(EXISTING_USER_NAME,EXISTING_CLIENT_PASS,EXISTING_CLIENT_NAME,EXISTING_CLIENT_ADDRESS,EXISTING_MAIL);
	AddItemService addService = new AddItemService(clienteDto, dto,restauranteDto,4);
	int nItemsBefore = getNumberOfItems(EXISTING_USER_NAME);


	// Act
	try {
	  addService.execute();
	} catch(ClientNotFoundException e) {
	    fail("Could not add item: " + e.getMessage());
	} catch (DishNotFoundException pdne) {
	    fail("Could not add item: " + pdne.getMessage());
	} catch (RestaurantNotFoundException pdne) {
	    fail("Could not add item: " + pdne.getMessage());
	}

	//Assert
		assertTrue("New item was not added to client compra", verificaItem(EXISTING_USER_NAME, EXISTING_PRATO, EXISTING_RESTAURANT_NAME));
		assertEquals("The number of Items should be incresed by one.", nItemsBefore + 1, getNumberOfItems(EXISTING_USER_NAME));
    }
	
	public void testAddExistingItemNegativeQuantity() {
		// Arrange
		PratoSimpleDto
		dto = new PratoSimpleDto(EXISTING_PRATO);
		RestauranteSimpleDto restauranteDto = new RestauranteSimpleDto(EXISTING_RESTAURANT_NAME);
		ClienteDto clienteDto = new ClienteDto(EXISTING_USER_NAME,EXISTING_CLIENT_PASS,EXISTING_CLIENT_NAME,EXISTING_CLIENT_ADDRESS,EXISTING_MAIL);
		AddItemService addService = new AddItemService(clienteDto, dto,restauranteDto,20);
		int nItemsBefore = getNumberOfItems(EXISTING_USER_NAME);
		
		
		// Act
		try {
			addService.execute();
			addService.setQuantidade(-30);
			addService.execute();
		} catch(ClientNotFoundException e) {
			fail("Could not add item: " + e.getMessage());
		} catch (RestaurantNotFoundException pdne) {
			fail("Could not add item: " + pdne.getMessage());
		} catch (DishNotFoundException pdne) {
			fail("Could not add item" + pdne.getMessage());
		}
		
		//Assert
		assertFalse("Existing Item should have been removed", verificaItem(EXISTING_USER_NAME, EXISTING_PRATO, EXISTING_RESTAURANT_NAME));
		assertEquals("The number of Items should be the same.", nItemsBefore, getNumberOfItems(EXISTING_USER_NAME));
		
	}
	
	public void testCreateNewItemInexistentClient() {
		// Arrange
	PratoSimpleDto
	dto = new PratoSimpleDto(EXISTING_PRATO);
	RestauranteSimpleDto restauranteDto = new RestauranteSimpleDto(EXISTING_RESTAURANT_NAME);
	ClienteDto clienteDto = new ClienteDto(NON_EXISTING_USER_NAME,EXISTING_CLIENT_PASS,EXISTING_CLIENT_NAME,EXISTING_CLIENT_ADDRESS,EXISTING_MAIL);
	AddItemService addService = new AddItemService(clienteDto, dto,restauranteDto,4);
	int nItemsBefore = getNumberOfItems(EXISTING_USER_NAME);

	String exceptionClientName = null;
	
	// Act
	try {
	    addService.execute();
	   fail("Client not exists. Should have thrown an exception.");
	} catch(ClientNotFoundException e) {
	    exceptionClientName = e.getClientName(); 
	} catch (DishNotFoundException pdne) {
	    fail("Could not add item: " + pdne.getMessage());
	} catch (RestaurantNotFoundException pdne) {
	    fail("Could not add item: " + pdne.getMessage());
	}

	//Assert
		
		assertEquals("The number of Items should be the same.", nItemsBefore, getNumberOfItems(EXISTING_USER_NAME));
		assertEquals("The Client name reported to not exist is not the one asked to be added.", NON_EXISTING_USER_NAME,exceptionClientName);
    }
	
	public void testCreateNewItemInexistentRestaurant() {
		// Arrange
	PratoSimpleDto
	dto = new PratoSimpleDto(EXISTING_PRATO);
	RestauranteSimpleDto restauranteDto = new RestauranteSimpleDto(NON_EXISTING_RESTAURANT_NAME);
	ClienteDto clienteDto = new ClienteDto(EXISTING_USER_NAME,EXISTING_CLIENT_PASS,EXISTING_CLIENT_NAME,EXISTING_CLIENT_ADDRESS,EXISTING_MAIL);
	AddItemService addService = new AddItemService(clienteDto, dto,restauranteDto,4);
	int nItemsBefore = getNumberOfItems(EXISTING_USER_NAME);

	String exceptionRestaurantName = null;
	
	// Act
	try {
	    addService.execute();
	   fail("Restaurant does not exist. Should have thrown an exception.");
	} catch(ClientNotFoundException e) {
	   fail("Could not add item: " + e.getMessage());
	} catch (RestaurantNotFoundException pdne) {
		exceptionRestaurantName = pdne.getRestaurantName(); 
	} catch (DishNotFoundException pdne) {
	    fail("Could not add item: " + pdne.getMessage());
	}

	//Assert
		
		assertEquals("The number of Items should be the same.", nItemsBefore, getNumberOfItems(EXISTING_USER_NAME));
		assertEquals("The Restaurant name reported to not exist is not the one asked to be added.", NON_EXISTING_RESTAURANT_NAME,exceptionRestaurantName);
    }
	
	public void testCreateNewItemInexistentDish() {
		// Arrange
	PratoSimpleDto
	dto = new PratoSimpleDto(NON_EXISTING_PRATO);
	RestauranteSimpleDto restauranteDto = new RestauranteSimpleDto(EXISTING_RESTAURANT_NAME);
	ClienteDto clienteDto = new ClienteDto(EXISTING_USER_NAME,EXISTING_CLIENT_PASS,EXISTING_CLIENT_NAME,EXISTING_CLIENT_ADDRESS,EXISTING_MAIL);
	AddItemService addService = new AddItemService(clienteDto, dto,restauranteDto,4);
	int nItemsBefore = getNumberOfItems(EXISTING_USER_NAME);

	String exceptionDishName = null;
	
	// Act
	try {
	    addService.execute();
	   fail("Dish does not exist. Should have thrown an exception.");
	} catch(ClientNotFoundException e) {
	   fail("Could not add item: " + e.getMessage());
	} catch (RestaurantNotFoundException pdne) {
		fail("Could not add item: " + pdne.getMessage());
	} catch (DishNotFoundException pdne) {
	    exceptionDishName = pdne.getDishName(); 
	}

	//Assert
		
		assertEquals("The number of Items should be the same.", nItemsBefore, getNumberOfItems(EXISTING_USER_NAME));
		assertEquals("The Dish name reported to not exist is not the one asked to be added.", NON_EXISTING_PRATO,exceptionDishName);
    }
	
	}
	
	
