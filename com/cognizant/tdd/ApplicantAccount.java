package com.cognizant.tdd;

public class ApplicantAccount {
	
	private int id;
	private int debtToIncome;
	private int creditScore;
	private int savings;
	private double loanAmountRequest;
	public double getLoanAmountRequest() {
		return loanAmountRequest;
	}

	public void setLoanAmountRequest(double loanAmountRequest) {
		this.loanAmountRequest = loanAmountRequest;
	}

	public String loanStatus;
	
	public String getLoanStatus() {
		return loanStatus;
	}

	public void setLoanStatus(String loanStatus) {
		this.loanStatus = loanStatus;
	}

	public ApplicantAccount() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ApplicantAccount(int id, int debtToIncome, int creditScore, int savings) {
		super();
		this.id = id;
		this.debtToIncome = debtToIncome;
		this.creditScore = creditScore;
		this.savings = savings;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDebtToIncome() {
		return debtToIncome;
	}

	public void setDebtToIncome(int debtToIncome) {
		this.debtToIncome = debtToIncome;
	}

	public int getCreditScore() {
		return creditScore;
	}

	public void setCreditScore(int creditScore) {
		this.creditScore = creditScore;
	}

	public int getSavings() {
		return savings;
	}

	public void setSavings(int savings) {
		this.savings = savings;
	}

	public void acceptLoan() {
		if (loanStatus.equalsIgnoreCase("approved")) {
			loanStatus = "accepted";
		}
	}
	
	public void rejectLoan() {
		if (loanStatus.equalsIgnoreCase("approved")) {
			loanStatus = "rejected";
		}
	}
	@Override
	public String toString() {
		return "ApplicantAccount [id=" + id + ", debtToIncome=" + debtToIncome + ", creditScore=" + creditScore
				+ ", savings=" + savings + "]";
	}
	
	

}
