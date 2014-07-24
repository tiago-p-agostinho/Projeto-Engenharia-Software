package pt.ist.rest.presentationserver;

import pt.ist.rest.service.dto.*;
import pt.ist.rest.service.ListaMenuService;

public class RestaurantPresenter {

	public static void show(RestDto dto){
		
		ListaMenuService lista;
		
		for(RestauranteSimpleDto restaurante: dto.getRestaurantes()){
			lista = new ListaMenuService(restaurante);
			lista.execute();
			System.out.println(restaurante.getNome() + "|" + restaurante.getMorada() +" "+"Pratos:"+ MenuPresenter.show(lista.getResult()));
		}
	}
	
}
