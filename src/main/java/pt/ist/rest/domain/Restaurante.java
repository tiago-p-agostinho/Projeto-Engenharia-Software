package pt.ist.rest.domain;

import pt.ist.rest.exception.*;

public class Restaurante extends Restaurante_Base {
	
	private final static int MAX_PRATOS = 15;  


	public  Restaurante(String nome, String morada) {
		super();
		setNome(nome);
		setMorada(morada);

	}
	@Override
	public void setRest(Rest rest){
		rest.addRestaurante(this);
		
	}

	public Gestor procuraGestorPorNome(String user){
		for(Gestor g : getGestorSet()){
			if(g.getUsername().equals(user))
				return g;	
		}
		return null;
	}
	@Override
	public void addGestor(Gestor g) throws BusyManagerException{
		
		if(g.hasRestaurante()){
			throw new BusyManagerException(g.getUsername());
		}
		else super.addGestor(g);
	}
	

    
    public void removeGestor(String username) throws RestWithoutManagerException{
    	Gestor gestor = procuraGestorPorNome(username);
    	if(getGestorCount() < 2)
    		throw new RestWithoutManagerException(username);	
    	else
    		super.removeGestor(gestor);

    }
    
    public Prato procuraPrato(String nome) throws DishNotFoundException{
    	for(Prato p: getPratoSet()){
    		if(p.getNome().equals(nome))
    			return p;
    	}
    	throw new DishNotFoundException(nome,this.getNome());
    }
    @Override
    public void addPrato(Prato p) throws DishesNumberExceedException{
    	this.adicionaPrato(p);
    }
    
    public void adicionaPrato(Prato p) throws DishesNumberExceedException{
	if (getRest().getLimitePrecoPrato() < p.getPreco()){
	    throw new PrecoInvalidoException();
	}	
    	if(getPratoCount() >= MAX_PRATOS)
    		throw new DishesNumberExceedException(getPratoCount());
    	else super.addPrato(p);	
    	
   }
    public int calculaClassificacao(){
	
		int classificacao = 0;
    	int numPratosClassificados = 0;
	
		for(Prato p: getPratoSet()){
    		if(p.calculaClassificacao()>=1)
    		   numPratosClassificados++;	
    	
    		classificacao += p.calculaClassificacao(); 
   		}
		
		final int numPratos = getPratoCount();
    	final float classificacaoMedia = (float)classificacao / (float)numPratos;
    	
		final boolean restauranteTemClassificacao = numPratosClassificados >=3;
		
    	if(restauranteTemClassificacao)
    	   return (int)Math.round(classificacaoMedia);
    	
    	return 0;
	}
    @Override
    public boolean hasGestor(Gestor gestor){
    	for (Gestor g:getGestorSet()){
    		if (g.equals(gestor))
    			return true;
    	}
    	return false;
    }

    public boolean hasGestor(String nomeGestor){
    	return procuraGestorPorNome(nomeGestor)!=null;
    }
    
    public String getPratoDescricao(){

    	String descricoes = "";

    	for(Prato p: getPratoSet()){
    		descricoes += p.getNome() + "|";
    	}
    	return descricoes;
    }

    public String toString(){
    	return getNome() + "|" + getMorada() + "|" + "pratos:" + getPratoDescricao(); 
    }

    
	
	public boolean equals(Restaurante r){
		return this.getNome().equals(r.getNome());
	}
	
	
	
	
}

