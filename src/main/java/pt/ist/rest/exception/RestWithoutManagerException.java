package pt.ist.rest.exception;

public class RestWithoutManagerException extends RestException {
	
	private static final long serialVersionUID = 1L;

	private String name;

	public RestWithoutManagerException(String name) {
		this.name = name;
	}
	public RestWithoutManagerException() {}
	
	public String getManagerName() {
		return this.name;
	}
}
