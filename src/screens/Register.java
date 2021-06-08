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

import backend.BankingApplication;
import tools.ImagePanel;
import tools.JTextFieldLimit;

public class Register extends JFrame implements ActionListener {
	//Nikko: These will be stored in the Static Memory
		private static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
		private static final int FRAME_WIDTH = (int) (SCREEN_SIZE.getWidth()/3);
		private static final int FRAME_HEIGHT = (int) (SCREEN_SIZE.getHeight()/2);
		
		private JPanel bannerPanel, userPanel, buttonPanel;
		private JLabel firstNameLabel, lastNameLabel, passwordLabel, verifyPasswordLabel, emailLabel;
		private JTextField firstNameInput, lastNameInput, emailInput;
		private JPasswordField passwordInput, verifyPasswordInput;
		private JButton registerButton, backButton, exitButton;
		
		public Register() {
			super("Nikko's Banking App Register");
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
			
			userPanel = new JPanel();
			userPanel.setLayout(new GridLayout(6, 1));
			addUserComponents(userPanel);
			
			buttonPanel = new JPanel();
			addButtons(buttonPanel);
			
			this.getContentPane().add(bannerPanel, BorderLayout.NORTH);
			this.getContentPane().add(userPanel, BorderLayout.CENTER);
			this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
			
		}
		
		public void addBannerComponents(JPanel bannerPanel) {
			ImagePanel bannerImage = new ImagePanel("banner.png");
			bannerPanel.add(bannerImage.getImage(), BorderLayout.NORTH);
		}
		
		public void addUserComponents(JPanel userPanel) {
			JPanel firstNamePanel = new JPanel();
			firstNameLabel = new JLabel("Enter First Name (max 15 chars): ");
			firstNameLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
			firstNameInput = new JTextField(20);
			firstNameInput.setDocument(new JTextFieldLimit(15));
			firstNamePanel.add(firstNameLabel);
			firstNamePanel.add(firstNameInput);
			
			JPanel lastNamePanel = new JPanel();
			lastNameLabel = new JLabel("Enter Last  Name (max 15 chars): ");
			lastNameLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
			lastNameInput = new JTextField(20);
			lastNameInput.setDocument(new JTextFieldLimit(15));
			lastNamePanel.add(lastNameLabel);
			lastNamePanel.add(lastNameInput);
			
			JPanel passwordPanel = new JPanel();
			passwordLabel = new JLabel("Enter Password   (max 15 chars): ");
			passwordLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
			passwordInput = new JPasswordField(20);
			passwordInput.setDocument(new JTextFieldLimit(15));
			passwordInput.setEchoChar('*');
			passwordPanel.add(passwordLabel);
			passwordPanel.add(passwordInput);
			
			JPanel verifyPasswordPanel = new JPanel();
			verifyPasswordLabel = new JLabel("Verify Password                          : ");
			verifyPasswordLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
			verifyPasswordInput = new JPasswordField(20);
			verifyPasswordInput.setDocument(new JTextFieldLimit(15));
			verifyPasswordInput.setEchoChar('*');
			verifyPasswordPanel.add(verifyPasswordLabel);
			verifyPasswordPanel.add(verifyPasswordInput);
			
			JPanel emailPanel = new JPanel();
			emailLabel = new JLabel("Enter E-Mail Address                  : ");
			emailLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
			emailInput = new JTextField(20);
			emailPanel.add(emailLabel);
			emailPanel.add(emailInput);
			
			JPanel registerPanel = new JPanel();
			registerButton = new JButton("Register");
			registerButton.setPreferredSize(new Dimension(200,25));
			registerButton.setBackground(new Color(102, 255, 102));
			registerButton.addActionListener(this);
			registerPanel.add(registerButton);

			userPanel.add(firstNamePanel);
			userPanel.add(lastNamePanel);
			userPanel.add(passwordPanel);
			userPanel.add(verifyPasswordPanel);
			userPanel.add(emailPanel);
			userPanel.add(registerPanel);
		}
		
		public void addButtons(JPanel buttonPanel) {
			backButton = new JButton("Back");
			backButton.setPreferredSize(new Dimension(150,25));
			backButton.setBackground(new Color(51, 204, 255));
			backButton.addActionListener(this);
			
			exitButton = new JButton("Exit");
			exitButton.setPreferredSize(new Dimension(150,25));
			exitButton.setBackground(new Color(255,102,102));
			exitButton.addActionListener(this);
			
			buttonPanel.add(exitButton);
			buttonPanel.add(backButton);
			
		}
		
		public void register() {
			String pass = String.valueOf(passwordInput.getPassword());
			String verifyPass = String.valueOf(verifyPasswordInput.getPassword());
			
			if(pass.equals(verifyPass)) {
				String fname = firstNameInput.getText();
				String lname = lastNameInput.getText();
				String email = emailInput.getText();
				
				BankingApplication.bankDB.addUser(fname, lname, pass, email);
			}else { // display register failed dialog
				
			}
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			switch(command) {
				case "Back":
					this.dispose();
					Login login = new Login();
					break;
				case "Exit":
					System.exit(0);
					break;
				case "Register":
					register();
					break;
			}
		}
}
