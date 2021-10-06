package com.cognizant.tdd;

import java.util.List;

public class LenderAccount implements Loan {
	
	public int id;
	public double availableFunds;
	public double pendingLoanAmount;
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


	public String depositToAccount(double amount) {
		
		return null;
	}


	@Override
	public String qualifyLoan(ApplicantAccount aa) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public double approveLoan(ApplicantAccount aa) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public String viewLoans(String status) {
		
		return null;
	}


}
