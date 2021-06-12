package backend;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dialogs.BalanceUpdateDialog;
import dialogs.VerificationDialog;
import screens.BankApp;
import tools.DisabledPanel;
import tools.InputValidation;

public class Withdraw extends JFrame implements ActionListener, WindowListener{
	private static final String ACTIVITY = "Withdraw";
	
	private String userID;
	private int frameWidth, frameHeight;
	private double balance;
	
	private BankApp app;
	private JPanel balancePanel, depositPanel, buttonPanel;
	private JTextField balanceField, withdrawField, withdrawInput;
	private JButton submitButton, backButton, exitButton;
	private Dimension screenSize;
	
	public Withdraw(BankApp app, String id) { 
		super("Withdraw from Account (ID:" + id + ")");
		this.userID = id;
		this.balance = BankApp.balance;
		this.app = app;
		
		// JFrame Related
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 			// Disposes only this frame 
		this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.frameWidth = (int) (this.screenSize.getWidth()/5); 			//Nikko: possible loss in precision
		this.frameHeight = (int) (this.screenSize.getHeight()/4);			//Nikko: possible loss in precision
		this.setSize(new Dimension(frameWidth, frameHeight));
		this.setLayout(new BorderLayout(5, 30));
		this.setResizable(false);
		this.setLocationRelativeTo(null);									// centers the frame
		this.addWindowListener(this);
		
		// components
		addComponents();
		
		this.repaint();
		this.requestFocus();
		this.setVisible(true);
	}
	
	public void addComponents() {
		balancePanel = new JPanel();
		addBalanceComponents(balancePanel);
		
		depositPanel = new JPanel();
		addDepositComponents(depositPanel);
		
		buttonPanel = new JPanel();
		addButtonComponents(buttonPanel);
		
		this.getContentPane().add(balancePanel, BorderLayout.NORTH); 
		this.getContentPane().add(depositPanel, BorderLayout.CENTER);
		this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
	}

	public void addBalanceComponents(JPanel balancePanel) {
		balanceField = new JTextField("Balance: $" + this.balance);
		balanceField.setFont(new Font("Dialog", Font.PLAIN, 20));
		balanceField.setEditable(false);
		balancePanel.add(balanceField);
	}

	public void addButtonComponents(JPanel buttonPanel) {
		backButton = new JButton("Back");
		backButton.addActionListener(this);
		
		exitButton = new JButton("Exit");
		exitButton.addActionListener(this);
		
		buttonPanel.add(backButton);
		buttonPanel.add(exitButton);
	}

	public void addDepositComponents(JPanel depositPanel) {
		withdrawField = new JTextField("Withdraw Amount: $");
		withdrawField.setFont(new Font("Dialog", Font.PLAIN, 15));
		withdrawField.setEditable(false);
		
		withdrawInput = new JTextField(10);
		withdrawInput.setText("0.00");
		withdrawInput.setFont(new Font("Dialog", Font.PLAIN, 15));

		submitButton = new JButton("Submit");
		submitButton.addActionListener(this);
		
		depositPanel.add(withdrawField);
		depositPanel.add(withdrawInput);
		depositPanel.add(submitButton);
	}
   
	public void submit() {
		// validate fields
		
		System.out.println(InputValidation.validateWithdraw(BankApp.balance, Double.parseDouble(withdrawInput.getText())));
		
		if(InputValidation.validateNumbers(withdrawInput.getText()) && InputValidation.validateWithdraw(BankApp.balance, Double.parseDouble(withdrawInput.getText()))) {
			VerificationDialog withdrawVerification = new VerificationDialog(this, this.frameWidth, this.frameHeight, Double.parseDouble(withdrawInput.getText()));
			withdrawVerification.setVisible(true);
		}else {
			System.out.println(InputValidation.MESSAGE);
			BalanceUpdateDialog failed = new BalanceUpdateDialog(frameWidth/2, frameHeight/2, InputValidation.MESSAGE);
		}

	}
	

	public int getID() {
		int id = Integer.parseInt(userID);
		return id;
	}
	
	public JTextField getDepositInput() {
		return this.withdrawInput;
	}

	public void updateBalance(double newBalance) {
		balanceField.setText("Balance: $" + String.valueOf(newBalance));
	}
	
   
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		switch(command){
			case "Back":
				this.app.setFocusableWindowState(true);
				DisabledPanel.enable(this.app.getButtonPanel());
				BankApp.balance = Double.parseDouble(balanceField.getText().substring(10));
				this.dispose();
				break;
			case "Exit": // exits from the whole program (doesn't need to save anything since it is only checking)
				System.exit(0);
				break;
			case "Submit":
				submit();
				break;
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		this.app.setFocusableWindowState(true);
		DisabledPanel.enable(this.app.getButtonPanel());
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
