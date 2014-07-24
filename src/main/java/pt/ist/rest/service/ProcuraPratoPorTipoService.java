package pt.ist.rest.service;

import java.util.ArrayList;
import java.util.List;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.rest.domain.*;
import pt.ist.rest.service.dto.*;
import pt.ist.rest.exception.DishesNotFoundException;


public class ProcuraPratoPorTipoService extends ProcuraPratoService{
	
	public ProcuraPratoPorTipoService(PratoSimpleDto p){
		super(p);
	}


	@Override
	public final List<Prato> procura(String tipo) throws DishesNotFoundException{
		Rest rest = FenixFramework.getRoot();
		List<Prato> pratos = new ArrayList<Prato>();
		for (Restaurante r: rest.getRestauranteSet())
			for (Prato p: r.getPratoSet())
    			if(p.isTipo(tipo))
    				   pratos.add(p);	

    	return pratos;
	}

}

