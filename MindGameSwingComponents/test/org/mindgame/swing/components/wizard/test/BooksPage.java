package org.mindgame.swing.components.wizard.test;

import java.awt.BorderLayout;
import java.util.ResourceBundle;

import javax.swing.JTextArea;

import org.mindgame.swing.components.wizard.WizardPage;

@SuppressWarnings("serial")
public class BooksPage extends WizardPage {
	public static final String IDENTIFIER = "BOOKS_PANEL";
	/*
	 * Information text
	 */
	private JTextArea introductionText;
	private ResourceBundle wizardBundle;

	public BooksPage() {
		super(IDENTIFIER);
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

	@Override
	public Object getNextPage() {
		return FINISH_IDENTIFIER;
	}

	@Override
	public Object getPreviousPage() {
		return ShopListPage.IDENTIFIER;
	}
	
	@Override
	public String getPageTitle() {
		return ResourceBundle.getBundle("org.mindgame.swing.components.wizard.test.Wizard").getString("bookshop.title.text");
	}
	

	@Override
	public void aboutToDisplay() {}

	@Override
	public void displaying() {}

	@Override
	public void aboutToHide() {}
}