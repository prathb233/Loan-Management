package com.hexaware.repo;

import java.util.List;
import java.util.Scanner;

import com.hexaware.dao.LoanDAO;
import com.hexaware.model.Loan;

public class LoanRepository implements ILoanRepository {
    Scanner scanner = new Scanner(System.in);
    Loan loan = new Loan();
    

	static LoanDAO loanDAO = new LoanDAO();


	public boolean applyHomeLoan() {
		loanDAO.getConn();
		System.out.println("Enter your customer ID: ");
		int customerID = scanner.nextInt();
		loan.setCustomerId(customerID);
		
		loan.setLoanType("HomeLoan");
		loan.setInterestRate(9.5);
		System.out.println("Your Interest rate is: " + loan.getInterestRate());
		
		System.out.println("Enter the tenure period (in months): ");
		int loanTerm = scanner.nextInt();
		loan.setLoanTerm(loanTerm);
		
		System.out.println("Enter the Loan Amount: ");
		double principalAmount = scanner.nextDouble();
		loan.setPrincipalAmount(principalAmount);
		
		loan.setLoanStatus("Pending");
		
		loanDAO.addHomeLoan(loan);
		System.out.println("Loan details added successfully");
		
		double interest = calculateInterest(loan);
		System.out.println("Monthly calculated interest: " + interest);
		
		loanDAO.updateLoanStatus(loan.getLoanId());
		return true;
	}

	public boolean applyCarLoan() {
		loanDAO.getConn();

		System.out.println("Enter your customer ID: ");
		int customerID = scanner.nextInt();
		loan.setCustomerId(customerID);
		
		loan.setLoanType("CarLoan");
		loan.setInterestRate(7.5);
		System.out.println("Your Interest rate is: " + loan.getInterestRate());
		
		System.out.println("Enter the tenure period (in months): ");
		int loanTerm = scanner.nextInt();
		loan.setLoanTerm(loanTerm);
		
		System.out.println("Enter the Loan Amount: ");
		double principalAmount = scanner.nextDouble();
		loan.setPrincipalAmount(principalAmount);
		
		loan.setLoanStatus("Pending");
		
		loanDAO.addCarLoan(loan);
		System.out.println("Loan details added successfully");
		
		double interest = calculateInterest(loan);
		System.out.println("Monthly calculated interest: " + interest);

		return true;
		
	}
	
	public double calculateInterest(Loan loan) {
		
		double interest = (loan.getPrincipalAmount()*
				(loan.getInterestRate()/100)*loan.getLoanTerm())/12; 
		
		return interest;
		
	}

	public static List<Loan> getAllLoan() {
		return loanDAO.getAllLoan();
	}

	public static void getLoanById(int loanId) {
		loanDAO.getLoanById(loanId);		
	}
	
}


