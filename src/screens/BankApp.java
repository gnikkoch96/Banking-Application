package screens;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import backend.BankingApplication;
import backend.CheckBalance;
import backend.Deposit;
import backend.PastTransactions;
import backend.Withdraw;
import models.Transaction;
import tools.DisabledPanel;
import tools.ImagePanel;

public class BankApp extends JFrame implements ActionListener{
	
	private static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
	private static final int FRAME_WIDTH = (int) (SCREEN_SIZE.getWidth()/4);	 //Nikko: possible loss in precision
	private static final int FRAME_HEIGHT = (int) (SCREEN_SIZE.getHeight()/1.5); //Nikko: possible loss in precision
	
	private String fullName, userID;
	private JPanel bannerPanel, introPanel, buttonPanel;
	private DisabledPanel dButtonPanel;
	private JButton checkBalance, deposit, withdraw, pastTransactions, logout;
	
	public static double balance;
	public static int id;

	public BankApp(String email, String fullName, String userID) {
		super("Nikko's Banking App (ID:" + id + ")"); 	
		this.userID = userID;
		this.fullName = fullName;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		this.setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT)); 	
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 30));	
		this.setResizable(false);
		this.setLocationRelativeTo(null);	
		balance = BankingApplication.bankDB.getBalance(email);
		id = Integer.parseInt(userID);
		
		// components
		addComponents();

		
		this.repaint();
		this.setVisible(true);
	}
	
	public void addComponents() {
		Color disableColor = Color.GRAY; // color of the components when they are disabled
		
		bannerPanel = new JPanel(); // contains the logo 
		addBanner(bannerPanel);
		
		introPanel = new JPanel(); // introduces the user along with their id
		addIntro(introPanel);
		
		buttonPanel = new JPanel(); // contains the buttons 
		buttonPanel.setPreferredSize(new Dimension((int)(FRAME_WIDTH/1.3), FRAME_HEIGHT/2)); //Nikko: possible loss in precision
		buttonPanel.setLayout(new GridLayout(5,1,10,20));
		addButtons(buttonPanel);

		dButtonPanel = new DisabledPanel(buttonPanel);
		dButtonPanel.setDisabledColor(disableColor);
		
		this.getContentPane().add(bannerPanel);
		this.getContentPane().add(introPanel);
		this.getContentPane().add(buttonPanel);
	}
	
	
	public void addBanner(JPanel bannerPanel) {
		ImagePanel bannerImage = new ImagePanel("banner.png");
		bannerPanel.add(bannerImage.getImage(), BorderLayout.NORTH);
	}
	
	public void addIntro(JPanel introPanel) {
		// User Introduction
		JTextField intro = new JTextField("Welcome " + this.fullName + ". ID: " + userID); // Nikko: dummy values are placed for now
		intro.setEditable(false);
		introPanel.add(intro);	
	}
	
	public void addButtons(JPanel buttonPanel) {	
		// Check Balance
		checkBalance = new JButton("Check Balance");
		checkBalance.setFont(new Font("Dialog", Font.PLAIN, 24));
		checkBalance.addActionListener(this);
		
		// Deposit
		deposit = new JButton("Deposit");
		deposit.setFont(new Font("Dialog", Font.PLAIN, 24));
		deposit.addActionListener(this);
		
		// Withdraw
		withdraw = new JButton("Withdraw");
		withdraw.setFont(new Font("Dialog", Font.PLAIN, 24));
		withdraw.addActionListener(this);
		
		// Past Transactions
		pastTransactions = new JButton("Past Transactions");
		pastTransactions.setFont(new Font("Dialog", Font.PLAIN, 24));
		pastTransactions.addActionListener(this);
		
		// Logout
		logout = new JButton("Logout");
		logout.setFont(new Font("Dialog", Font.PLAIN, 24));
		logout.addActionListener(this);
		
		// Adding components
		buttonPanel.add(checkBalance);
		buttonPanel.add(deposit);
		buttonPanel.add(withdraw);
		buttonPanel.add(pastTransactions);
		buttonPanel.add(logout);
	}

	
	public JPanel getButtonPanel() {return this.buttonPanel;}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		//Nikko: this might not be resourceful (i.e. creating a new object), instead possibly use static objects
		switch(command){
			case "Check Balance":
				CheckBalance cBalance = new CheckBalance(this, this.userID); 
				break;
			case "Deposit":
				Deposit deposit = new Deposit(this, this.userID);
				break;
			case "Withdraw":
				Withdraw withdraw = new Withdraw(this, this.userID);
				break;
			case "Past Transactions":
				List<Transaction> transactions = BankingApplication.bankDB.getTransactionFromUser(Integer.parseInt(this.userID));
				PastTransactions pastTransactions = new PastTransactions(this, this.userID, transactions);
				break;
			case "Logout":
				this.dispose();
				Login login = new Login();
				break;
		}
		
		this.setFocusableWindowState(false);
		DisabledPanel.disable(buttonPanel);
	}
	
}
