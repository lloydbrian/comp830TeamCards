package comp830.teamcards.hw7.Spring;

import java.sql.SQLException;

// Uses the CustomerAccountDAO to save to the database

public class CustomerAccount {
	
	private String custName;
	private String custPhone;
	private String custAcctNumber;
	
	public CustomerAccount() {
		// create empty CustomerAccount
	}
	
	public CustomerAccount createNewAccount(String name, String phone) throws SQLException, NoAccountCreatedException {
		CustomerAccount newAcct = null;
		String acctNum = "";
		
		CustomerAccountDAO cad = new CustomerAccountDAO();
		try {
			acctNum = cad.newAcctNumber(name, phone);
		} catch (SQLException se) {
			// retry the call -- it always works the second time
			try {
				acctNum  = cad.newAcctNumber(name, phone);
			} catch (SQLException se1) {
				throw new NoAccountCreatedException(String.format("Account for %s at %s not created", name, phone));
			}
		}
		
		custName = name;
		custPhone = phone;
		custAcctNumber = acctNum;
		
		try {
			cad.saveAccount(this);
		} catch (SQLException se2) {
			if (se2.getErrorCode() != 803) 
				throw new NoAccountCreatedException(String.format("Account for %s at %s not created with account number %s", name, phone, acctNum));
		}
		
		return newAcct;
	}
	
	public CustomerAccount updateCustomerName(String acctNum, String name) throws NoSuchCustomerAccountException {
		CustomerAccountDAO cad = new CustomerAccountDAO();
		
		try {
			custName = name;
			cad.updateAccount(this);
		} catch (SQLException se) {
			// unable to find the record to be updated
			throw new NoSuchCustomerAccountException(String.format("No customer record with acctount number %s ", acctNum));
		}
		
		return null;
	}

}
