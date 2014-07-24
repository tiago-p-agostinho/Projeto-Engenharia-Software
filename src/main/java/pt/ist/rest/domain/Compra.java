package pt.ist.rest.domain;

import java.util.List;

import pt.ist.rest.exception.EmptyShoppingTrayException;
import pt.ist.registofatura.ws.Fatura;
import pt.ist.registofatura.ws.ItemFatura;



public class Compra extends Compra_Base {
    
    public  Compra() {
        super();
        setCusto(0);
        setConfirma(new Integer(0));
    }
   
    @Override
    public void setCliente(Cliente c){
    	c.addCompra(this);
    	 
 }

    /**
      *	Adiciona um prato ao carrinho de compras.
      *	Se o prato ja' existir, muda a quantidade desse prato.
      *	Se a quantidade resultante for negativa, remove o custo do prato na encomenda
      * e remove-o.
      *
      *	@param prato 	que se quer adicionar 'a compra
      *	@param quantidadePrato		valor da quantidade relativo ao prato
      *
      */
    public void adicionaItem(Prato prato, Integer quantidadePrato){
        Item item = getItemPorPrato(prato);    
		if (item == null){
			if (quantidadePrato > 0){
				super.addItem(new Item(prato,quantidadePrato));
			    this.somaCusto(getItemPorPrato(prato),quantidadePrato);
            }
			return;
		}
		item.mudaQuantidade(quantidadePrato);
    
		
		if (item.getQuantidade() <= 0){
			final int custoCompraSemItem = item.getQuantidade() - quantidadePrato;
			this.somaCusto(item,(-custoCompraSemItem));
			super.removeItem(item);			
		}
		else this.somaCusto(item, quantidadePrato);

    }
    /**
    *   Comunica a fatura para finalizar a compra 
    *
    */    

    public void retiraFatura(Fatura fatura){ 
        for (Item item: this.getItemSet()){
            ItemFatura itemF = new ItemFatura();
            itemF.setDescricao(item.getPrato().getNome());
            itemF.setQuantidade(item.getQuantidade());
            itemF.setPreco(item.getPrato().getPreco()*item.getQuantidade());
            fatura.getItens().add(itemF);
        }
        fatura.setTotal(this.getCusto());
        fatura.setIva((int)((((float)fatura.getTotal()) * 0.23)/1.23));
        fechaCompra();
    }
    
    @Override
    public void removeItem(Item item){
    	super.removeItem(item);	
    }

    public void somaCusto(Item item, int quantidadePrato){

        final int CUSTO = this.getCusto();
        final int PRECO = item.getPrato().getPreco();
		this.setCusto(CUSTO + (PRECO * quantidadePrato));
    }

    
	public Item getItemPorPrato(Prato prato){
		for (Item i: getItemSet()){
			if (i.isItemDe(prato))
				return i;
		}
		return null;
	}
	
	public boolean compraTemPrato(Prato prato){
		for (Item i: getItemSet()){
			if (i.isItemDe(prato))
				return true;
		}
		return false;
	}
    @Override
	public List<Item> getItem() throws EmptyShoppingTrayException{
		List<Item> list = super.getItem();
    	if (list.isEmpty())
    		throw new EmptyShoppingTrayException();
    	return list;
    }

    public void fechaCompra(){
       this.setConfirma(new Integer(1));
       getCliente().addCompra(new Compra());
    }

}

