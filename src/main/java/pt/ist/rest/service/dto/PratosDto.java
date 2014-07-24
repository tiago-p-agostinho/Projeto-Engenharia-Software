package pt.ist.rest.service.dto;

import java.util.List;

public class PratosDto implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private List<PratoDeRestauranteDto> pratoRestaurante;

	public PratosDto() {}
	
	public PratosDto(List<PratoDeRestauranteDto> dto){
		this.pratoRestaurante = dto;
	}

	public List<PratoDeRestauranteDto> getPratoRestaurante() {
		return pratoRestaurante;
	}
	public void add(PratoDeRestauranteDto pdto){
		this.pratoRestaurante.add(pdto);
	}

}
