package pt.ist.rest.service;

import pt.ist.fenixframework.FenixFramework;

import java.util.ArrayList;
import java.util.List;

import pt.ist.rest.domain.*;
import pt.ist.rest.service.dto.*;
import pt.ist.rest.exception.DishesNotFoundException;
import pt.ist.rest.service.ProcuraPratoService;


public class ProcuraPratoSubstringService extends ProcuraPratoService {

	public ProcuraPratoSubstringService(PratoSimpleDto p){
		super(p);
	}

	public final List<Prato> procura(String substring) throws DishesNotFoundException{
		Rest rest = FenixFramework.getRoot();
		List<Prato> pratos = new ArrayList<Prato>();
		for (Restaurante r: rest.getRestauranteSet())
			for (Prato p: r.getPratoSet())
    			if(p.containsSubstring(substring))
    				   pratos.add(p);
    	return pratos;
    	
	}


}