package org.mindgame.swing.components.wizard.test;

import org.mindgame.swing.components.wizard.WizardPageDescriptor;


public class IntroductionPageDescriptor extends WizardPageDescriptor {
    
    public static final String IDENTIFIER = "INTRODUCTION_PANEL";
    
    public IntroductionPageDescriptor() {
        super(IDENTIFIER, new IntroductionPage());
    }
    
    public Object getNextPageDescriptor() {
        return ShopListDescriptor.IDENTIFIER;
    }
    
    public Object getBackPageDescriptor() {
        return null;
    }

	@Override
	public void aboutToDisplayPanel() {}
	@Override
	public void displayingPanel() {}
	@Override
	public void aboutToHidePanel() {}  
}