package tools;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

// Add Images to JPanel
public class ImagePanel {
	private BufferedImage image;
	private JLabel imageContainer;
	
	public ImagePanel(String sourceName) {
		try {
			image = ImageIO.read(new File("C:\\Users\\Nikko\\Documents\\GitHub\\Banking-Application\\assets\\" + sourceName)); //Nikko: Will need to figure out how to reference within the program itself
			imageContainer = new JLabel(new ImageIcon(image));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public JLabel getImage() {return this.imageContainer;}
}
