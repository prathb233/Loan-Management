-- Inserting data into Customer table
INSERT INTO Customer (name, emailAddress, phoneNumber, address, creditScore)
VALUES
    ('Rahul Sharma', 'rahul.sharma@example.com', '9876543210', 'Delhi', 750),
    ('Priya Patel', 'priya.patel@example.com', '8765432109', 'Mumbai', 800),
    ('Amit Kumar', 'amit.kumar@example.com', '7654321098', 'Bangalore', 700),
    ('Ananya Singh', 'ananya.singh@example.com', '6543210987', 'Kolkata', 820),
    ('Vikram Gupta', 'vikram.gupta@example.com', '5432109876', 'Chennai', 680);

-- Inserting data into Loan table
INSERT INTO Loan (customerId, principalAmount, interestRate, loanTerm, loanType, loanStatus)
VALUES
    (1, 500000, 8.5, 36, 'HomeLoan', 'Pending'),
    (2, 300000, 7.0, 24, 'CarLoan', 'Approved'),
    (3, 800000, 9.0, 48, 'HomeLoan', 'Pending'),
    (4, 400000, 6.5, 36, 'CarLoan', 'Pending'),
    (5, 600000, 8.0, 60, 'HomeLoan', 'Approved');
