package pt.ist.rest.domain;

public class Item extends Item_Base {
    
    public Item(Prato prato,Integer quantidade) {
        super();
        setQuantidade(quantidade);
		this.setPrato(prato);
    }
	
	/**
	 *	Compara um prato com o item para verificar se este o referencia.
	 *
	 *	@param prato o objecto prato a comparar
	 *	@return		<c>true</c> se os pratos tiverem o mesmo identificador, 
	 *				<c>false</c> em caso contrario.
	 *	@see Prato
	 */			
	public boolean isItemDe(Prato prato){
		return this.getPrato().getIDPrato().equals(prato.getIDPrato());
	}

	/**
	 *	Altera o valor da quantidade do item
	 *
	 *	@param  diferenca	 valor a somar 'a quantidade atual,
	 *						 pode tomar valores positivos ou negativos
	 */	
	public void mudaQuantidade(int diferenca){
		this.setQuantidade( this.getQuantidade() + diferenca );
	}
	
}
