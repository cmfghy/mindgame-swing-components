package org.mindgame.swing.components.wizard.test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.mindgame.swing.components.wizard.WizardPageDescriptor;

public class ShopListDescriptor extends WizardPageDescriptor {
	
	public static final String IDENTIFIER = "SHOPLIST_PANEL";
	
	private Object nextPageId;
	
	private ShopListPage page;

	public ShopListDescriptor() {
		super(IDENTIFIER,new ShopListPage());
		this.page = (ShopListPage) getPage();
		init();
	}

	private void init() {
		page.addPropertyChangeListener("option", new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				String option = (String) evt.getNewValue();
				if("BOOKS".equals(option)) {
					nextPageId = BooksPageDescriptor.IDENTIFIER;
				} else if("WINES".equals(option)) {
					nextPageId = WinePageDescriptor.IDENTIFIER;
				} else if("MEDICINES".equals(option)) {
					nextPageId = MedicinePageDescriptor.IDENTIFIER;
				} else if("TOYS".equals(option)) {
					nextPageId = ToysPageDescriptor.IDENTIFIER;
				}
				getWizard().resetButtonsToPanelRules();
			}
		});
	}

	@Override
	public Object getNextPageDescriptor() {
		return nextPageId;
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
