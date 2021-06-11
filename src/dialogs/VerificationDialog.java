package dialogs;


import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;

import backend.BankingApplication;
import backend.Deposit;
import backend.Withdraw;
import screens.BankApp;

public class VerificationDialog extends JDialog implements ActionListener{
	private double changedBalanceAmount;
	private int dialogWidth, dialogHeight;
	private JButton yesButton, noButton;
	private JTextField warningText;
	private JPanel warningPanel, buttonPanel;
	private Frame activity;
	

	public VerificationDialog(Frame activity, int dialogWidth, int dialogHeight, double amount) {
		super(activity);
		this.activity = activity;
		this.changedBalanceAmount = amount;
		this.dialogWidth = dialogWidth/2;
		this.dialogHeight = dialogHeight/2;
		this.setSize(this.dialogWidth, this.dialogHeight);
		this.setTitle(activity.getName());
		this.setLocationRelativeTo(activity);
		
		addComponents();
		
		this.setVisible(true);
	}
	
	public void addComponents() {
		warningPanel = new JPanel();
		addWarningComponents(warningPanel);
		
		buttonPanel = new JPanel();
		addButtons(buttonPanel);
		
		this.add(warningPanel, BorderLayout.CENTER);
		this.add(buttonPanel, BorderLayout.SOUTH);
	}
	
	public void addWarningComponents(JPanel warningPanel) {
		warningText = new JTextField("Are you sure?");
		warningText.setFont(new Font("Dialog", Font.BOLD, 15));
		warningText.setEditable(false);
		warningPanel.add(warningText);
	}
	
	public void addButtons(JPanel buttonPanel) {
		yesButton = new JButton("Yes");
		yesButton.addActionListener(this);
		
		noButton = new JButton("No");
		noButton.addActionListener(this);
		
		buttonPanel.add(yesButton);
		buttonPanel.add(noButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		switch(command) {
			case "Yes":
				int userID = BankApp.id;
				double changedBalance = BankApp.balance;
				if(activity instanceof Deposit) {//Nikko: Probably not as scaleable
					changedBalance += this.changedBalanceAmount;
					BankingApplication.bankDB.updateBalance(userID, changedBalance); // updates balance in database
					BankingApplication.bankDB.addBankActivity(userID, "Deposit", this.changedBalanceAmount); // records activity in database
					((Deposit)activity).getDepositInput().setText("0.00");
					((Deposit)activity).updateBalance(changedBalance); // updates deposit's balance text field
				}else {
					changedBalance -= this.changedBalanceAmount;
					BankingApplication.bankDB.updateBalance(userID, changedBalance); // updates balance in database
					BankingApplication.bankDB.addBankActivity(userID, "Withdraw", this.changedBalanceAmount);
					((Withdraw)activity).getDepositInput().setText("0.00");
					((Withdraw)activity).updateBalance(changedBalance); // updates deposit's balance text field
				}
				BankingApplication.bankDB.updateDB();
				this.dispose();
				BalanceUpdateDialog updateDialog = new BalanceUpdateDialog(this.dialogWidth, this.dialogHeight, "Balance Updated");
				break;
			case "No":
				this.dispose();
				break;
		}
	}
}
