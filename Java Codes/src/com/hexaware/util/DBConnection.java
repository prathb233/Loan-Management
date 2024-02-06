package com.hexaware.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/loanmanagement";
    private static final String USER = "root";
    private static final String PASSWORD = "abc123";
    
    protected static Connection con;
    protected PreparedStatement ps;
    protected Statement stmt;
    protected ResultSet rs;
    
    
    protected static Connection getDBConn() {
        try {
            if (con == null || con.isClosed()) {
                con = DriverManager.getConnection(URL, USER, PASSWORD);
            }
        } catch (SQLException e) {
        	e.printStackTrace();
        }
        return con;
    }

    protected void closeConnection() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQL exception
        }
    }
}