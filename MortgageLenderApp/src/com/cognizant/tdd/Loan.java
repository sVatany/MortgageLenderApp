package com.cognizant.tdd;

public interface Loan {
	
	public abstract String qualifyLoan(ApplicantAccount aa);
	
	public abstract double approveLoan(ApplicantAccount aa);

}
