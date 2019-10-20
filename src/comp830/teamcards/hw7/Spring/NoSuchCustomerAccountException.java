package comp830.teamcards.hw7.Spring;

public class NoSuchCustomerAccountException extends Exception {
	
	private String msg;
	
	public NoSuchCustomerAccountException(String msg) {
		super(msg);
		this.msg = msg;
	}
	
	public String getExceptionMessage() {
		return msg;
	}

}
