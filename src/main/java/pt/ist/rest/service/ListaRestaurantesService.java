package pt.ist.rest.service;

import java.util.ArrayList;
import java.util.List;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.rest.domain.*;
import pt.ist.rest.exception.NoRestaurantsException;
import pt.ist.rest.service.dto.RestauranteSimpleDto;
import pt.ist.rest.service.dto.RestDto;


public class ListaRestaurantesService extends RestService {


	private RestDto result;
	
	public final void dispatch() throws NoRestaurantsException {
		Rest rest = FenixFramework.getRoot();

		if (rest.getRestauranteCount() == 0)
			throw new NoRestaurantsException();

		List<RestauranteSimpleDto> restaurantes = new ArrayList<RestauranteSimpleDto>();

		for (Restaurante r: rest.getRestauranteSet()){ 
			RestauranteSimpleDto restaurante = new RestauranteSimpleDto(r.getNome(),r.getMorada(),r.calculaClassificacao());
			restaurantes.add(restaurante);
		}

		result = new RestDto(restaurantes);

	}

	public RestDto getResult(){
		return result;
	}

}