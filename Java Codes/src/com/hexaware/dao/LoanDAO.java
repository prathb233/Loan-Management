package com.hexaware.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hexaware.model.Loan;
import com.hexaware.exception.*;
import com.hexaware.util.DBConnection;

public class LoanDAO extends DBConnection {

	// Get the database connection from DBConnection
	public void getConn() {
		con = getDBConn();
	}



	public void addHomeLoan(Loan loan) {
		try {
			ps = con.prepareStatement("INSERT INTO Loan"
					+ "(customerId, principalAmount, interestRate, loanTerm, loanType, loanStatus)" 
					+ "VALUES (?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1, loan.getCustomerId());
			ps.setDouble(2, loan.getPrincipalAmount());
			ps.setDouble(3, loan.getInterestRate());
			ps.setInt(4, loan.getLoanTerm());
			ps.setString(5, loan.getLoanType());
			ps.setString(6, loan.getLoanStatus());

			int affectedRows = ps.executeUpdate();
			// System.out.println(no_of_rows + " inserted Successfully !!!" );

			if (affectedRows > 0) {
				// Retrieve auto-generated keys (in this case, customerID)
				ResultSet generatedKeys = ps.getGeneratedKeys();
				if (generatedKeys.next()) {
					int generatedLoanID = generatedKeys.getInt(1);
					loan.setLoanId(generatedLoanID );
					System.out.println(
							"Your Loan ID is: " + generatedLoanID);
				}
			}

		} catch (SQLException e) {// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addCarLoan(Loan loan) {
		try {
			ps = con.prepareStatement("INSERT INTO Loan"
					+ "(customerId, principalAmount, interestRate, loanTerm, loanType, loanStatus)" 
					+ "VALUES (?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1, loan.getCustomerId());
			ps.setDouble(2, loan.getPrincipalAmount());
			ps.setDouble(3, loan.getInterestRate());
			ps.setInt(4, loan.getLoanTerm());
			ps.setString(5, loan.getLoanType());
			ps.setString(6, loan.getLoanStatus());

			int affectedRows = ps.executeUpdate();
			// System.out.println(no_of_rows + " inserted Successfully !!!" );

			if (affectedRows > 0) {
				// Retrieve auto-generated keys (in this case, customerID)
				ResultSet generatedKeys = ps.getGeneratedKeys();
				if (generatedKeys.next()) {
					int generatedLoanID = generatedKeys.getInt(1);
					loan.setLoanId(generatedLoanID );
					System.out.println(
							"Your Loan ID is: " + generatedLoanID);
				}
			}

		} catch (SQLException e) {// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Close the database connection from DBConnection
	public void callCloseCon() {
		closeConnection();
	}


	public void updateLoanStatus(int loanId) {
	    try {
	        // Retrieve the customer credit score from the Customer table
	        String creditScoreQuery = "SELECT c.creditScore " +
	                                  "FROM Customer c " +
	                                  "JOIN Loan l ON c.customerId = l.customerId " +
	                                  "WHERE l.loanId = ?";
	        ps = con.prepareStatement(creditScoreQuery);
	        ps.setInt(1, loanId);

	        rs = ps.executeQuery();

	        // Check if the loanId exists and retrieve the credit score
	        if (rs.next()) {
	            int creditScore = rs.getInt("creditScore");

	            // Example condition for updating loanStatus based on credit score
	            String newStatus = (creditScore >= 650) ? "Approved" : "Rejected";

	            // Update the loanStatus in the Loan table
	            String updateQuery = "UPDATE Loan SET loanStatus = ? WHERE loanId = ?";
	            ps = con.prepareStatement(updateQuery);
	            ps.setString(1, newStatus);
	            ps.setInt(2, loanId);

	            int rowsAffected = ps.executeUpdate();

	            if (rowsAffected > 0 && "Approved".equals(newStatus)) {
	                System.out.println("Loan status: Approved!");
	            } else {
	                System.out.println("Loan status: Rejected!");
	            }
	        } else {
	            System.out.println("Loan with ID " + loanId + " not found.");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // Close resources
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	// Method to get all loans and print their details
    public List<Loan> getAllLoan() {
        List<Loan> loans = new ArrayList<>();

        try {
            ps = con.prepareStatement("SELECT * FROM Loan");
            rs = ps.executeQuery();

            while (rs.next()) {
                Loan loan = extractLoanFromResultSet(rs);
                loans.add(loan);
                System.out.println(loan);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return loans;
    }

	
	// Method to get a loan by its ID and print its details
    public Loan getLoanById(int loanId) {
    	Loan loan = null;
        try {
            ps = con.prepareStatement("SELECT * FROM Loan WHERE loanId = ?");
            ps.setInt(1, loanId);
            rs = ps.executeQuery();

            if (rs.next()) {
                loan = extractLoanFromResultSet(rs);
                System.out.println("Loan details for Loan ID " + loanId + ":");
                System.out.println(loan);
            } else {
            	throw new InvalidLoanException();
                //System.out.println("Loan with ID " + loanId + " not found.");
            } 

        } catch (SQLException e) {
            //e.printStackTrace();
            
        } catch(InvalidLoanException e) {
        	//System.out.println(e.getMessage()+ "\nLoan with ID" + loanId + " not found.");
        } finally {
            // Close resources
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } 
        }
        return loan;
    }
    
    // Method to extract a Loan object from the ResultSet
    private static Loan extractLoanFromResultSet(ResultSet rs) throws SQLException {
        int loanId = rs.getInt("loanId");
        int customerId = rs.getInt("customerId");
        double principalAmount = rs.getDouble("principalAmount");
        double interestRate = rs.getDouble("interestRate");
        int loanTerm = rs.getInt("loanTerm");
        String loanType = rs.getString("loanType");
        String loanStatus = rs.getString("loanStatus");

        return new Loan(loanId, customerId, principalAmount, interestRate, loanTerm, loanType, loanStatus);
    }


}
