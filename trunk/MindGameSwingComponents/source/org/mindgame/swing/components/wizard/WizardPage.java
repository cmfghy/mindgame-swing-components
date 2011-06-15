package org.mindgame.swing.components.wizard;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class WizardPage extends JPanel {
	public abstract boolean doValidate(); 
}
