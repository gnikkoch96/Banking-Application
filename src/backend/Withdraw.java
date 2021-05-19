package backend;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dialogs.Verification;
import screens.BankApp;
import tools.DisabledPanel;

public class Withdraw extends JFrame implements ActionListener{
	private String userID;
	private int frameWidth, frameHeight;
	private double balance;
	
	private BankApp app;
	private JPanel balancePanel, depositPanel, buttonPanel;
	private JButton submitButton, backButton, exitButton;
	private Dimension screenSize;
	
	public Withdraw(BankApp app, String id) { 
		super("Withdraw from Account (ID:" + id + ")");
		this.userID = id;
		this.balance = 0; //Nikko: Dummy value for now...will read off from a txt file in the future
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
		JTextField balanceField = new JTextField("Balance: $" + this.balance);
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
		JTextField depositLabel = new JTextField("Withdraw Amount: ");
		depositLabel.setFont(new Font("Dialog", Font.PLAIN, 15));
		depositLabel.setEditable(false);
		
		JTextField depositInput = new JTextField(10);
		depositInput.setFont(new Font("Dialog", Font.PLAIN, 15));

		submitButton = new JButton("Submit");
		submitButton.addActionListener(this);
		
		depositPanel.add(depositLabel);
		depositPanel.add(depositInput);
		depositPanel.add(submitButton);
	}
   
	public void submit() {
		Verification depositVerification = new Verification(this, this.frameWidth, this.frameHeight);
		depositVerification.setVisible(true);
	}
   
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		switch(command){
			case "Back":
				this.app.setFocusableWindowState(true);
				DisabledPanel.enable(this.app.getButtonPanel());
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
}
