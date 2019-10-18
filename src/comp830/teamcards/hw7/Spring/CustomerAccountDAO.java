package comp830.teamcards.hw7.Spring;

import java.sql.SQLException;


// Because I didn't want to write a real class for something I want you to mock anyway...
public class CustomerAccountDAO {
	
	public boolean saveAccount(CustomerAccount ca) throws SQLException {
		return true;
	}
	public boolean updateAccount(CustomerAccount ca) throws SQLException {
		return true;
	}	
	public boolean deleteAccount(CustomerAccount ca) throws SQLException {
		return true;
	}	
	public CustomerAccount getAccount(String acctNum) throws SQLException {
		return null;
	}
	
	public String newAcctNumber(String name, String phone) throws SQLException {
		return "1111222233334444";
	}
}
