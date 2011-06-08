package org.mindgame.swing.components.wizard.test;

import java.util.ResourceBundle;

import org.mindgame.swing.components.wizard.WizardPageDescriptor;

public class MedicinePageDescriptor extends WizardPageDescriptor {
	public static final String IDENTIFIER = "MEDICINES_PANEL";
	public MedicinePageDescriptor() {
		super(IDENTIFIER,new MedicinePage());
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
	public String getPageTitle() {
		return ResourceBundle.getBundle("org.mindgame.swing.components.wizard.test.Wizard").getString("medishop.title.text");
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
