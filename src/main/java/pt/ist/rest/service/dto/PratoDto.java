package pt.ist.rest.service.dto;




public class PratoDto extends PratoSimpleDto implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer calorias;
	private Integer preco;
	private Integer classificacao;
	private Integer quantidade;

	public PratoDto() {}

   public PratoDto(String name,Integer calorias,Integer preco,int classificacao){
		super(name);
		this.calorias = calorias;
		this.preco = preco;
		this.classificacao = classificacao;
	

	}
	

  public int getQuantidade(){
		return quantidade; 
	}
	
   public String getAtributo() {
		return super.getAtributo();
	}

   public Integer getCalorias() {
	   return calorias;
   }
   
   
   public Integer getPreco() {
	   return preco;
   }
   
   
   public void setPreco(Integer preco) {
	   this.preco = preco;
   }
   
   
   public Integer getClassificacao() {
	   return classificacao;
   }
   
   
   public void setClassificacao(Integer classificacao) {
	   this.classificacao = classificacao;
   }


@Override
public String toString() {
	return "PratoDto [nomeP=" + getAtributo() + ", calorias=" + calorias + ", preco="
			+ preco + ", classificacao=" + classificacao + "]";
}
   
   
 }
