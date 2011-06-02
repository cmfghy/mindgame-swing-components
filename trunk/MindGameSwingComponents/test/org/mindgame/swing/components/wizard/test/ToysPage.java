package org.mindgame.swing.components.wizard.test;

import java.awt.BorderLayout;
import java.util.ResourceBundle;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class ToysPage extends JPanel {
	/*
	 * Title
	 * Information text
	 */
	private JTextArea introductionText;
    private JLabel title;
	private ResourceBundle wizardBundle;

	public ToysPage() {
		wizardBundle = ResourceBundle.getBundle("org.mindgame.swing.components.wizard.test.Wizard");
		init();
	}
	
	private void init() {
		setLayout(new BorderLayout());
		title = new JLabel(wizardBundle.getString("toyshop.title.text"));
		add(title, BorderLayout.NORTH);
		
		introductionText = new JTextArea(wizardBundle.getString("toyshop.information.text"));
		introductionText.setEditable(false);
		introductionText.setOpaque(false);
		add(introductionText, BorderLayout.CENTER);
	}
		
}