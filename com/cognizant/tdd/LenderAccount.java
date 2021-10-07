package com.cognizant.tdd;



import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LenderAccount implements Loan {
	
	public int id;
	public double availableFunds;
	public double pendingLoanAmount;
	public Map<Integer, ApplicantAccount> applicantMap = new HashMap<>();

	
	


	public Map<Integer, ApplicantAccount> getApplicantMap() {
		return applicantMap;
	}


	public void setApplicantMap(Map<Integer, ApplicantAccount> applicantMap) {
		this.applicantMap = applicantMap;
	}


	public LenderAccount() {
		super();
		// TODO Auto-generated constructor stub
	}


	public LenderAccount(int id, double availableFunds) {
		super();
		this.id = id;
		this.availableFunds = availableFunds;
	}
	
	

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public double getAvailableFunds() {
		return availableFunds;
	}


	public void setAvailableFunds(double availableFunds) {
		this.availableFunds = availableFunds;
	}


	public double getPendingLoanAmount() {
		return pendingLoanAmount;
	}


	public void setPendingLoanAmount(double pendingLoanAmount) {
		this.pendingLoanAmount = pendingLoanAmount;
	}


	public void depositToAccount(double amount) {
		if (amount > 0) {
			availableFunds += amount;
		}
		
	}


	@Override
	public String qualifyLoan(ApplicantAccount account) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void approveLoan(ApplicantAccount account) {
		if (account.getLoanStatus().equalsIgnoreCase("qualified") 
			|| account.getLoanStatus().equalsIgnoreCase("partially qualified")
			|| account.getLoanStatus().equalsIgnoreCase("on hold")) {
			if (availableFunds >= account.getLoanAmountRequest()) {
				account.setLoanStatus("approved");
				applicantMap.put(account.getId(), account);
				pendingLoanAmount += account.getLoanAmountRequest();
				availableFunds -= account.getLoanAmountRequest();
			}
			else {
				account.setLoanStatus("on hold");
				applicantMap.put(account.getId(), account);
			}
		}
		else {
			System.out.println("Do not proceed");
		}
		
	}


	public String viewLoans(String status) {
		
		return null;
	}
	
	public void addLoanApp(ApplicantAccount applicant) {
		// TODO Auto-generated method stub
		
		if (applicant.getDebtToIncome() <= 36 
				&& applicant.getSavings() / applicant.getLoanAmountRequest() >= 0.25 
				&& applicant.getCreditScore() > 620) {
			applicant.setLoanStatus("qualified");
			applicant.setLoan(applicant.getLoanAmountRequest());
			this.applicantMap.put(applicant.getId(), applicant);
		}
		else if (applicant.getDebtToIncome() <= 36  && applicant.getCreditScore() > 620) {
			applicant.setLoanStatus("partially qualified");
			applicant.setLoan(applicant.getLoanAmountRequest() / 4);
			this.applicantMap.put(applicant.getId(), applicant);
		}
		else {
			applicant.setLoanStatus("loan rejected");
			applicant.setLoan(-1);
 		}
	}
	
	public void processResponse(ApplicantAccount account) {
		applicantMap.put(account.getId(), account);
		if (account.getLoanStatus().equalsIgnoreCase("accepted")) {
			pendingLoanAmount -= account.getLoanAmountRequest();	
		}
		else if (account.getLoanStatus().equalsIgnoreCase("rejected")) {
			pendingLoanAmount -= account.getLoanAmountRequest();
			availableFunds += account.getLoanAmountRequest();
		}
	}


}
