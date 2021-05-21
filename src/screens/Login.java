package screens;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import parents.DefaultFrame;
import tools.ImagePanel;
import tools.JTextFieldLimit;

public class Login extends JFrame implements ActionListener{
	//Nikko: These will be stored in the Static Memory
	private static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
	private static final int FRAME_WIDTH = (int) (SCREEN_SIZE.getWidth()/3);
	private static final int FRAME_HEIGHT = (int) (SCREEN_SIZE.getHeight()/2.5);
	
	private JPanel bannerPanel, loginPanel, buttonPanel;
	private JLabel usernameLabel, passwordLabel;
	private JTextField usernameInput;
	private JPasswordField passwordInput;
	private JButton registerButton, loginButton, exitButton;
	
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
		addBannerComponents(bannerPanel);
		
		loginPanel = new JPanel();
		loginPanel.setLayout(new GridLayout(3, 1));
		addLoginComponents(loginPanel);
		
		buttonPanel = new JPanel();
		addButtons(buttonPanel);
		
		this.getContentPane().add(bannerPanel, BorderLayout.NORTH);
		this.getContentPane().add(loginPanel, BorderLayout.CENTER);
		this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		
	}
	
	public void addBannerComponents(JPanel bannerPanel) {
		ImagePanel bannerImage = new ImagePanel("banner.png");
		bannerPanel.add(bannerImage.getImage(), BorderLayout.NORTH);
	}
	
	public void addLoginComponents(JPanel loginPanel) {
		JPanel usernamePanel = new JPanel();
		usernameLabel = new JLabel("Enter Username: " );
		usernameLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
		usernameInput = new JTextField(20);
		usernameInput.setDocument(new JTextFieldLimit(15));
		usernamePanel.add(usernameLabel);
		usernamePanel.add(usernameInput);
		
		JPanel passwordPanel = new JPanel();
		passwordLabel = new JLabel("Enter Password: ");
		passwordLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
		passwordInput = new JPasswordField(20);
		passwordInput.setDocument(new JTextFieldLimit(15));
		passwordInput.setEchoChar('*');
		passwordPanel.add(passwordLabel);
		passwordPanel.add(passwordInput);
		
		JPanel registerPanel = new JPanel();
		registerButton = new JButton("New User? Click Here!");
		registerButton.setPreferredSize(new Dimension(200,25));
		registerButton.addActionListener(this);
		registerPanel.add(registerButton);

		loginPanel.add(usernamePanel);
		loginPanel.add(passwordPanel);
		loginPanel.add(registerPanel);
	}
	
	public void addButtons(JPanel buttonPanel) {
		loginButton = new JButton("Login");
		loginButton.setPreferredSize(new Dimension(150,25));
		loginButton.setBackground(new Color(51, 204, 255));
		loginButton.addActionListener(this);
		
		exitButton = new JButton("Exit");
		exitButton.setPreferredSize(new Dimension(150,25));
		exitButton.setBackground(new Color(255,102,102));
		exitButton.addActionListener(this);
		
		buttonPanel.add(exitButton);
		buttonPanel.add(loginButton);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		switch(command) {
			case "Login":
				break;
			case "Exit":
				System.exit(0);
				break;
			case "New User? Click Here!":
				break;
		}
	}

}
