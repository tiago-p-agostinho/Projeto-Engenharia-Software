package pt.ist.rest.service.dto;

public class RestauranteSimpleDto implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
	private String morada;
	private String nome;
	private int classificacao;
	
	public RestauranteSimpleDto() {}
	
	public RestauranteSimpleDto(String nome, String morada,int classificacao) {
		
		this.nome = nome;
		this.morada = morada;
		this.classificacao = classificacao; 
		
	}
	
	public RestauranteSimpleDto(String nome) {
		this.nome = nome;
	}

	public int getClassificacao() {
		return classificacao;
	}
		
	public final String getMorada() {
		return morada;
	}
		
	public final String getNome() {
		return nome;
	}
	
	

	@Override
	public String toString() {
		return "RestauranteSimpleDto [morada=" + morada + ", nome=" + nome
				+ ", classificacao=" + classificacao + "]";
	}
	
	
}
	