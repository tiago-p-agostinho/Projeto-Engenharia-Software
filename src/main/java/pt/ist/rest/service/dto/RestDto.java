package pt.ist.rest.service.dto;


import java.util.List;

public class RestDto implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<RestauranteSimpleDto> restauranteSimple; 
	
	public RestDto() {}
	
	public RestDto(List<RestauranteSimpleDto> dto){
		this.restauranteSimple = dto;
	}

	public List<RestauranteSimpleDto> getRestaurantes(){
		return this.restauranteSimple;
	}
	

}
