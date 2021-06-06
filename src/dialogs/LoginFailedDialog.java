package dialogs;

import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class LoginFailedDialog extends JDialog implements ActionListener{
	private Frame loginActivity;
	private int dialogWidth, dialogHeight;
	private JButton buttonOk;
	private JLabel loginFailedLabel;
	
	public LoginFailedDialog(Frame loginActivity, int dialogWidth, int dialogHeight) {
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
		loginFailedLabel = new JLabel("Error: Login Failed");
		loginFailedLabel.setFont(new Font("Dialog", Font.BOLD, 12));
		
		buttonOk = new JButton("Ok");
		buttonOk.addActionListener(this);
		
		this.getContentPane().add(loginFailedLabel);
		this.getContentPane().add(buttonOk);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		switch(command) {
			case "Ok":
				this.dispose();
				break;
		}
	}
	
}
