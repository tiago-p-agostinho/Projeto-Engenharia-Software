package pt.ist.rest.domain;

import pt.ist.rest.exception.*;

public class Cliente extends Cliente_Base {
    
	private final static int MAX_GOSTOS = 15;  
    
    
    public  Cliente() {
        super();

    }
    
     public Cliente(String nome, String morada, String user, String mail, String pass,Integer nif){
    	
    	super();
    	init(user,nome,pass,mail,morada,nif);
    }
    
    
    protected void init(String user, String nome, String pass, String mail, String morada,Integer nif) throws NifInvalidoException {
    	super.init(user, pass);
    	setNome(nome);
    	setEmail(mail);
    	setMorada(morada);
    	
        if(nif.toString().length() == 4)
    	  setNif(nif);
        else
        	throw new NifInvalidoException(user); 
    	
        setSaldo(0);
    		
    }

    public Compra getCompraAberta(){
        for (Compra c:getCompraSet())
            if (c.getConfirma() == 0)
                return c;
        this.addCompra(new Compra());
        assert (getCompraAberta() != null) : "A compra nao existe";
        return getCompraAberta(); 
    }
    
    public void adicionaItemACompra(Prato itemComprado,int quantidadePrato){

        final Compra compra = getCompraAberta();
        compra.adicionaItem(itemComprado,quantidadePrato);
    }

    @Override   
    public void setSaldo(Integer saldo) throws NegativeBalanceException{
        if(saldo < 0)
            throw new NegativeBalanceException(this.getSaldo());
        super.setSaldo(saldo);
    }
    
    public void addSaldo(int diferencaSaldo) throws NegativeBalanceException{
    	
    	this.setSaldo(this.getSaldo()+diferencaSaldo);
    }
        
    @Override
    public void addCompra(Compra compraParaAdicionar) { 

      super.addCompra(compraParaAdicionar);	
    }
    @Override
    public void setRest(Rest r){
    	r.addCliente(this);
    } 
    /**
      *	Operacao para finalizar um compra, verificando se nao esta' vazia e
      * se o cliente tem saldo, a compra nao fica fechada ate ser pedida a fatura
      * 
      * @throws NegativeBalanceException Cliente nao tem saldo para efectuar a compra
      * @throws EmptyShoppingTrayException Cliente nao tem items na compra
      */
    public void confirmaCompra() throws NegativeBalanceException,
    									EmptyShoppingTrayException{

		if (!hasComprasAberta())
			throw new EmptyShoppingTrayException(); 
		
		final Compra compra = this.getCompraAberta();
		final boolean compraTemItems = (compra.getItemCount() > 0);

		if (!compraTemItems)
			throw new EmptyShoppingTrayException(); 
			 
        final int CUSTO = compra.getCusto();
        final int SALDO = getSaldo();
        
        this.setSaldo(SALDO - CUSTO);
        assert (SALDO - CUSTO)>=0;
        

    }


    
    public boolean hasComprasAberta(){

        for (Compra c:getCompraSet())
            if (c.getConfirma() == 0)
                return true;

        return false;
    }
    
    @Override
    public void removeCompra(Compra compra){
    	
    	super.removeCompra(compra);
    		
    }
    
    
    public void imprimeTabuleiro() throws EmptyShoppingTrayException{
    	if(getCompraAberta().hasAnyItem()){

    		for(Item i: getCompraAberta().getItemSet()){
    			System.out.println(i.getPrato().getNome() +"|"+ i.getQuantidade());		
    		}
    	}
    	else throw new EmptyShoppingTrayException();
    }
    
    
    public void addGosto(Prato p) throws LikesNumberExceedException, ClientAlreadyLikesDishException{
    	if(getPratoCount() > MAX_GOSTOS)
    		throw new LikesNumberExceedException(getPratoCount());
			
    	else if (hasPrato(p))
			throw new ClientAlreadyLikesDishException(super.getUsername(),p.getNome());
			
    	else 
			super.addPrato(p);		
    }

    
    public void removeGosto(Prato pratoRemove){

    	for(Prato p: getPratoSet()){
        	if(p.equals(pratoRemove))
    			super.removePrato(p);   

    	}
    }
	
	
    public void substituiGosto(Prato pratoIn,Prato pratoOut){
    	
    	removeGosto(pratoOut);
    	addGosto(pratoIn);
    }
	
	public String toString(){
		return "Cliente | "+ this.getNome();
	}
    
  }
