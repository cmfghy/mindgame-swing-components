package org.mindgame.swing.components.wizard.test;

import org.mindgame.swing.components.wizard.Wizard;

public class WizardTest {
    
    public static void main(String[] args) {
        
        Wizard wizard = new Wizard();
        wizard.setTitle("Test Wizard Dialog");
        
        wizard.registerWizardPage(new IntroductionPage());

        wizard.registerWizardPage(new ShopListPage());
        
        wizard.registerWizardPage(new BooksPage());
        
        wizard.registerWizardPage(new WinePage());
        
        wizard.registerWizardPage(new MedicinePage());
        
        wizard.registerWizardPage(new ToysPage());

        wizard.setCurrentPage(IntroductionPage.IDENTIFIER);
        
        int ret = wizard.showAsModalDialog();
        
        wizard.setSize(700, 400);
        
        System.out.println("Dialog return code is (0=Finish,1=Cancel,2=Error): " + ret);
        
        System.exit(0);
    }
}
