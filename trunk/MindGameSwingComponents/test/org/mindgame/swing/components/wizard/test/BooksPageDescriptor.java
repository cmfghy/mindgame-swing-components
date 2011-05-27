package org.mindgame.swing.components.wizard.test;

import org.mindgame.swing.components.wizard.WizardPageDescriptor;

public class BooksPageDescriptor extends WizardPageDescriptor {
	public static final String IDENTIFIER = "BOOKS_PANEL";
	public BooksPageDescriptor() {
		super(IDENTIFIER);
	}

	@Override
	public Object getNextPageDescriptor() {
		return null;
	}

	@Override
	public Object getBackPageDescriptor() {
		return ShopListDescriptor.IDENTIFIER;
	}

	@Override
	public void aboutToDisplayPanel() {
		// TODO Auto-generated method stub

	}

	@Override
	public void displayingPanel() {
		// TODO Auto-generated method stub

	}

	@Override
	public void aboutToHidePanel() {
		// TODO Auto-generated method stub

	}

}
