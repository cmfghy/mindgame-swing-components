package org.mindgame.swing.components.wizard.test;

import java.awt.BorderLayout;
import java.util.ResourceBundle;

import javax.swing.JTextArea;

import org.mindgame.swing.components.wizard.WizardPage;

@SuppressWarnings("serial")
public class ToysPage extends WizardPage {
	public static final String IDENTIFIER = "TOYS_PANEL";
	/*
	 * Information text
	 */
	private JTextArea introductionText;
	private ResourceBundle wizardBundle;

	public ToysPage() {
		super(IDENTIFIER);
		wizardBundle = ResourceBundle.getBundle("org.mindgame.swing.components.wizard.test.Wizard");
		init();
	}
	
	private void init() {
		setLayout(new BorderLayout());
		introductionText = new JTextArea(wizardBundle.getString("toyshop.information.text"));
		introductionText.setEditable(false);
		introductionText.setOpaque(false);
		add(introductionText, BorderLayout.CENTER);
	}

	@Override
	public boolean doValidate() {
		return true;
	}
	
	
	@Override
	public String getPageTitle() {
		return wizardBundle.getString("toyshop.title.text");
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
	public void aboutToDisplay() {}

	@Override
	public void displaying() {}

	@Override
	public void aboutToHide() {}
}
