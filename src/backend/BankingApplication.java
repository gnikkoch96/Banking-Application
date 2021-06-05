package backend;
import database.BankDB;
import screens.Login;

public class BankingApplication {
	public static BankDB bankDB = new BankDB();
	
	public static void main(String args[]) {		
		Login login = new Login();
	}
}
