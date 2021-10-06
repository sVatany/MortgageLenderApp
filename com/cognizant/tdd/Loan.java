package com.cognizant.tdd;

public interface Loan {
	
	public abstract String qualifyLoan(ApplicantAccount account);
	
	public abstract void approveLoan(ApplicantAccount account);

}
