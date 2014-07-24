package pt.ist.rest.service.dto;

public class PratoTipoDto extends PratoSimpleDto {


	private static final long serialVersionUID = 1L;
	private  String tipoPrato;

	public PratoTipoDto() {}
	
	public PratoTipoDto(String nome,String tipo){
		super(nome);
		this.tipoPrato = tipo;
	}
	
	public String getTipoPrato(){
		return tipoPrato;
	}
	
}
