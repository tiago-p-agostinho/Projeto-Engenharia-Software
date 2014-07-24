package pt.ist.rest.domain;

public class Alimento extends Alimento_Base {

	@Override
	public void setPrato(Prato prato){
		prato.addAlimento(this);
	}

    public  Alimento(String tipo) {
		super();
    	this.setTipo(tipo);
    }
    
    public boolean isTipo(String tipo){
    	return this.getTipo().equals(tipo);
    }

}
