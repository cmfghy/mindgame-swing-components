package org.mindgame.swing.components.wizard.test;

import org.mindgame.swing.components.wizard.WizardPageDescriptor;

public class WinePageDescriptor extends WizardPageDescriptor {
	public static final String IDENTIFIER = "WINES_PANEL";
	public WinePageDescriptor() {
		super(IDENTIFIER,new WinePage());
	}

	@Override
	public Object getNextPageDescriptor() {
		return FINISH;
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
