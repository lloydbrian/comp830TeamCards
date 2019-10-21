package comp830.teamcards.hw7.Spring;

/*
 * @updated
 * Lloyd Dagoc 2019
 * Surya Kranthi 2019
 * Valerie Therrien 2019
 */
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
