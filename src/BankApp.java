import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;

public class BankApp extends JFrame {
	private Dimension screenSize;
	
	public BankApp() {
		super("Nikko's Banking App"); 									
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 			
		this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();	
		this.setSize((int)(this.screenSize.getWidth()/2), (int) (this.screenSize.getHeight()/2)); 	//Nikko: possible loss in precision
		this.setVisible(true);
	}
	
}
