package pt.ist.rest.service.dto;

import java.util.List;

public class ChequesDto implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	public ClienteDto clienteDto;
	public List<String> cheques;
	
	public int valor;
	public ChequesDto() {}

	public ChequesDto(ClienteDto dto, List<String> cqs) {
		clienteDto = dto;
		cheques = cqs;
		
	}

}