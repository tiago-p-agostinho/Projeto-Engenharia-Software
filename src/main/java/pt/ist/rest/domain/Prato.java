package pt.ist.rest.domain;

import java.util.Set;

public class Prato extends Prato_Base {
   
    public final static String CARNE = "carne";
    public final static String PEIXE = "peixe";
    public final static String VEGETARIANO = "vegetariano";

	
    public  Prato(String nome, Integer preco, Integer calorias,Integer IDPrato) {
        super();
        setNome(nome);
        setPreco(preco);
        setCalorias(calorias);
		setIDPrato(IDPrato);
        
    }    
    @Override
    public void setRestaurante(Restaurante r){
    	r.addPrato(this);
    }
    public int calculaClassificacao(){
    	return  getClienteCount(); 
   }
    /**
     * 	Procura uma cadeira de caracteres especifica no nome do Prato.
     * 	
     * @param substring	cadeia de caracteres a procurar
     */
    public boolean containsSubstring(String substring){
    	return this.getNome().toLowerCase().contains(substring.toLowerCase());
    }


    public boolean isTipo(String tipo){

        for (Alimento a: getAlimentoSet()){
            if (!tipo.equals(VEGETARIANO) && a.getTipo().equals(tipo))
                return true;            //procura prato CARNE ou PEIXE encontra 1 alimento carne ou peixe
            if (tipo.equals(VEGETARIANO) && !a.getTipo().equals(tipo))
                return false;           //procura prato VEGETARIANO, nao encontra
        }
        return tipo.equals(VEGETARIANO);  //prato VEGETARIANO
    }

    /*
     * Metodos auxiliares para manipular os Alimentos dos pratos, 
     * 
     */
    @Override
    public void addAlimento(Alimento alimento){
    	if (!this.hasAlimento(alimento))
    		super.addAlimento(alimento);	
    }
    /** 
     * Adiciona um Set de alimentos ao prato.
     * O metodo addAlimento(Alimento) verifica se o alimento ja existe
     * 
     * @param alimentos 	Set de alimentos a adicionar ao prato
     * 
     */
    public void addAlimentos(Set<Alimento> alimentos){
    	for (Alimento a: alimentos){
    		this.addAlimento(a);
    	}
    }
    
    
    public String toString(){
    	return getNome() + "|" + getCalorias() + "|" + getPreco() + "|" + Integer.toString(calculaClassificacao());
    }
    
}