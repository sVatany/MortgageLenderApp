package test.com.cognizant.tdd;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.cognizant.tdd.LenderAccount;
import com.cognizant.tdd.ApplicantAccount;
import com.cognizant.tdd.Loan;


public class MortgageLenderAppTest {
	
	static LenderAccount lender;
	static ApplicantAccount applicant;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		lender = new LenderAccount();
		applicant = new ApplicantAccount();
	}
	
	//1
	@Test
	void testCheckLenderBalance() {
		lender.setAvailableFunds(1000.00);
		assertTrue (lender.getAvailableFunds() == 1000.00);
	}
		
	//3
	@Test
	void testAccetpAndQualifyLoans() {
		applicant.setDebtToIncome(24);
		applicant.setCreditScore(880);
		applicant.setSavings(10000);
		lender.addLoanApp(applicant, 20000);
		assertTrue(lender.getApplicantMap().size() == 1);
	}
	
	/*
	//3 partial qualify 
	@Test
	void testAccetpAndQualifyLoansV2() {
		applicant.setDebtToIncome(24);
		applicant.setCreditScore(880);
		applicant.setSavings(3000);
		lender.addLoanApp(applicant, 20000);
		assertTrue(lender.getApprovedLoans().size() == 1);
		
	}
	
	//3 deny 
	@Test
	void testAccetpAndQualifyLoansV3() {
		applicant.setDebtToIncome(48);
		applicant.setCreditScore(880);
		applicant.setSavings(10000);
		lender.addLoanApp(applicant, 20000);
		assertTrue(lender.getApprovedLoans().size() == 0);
		
	}*/
	
	//5
	@Test
	void testPendingLoan() {
		lender.setAvailableFunds(40000.00);
		applicant.setId(0);
		applicant.setDebtToIncome(24);
		applicant.setCreditScore(880);
		applicant.setSavings(10000);
		lender.addLoanApp(applicant, 20000);
		//lender.moveFunds();
		assertEquals(lender.getAvailableFunds(), 10000);
		
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
		applicantAccount.setLoanStatus("qualified");
		lenderAccount.approveLoan(applicantAccount);
		assertEquals(lenderAccount.getApplicantMap().get(1).getLoanStatus(), "approved");
	}
	
	@Test 
	void testApprovePartiallyQualifiedLoans() {
		LenderAccount lenderAccount = new LenderAccount(1, 300000);
		ApplicantAccount applicantAccount = new ApplicantAccount(1, 30, 700, 50000);
		applicantAccount.setLoanAmountRequest(250000);
		applicantAccount.setLoanStatus("partially qualified");
		lenderAccount.approveLoan(applicantAccount);
		assertEquals(lenderAccount.getApplicantMap().get(1).getLoanStatus(), "approved");
	}
	
	@Test
	void testOnHoldQualifiedLoans() {
		LenderAccount lenderAccount = new LenderAccount(1, 100000);
		ApplicantAccount applicantAccount = new ApplicantAccount(1, 21, 700, 100000);
		applicantAccount.setLoanAmountRequest(200000);
		applicantAccount.setLoanStatus("qualified");
		lenderAccount.approveLoan(applicantAccount);
		assertEquals(lenderAccount.getApplicantMap().get(1).getLoanStatus(), "on hold");
	}
	
	@Test 
	void testNotQualifiedLoans() {
		LenderAccount lenderAccount = new LenderAccount(1, 100000);
		ApplicantAccount applicantAccount = new ApplicantAccount(1, 37, 700, 100000);
		applicantAccount.setLoanAmountRequest(250000);
		applicantAccount.setLoanStatus("not qualified");
		lenderAccount.approveLoan(applicantAccount);
		assertEquals(lenderAccount.getApplicantMap().containsKey(1), false);
	}

}
