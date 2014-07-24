
package pt.ist.rest.service;
import pt.ist.rest.service.dto.*;
import pt.ist.rest.exception.*;
//import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;


public class ProcurarPratoTest extends RestServiceTestCase{
	 private static String EXISTING_RESTAURANT_NAME = "Barriga Feliz";
	 private static String EXISTING_RESTAURANT_ADDRESS = "Lisboa";

	 private static String EXISTING_DISH_NAME = "Bacalhau com natas";
	 private static String EXISTING_TYPE_NAME = "PEIXE";
	 private static String NON_EXISTING_TYPE_NAME = "CARNE";
	 private static String NON_EXISTING_DISH_NAME = "Tripas a moda do Porto";


	 @Override
	    public void setUp() {
		super.setUp();
		adicionaRestaurante(EXISTING_RESTAURANT_NAME, EXISTING_RESTAURANT_ADDRESS);
		addPrato(EXISTING_RESTAURANT_NAME, EXISTING_DISH_NAME ,12, 89, 0);
		
	    }

	 public void testProcuraPratoSubstringExistente() {

			PratoSimpleDto dto = new PratoSimpleDto(EXISTING_DISH_NAME);
			ProcuraPratoService procuraPratoService = new ProcuraPratoSubstringService(dto);
			int numPratos = getNumeroPratos(EXISTING_RESTAURANT_NAME);

			try {
			    procuraPratoService.execute();
			} catch(DishNotFoundException e) {
			    fail("Could not found a dish with the name: " + e.getMessage());
			}

			//Assert
			assertTrue("Dish was not in restaurant, but it should be", 
							verificaPrato(EXISTING_RESTAURANT_NAME, EXISTING_DISH_NAME));
			assertEquals("The number of dishes before and after is not the same", numPratos,
												getNumeroPratos(EXISTING_RESTAURANT_NAME));

	 }
	 
	 public void testProcuraPratoTipoExistente() {

			PratoSimpleDto dto = new PratoSimpleDto(EXISTING_TYPE_NAME);
			ProcuraPratoService procuraPratoService = new ProcuraPratoPorTipoService(dto);
			int numPratos = getNumeroPratos(EXISTING_RESTAURANT_NAME);

			try {
			    procuraPratoService.execute();
			} catch(DishNotFoundException e) {
			    fail("Could not found a dish with the name: " + e.getMessage());
			}

			//Assert
			assertTrue("Dish was not in restaurant, but it should be", 
							verificaPrato(EXISTING_RESTAURANT_NAME, EXISTING_DISH_NAME));
			assertEquals("The number of dishes before and after is not the same", numPratos,
												getNumeroPratos(EXISTING_RESTAURANT_NAME));

	 }


	 public void testProcuraPratoSubstringInexistente() {

			PratoSimpleDto dto = new PratoSimpleDto(NON_EXISTING_DISH_NAME);
			ProcuraPratoService procuraPratoService = new ProcuraPratoSubstringService(dto);
			boolean exceptionThrown = false;
			int numPratos = getNumeroPratos(EXISTING_RESTAURANT_NAME);
            System.out.println("Antes do servico");

			try {
			    procuraPratoService.execute();
			    fail("O prato nao existe");
			} catch(DishNotFoundException e) {
				
			    exceptionThrown = true;
			}
			//Assert
			assertTrue("Service found a dish that doesn't exists",exceptionThrown);
			assertEquals("The number of dishes before and after is not the same", numPratos,
					getNumeroPratos(EXISTING_RESTAURANT_NAME));
}
	 
	 public void testProcuraPratoTipoInexistente() {

			PratoSimpleDto dto = new PratoSimpleDto(NON_EXISTING_TYPE_NAME);
			ProcuraPratoService procuraPratoService = new ProcuraPratoPorTipoService(dto);
			boolean exceptionThrown = false;
			int numPratos = getNumeroPratos(EXISTING_RESTAURANT_NAME);


			try {
			    procuraPratoService.execute();
			    fail("O prato nao existe");
			} catch(DishNotFoundException e) {
			    exceptionThrown = true;
			}
			//Assert
			assertTrue("Service found a dish that doesn't exists",exceptionThrown);
			assertEquals("The number of dishes before and after is not the same", numPratos,
					getNumeroPratos(EXISTING_RESTAURANT_NAME));



	 }


}