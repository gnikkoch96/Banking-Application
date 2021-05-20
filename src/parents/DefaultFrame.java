package parents;

import java.awt.BorderLayout;
import java.awt.Dimension;
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

/*
 * This class is mainly used for the back end classes that use JFrame
 */

public class DefaultFrame extends JFrame{
	protected String userID;
	protected int frameWidth, frameHeight;
	protected double balance;
	
	protected BankApp app;
	protected JPanel balancePanel, buttonPanel;
	protected JButton backButton, exitButton;
	protected Dimension screenSize;
	
	public DefaultFrame(BankApp app, String action, String id, int frameWidth, int frameHeight) { 
		super(action + "  (ID:" + id + ")");
		this.userID = id;
		this.balance = 0; //Nikko: Dummy value for now...will read off from a txt file in the future
		this.app = app;
		
		// JFrame Related
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 			// Disposes only this frame 
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
	
	
	protected void addComponents() {

	}
	

	  
	
}
