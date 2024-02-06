package com.hexaware.main;

import java.util.List;
import java.util.Scanner;

import com.hexaware.model.Loan;
import com.hexaware.repo.*;

public class LoanManagementApp {
	
    static LoanRepository homeLoan = new LoanRepository();
    static LoanRepository carLoan = new LoanRepository();

    static CustomerRepository customerRepository = new CustomerRepository();

	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Loan Management App!");

        // Get user input for loan type
        System.out.println("Choose the loan type\n");
        System.out.println("1. Home Loan");
        System.out.println("2. Car Loan");
        System.out.print("Enter your choice (1 or 2): ");

        int loanTypeChoice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (loanTypeChoice == 1) {
            // Apply for Home Loan
            System.out.println("Enter customer details for Home Loan:");
            customerRepository.getCustomerDetails(scanner);
            homeLoan.applyHomeLoan();


        } else if (loanTypeChoice == 2) {
            // Apply for Car Loan
            //CarLoan carLoan = new CarLoan();
            System.out.println("Enter customer details for Home Loan:");
            customerRepository.getCustomerDetails(scanner);
            carLoan.applyCarLoan();

        } else {
            System.out.println("Invalid choice. Exiting...");
        }
        
        
        displayLoanOptions(scanner);

        scanner.close();
    }
	
	private static void displayLoanOptions(Scanner scanner) {
        System.out.println("\nChoose an option to view loans:");
        System.out.println("3. Display all loans");
        System.out.println("4. Display loan by ID");
        System.out.print("Enter your choice (3 or 4): ");

        int optionChoice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        switch (optionChoice) {
            case 3:
                displayAllLoans();
                break;
            case 4:
                displayLoanById(scanner);
                break;
            default:
                System.out.println("Invalid choice. Exiting...");
        }
    }

    private static void displayAllLoans() {
        System.out.println("\nAll Loans:");
        List<Loan> allLoans = LoanRepository.getAllLoan();
        for (Loan loan : allLoans) {
            System.out.println(loan);
        }
    }

    private static void displayLoanById(Scanner scanner) {
        System.out.print("\nEnter the Loan ID to view details: ");
        int loanId = scanner.nextInt();
        LoanRepository.getLoanById(loanId);
    }
}


