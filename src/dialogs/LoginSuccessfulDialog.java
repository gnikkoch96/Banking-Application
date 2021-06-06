package dialogs;

import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import screens.BankApp;
import tools.DisabledPanel;

public class LoginSuccessfulDialog extends JDialog implements ActionListener{
	private JButton btnOk;
	private JLabel loginSuccessfulLabel;
	private JPanel buttonPanel, labelPanel;
	private BankApp activity; // activity calling on the dialog
	
	public LoginSuccessfulDialog(BankApp activity, int dialogWidth, int dialogHeight) {
		super(activity);
		this.activity = activity;
		this.setSize(dialogWidth/3, dialogHeight/3);
		this.setLocationRelativeTo(null);
		this.setLayout(new GridLayout(2,1));
		
		addComponents();
		
		this.setVisible(true);
	}
	
	public void addComponents() {
		labelPanel = new JPanel();
		addLabels(labelPanel);
		
		buttonPanel = new JPanel();
		addButtons(buttonPanel);
		
		this.getContentPane().add(labelPanel);
		this.getContentPane().add(buttonPanel);
	}
	
	public void addButtons(JPanel buttonPanel) {
		btnOk = new JButton("Ok");
		btnOk.addActionListener(this);
		
		buttonPanel.add(btnOk);
	}
	
	public void addLabels(JPanel labelPanel) {
		loginSuccessfulLabel = new JLabel("Login Successful!");
		loginSuccessfulLabel.setFont(new Font("Dialog", Font.BOLD, 12));
		
		labelPanel.add(loginSuccessfulLabel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String command = e.getActionCommand();
		switch(command){
			case "Ok":
				this.activity.setFocusableWindowState(true);
				DisabledPanel.enable(this.activity.getButtonPanel());
				this.dispose();
				break;
		}
	}
}
