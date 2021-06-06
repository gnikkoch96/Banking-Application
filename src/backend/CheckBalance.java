package backend;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import screens.BankApp;
import tools.DisabledPanel;

public class CheckBalance extends JFrame implements ActionListener {
	private String userID;
	private int frameWidth, frameHeight;
	private double balance;
	
	private BankApp app;
	private JPanel balancePanel, buttonPanel;
	private JButton backButton, exitButton;
	private Dimension screenSize;
	
	public CheckBalance(BankApp app, String id) { 
		super("Checking Balance (ID:" + id + ")");
		this.userID = id;
		this.balance = BankApp.balance; 
		this.app = app;
		
		// JFrame Related
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 			// Disposes only this frame 
		this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.frameWidth = (int) (this.screenSize.getWidth()/5); 			//Nikko: possible loss in precision
		this.frameHeight = (int) (this.screenSize.getHeight()/4);			//Nikko: possible loss in precision
		this.setSize(new Dimension(frameWidth, frameHeight));
		this.setLayout(new BorderLayout(5, 60));
		this.setResizable(false);
		this.setLocationRelativeTo(null);									// centers the frame
		
		// components
		addComponents();
		
		this.repaint();
		this.requestFocus();
		this.setVisible(true);
	}
	
	public void addComponents() {
		balancePanel = new JPanel();
		addBalanceComponents(balancePanel);
		
		buttonPanel = new JPanel();
		addButtonComponents(buttonPanel);
		
		this.getContentPane().add(new JPanel(), BorderLayout.NORTH); //Nikko: This is not resourceful
		this.getContentPane().add(balancePanel, BorderLayout.CENTER);
		this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
	}
	
	public void addBalanceComponents(JPanel balancePanel) {
		JTextField balanceField = new JTextField("Balance: $" + this.balance);
		balanceField.setFont(new Font("Dialog", Font.PLAIN, 24));
		balanceField.setEditable(false);
		balancePanel.add(balanceField);
	}
	
	public void addButtonComponents(JPanel buttonPanel) {
		backButton = new JButton("Back");
		backButton.addActionListener(this);
		
		exitButton = new JButton("Exit");
		exitButton.addActionListener(this);
		
		buttonPanel.add(backButton);
		buttonPanel.add(exitButton);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		switch(command){
			case "Back":
				this.app.setFocusableWindowState(true);
				DisabledPanel.enable(this.app.getButtonPanel());
				this.dispose();
				break;
			case "Exit": // exits from the whole program (doesn't need to save anything since it is only checking)
				System.exit(0);
				break;
		}
	}
}
