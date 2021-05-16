import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BankApp extends JFrame implements ActionListener{
	private Dimension screenSize;
	private JPanel bannerPanel, buttonPanel;
	private JButton checkBalance, deposit, withdraw, pastTransactions, logout;
	
	public BankApp() {
		super("Nikko's Banking App"); 									
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 			
		this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();	
		this.setSize((int)(this.screenSize.getWidth()/4), (int) (this.screenSize.getHeight()/1.5)); 	//Nikko: possible loss in precision
		this.setLayout(new BorderLayout());	
		
		// components
		addComponents();
		
		this.setVisible(true);
	}
	
	public void addComponents() {
		bannerPanel = new JPanel(); // contains the logo plus the name and id of the user
		addBanner(bannerPanel);
		
		buttonPanel = new JPanel(); // contains the buttons 
		addButtons(buttonPanel);
		
		this.add(bannerPanel, BorderLayout.NORTH);
		this.add(buttonPanel, BorderLayout.CENTER);
	}
	
	public void addBanner(JPanel bannerPanel) {
		// Banner
		JTextField banner = new JTextField("Banking Application");
		banner.setEditable(false);
		
		// Introduction
		JTextField intro = new JTextField("Welcome -enter_name-, ID: 0123123"); // Nikko: dummy values are placed for now
		intro.setEditable(false);
		
		bannerPanel.add(banner, BorderLayout.NORTH);
		bannerPanel.add(intro, BorderLayout.CENTER);
		
	}
	
	public void addButtons(JPanel buttonPanel) {
		// Check Balance
		checkBalance = new JButton("Check Balance");
		checkBalance.addActionListener(this);
		
		// Deposit
		deposit = new JButton("Deposit");
		deposit.addActionListener(this);
		
		// Withdraw
		withdraw = new JButton("Withdraw");
		withdraw.addActionListener(this);
		
		// Past Transactions
		pastTransactions = new JButton("Past Transactions");
		pastTransactions.addActionListener(this);
		
		// Exit
		logout = new JButton("Logout");
		logout.addActionListener(this);
		
		// Adding components
		buttonPanel.add(checkBalance);
		buttonPanel.add(deposit);
		buttonPanel.add(withdraw);
		buttonPanel.add(pastTransactions);
		buttonPanel.add(logout);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		switch(command){
			case "Check Balance":
				break;
			case "Deposit":
				break;
			case "Withdraw":
				break;
			case "Past Transactions":
				break;
			case "Logout":
				break;
		}
	}
	
}
