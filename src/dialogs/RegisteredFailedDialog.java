package dialogs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import screens.Register;

public class RegisteredFailedDialog extends JDialog implements ActionListener{
	private Register registerActivity;
	private int dialogWidth, dialogHeight;
	private JButton buttonOk;
	private JPanel labelPanel, buttonPanel;
	private JLabel registerFailedLabel;
	
	public RegisteredFailedDialog(Register registerActivity, int dialogWidth, int dialogHeight) {
		super(registerActivity);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
