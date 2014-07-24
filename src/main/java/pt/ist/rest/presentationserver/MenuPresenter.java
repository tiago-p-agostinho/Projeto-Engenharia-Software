package pt.ist.rest.presentationserver;

import pt.ist.rest.service.dto.*;

public class MenuPresenter {
	
	public static String show(RestauranteDto dto){
		String result = "";
		for(PratoDto prato: dto.getPratos()){
			result += prato.getAtributo() + "|" + prato.getPreco() + "|" + prato.getCalorias() + "|"; 
		}
		return result;
	}
	
}
