package dialogs;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import screens.Register;
import tools.DisabledPanel;

public class RegisterDialog extends JDialog implements ActionListener, WindowListener{
	private Register registerActivity;
	private int dialogWidth, dialogHeight;
	private JButton buttonOk;
	private JPanel labelPanel, buttonPanel;
	private JLabel registerFailedLabel;
	private String message;
	
	public RegisterDialog(Register registerActivity, int dialogWidth, int dialogHeight, String message) {
		super(registerActivity);
		this.registerActivity = registerActivity;
		this.dialogWidth = dialogWidth/2;
		this.dialogHeight = dialogHeight/3;
		this.message = message;
		this.setSize(this.dialogWidth, this.dialogHeight);
		this.setLocationRelativeTo(registerActivity);
		this.setLayout(new GridLayout(2,1));
		this.addWindowListener(this);
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
		registerFailedLabel = new JLabel(this.message);
		registerFailedLabel.setFont(new Font("Dialog", Font.BOLD, 12));
		
		labelPanel.add(registerFailedLabel);
	}
	
	public void addButtons(JPanel buttonPanel) {
		buttonOk = new JButton("Ok");
		buttonOk.addActionListener(this);
		
		buttonPanel.add(buttonOk);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch(e.getActionCommand()) {
			case "Ok":
				this.registerActivity.setFocusableWindowState(true);
				DisabledPanel.enable(this.registerActivity.getUserPanel());
				DisabledPanel.enable(this.registerActivity.getButtonPanel());
				this.dispose();
				break;
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		this.registerActivity.setFocusableWindowState(true);
		DisabledPanel.enable(this.registerActivity.getUserPanel());
		DisabledPanel.enable(this.registerActivity.getButtonPanel());
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
