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

public class LoginSuccessfulDialog extends JDialog{
	private JButton btnOk;
	private JLabel lLoginSuccessful;
	private JPanel buttonPanel, labelPanel;
	private Frame activity; // activity calling on the dialog
	
	public LoginSuccessfulDialog(Frame activity, int dialogWidth, int dialogHeight) {
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
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Enable the DisabledPanel panels
			}
			
		});
		
		buttonPanel.add(btnOk);
	}
	
	public void addLabels(JPanel labelPanel) {
		lLoginSuccessful = new JLabel("Login Successful!");
		lLoginSuccessful.setFont(new Font("Dialog", Font.BOLD, 12));
		
		labelPanel.add(lLoginSuccessful);
	}
}
