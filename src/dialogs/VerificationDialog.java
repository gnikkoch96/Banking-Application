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

import backend.Deposit;
import backend.Withdraw;

public class VerificationDialog extends JDialog implements ActionListener{
	private double changedBalanceAmount;
	private int dialogWidth, dialogHeight;
	private JButton yesButton, noButton;
	private JTextField warningText;
	private JPanel warningPanel, buttonPanel;
	private Frame activity;
	

	public VerificationDialog(Frame activity, int dialogWidth, int dialogHeight, double changedAmount) {
		super(activity);
		this.activity = activity;
		this.changedBalanceAmount = changedAmount;
		this.dialogWidth = dialogWidth/2;
		this.dialogHeight = dialogHeight/2;
		this.setSize(this.dialogWidth, this.dialogHeight);
		this.setTitle(activity.getName());
		this.setLocationRelativeTo(null);
		
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
				if(activity instanceof Deposit) {//Nikko: Probably not as scaleable
					Deposit.addBalance(this.changedBalanceAmount);
				}else {
					Withdraw.reduceBalance(this.changedBalanceAmount);
				}
				this.dispose();
				BalanceUpdateDialog updateDialog = new BalanceUpdateDialog(this.dialogWidth, this.dialogHeight);
				break;
			case "No":
				this.dispose();
				break;
		}
	}
}
