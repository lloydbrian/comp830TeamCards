package comp830.teamcards.hw7.Spring;

import java.sql.SQLException;

// Uses the CustomerAccountDAO to save to the database

/**
 * 
 * @updated
 * Lloyd Dagoc 2019
 * Surya Kranthi 2019
 * Valerie Therrien 2019
 *
 */
public class CustomerAccount {
	
	private String custName;
	private String custPhone;
	private String custAcctNumber;
	
	public CustomerAccount() {
		// create empty CustomerAccount
	}
		
	/**
	 * @return the custName
	 */
	public String getCustName() {
		return custName;
	}

	/**
	 * @param custName the custName to set
	 */
	public void setCustName(String custName) {
		this.custName = custName;
	}

	/**
	 * @return the custPhone
	 */
	public String getCustPhone() {
		return custPhone;
	}

	/**
	 * @param custPhone the custPhone to set
	 */
	public void setCustPhone(String custPhone) {
		this.custPhone = custPhone;
	}

	/**
	 * @return the custAcctNumber
	 */
	public String getCustAcctNumber() {
		return custAcctNumber;
	}

	/**
	 * @param custAcctNumber the custAcctNumber to set
	 */
	public void setCustAcctNumber(String custAcctNumber) {
		this.custAcctNumber = custAcctNumber;
	}

	public CustomerAccount createNewAccount(String name, String phone) throws SQLException, NoAccountCreatedException {
		/*
		 *  Updated from: 
		 *  CustomerAccount newAcct = null;
		 *  FixID: TestCase01
		 */
		CustomerAccount newAcct = new CustomerAccount();
		CustomerAccountDAO cad = new CustomerAccountDAO();
		String acctNum = "";
		
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
		
		/* Updated from:
		 * custName = name;
		 * custPhone = phone;
		 * custAcctNumber = acctNum;
		 * FixID: TestCase01
		 */
		newAcct.setCustAcctNumber(acctNum);
		newAcct.setCustName(name);
		newAcct.setCustPhone(phone);
		
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
		String origCustName = this.custName;
		
		try {
			cad.updateAccount(this);
		} catch (SQLException se) {
			// unable to find the record to be updated
			throw new NoSuchCustomerAccountException(String.format("No customer record with acctount number %s ", acctNum));
		}

		// successful run needs an update on the object custName
		custName = name;

		// Updated from return null
		// FixID: TestCase02
		return this;
	}
	
	public CustomerAccount getCustomerAccount(String acctNum) throws NoSuchCustomerAccountException {
		CustomerAccount cAcct;
		CustomerAccountDAO cad = new CustomerAccountDAO();
		
		try {
			cAcct = cad.getAccount(acctNum);
		} catch (SQLException sE) {
			throw new NoSuchCustomerAccountException(String.format("No such account exists for %s", acctNum));
		}
		
		return cAcct;
		
	}

	
	public CustomerAccount deleteCustomerAccount(CustomerAccount ca) throws NoSuchCustomerAccountException {
		CustomerAccountDAO cad = new CustomerAccountDAO();
		CustomerAccount newAcct = new CustomerAccount();
		newAcct = ca;
		
		try {
			cad.deleteAccount(newAcct);
		} catch (SQLException se) {
			// unable to find the record to be deleted
			throw new NoSuchCustomerAccountException(String.format("No customer record with customer name %s ", ca.getCustName()));
		}
		
		return ca;
	}


}
