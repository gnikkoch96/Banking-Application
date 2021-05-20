package screens;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import parents.DefaultFrame;

public class Login extends JFrame implements ActionListener{
	//Nikko: These will be stored in the Static Memory
	private static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
	private static final int FRAME_WIDTH = (int) (SCREEN_SIZE.getWidth()/3);
	private static final int FRAME_HEIGHT = (int) (SCREEN_SIZE.getHeight()/2.5);
	
	private JPanel bannerPanel, loginPanel, buttonPanel;
	private JLabel usernameLabel, passwordLabel;
	private JTextField usernameInput, passwordInput;
	private JButton newUserButton, loginButton, exitButton;
	
	public Login() {
		super("Nikko's Banking App Login");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setLocationRelativeTo(null);
		
		addComponents();
		
		this.setVisible(true);
	}
	
	public void addComponents() {
		bannerPanel = new JPanel();
		
	}
	
	public void addBannerComponents(JPanel bannerPanel) {
		
	}
	
	public void addLoginComponents(JPanel loginPanel) {
		
	}
	
	public void addButtons(JPanel buttonPanel) {
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
