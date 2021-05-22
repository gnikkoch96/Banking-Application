package tools;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

//This class is used along with JText Component to limit the amount of words entered
public final class JTextFieldLimit extends PlainDocument { // we are writing to this document which is then updated in the JTextField
	private final int limit;

	public JTextFieldLimit(int limit) {
		super();
	    this.limit = limit;
	}

	public void insertString(int offs, String str, AttributeSet a)
      throws BadLocationException {
		if (str == null)
			return;

		if ((super.getLength() + str.length()) <= limit) {
			super.insertString(offs, str, a);
		}
	}
}
