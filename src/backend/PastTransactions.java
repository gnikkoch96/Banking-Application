package backend;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import models.Transaction;
import screens.BankApp;
import tools.DisabledPanel;

public class PastTransactions extends JFrame implements ActionListener, WindowListener{
	private String userID;
	private int frameWidth, frameHeight;
	private double balance;
	
	private BankApp app;
	private JPanel titlePanel, historyPanel, buttonPanel;
	private JButton submitButton, backButton, exitButton;
	
	private JTextArea historyArea;
	private JScrollPane scrollArea;
	
	private Dimension screenSize;
	private List<Transaction> transactions;
	
	public PastTransactions(BankApp app, String id, List<Transaction> transactions) { 
		super("Past Transactions (ID:" + id + ")");
		this.userID = id;
		this.balance = 0;
		this.app = app;
		this.transactions = transactions;
		
		// JFrame Related
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 			// Disposes only this frame 
		this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.frameWidth = (int) (this.screenSize.getWidth()/4.5); 			//Nikko: possible loss in precision
		this.frameHeight = (int) (this.screenSize.getHeight()/2);			//Nikko: possible loss in precision
		this.setSize(new Dimension(frameWidth, frameHeight));
		this.setLayout(new BorderLayout(5, 30));
		this.setResizable(false);
		this.setLocationRelativeTo(null);									// centers the frame
		this.addWindowListener(this);
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
		historyPanel.setLayout(new BorderLayout());
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
		historyArea = new JTextArea();
		historyArea.setFont(new Font("Dialog", Font.PLAIN, 15));
		historyArea.setLineWrap(true);
		historyArea.setEditable(false);
		
		// add content
		for(Transaction transaction : this.transactions) {
//			String format = transaction.getId() + "| " + transaction.getActivity() + "| $" + transaction.getAmount();
			String format = String.format("Date: %-10s\n %-50s| $%8.2f\n", transaction.getDate(), transaction.getActivity(), transaction.getAmount());
			historyArea.append(format + "\n");
		}
		
		
		scrollArea = new JScrollPane(historyArea);
		scrollArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		historyPanel.add(scrollArea);
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

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		this.app.setFocusableWindowState(true);
		DisabledPanel.enable(this.app.getButtonPanel());
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
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
