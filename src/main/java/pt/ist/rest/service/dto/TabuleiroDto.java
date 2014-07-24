package pt.ist.rest.service.dto;


import java.util.List;

public class TabuleiroDto implements java.io.Serializable{


	private static final long serialVersionUID = 1L;
	private int saldo;
	private int custo;
	private List<ItemDto> items; 

	public TabuleiroDto() {}
	
	public TabuleiroDto(int custo,int saldo,List<ItemDto> dto){
		this.items = dto;
		this.custo = custo;
		this.saldo = saldo;
	}

	public List<ItemDto> getItems(){
		return this.items;
	}

	public int getSaldo() {
		return saldo;
	}

	public int getCusto() {
		System.out.println("Custo em dto:" + custo);
		return custo;
	}


}
