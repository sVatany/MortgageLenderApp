package test.com.cognizant.tdd;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

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
	
	
	//3 partial qualify 
	@Test
	void testAccetpAndQualifyLoansV2() {
		applicant.setDebtToIncome(24);
		applicant.setCreditScore(880);
		applicant.setSavings(3000);
		lender.addLoanApp(applicant, 20000);
		assertTrue(lender.getApplicantMap().size() == 1);
		
	}
	
	//3 deny 
	@Test
	void testAccetpAndQualifyLoansV3() {
		applicant.setDebtToIncome(48);
		applicant.setCreditScore(880);
		applicant.setSavings(10000);
		lender.addLoanApp(applicant, 20000);
		assertTrue(applicant.getLoanStatus().contentEquals("not qualified"));
		
	}
	
	//5
	@Test
	void testPendingLoan() {
		lender.setAvailableFunds(40000.00);
		applicant.setId(0);
		applicant.setDebtToIncome(24);
		applicant.setCreditScore(880);
		applicant.setSavings(10000);
		lender.addLoanApp(applicant, 20000);
		lender.approveLoan(applicant);
		System.out.println(lender.getPendingLoanAmount());
		assertTrue(lender.getPendingLoanAmount() == 20000);
		
	}
	
	//7
	@Test
	void testExpiredLoans() {
		LenderAccount lenderAccount = new LenderAccount(1, 500000);
		ApplicantAccount applicantAccount = new ApplicantAccount(1, 21, 700, 100000);
		applicantAccount.setLoanAmountRequest(250000);
		//applicantAccount.setLoanStatus("approved");
		//lenderAccount.setPendingLoanAmount(250000);
		//lenderAccount.setAvailableFunds(50000);
		lenderAccount.addLoanApp(applicantAccount);
		lenderAccount.approveLoan(applicantAccount);
		applicantAccount.rejectLoan();
		try {
			TimeUnit.SECONDS.sleep(5);
			lenderAccount.processResponse(applicantAccount);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(lenderAccount.getAvailableFunds(), 500000);
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

	@Test
	void testAcceptLoan() {
		LenderAccount lenderAccount = new LenderAccount(1, 300000);
		ApplicantAccount applicantAccount = new ApplicantAccount(1, 21, 700, 100000);
		applicantAccount.setLoanAmountRequest(250000);
		applicantAccount.setLoanStatus("approved");
		lenderAccount.setPendingLoanAmount(250000);
		lenderAccount.setAvailableFunds(50000);
		applicantAccount.acceptLoan();
		lenderAccount.processResponse(applicantAccount);
		assertEquals("accepted", applicantAccount.getLoanStatus());
		assertEquals(0, lenderAccount.getPendingLoanAmount());
	}
	
	@Test
	void testRejectLoan() {
		LenderAccount lenderAccount = new LenderAccount(1, 300000);
		ApplicantAccount applicantAccount = new ApplicantAccount(1, 21, 700, 100000);
		applicantAccount.setLoanAmountRequest(250000);
		applicantAccount.setLoanStatus("approved");
		lenderAccount.setPendingLoanAmount(250000);
		lenderAccount.setAvailableFunds(50000);
		applicantAccount.rejectLoan();
		lenderAccount.processResponse(applicantAccount);
		assertEquals("rejected", applicantAccount.getLoanStatus());
		assertEquals(0, lenderAccount.getPendingLoanAmount());
		assertEquals(300000, lenderAccount.getAvailableFunds());
	}
	
	@Test
	void testFilterQualifiedLoans() {
		LenderAccount lenderAccount = new LenderAccount(1, 300000);
		ApplicantAccount applicantAccount1 = new ApplicantAccount(1, 21, 700, 100000);
		lenderAccount.addLoanApp(applicantAccount1, 250000);
		ApplicantAccount applicantAccount2 = new ApplicantAccount(2, 30, 700, 50000);
		lenderAccount.addLoanApp(applicantAccount2, 100000);
		List<ApplicantAccount> qualifiedLoans = lenderAccount.filterLoans("qualified");
		assertEquals(qualifiedLoans.get(0), applicantAccount1);
		
	}
	
	@Test 
	void testFilterPartiallyQualifiedLoans() {
		LenderAccount lenderAccount = new LenderAccount(1, 300000);
		ApplicantAccount applicantAccount1 = new ApplicantAccount(1, 21, 700, 100000);
		lenderAccount.addLoanApp(applicantAccount1, 250000);
		ApplicantAccount applicantAccount2 = new ApplicantAccount(2, 30, 700, 50000);
		lenderAccount.addLoanApp(applicantAccount2, 250000);
		List<ApplicantAccount> qualifiedLoans = lenderAccount.filterLoans("partially qualified");
		assertEquals(qualifiedLoans.get(0), applicantAccount2);
	}
	
	@Test 
	void testFilterNotQualifiedLoans() {
		LenderAccount lenderAccount = new LenderAccount(1, 300000);
		ApplicantAccount applicantAccount1 = new ApplicantAccount(1, 21, 700, 100000);
		lenderAccount.addLoanApp(applicantAccount1, 250000);
		ApplicantAccount applicantAccount2 = new ApplicantAccount(2, 37, 700, 100000);
		lenderAccount.addLoanApp(applicantAccount2, 250000);
		List<ApplicantAccount> qualifiedLoans = lenderAccount.filterLoans("not qualified");
		assertEquals(qualifiedLoans.get(0), applicantAccount2);
		
	}
	
	@Test
	void testFilterOnHoldLoans() {
		LenderAccount lenderAccount = new LenderAccount(1, 300000);
		ApplicantAccount applicantAccount1 = new ApplicantAccount(1, 21, 700, 100000);
		lenderAccount.addLoanApp(applicantAccount1, 250000);
		ApplicantAccount applicantAccount2 = new ApplicantAccount(2, 21, 700, 100000);
		lenderAccount.addLoanApp(applicantAccount2, 250000);
		lenderAccount.approveLoan(applicantAccount1);
		lenderAccount.approveLoan(applicantAccount2);
		List<ApplicantAccount> qualifiedLoans = lenderAccount.filterLoans("on hold");
		assertEquals(qualifiedLoans.get(0), applicantAccount2);
	}
	
	@Test 
	void testFilterApprovedLoans() {
		LenderAccount lenderAccount = new LenderAccount(1, 300000);
		ApplicantAccount applicantAccount1 = new ApplicantAccount(1, 21, 700, 100000);
		lenderAccount.addLoanApp(applicantAccount1, 250000);
		ApplicantAccount applicantAccount2 = new ApplicantAccount(2, 21, 700, 100000);
		lenderAccount.addLoanApp(applicantAccount2, 250000);
		lenderAccount.approveLoan(applicantAccount1);
		lenderAccount.approveLoan(applicantAccount2);
		List<ApplicantAccount> qualifiedLoans = lenderAccount.filterLoans("approved");
		assertEquals(qualifiedLoans.get(0), applicantAccount1);
	}
	
	@Test
	void testFilterAcceptedLoans() {
		LenderAccount lenderAccount = new LenderAccount(1, 300000);
		ApplicantAccount applicantAccount1 = new ApplicantAccount(1, 21, 700, 100000);
		lenderAccount.addLoanApp(applicantAccount1, 250000);
		lenderAccount.approveLoan(applicantAccount1);
		applicantAccount1.acceptLoan();
		lenderAccount.processResponse(applicantAccount1);
		List<ApplicantAccount> qualifiedLoans = lenderAccount.filterLoans("accepted");
		assertEquals(qualifiedLoans.get(0), applicantAccount1);
	}
	
	@Test 
	void testFilterRejectedLoans() {
		LenderAccount lenderAccount = new LenderAccount(1, 300000);
		ApplicantAccount applicantAccount1 = new ApplicantAccount(1, 21, 700, 100000);
		lenderAccount.addLoanApp(applicantAccount1, 250000);
		lenderAccount.approveLoan(applicantAccount1);
		applicantAccount1.rejectLoan();
		lenderAccount.processResponse(applicantAccount1);
		List<ApplicantAccount> qualifiedLoans = lenderAccount.filterLoans("rejected");
		assertEquals(qualifiedLoans.get(0), applicantAccount1);
	}
}
