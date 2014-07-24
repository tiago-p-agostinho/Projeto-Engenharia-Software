package pt.ist.rest.service.dto;



public class ItemDto implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private String nomePrato;
	private int preco;
	private int id;
	private int quantidade;
	private RestauranteSimpleDto restaurante;

	public ItemDto() {}

	public ItemDto(String nome, int preco, int identificador, int quantidade,RestauranteSimpleDto rest) {

		this.nomePrato = nome;
		this.preco = preco;
		this.id = identificador;
		this.quantidade = quantidade;
		this.restaurante = rest;
	
	}
	public final RestauranteSimpleDto getRestaurante(){
		return restaurante;
	}
	public final String getNomePrato() {
		return nomePrato;
	}


	public final int getPreco() {
		return preco;
	}

	public final int getId() {
		return id;
	}

	public final int getQuantidade() {
		return quantidade;
	}

}
