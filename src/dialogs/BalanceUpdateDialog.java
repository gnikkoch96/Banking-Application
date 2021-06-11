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

public class BalanceUpdateDialog extends JDialog implements ActionListener{
	private JButton confirmButton;
	private JTextField text;
	private JPanel textPanel, buttonPanel;
	private String message;

	public BalanceUpdateDialog(int dialogWidth, int dialogHeight, String message) {
		this.setSize(dialogWidth, dialogHeight);
		this.setTitle("Balance Update Confirmation");
		this.message = message;
		this.setLocationRelativeTo(null);
		
		addComponents();
		
		this.setVisible(true);
	}
	
	public void addComponents() {
		textPanel = new JPanel();
		addTextComponents(textPanel);
		
		buttonPanel = new JPanel();
		addButtons(buttonPanel);
		
		this.add(textPanel, BorderLayout.CENTER);
		this.add(buttonPanel, BorderLayout.SOUTH);
	}
	
	public void addTextComponents(JPanel warningPanel) {
		text = new JTextField(this.message);
		text.setFont(new Font("Dialog", Font.PLAIN, 12));
		text.setEditable(false);
		
		warningPanel.add(text);
	}
	
	public void addButtons(JPanel buttonPanel) {
		confirmButton = new JButton("Confirm");
		confirmButton.addActionListener(this);

		buttonPanel.add(confirmButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		switch(command) {
			case "Confirm":
				this.dispose();
				break;

		}
	}
}
