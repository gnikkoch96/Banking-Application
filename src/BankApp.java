import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import tools.ImagePanel;

public class BankApp extends JFrame implements ActionListener{
	private int frameWidth, frameHeight;
	private Dimension screenSize;
	private JPanel bannerPanel, introPanel, buttonPanel;
	private JButton checkBalance, deposit, withdraw, pastTransactions, logout;
	
	public BankApp() {
		super("Nikko's Banking App"); 			
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();	
		
		this.frameWidth = (int) (this.screenSize.getWidth()/4);
		this.frameHeight = (int) (this.screenSize.getHeight()/1.5);
		this.setSize(new Dimension(frameWidth, frameHeight)); 	//Nikko: possible loss in precision
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 30));	
		this.setResizable(false);
		this.setLocationRelativeTo(null);	// centers the application

		// components
		addComponents();
		
		this.repaint();
		this.setVisible(true);
	}
	
	public void addComponents() {
		bannerPanel = new JPanel(); // contains the logo 
		addBanner(bannerPanel);
		
		introPanel = new JPanel(); // introduces the user along with their id
		addIntro(introPanel);
		
		buttonPanel = new JPanel(); // contains the buttons 
		buttonPanel.setPreferredSize(new Dimension((int)(frameWidth/1.3), frameHeight/2));
		buttonPanel.setLayout(new GridLayout(5,1,10,20));
		addButtons(buttonPanel);
		
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
		JTextField intro = new JTextField("Welcome -enter_name-, ID: 0123123"); // Nikko: dummy values are placed for now
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
