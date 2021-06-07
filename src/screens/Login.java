package screens;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import backend.BankingApplication;
import dialogs.LoginFailedDialog;
import dialogs.LoginSuccessfulDialog;
import parents.DefaultFrame;
import tools.DisabledPanel;
import tools.ImagePanel;
import tools.JTextFieldLimit;

public class Login extends JFrame implements ActionListener{
	//Nikko: These will be stored in the Static Memory
	private static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
	private static final int FRAME_WIDTH = (int) (SCREEN_SIZE.getWidth()/3);
	private static final int FRAME_HEIGHT = (int) (SCREEN_SIZE.getHeight()/2.5);
	
	private JPanel bannerPanel,loginPanel, buttonPanel;
	private DisabledPanel dLoginPanel, dButtonPanel;
	private JLabel usernameLabel, passwordLabel;
	private JTextField usernameInput;
	private JPasswordField passwordInput;
	private JButton registerButton, loginButton, exitButton;
	
	public Login() {
		super("Nikko's Banking App Login");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		addComponents();
		
		this.setVisible(true);
	}
	
	public void addComponents() {
		bannerPanel = new JPanel();
		addBannerComponents(bannerPanel);
		
		loginPanel = new JPanel();
		loginPanel.setLayout(new GridLayout(3, 1)); // layout is 4 x 1 (change the first value if you plan to add more components vertically
		addLoginComponents(loginPanel);
		dLoginPanel = new DisabledPanel(loginPanel);
		
		
		buttonPanel = new JPanel();
		addButtons(buttonPanel);
		dButtonPanel = new DisabledPanel(buttonPanel);
		
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
		usernameLabel = new JLabel("Enter E-Mail      : " );
		usernameLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
		usernameInput = new JTextField(20);
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
	
	public void login() {
		//verify email and password
		if(BankingApplication.bankDB.verifyLogin(usernameInput.getText(), String.valueOf(passwordInput.getPassword()))) { // login successful
			this.dispose();
			String email = usernameInput.getText();
			String name = BankingApplication.bankDB.getName(email);
			String id = String.format("%06d", BankingApplication.bankDB.getID(email));
			BankApp bankApp = new BankApp(email, name, id); // pass name (fname + lname) and id
			bankApp.setFocusableWindowState(false); // makes the user not able to give focus to the bank app until they hit ok
			DisabledPanel.disable(bankApp.getButtonPanel()); // prevents the user from doing anything until they hit the ok button from login
			LoginSuccessfulDialog dialog = new LoginSuccessfulDialog(bankApp, FRAME_WIDTH, FRAME_HEIGHT);		
		}else { // login failed
			this.setFocusableWindowState(false);
			DisabledPanel.disable(this.loginPanel);
			DisabledPanel.disable(this.buttonPanel);;
			LoginFailedDialog dialog = new LoginFailedDialog(this, FRAME_WIDTH, FRAME_HEIGHT);
		}		
	}
	
	public JPanel getLoginPanel() {
		return this.loginPanel;
	}
	
	public JPanel getButtonPanel() {
		return this.buttonPanel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		switch(command) {
			case "Login":
				login();
				break;
			case "Exit":
				System.exit(0);
				break;
			case "New User? Click Here!":
				this.dispose();
				Register register = new Register();
				break;
		}
	}

}
