package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import models.User;
import models.Transaction;

public class Datasource {
	// The database that we are trying to connect to (I made it within the project) 
	public static final String DB_NAME = "BankApp.db";
	public static final String DIRECTORY_PATH = System.getProperty("user.dir");
	public static final String CONNECTION_STRING = "jdbc:sqlite:" + DIRECTORY_PATH + "\\src\\" + DB_NAME;


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
	public static final String COLUMN_TRANSACTION_DATE = "date";
	public static final String COLUMN_TRANSACTION_ACTIVITY = "activity";
	public static final String COLUMN_TRANSACTION_AMOUNT = "amount";
	public static final String COLUMN_TRANSACTION_USER = "user";
	
	// (Transactions) Index Values
	public static final int INDEX_TRANSACTION_ID = 1;
	public static final int INDEX_TRANSACTION_USER = 2;
	public static final int INDEX_TRANSACTION_DATE = 3;
	public static final int INDEX_TRANSACTION_ACTIVITY = 4;
	public static final int INDEX_TRANSACTION_AMOUNT = 5;

	
	
	// ORDER BY
	public static final int ORDER_BY_NONE = 1;
	public static final int ORDER_BY_ASC = 2;
	public static final int ORDER_BY_DESC = 3;
	
	// Static Queries
	public static final String QUERY_USER = 
			"SELECT * FROM " + TABLE_USERS;
	
	public static final String QUERY_TRANSACTION_DETAILS = 
			"SELECT " + TABLE_TRANSACTIONS + "." + COLUMN_TRANSACTION_ID + ", " + TABLE_TRANSACTIONS + "." + COLUMN_TRANSACTION_DATE
				+ ", " + TABLE_TRANSACTIONS + "." + COLUMN_TRANSACTION_ACTIVITY
				+ ", " + TABLE_TRANSACTIONS + "." + COLUMN_TRANSACTION_AMOUNT + " FROM "+ TABLE_TRANSACTIONS 
				+ " WHERE " + TABLE_TRANSACTIONS + "." + COLUMN_TRANSACTION_USER + " =";
	
	public static final String QUERY_TRANSACTION_DETAILS_SORT = 
			" ORDER BY " + TABLE_TRANSACTIONS + "." + COLUMN_TRANSACTION_ID; 
	
	public static final String INSERT_INTO_TRANSACTION = 
			"INSERT INTO " + TABLE_TRANSACTIONS + " (" + COLUMN_TRANSACTION_USER + ", " + COLUMN_TRANSACTION_ACTIVITY
			+ ", " + COLUMN_TRANSACTION_AMOUNT  + ", " + COLUMN_TRANSACTION_DATE
			+ ") VALUES(";
	
	public static final String UPDATE_USER_BALANCE = 
			"UPDATE " + TABLE_USERS + " SET " + COLUMN_USER_BALANCE + "=";
	
	public static final String INSERT_INTO_USER =
			"INSERT INTO " + TABLE_USERS + " (" + COLUMN_USER_FNAME + ", " + COLUMN_USER_LNAME + ", " + COLUMN_USER_PASS
			+ ", " + COLUMN_USER_EMAIL + ") VALUES(";
	
	// Database Connections
	private Connection conn;
	
	public boolean open() {// Tries to connect to database
		try {
			conn = DriverManager.getConnection(CONNECTION_STRING);
			return true;
		}catch(SQLException e) {
			System.out.println("Could not connect to database: " + e.getMessage());
			return false;
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
	
	public List<User> queryUser(){
		StringBuilder sb = new StringBuilder(QUERY_USER);
		
		try(Statement statement = conn.createStatement();
				ResultSet results = statement.executeQuery(sb.toString())){
			List<User> users = new LinkedList<>();
			while(results.next()) {
				User user = new User();
				user.setId(results.getInt(INDEX_USER_ID));
				user.setfName(results.getString(INDEX_USER_FNAME));
				user.setlName(results.getString(INDEX_USER_LNAME));
				user.setEmail(results.getString(INDEX_USER_EMAIL));
				user.setPassword(results.getString(INDEX_USER_PASS));
				user.setBalance(results.getDouble(INDEX_USER_BALANCE));
				users.add(user);
			}
			return users;	
		}catch(SQLException e) {
			System.out.println("Query Failed: "+ e.getMessage());
			return null;
		}
		
	}
	
	public List<Transaction> queryTransactionDetails(int sortOrder, int userid){
		StringBuilder sb = new StringBuilder(QUERY_TRANSACTION_DETAILS);
		sb.append(String.valueOf(userid));
		
		if(sortOrder != ORDER_BY_NONE) {
			sb.append(QUERY_TRANSACTION_DETAILS_SORT);
			if(sortOrder == ORDER_BY_DESC) {
				sb.append(" DESC");
			}else { // ASC
				sb.append(" ASC");
			}
		}
		
		System.out.println(sb.toString());
		try(Statement statement = conn.createStatement();
				ResultSet results = statement.executeQuery(sb.toString())){
			List<Transaction> transactions = new LinkedList<>();
			while(results.next()) {
				Transaction transaction = new Transaction();
				transaction.setId(results.getInt(COLUMN_TRANSACTION_ID));
				transaction.setDate(results.getString(COLUMN_TRANSACTION_DATE));
				transaction.setActivity(results.getString(COLUMN_TRANSACTION_ACTIVITY));
				transaction.setAmount(results.getDouble(COLUMN_TRANSACTION_AMOUNT));
				transactions.add(transaction);
			}
			
			return transactions;
		}catch(SQLException e) {
			System.out.println("Query Failed: " + e.getMessage());
			return null;
		}
	}
	
	public void insertTransaction(int id, String date, String activity, double amount) {
		StringBuilder sb = new StringBuilder(INSERT_INTO_TRANSACTION); // don't need to insert id as it auto increment
		System.out.println(sb.toString());
		try(Statement statement = conn.createStatement()){
			sb.append(id + ",");
			sb.append("\'" + activity + "\',");
			sb.append(amount + ",");
			sb.append("\'" + date + "\'");
			sb.append(")");
			System.out.println(sb.toString());
			statement.executeUpdate(sb.toString());
		}catch(SQLException e) {
			System.out.println("Transaction Insert Failed: " + e.getMessage());
		}
	}
	
	public void insertUser(String fname, String lname, String password, String email) {
		StringBuilder sb = new StringBuilder(INSERT_INTO_USER);
		try(Statement statement = conn.createStatement()){
			sb.append("\'" + fname + "\',");
			sb.append("\'" + lname + "\',");
			sb.append("\'" + password + "\',");
			sb.append("\'" + email + "\')");
			
			System.out.println(sb.toString());
			statement.executeUpdate(sb.toString());
		}catch(SQLException e){
			System.out.println("Failed to insert user: " + e.getMessage());
		}
	}
	
	public void updateBalance(int id, double amount) { // amount should already be calculated
		StringBuilder sb = new StringBuilder(UPDATE_USER_BALANCE);
		try(Statement statement = conn.createStatement()){
			sb.append(amount);
			sb.append(" WHERE " + COLUMN_USER_ID + "=");
			sb.append(id);
			
			System.out.println(sb.toString());
			statement.executeUpdate(sb.toString());
		}catch(SQLException e) {
			System.out.println("Update Balance Failed: " + e.getMessage());
		}
		
	}
	
}
