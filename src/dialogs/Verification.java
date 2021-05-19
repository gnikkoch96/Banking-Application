package dialogs;

import java.awt.Dialog;
import java.awt.Frame;

public class Verification extends Dialog{
	public Verification(Frame activity, int frameWidth, int frameHeight) {
		super(activity);
		this.setSize(frameWidth/2, frameHeight/2);
		this.setLocationRelativeTo(null);
		
	}
}
