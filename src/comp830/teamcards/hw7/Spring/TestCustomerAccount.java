/**
 * 
 */
package comp830.teamcards.hw7.Spring;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

import org.junit.Rule;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import comp830.teamcards.hw7.Spring.*;

import java.sql.SQLException;

/**
 * @author lloydbriantech 2019
 *
 */
class TestCustomerAccount {

	private final String[] gofErich = {"Erich Gamma", "1112223333"};
	private final String[] gofRichard = {"Richard Helm", "2223334444"};
	private final String[] gofRalph = {"Ralph Johsnon", "3334445555"};
	private final String[] gofJohn = {"John Vlissides", "4445556666"};
	
	
	
	CustomerAccount custAcct;
	CustomerAccount custAcctMock;
	CustomerAccountDAO custDAOMock;
	

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
		custAcct = null;
		custAcctMock = null;
		custDAOMock = null;

	}

	@Test
	@DisplayName("TestCase01: Basic object creation check using JUnit only.")
	void testCase01() throws SQLException, NoAccountCreatedException  {
		
		CustomerAccount custA = custAcct.createNewAccount(gofErich[0], gofErich[1]);
		
		assertAll("Basic object check: ",
				() -> assertEquals(custA instanceof CustomerAccount, true),
				() -> assertNotEquals(custA.getCustName(), "Gamma"),
				() -> assertEquals(custA.getCustName(), gofErich[0]),
				() -> assertEquals(custA.getCustPhone(),  gofErich[1])
		);		
		
	}
	
	@Test
	@DisplayName("TestCase02: Basic object update check using JUnit only.")
	void testCase02() throws NoSuchCustomerAccountException, NoAccountCreatedException, SQLException {
		CustomerAccount custC = custAcct.createNewAccount(gofRalph[0], gofErich[1]);
		
		String custCAcctNum = custC.getCustAcctNumber();
		
		assertAll("Basic object update check: ",
				() -> assertNotNull(custC.updateCustomerName(custC.getCustAcctNumber(), "Johnson Ralph")),
				() -> assertEquals(custC.updateCustomerName(custCAcctNum, "Johnson Ralph").getCustAcctNumber(), custCAcctNum),		
				() -> assertEquals(custC.getCustName(), "Johnson Ralph")		
		);
		
		
	}

	@Test
	@DisplayName("TestCase03: Test exception handling for object creation")
	void testCase03() throws SQLException, NoAccountCreatedException  {
		
		String message = String.format("testCase03: Account for %s at %s not created", gofRichard[0], gofRichard[1]);

		doThrow(new NoAccountCreatedException(message)).when(custAcctMock).createNewAccount(gofRichard[0], gofRichard[1]);
		
		try {
			custAcctMock.createNewAccount(gofRichard[0], gofRichard[1]);			
		} catch (NoAccountCreatedException e) {
			assertEquals(e.getExceptionMessage(), message);
		} 
	}
	
	@Test
	@DisplayName("TestCase04: Test exception handling for object creation scenario2")
	void testCase04() {
		
		try {
			when((custAcctMock)
					.createNewAccount(gofJohn[0], gofJohn[1]))
					.thenThrow(new NoAccountCreatedException(gofJohn[0] + " not created."));
			custAcctMock.createNewAccount(gofJohn[0], gofJohn[1]);
		} catch (NoAccountCreatedException e) {
			assertTrue(e != null);
		} catch (SQLException sE) {
			fail("SQLException is thrown instead of NoAccountCreatedException");
		}
	}

	
}
