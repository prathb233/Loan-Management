package com.hexaware.repo;

import java.util.Scanner;

import com.hexaware.dao.CustomerDAO;
import com.hexaware.dao.LoanDAO;
import com.hexaware.model.Customer;

public class CustomerRepository {
	Scanner sc = new Scanner(System.in);

	Customer customer;
	CustomerDAO dao = new CustomerDAO();
	LoanDAO loanDAO = new LoanDAO();

	
	public void getCustomerDetails(Scanner sc) {
		dao.getConn();

		customer = new Customer();
		
		System.out.println("Enter Your Name: ");
		String name = sc.next();
		customer.setName(name);
		
		System.out.println("Enter Your Email: ");
		String email = sc.next();
		customer.setEmailAddress(email);
		
		System.out.println("Enter Your Phone: ");
		String phone = sc.next();
		customer.setPhoneNumber(phone);
		
		System.out.println("Enter Your address: ");
		String address = sc.next();
		customer.setAddress(address);
		
		System.out.println("Enter Your Credit Score: ");
		int creditScore = sc.nextInt();
		customer.setCreditScore(creditScore);
		
		System.out.println("Customer details were added succesfully!");
		dao.addCustomer(customer);
		
		
		
	}

}
