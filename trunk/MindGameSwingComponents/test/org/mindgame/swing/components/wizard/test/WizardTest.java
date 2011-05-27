package org.mindgame.swing.components.wizard.test;

import org.mindgame.swing.components.wizard.Wizard;
import org.mindgame.swing.components.wizard.WizardPageDescriptor;

public class WizardTest {
    
    public static void main(String[] args) {
        
        Wizard wizard = new Wizard();
        wizard.setTitle("Test Wizard Dialog");
        
        WizardPageDescriptor itro = new IntroductionPageDescriptor();
        wizard.registerWizardPage(IntroductionPageDescriptor.IDENTIFIER, itro);

        WizardPageDescriptor list = new ShopListDescriptor();
        wizard.registerWizardPage(ShopListDescriptor.IDENTIFIER, list);

//        WizardPageDescriptor descriptor3 = new TestPanel3Descriptor();
//        wizard.registerWizardPage(TestPanel3Descriptor.IDENTIFIER, descriptor3);
        
        wizard.setCurrentPanel(IntroductionPageDescriptor.IDENTIFIER);
        
        int ret = wizard.showAsModalDialog();
        
        wizard.setBounds(10, 10, 700, 400);
        
        System.out.println("Dialog return code is (0=Finish,1=Cancel,2=Error): " + ret);
        
        System.exit(0);
    }
}
