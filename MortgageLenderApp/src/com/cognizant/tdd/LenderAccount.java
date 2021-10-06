package com.cognizant.tdd;

import java.util.List;

public class LenderAccount implements Loan {
	
	public int id;
	public double availableFunds;
	public double pendingLoanAmount;
	public List<ApplicantAccount> applications;
	public List<ApplicantAccount> approvedLoans;
	public List<ApplicantAccount> pendingLoans;
	
	

	public List<ApplicantAccount> getApprovedLoans() {
		return approvedLoans;
	}


	public void setApprovedLoans(List<ApplicantAccount> approvedLoans) {
		this.approvedLoans = approvedLoans;
	}


	public List<ApplicantAccount> getPendingLoans() {
		return pendingLoans;
	}


	public void setPendingLoans(List<ApplicantAccount> pendingLoans) {
		this.pendingLoans = pendingLoans;
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
				applications.add(account);
				pendingLoanAmount += account.getLoanAmountRequest();
				availableFunds -= account.getLoanAmountRequest();
			}
			else {
				account.setLoanStatus("on hold");
				applications.add(account);
			}
		}
		else {
			System.out.println("Do not proceed");
		}
		
	}
	
	public List<ApplicantAccount> getApplications() {
		return applications;
	}


	public void setApplications(List<ApplicantAccount> applications) {
		this.applications = applications;
	}


	public String viewLoans(String status) {
		
		return null;
	}


}
