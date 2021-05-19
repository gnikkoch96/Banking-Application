package backend;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import dialogs.Verification;
import screens.BankApp;
import tools.DisabledPanel;

public class PastTransactions extends JFrame implements ActionListener{
	private String userID;
	private int frameWidth, frameHeight;
	private double balance;
	
	private BankApp app;
	private JPanel titlePanel, historyPanel, buttonPanel;
	private JButton submitButton, backButton, exitButton;
	
	private JTextArea historyArea;
	private Dimension screenSize;
	
	public PastTransactions(BankApp app, String id) { 
		super("Past Transactions (ID:" + id + ")");
		this.userID = id;
		this.balance = 0;
		this.app = app;
		
		// JFrame Related
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 			// Disposes only this frame 
		this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.frameWidth = (int) (this.screenSize.getWidth()/4.5); 			//Nikko: possible loss in precision
		this.frameHeight = (int) (this.screenSize.getHeight()/2);			//Nikko: possible loss in precision
		this.setSize(new Dimension(frameWidth, frameHeight));
		this.setLayout(new BorderLayout(5, 30));
		this.setResizable(false);
		this.setLocationRelativeTo(null);									// centers the frame
		
		// components
		addComponents();
	
		this.repaint();
		this.requestFocus();
		this.setVisible(true);
	}
	
	public void addComponents() {
		titlePanel = new JPanel();
		addTitleComponents(titlePanel);
		
		historyPanel = new JPanel();
		addHistoryComponents(historyPanel);
		
		buttonPanel = new JPanel();
		addButtonComponents(buttonPanel);
		
		this.getContentPane().add(titlePanel, BorderLayout.NORTH); 
		this.getContentPane().add(historyPanel, BorderLayout.CENTER);
		this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
	}
	
	public void addTitleComponents(JPanel titlePanel) {
		JTextField titleField = new JTextField("Past Transactions");
		titleField.setFont(new Font("Dialog", Font.PLAIN, 24));
		titleField.setEditable(false);
		
		titlePanel.add(titleField);
	}
	
	public void addHistoryComponents(JPanel historyPanel) {
		JTextArea historyArea = new JTextArea(25,25);
		historyArea.setFont(new Font("Dialog", Font.PLAIN, 15));
		historyArea.setLineWrap(true);
		
		JScrollPane scroll = new JScrollPane(historyArea);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		this.getContentPane().add(scroll);
		historyPanel.add(historyArea);

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
