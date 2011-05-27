package org.mindgame.swing.components.wizard.test;

import org.mindgame.swing.components.wizard.Wizard;

public class WizardTest {
    
    public static void main(String[] args) {
        
        Wizard wizard = new Wizard();
        wizard.setTitle("Test Wizard Dialog");
        
        wizard.registerWizardPage(new IntroductionPageDescriptor());

        wizard.registerWizardPage(new ShopListDescriptor());
        
        wizard.registerWizardPage(new BooksPageDescriptor());
        
        wizard.registerWizardPage(new WinePageDescriptor());
        
        wizard.registerWizardPage(new MedicinePageDescriptor());
        
        wizard.registerWizardPage(new ToysPageDescriptor());

        wizard.setCurrentPanel(IntroductionPageDescriptor.IDENTIFIER);
        
        int ret = wizard.showAsModalDialog();
        
        wizard.setBounds(10, 10, 700, 400);
        
        System.out.println("Dialog return code is (0=Finish,1=Cancel,2=Error): " + ret);
        
        System.exit(0);
    }
}
