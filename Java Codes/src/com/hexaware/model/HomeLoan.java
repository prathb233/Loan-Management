package com.hexaware.model;

public class HomeLoan extends Loan {
    private String propertyAddress;
    private int propertyValue;

    // Default constructor
    public HomeLoan() {
        super(); // Call the constructor of the superclass (Loan)
    }

    // Parameterized constructor
    public HomeLoan(int loanId, int customerId, double principalAmount, 
    		double interestRate, int loanTerm, String loanType, String loanStatus,
    		String propertyAddress, int propertyValue) 
    {
        super(loanId, customerId, principalAmount, interestRate, loanTerm, loanType, loanStatus);
        this.propertyAddress = propertyAddress;
        this.propertyValue = propertyValue;
    }

    // Getters and setters for specific attributes of HomeLoan

    public String getPropertyAddress() {
        return propertyAddress;
    }

    public void setPropertyAddress(String propertyAddress) {
        this.propertyAddress = propertyAddress;
    }

    public int getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(int propertyValue) {
        this.propertyValue = propertyValue;
    }

    @Override
    public String toString() {
        return super.toString() + " HomeLoan{" +
                "propertyAddress='" + propertyAddress + '\'' +
                ", propertyValue=" + propertyValue +
                '}';
    }
}
