package database;

import java.util.List;

import models.Transaction;
import models.User;

public class BankDB {
	private Datasource datasource;
	public List<User> users;
	
	public BankDB() {
		datasource = new Datasource();
		if(!datasource.open()) {
			System.out.println("Can't open datasource");
			return;
		}
		
		users = datasource.queryUser();
		
		datasource.close();
	}
	
	public static void main(String args[]) {
		 BankDB bank = new BankDB();
		 for(User user : bank.users) {
			 System.out.println("ID: " + user.getId() + ", Name: " + user.getfName() + " " + user.getlName());
		 }
	}
}
