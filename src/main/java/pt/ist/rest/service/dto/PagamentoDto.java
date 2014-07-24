package pt.ist.rest.service.dto;

import java.util.List;

public class PagamentoDto implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ClienteDto clienteDto;
public List<String> cheques;
 public int custo;
	public PagamentoDto() {}

	public PagamentoDto(ClienteDto dto, List<String> cqs, int custo) {
		clienteDto = dto;
		cheques = cqs;
		this.custo = custo;
		
	}
}