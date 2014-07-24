package pt.ist.rest.presentationserver;

import pt.ist.rest.service.ListaTabuleiroService;
import pt.ist.rest.service.dto.TabuleiroDto;
import pt.ist.rest.service.dto.ItemDto;

public class TabuleiroPresenter {

	
public static void show(TabuleiroDto dto){
		
		for(ItemDto item: dto.getItems()){
			System.out.print(item.getNomePrato() + "|" + item.getQuantidade() + "|");
		}
	}
}
