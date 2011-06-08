package org.mindgame.swing.components.wizard.test;

import java.awt.BorderLayout;
import java.util.ResourceBundle;

import javax.swing.JPanel;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class BooksPage extends JPanel {
	/*
	 * Information text
	 */
	private JTextArea introductionText;
	private ResourceBundle wizardBundle;

	public BooksPage() {
		wizardBundle = ResourceBundle.getBundle("org.mindgame.swing.components.wizard.test.Wizard");
		init();
	}
	
	private void init() {
		setLayout(new BorderLayout());
		introductionText = new JTextArea(wizardBundle.getString("bookshop.information.text"));
		introductionText.setEditable(false);
		introductionText.setOpaque(false);
		add(introductionText, BorderLayout.CENTER);
	}
}