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

import screens.Login;
import tools.DisabledPanel;

public class LoginFailedDialog extends JDialog implements ActionListener{
	private Login loginActivity;
	private int dialogWidth, dialogHeight;
	private JButton buttonOk;
	private JPanel labelPanel, buttonPanel;
	private JLabel loginFailedLabel;
	
	public LoginFailedDialog(Login loginActivity, int dialogWidth, int dialogHeight) {
		super(loginActivity);
		this.loginActivity = loginActivity;
		this.dialogWidth = dialogWidth/3;
		this.dialogHeight = dialogHeight/3;
		this.setSize(this.dialogWidth, this.dialogHeight);
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
	
	public void addLabels(JPanel labelPanel) {
		loginFailedLabel = new JLabel("Error: Login Failed");
		loginFailedLabel.setFont(new Font("Dialog", Font.BOLD, 12));
		
		labelPanel.add(loginFailedLabel);
	}
	
	public void addButtons(JPanel buttonPanel) {
		buttonOk = new JButton("Ok");
		buttonOk.addActionListener(this);
		
		buttonPanel.add(buttonOk);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		switch(command) {
			case "Ok":
				this.loginActivity.setFocusableWindowState(true);
				DisabledPanel.enable(this.loginActivity.getLoginPanel());
				DisabledPanel.enable(this.loginActivity.getButtonPanel());
				this.dispose();
				break;
		}
	}
	
}
