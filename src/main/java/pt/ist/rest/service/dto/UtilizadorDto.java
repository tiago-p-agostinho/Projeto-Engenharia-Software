package pt.ist.rest.service.dto;

public class UtilizadorDto implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private  String pass;
	private  String user;
	
	
	protected UtilizadorDto() {}

	public UtilizadorDto(String user, String pass) {
		this.pass = pass;
		this.user = user;
		
	}
	
	public final String getPass() {
		return pass;
	}

	public final String getUser() {
		return user;
	}
	
	
}
	
	
	
	