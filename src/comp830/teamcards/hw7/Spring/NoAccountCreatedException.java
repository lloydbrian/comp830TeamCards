package comp830.teamcards.hw7.Spring;

public class NoAccountCreatedException extends Exception {
	
	private String msg;
	
	public NoAccountCreatedException(String msg) {
		super(msg);
		this.msg = msg;
	}
	
	public String getExceptionMessage() {
		return msg;
	}
}
