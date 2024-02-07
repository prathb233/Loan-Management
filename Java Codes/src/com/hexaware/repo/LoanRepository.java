package com.hexaware.repo;

import java.util.List;
import java.util.Scanner;

import com.hexaware.dao.LoanDAO;
import com.hexaware.exception.InvalidLoanException;
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
		loanDAO.getConn();

		return loanDAO.getAllLoan();
	}

	public void getLoanById(Scanner scanner) {
		loanDAO.getConn();

	    int loanId = 0;
	    boolean isValidInput = false;

	    do {
	        try {
	            System.out.print("\nEnter the Loan ID to view details: ");
	            loanId = scanner.nextInt();
	    		loanDAO.getLoanById(loanId);
	            isValidInput = true; // Set to true if no exception is thrown
	        } catch (InvalidLoanException e) {
	            System.out.println(e.getMessage() + "\nLoan with ID " + loanId + " not found. Please try again.");
	            isValidInput = false; // Set to false to repeat the loop

	        }
	    } while (!isValidInput);
	}
	
	public double calculateEMI(int loanId) {
		loanDAO.getConn();

	    Loan loan = loanDAO.getLoanById(loanId);  // Fetch details from the database


	    double principalAmount = loan.getPrincipalAmount();
	    double interestRate = loan.getInterestRate() / 100 / 12; // Monthly interest rate
	    int loanTerm = loan.getLoanTerm();

	    return calculateEMI(principalAmount, interestRate, loanTerm);
	}

	public double calculateEMI(double principalAmount, double interestRate, int loanTerm) {
	    double monthlyInterestRate = interestRate / 12;

	    double emi = (principalAmount * monthlyInterestRate * Math.pow((1 + monthlyInterestRate), loanTerm))
	            / (Math.pow((1 + monthlyInterestRate), loanTerm) - 1);

	    return emi;
	}
	
}


