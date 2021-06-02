package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Datasource {
	public static final String DB_NAME = "BankApp.db";
	public static final String CONNECTION_STRING = "jdbc:sqlite:C:\\Users\\Nikko\\Documents\\GitHub\\Banking-Application\\src\\" + DB_NAME;
	
	// Table Users
	public static final String TABLE_USERS = "users";
	public static final String COLUMN_USER_ID = "id";
	public static final String COLUMN_USER_FNAME = "fname";
	public static final String COLUMN_USER_LNAME = "lname";
	public static final String COLUMN_USER_EMAIL = "email";
	public static final String COLUMN_USER_PASS = "password";
	public static final String COLUMN_USER_BALANCE = "balance";
	
	// (Users) Index Values
	public static final int INDEX_USER_ID = 1;
	public static final int INDEX_USER_FNAME = 2;
	public static final int INDEX_USER_LNAME = 3;
	public static final int INDEX_USER_EMAIL = 4;
	public static final int INDEX_USER_PASS = 5;
	public static final int INDEX_USER_BALANCE = 6;
	
	// Table Transactions
	public static final String TABLE_TRANSACTIONS = "transactions";
	public static final String COLUMN_TRANSACTION_ID = "id";
	public static final String COLUMN_TRANSACTION_ACTIVITY = "activity";
	public static final String COLUMN_TRANSACTION_AMOUNT = "amount";
	public static final String COLUMN_TRANSACTION_USER = "user";
	
	// (Transactions) Index Values
	public static final int INDEX_TRANSACTION_ID = 1;
	public static final int INDEX_TRANSACTION_ACTIVITY = 2;
	public static final int INDEX_TRANSACTION_AMOUNT = 3;
	public static final int INDEX_TRANSACTION_USER = 4;
	
	
	// ORDER BY
	public static enum ORDERBY{NONE, DESC, ASC};
	
	
	// Database Connections
	private Connection conn;
	
	public boolean open() {// Tries to connect to database
		try {
			conn = DriverManager.getConnection(CONNECTION_STRING);
			return true;
		}catch(SQLException e) {
			System.out.println("Could not connect to database: " + e.getMessage());
			return null;
		}
	}
	
	public void close() { // Close the connection to database
		try {
			if(conn != null) { // if there is a connection
				conn.close(); 
			}
		}catch(SQLException e) {
			System.out.println("Couldn't close connection: " + e.getMessage());
		}
	}
	
	
	
	
	
}
