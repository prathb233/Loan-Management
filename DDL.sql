CREATE DATABASE LoanManagement;
USE LoanManagement;

-- Customer table
CREATE TABLE Customer (
    customerId INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    emailAddress VARCHAR(255) NOT NULL,
    phoneNumber VARCHAR(20),
    address VARCHAR(255),
    creditScore INT
);

-- Loan table
CREATE TABLE Loan (
    loanId INT PRIMARY KEY AUTO_INCREMENT,
    customerId INT,
    principalAmount DECIMAL(10, 2) NOT NULL,
    interestRate DECIMAL(5, 2) NOT NULL,
    loanTerm INT NOT NULL,
    loanType VARCHAR(50) NOT NULL,
    loanStatus VARCHAR(50) NOT NULL,
    FOREIGN KEY (customerId) REFERENCES Customer(customerId)
);
