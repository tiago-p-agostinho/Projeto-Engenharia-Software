package pt.ist.rest.exception;

public class LikesNumberExceedException extends RestException {
	
	private static final long serialVersionUID = 1L;

	private int likesNumber;

	public LikesNumberExceedException(int likesNumber) {
		this.likesNumber = likesNumber;
	}
	public LikesNumberExceedException() {}
	
	public int getLikesNumber() {
		return this.likesNumber;
	}
}
