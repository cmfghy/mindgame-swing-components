package org.mindgame.swing.components;

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

/**
 * This is a JComboBox extension that suggests possible selections from the available selections.
 * as the user types into the editor of the combobox. This component is useful when the available
 * selections are more in number and selecting an item using mouse is difficult or time consuming. 
 * @author Mayuresh Halshikar
 *
 */
public class SuggesionComboBox extends JComboBox {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -3205605545280334098L;
	
	/**
	 * Combobox model used.
	 */
    private ComboBoxModel model;
    /**
     * Editor for the combo box in which the user types.
     */
    private JTextComponent editor;
    
    /**
     * The below flag tells if the setSelectedItem has been called or not.
     */
    private boolean selecting=false;
    /**
     * The document that will be used for the editor component
     */
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
    	/*
    	 * Set the flag 'selecting' to true and then call setSelectedItem on the model
    	 * so that subsequent calls to insertString or setText on the document will be
    	 * ignored until the 'setSelectedItem' method completes. 
    	 */
        selecting = true;
        model.setSelectedItem(item);
        /*
         * As the setSelectedItem is called on the model let's make the flag 'selecting' 
         * to true. So that further selections can be made.
         */
        selecting = false;
    }
    
	/**
	 * Document class that will be used as a document for the editor component of the combo box
	 * @author Mayuresh Halshikar
	 *
	 */
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
	        Object item = findItem(getText(0, getLength()));
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
		
	    private Object findItem(String pattern) {
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