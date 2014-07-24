package pt.ist.rest.service.dto;

import java.util.List;


public class RestauranteDto extends RestauranteSimpleDto {
	private static final long serialVersionUID = 1L;
	private List<PratoDto> pratoDtoList;
	
	public RestauranteDto() {}
	public RestauranteDto(String nameR,String morada,int classificacao,List<PratoDto> dtoList){
		super(nameR,morada,classificacao);
		pratoDtoList = dtoList;
		
	}
	
	public List<PratoDto> getPratos(){
		return this.pratoDtoList;
	}

}
