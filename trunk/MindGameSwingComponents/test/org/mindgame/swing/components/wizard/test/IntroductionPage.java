package org.mindgame.swing.components.wizard.test;

import java.awt.BorderLayout;
import java.util.ResourceBundle;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.mindgame.swing.components.wizard.WizardPage;

@SuppressWarnings("serial")
public class IntroductionPage extends WizardPage {
 
	public static final String IDENTIFIER = "INTRODUCTION_PANEL";
	
	private JTextArea introductionText;
	
	private ResourceBundle wizardBundle;
    
    public IntroductionPage() {
    	super(IDENTIFIER);
        setLayout(new java.awt.BorderLayout());
        add(getContentPanel(), BorderLayout.CENTER);
    }
    
    private JPanel getContentPanel() {
    	wizardBundle = ResourceBundle.getBundle("org.mindgame.swing.components.wizard.test.Wizard");
    	JPanel panel = new JPanel();
        introductionText = new JTextArea();
        introductionText.setEditable(false);
        introductionText.setOpaque(false);
        panel.setLayout(new java.awt.BorderLayout());
        introductionText.setText(wizardBundle.getString("introduction.text"));
        panel.add(new JScrollPane(introductionText), java.awt.BorderLayout.CENTER);
        return panel;
    }
    
    @Override
    public String getPageTitle() {
    	return wizardBundle.getString("introduction.title.text");
    }

	@Override
	public Object getNextPage() {
		return ShopListPage.IDENTIFIER;
	}

	@Override
	public Object getPreviousPage() {
		return null;
	}

	@Override
	public void aboutToDisplay() {}

	@Override
	public void displaying() {}

	@Override
	public void aboutToHide() {}
}
