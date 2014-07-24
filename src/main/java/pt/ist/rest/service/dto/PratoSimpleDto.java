package pt.ist.rest.service.dto;

public class PratoSimpleDto implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	private String atributo;
	private boolean  customString = false;

	public PratoSimpleDto() {}
	
	public PratoSimpleDto(String atributo) {
		this.atributo = atributo;
	}

	public String getAtributo() {
		return atributo;
	}
	
	public boolean customCheck() {
		return customString;
	}
	public void setCustomCheck(boolean value) {
		this.customString = value;
	}
	
}
