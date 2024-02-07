package com.hexaware.exception;

public class InvalidLoanException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public InvalidLoanException () {
		super("Invalid Loan ID");
	}

}
