package pt.ist.rest.service.dto;


public class FaturaDto implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	public int seqNum;
	public int serNum;
	
	public FaturaDto() {}

	public FaturaDto(int serNum, int seqNum) {

		this.seqNum = seqNum;
		this.serNum = serNum;
	}

}
	