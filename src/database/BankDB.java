package database;

import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import models.Transaction;
import models.User;

public class BankDB {
	private Datasource datasource;
	private List<User> users;
	private List<Transaction> transactions;
	
	public BankDB() {
		updateDB();
	}
	
	public void updateDB() {
		datasource = new Datasource();
		if(!datasource.open()) {
			System.out.println("Can't open datasource");
			return;
		}
		
		users = datasource.queryUser();
		datasource.close();
	}
	
	public List<Transaction> getTransactionFromUser(int id){ // gets the transaction for a specific id
		if(!datasource.open()) {
			System.out.println("Can't open datasource");
			return null;
		}
		
		transactions = datasource.queryTransactionDetails(datasource.ORDER_BY_ASC, id);
		datasource.close();
		
		return transactions;
	}
	
	public void addBankActivity(int id, String date, String activity, double amount) { // updates the bank based on whether user deposits or withdraws from the bank
		if(!datasource.open()) {
			System.out.println("Can't open datasource");
			return;
		}
		
		datasource.insertTransaction(id, date, activity, amount);
		
		datasource.close();

	}
	
	public void addUser(String fname, String lname, String password, String email) {
		if(!datasource.open()){
			System.out.println("Can't open datasource");
			return;
		}
		
		datasource.insertUser(fname, lname, password, email);
		datasource.close();
	}
	
	public String getName(String email) {
		for(User user : users) {
			if(user.getEmail().equals(email)) {
				return user.getfName() + " " + user.getlName(); // returning the name of the user (after we know that their email is validated in login)
			}
		}
		return null;		
	}
	
	public int getID(String email) {
		for(User user : users) {
			if(user.getEmail().equals(email)) {
				return user.getId();
			}
		}
		return 0;
	}
	
	
	public double getBalance(String email) {
		for(User user : users) {
			if(user.getEmail().equals(email)) {
				return user.getBalance();
			}
		}
		return 0;
	}
	
	public double getBalance(int id) {
		for(User user : users) {
			if(user.getId() == id) {
				return user.getBalance();
			}
		}
		return 0;
	}
	
	public List<User> getUsers(){
		return this.users;
	}
	public void updateBalance(int id, double amount) {
		if(!datasource.open()) {
			System.out.println("Could not open database");
			return;
		}
		
		datasource.updateBalance(id, amount);
		datasource.close();
	}
	
	public boolean verifyLogin(String email, String password) {
		Hashtable<String, String> credentials = new Hashtable<>();
		for(int i = 0; i < this.users.size(); i++) {
			credentials.put(users.get(i).getEmail(), users.get(i).getPassword());
		}
		
		if(credentials.containsKey(email)) {
			if(credentials.get(email).equals(password))
				return true;
		}
		
		return false;
	}
	
}
