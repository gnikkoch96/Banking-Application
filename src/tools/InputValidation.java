package tools;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import backend.BankingApplication;
import models.User;

public class InputValidation {
	private static final String EMAIL_REGEX = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:"
			+ "[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")"
			+ "@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\"
			+ "[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*"
			+ "[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
	
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    

	public static String MESSAGE; 
	
	public static boolean validateNumbers(String value) { // if it can't parse it then it isn't a possible value
		try {
			Double.parseDouble(value);
			try{
				value.indexOf('.');
			}catch(StringIndexOutOfBoundsException e2) {
				if(value.substring(value.indexOf('.')).length() > 3){ // checks if the user is placing more than 2 digits after decimal
					MESSAGE = "Error: only 2 decimal places";
					return false;
				}
			}
			
			if(value.charAt(0) == '-') {
				MESSAGE = "Error: No negatives allowed";
				return false;
			}
			return true;
		}catch(NumberFormatException e){
			MESSAGE = "Error: Improper Input";
			return false;
		}		
	}
	
	public static boolean validateWithdraw(double balance, double input) {
		if(balance - input < 0) {
			MESSAGE = "Error: You are taking too much";
			return false;
		}
		
		return true;
	}
	public static boolean validateRegister(String fname, String lname, String password, String rePassword, String email) {
		// order of check does matter here (make it specific as you go down each line)
		
		System.out.println(validateEmptyFields(fname, lname, password, email));
		if(!validateEmptyFields(fname, lname, password, email)) {
			MESSAGE = "Error: Some or all Fields are empty";
			return false;
		}
		
		if(!validatePassword(password, rePassword)) {
			MESSAGE = "Error: Password must match in both fields";
			return false;
		}
		
		if(!validateEmailSyntax(email)) {
			MESSAGE = "Error: Email can not be verified";
			return false;
		}
		
		if(!validateEmail(email)) {
			MESSAGE = "Error: Email taken already";
			return false;
		}
		
		MESSAGE = "Congratulations, you are now registered"; // no errors means that they have successfully registered with us
		return true;
	}
	
	// Make sure that the fields are not empty
	private static boolean validateEmptyFields(String fname, String lname, String password, String email) {
		if(fname.equals(""))
			return false;
		else if(lname.equals(""))
			return false;
		else if(password.equals(""))
			return false;
		else if(email.equals(""))
			return false;
		
		return true;
	}
	
	//Nikko: Might want to include a policy that requires complex input (i.e. special characters + numbers) in the future
	private static boolean validatePassword(String password, String rePassword) {
		if(!password.equals(rePassword))
			return false;
		return true;
	}
	
	private static boolean validateEmailSyntax(String email) { // validates the syntax of email using regex
		if(email == null)
			return false;
		
		//ignore the first '.' @ index 0

		try {
			String substring = email.substring(email.indexOf("."));
			for(int i = 1; i < substring.length(); i++) {
				if(substring.charAt(i) == '.')
					return false;
			}
		}catch(StringIndexOutOfBoundsException e) {
			return false;
		}

		
		Matcher matcher = EMAIL_PATTERN.matcher(email);
		return matcher.matches();
	}
	
	private static boolean validateEmail(String email) { // checks if the email already exists in the database 
		if(email == null)
			return false;
		
		List<User> users = BankingApplication.bankDB.getUsers();
		for(User user : users) {
			if(user.getEmail().equals(email))
				return false;
		}
		
		return true;
	}
}
