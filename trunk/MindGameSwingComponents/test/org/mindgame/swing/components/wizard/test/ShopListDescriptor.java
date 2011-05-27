package org.mindgame.swing.components.wizard.test;

import org.mindgame.swing.components.wizard.WizardPageDescriptor;

public class ShopListDescriptor extends WizardPageDescriptor {
	
	public static final String IDENTIFIER = "SHOPLIST_PANEL";

	public ShopListDescriptor() {
		super(IDENTIFIER,new ShopListPage());
	}

	@Override
	public Object getNextPageDescriptor() {
		return null;
	}

	@Override
	public Object getBackPageDescriptor() {
		return IntroductionPageDescriptor.IDENTIFIER;
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
