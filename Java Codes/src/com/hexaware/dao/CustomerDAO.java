package com.hexaware.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.hexaware.model.Customer;
import com.hexaware.util.DBConnection;

public class CustomerDAO extends DBConnection {

	// Get the database connection from DBConnection
	public void getConn() {
		con = getDBConn();
	}

	public void addCustomer(Customer customer) {
	    try {
	      con = DBConnection.getDBConn();
	      ps =
	        con.prepareStatement(
	          "INSERT INTO Customer" +
	          "(name, emailAddress, phoneNumber, address, creditScore)" +
	          "VALUES (?,?,?,?,?)",
	          Statement.RETURN_GENERATED_KEYS);
	      
	      ps.setString(1, customer.getName());
	      ps.setString(2, customer.getEmailAddress());
	      ps.setString(3, customer.getPhoneNumber());
	      ps.setString(4, customer.getAddress());
	      ps.setInt(5, customer.getCreditScore());

	      int affectedRows = ps.executeUpdate();

	
	      if (affectedRows > 0) {
	        // Retrieve auto-generated keys (in this case, customerID)
	        ResultSet generatedKeys = ps.getGeneratedKeys();
	        if (generatedKeys.next()) {
	          int generatedCustomerID = generatedKeys.getInt(1);
	          customer.setCustomerId(generatedCustomerID);
	          System.out.println(
	            "Your new Customer ID: " + generatedCustomerID);
	        }
	      }
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	    }
	// Get an customer by ID
	public Customer getCustomerById(int customerId) {

		Customer customer = null;
		try {
			String sqlQuery = "SELECT * FROM customer WHERE customerId = ?";

			ps = con.prepareStatement(sqlQuery);
			ps.setInt(1, customerId);
			rs = ps.executeQuery();

			if (rs.next()) {

				// Create Customer object from ResultSet
				customer = new Customer(rs.getInt("customerId"),
						rs.getString("name"), rs.getString("emailAddress"),
						rs.getString("phoneNumber"), rs.getString("address"),
						rs.getInt("creditScore"));
				System.out.println(customer);
			} else {
				// Customer not found
				return null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			// Handle SQL exception
		}
		return customer;
	}


}
