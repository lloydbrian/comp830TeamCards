/**
 * 
 */
package comp830.teamcards.hw7.Spring;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import comp830.teamcards.hw7.Spring.*;

import java.sql.SQLException;

/**
 * @author lloydbriantech 2019
 *
 */
class TestCustomerAccount {

	CustomerAccount custAcct;
	CustomerAccount custAcctMock;
	CustomerAccountDAO custDAOMock;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		custAcct = new CustomerAccount();
		custAcctMock = mock(CustomerAccount.class);
		custDAOMock = mock(CustomerAccountDAO.class);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@DisplayName("TestCase01: Basic object creation check")
	void testCase01() throws SQLException, NoAccountCreatedException  {
		
		/*
		CustomerAccount custB = custAcct.createNewAccount("Richard Helm", "2223334444");
		CustomerAccount custC = custAcct.createNewAccount("Ralph Johnson", "3334445555");
		CustomerAccount custD = custAcct.createNewAccount("John Vlissides", "4445556666");
		*/
		
		//when(custDAOMock.newAcctNumber("Erich Gamma", "1112223333")).thenReturn("123456789");
		//when(custAcctMock.createNewAccount("Erich Gamma", "1112223333")).thenReturn(custA);
		CustomerAccount custA = custAcct.createNewAccount("Erich Gamma", "1112223333");
		
		assertAll("Basic object check: ",
				() -> assertEquals(custA instanceof CustomerAccount, true),
				() -> assertNotEquals(custA.getCustName(), "Gamma"),
				() -> assertEquals(custA.getCustName(), "Erich Gamma"),
				() -> assertEquals(custA.getCustPhone(),  "1112223333")
		);		
		
	}
	
	@Test
	@DisplayName("TestCase02: Basic object update check")
	void testCase02() throws NoSuchCustomerAccountException, NoAccountCreatedException, SQLException {
		CustomerAccount custC = custAcct.createNewAccount("Ralph Johnson", "3334445555");
		
		String custCAcctNum = custC.getCustAcctNumber();
		
		assertAll("Basic object update check: ",
				() -> assertNotNull(custC.updateCustomerName(custC.getCustAcctNumber(), "Johnson Ralph")),
				() -> assertEquals(custC.updateCustomerName(custCAcctNum, "Johnson Ralph").getCustAcctNumber(), custCAcctNum),		
				() -> assertEquals(custC.getCustName(), "Johnson Ralph")		
		);
		
		
	}

}
