package pt.ist.rest.domain;

public class Gestor extends Gestor_Base {
    
	public  Gestor() {
		super();
	}

	public Gestor(String nome, String user, String pass){

		super();
		init(user,nome,pass);
	}


	protected void init(String user, String nome, String pass){
		super.init(user, pass);
		setNome(nome);

	}
	@Override
	public void setRestaurante(Restaurante r){
		r.addGestor(this);
	}
	
    public String toString(){
		return "Gestor | "+ this.getNome();
	}
}
