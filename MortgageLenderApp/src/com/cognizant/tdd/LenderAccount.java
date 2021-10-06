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
	
	public Map<String, Double> addLoanApp(ApplicantAccount applicant, double amount) {
		// TODO Auto-generated method stub
		String status;
		double loanAmount;
		Map<String, Double> result = new HashMap<>();
		
		if (applicant.getDebtToIncome() <= 36 
				&& applicant.getSavings() / amount >= 0.25 
				&& applicant.getCreditScore() > 620) {
			status = "fully qualify";
			loanAmount = amount;
			//this.approvedLoans.add(applicant);
		}
		else if (applicant.getDebtToIncome() <= 36  && applicant.getCreditScore() > 620) {
			status = "partially qualify";
			loanAmount = amount / 4;
			//this.approvedLoans.add(applicant);
		}
		else {
			status = "loan rejected";
			loanAmount = 0;
		}
		
		result.put(status, loanAmount);
		return result;
	}


}
