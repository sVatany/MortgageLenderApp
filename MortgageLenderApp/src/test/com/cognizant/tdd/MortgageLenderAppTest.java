package test.com.cognizant.tdd;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.cognizant.tdd.LenderAccount;
import com.cognizant.tdd.ApplicantAccount;
import com.cognizant.tdd.Loan;


public class MortgageLenderAppTest {
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}
	
	@Test
	void testCheckLenderBalance() {
		fail("this will fail");
	}
	
	@Test 
	void testDepositToAccount() {
		LenderAccount lenderAccount = new LenderAccount(1, 100000);
		lenderAccount.depositToAccount(50000);
		assertEquals(150000, lenderAccount.getAvailableFunds());
	}
	
	//cannot deposit negative amount so available funds will remain the same
	@Test
	void testDepositNegativeAmountToAccount() {
		LenderAccount lenderAccount = new LenderAccount(1, 100000);
		lenderAccount.depositToAccount(-500);
		assertEquals(100000, lenderAccount.getAvailableFunds());
	}
	
	@Test 
	void testApproveQualifiedLoans() {
		LenderAccount lenderAccount = new LenderAccount(1, 300000);
		ApplicantAccount applicantAccount = new ApplicantAccount(1, 21, 700, 100000);
		applicantAccount.setLoanAmountRequest(250000);
		lenderAccount.qualifyLoan(applicantAccount);
		lenderAccount.approveLoan(applicantAccount);
		assertEquals(lenderAccount.getApplications().get(0).getLoanStatus(), "approved");
	}
	
	@Test 
	void testApprovePartiallyQualifiedLoans() {
		LenderAccount lenderAccount = new LenderAccount(1, 300000);
		ApplicantAccount applicantAccount = new ApplicantAccount(1, 30, 700, 50000);
		applicantAccount.setLoanAmountRequest(250000);
		lenderAccount.qualifyLoan(applicantAccount);
		lenderAccount.approveLoan(applicantAccount);
		assertEquals(lenderAccount.getApplications().get(0).getLoanStatus(), "approved");
	}
	
	@Test
	void testOnHoldQualifiedLoans() {
		LenderAccount lenderAccount = new LenderAccount(1, 100000);
		ApplicantAccount applicantAccount = new ApplicantAccount(1, 21, 700, 100000);
		applicantAccount.setLoanAmountRequest(200000);
		lenderAccount.qualifyLoan(applicantAccount);
		lenderAccount.approveLoan(applicantAccount);
		assertEquals(lenderAccount.getApplications().get(0).getLoanStatus(), "on hold");
	}
	
	@Test 
	void testNotQualifiedLoans() {
		LenderAccount lenderAccount = new LenderAccount(1, 100000);
		ApplicantAccount applicantAccount = new ApplicantAccount(1, 37, 700, 100000);
		applicantAccount.setLoanAmountRequest(250000);
		lenderAccount.qualifyLoan(applicantAccount);
		lenderAccount.approveLoan(applicantAccount);
		assertEquals(lenderAccount.getApplications().get(0).getLoanStatus(), "not qualified");
	}

}
