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
	
	@Override
	public void addLoanApp(ApplicantAccount applicant, double amount) {
		String status;
		double loanAmount;
		
		if (applicant.getDebtToIncome() <= 36 
				&& applicant.getSavings() / amount >= 0.25
				&& applicant.getCreditScore() > 620) {
			status = "qualified";
			loanAmount = amount;
			applicant.setLoanStatus(status);
			applicant.setLoanAmountRequest(loanAmount);
			this.applicantMap.put(applicant.getId(), applicant);
		}
		else if (applicant.getDebtToIncome() <= 36  && applicant.getCreditScore() > 620) {
			status = "partially qualified";
			loanAmount = amount / 4;
			applicant.setLoanStatus(status);
			applicant.setLoanAmountRequest(loanAmount);
			this.applicantMap.put(applicant.getId(), applicant);
		}
		else {
			status = "not qualified";
			loanAmount = 0;
			applicant.setLoanStatus(status);
			applicant.setLoanAmountRequest(loanAmount);
			this.applicantMap.put(applicant.getId(), applicant);
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
	
	public List<ApplicantAccount> filterLoans(String loanStatus) {
		List<ApplicantAccount> filteredApplicants = new ArrayList<>();
		for (Integer key : applicantMap.keySet()) {
			if (applicantMap.get(key).getLoanStatus().equalsIgnoreCase(loanStatus)) {
				filteredApplicants.add(applicantMap.get(key));
			}
		}
		return filteredApplicants;
	}


}
