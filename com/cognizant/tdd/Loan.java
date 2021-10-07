package com.cognizant.tdd;

public interface Loan {
	
	public abstract void addLoanApp(ApplicantAccount account, double amount);
	
	public abstract void approveLoan(ApplicantAccount account);

}
