package org.mindgame.swing.components.models;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;

public class SuggesionComboBox extends JComboBox {

	private static final long serialVersionUID = -3205605545280334098L;
	
    private ComboBoxModel model;
    private JTextComponent editor;
    // flag to indicate if setSelectedItem has been called
    // subsequent calls to remove/insertString should be ignored
    private boolean selecting=false;
    
    private SuggesionDocument document;
	

	public SuggesionComboBox() {
		super();
		init();
	}

	public SuggesionComboBox(ComboBoxModel aModel) {
		super(aModel);
		init();
	}

	public SuggesionComboBox(Object[] items) {
		super(items);
		init();
	}

	public SuggesionComboBox(Vector<?> items) {
		super(items);
		init();
	}
	
	private void init() {
		setEditable(true);
        model = getModel();
        editor = (JTextComponent) getEditor().getEditorComponent();
        document = new SuggesionDocument();
        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!selecting) highlightCompletedText(0);
            }
        });
        editor.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (isDisplayable()) setPopupVisible(true);
            }
        });
        editor.setDocument(document);
		
	}
	
    private void highlightCompletedText(int start) {
        editor.setSelectionStart(start);
        editor.setSelectionEnd(document.getLength());
    }
    
    
    public void setSelectedItem(Object item) {
        selecting = true;
        model.setSelectedItem(item);
        selecting = false;
    }
    
	
	private class SuggesionDocument extends PlainDocument {
		private static final long serialVersionUID = 3433053469558585929L;

	    public void remove(int offs, int len) throws BadLocationException {
	        // return immediately when selecting an item
	        if (selecting) return;
	        super.remove(offs, len);
	    }
	    
	    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
	        // return immediately when selecting an item
	        if (selecting) return;
	        // insert the string into the document
	        super.insertString(offs, str, a);
	        // lookup and select a matching item
	        Object item = lookupItem(getText(0, getLength()));
	        if (item != null) {
	            setSelectedItem(item);
	        } else {
	            // keep old item selected if there is no match
	            item = SuggesionComboBox.this.getSelectedItem();
	            // imitate no insert (later on offs will be incremented by str.length(): selection won't move forward)
	            offs = offs-str.length();
	            // provide feedback to the user that his input has been received but can not be accepted
	            SuggesionComboBox.this.getToolkit().beep(); // when available use: UIManager.getLookAndFeel().provideErrorFeedback(comboBox);
	        }
	        setText(item.toString());
	        // select the completed part
	        highlightCompletedText(offs+str.length());
	    }
	    
	    private void setText(String text) throws BadLocationException {
	        // remove all text and insert the completed string
	        super.remove(0, getLength());
	        super.insertString(0, text, null);
	    }
		
	    private Object lookupItem(String pattern) {
	        Object selectedItem = model.getSelectedItem();
	        // only search for a different item if the currently selected does not match
	        if (selectedItem != null && startsWithIgnoreCase(selectedItem.toString(), pattern)) {
	            return selectedItem;
	        } else {
	            // iterate over all items
	            for (int i=0, n=model.getSize(); i < n; i++) {
	                Object currentItem = model.getElementAt(i);
	                // current item starts with the pattern?
	                if (startsWithIgnoreCase(currentItem.toString(), pattern)) {
	                    return currentItem;
	                }
	            }
	        }
	        // no item starts with the pattern => return null
	        return null;
	    }
	    
	    // checks if str1 starts with str2 - ignores case
	    private boolean startsWithIgnoreCase(String str1, String str2) {
	        return str1.toUpperCase().startsWith(str2.toUpperCase());
	    }
	}
}
