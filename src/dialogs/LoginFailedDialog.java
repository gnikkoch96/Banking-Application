package dialogs;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LoginFailedDialog extends JDialog{
	private JButton btnOk;
	private JLabel lLoginFail;
	private JPanel buttonPanel, labelPanel;
	
	private dialogWidth, dialogHeight;
	
	public LoginFailedDialog(int dialogWidth, int dialogHeight) {
		this.setSize(dialogWidth, dialogHeight);
		this.setTitle("Balance Update Confirmation");
		this.setLocationRelativeTo(null);
		
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
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Enable the Login panels
//				this.dispose(); here, this refers to the ActionListener and not the JDialog
			}
			
		});
		
		buttonPanel.add(btnOk);
	}
	
	public void addLabels(JPanel labelPanel) {
		lLoginFail = new JLabel("Error: Login Failed");
		lLoginFail.setFont(new Font("Dialog", Font.BOLD, 12));
		
		labelPanel.add(lLoginFail);
	}
}
