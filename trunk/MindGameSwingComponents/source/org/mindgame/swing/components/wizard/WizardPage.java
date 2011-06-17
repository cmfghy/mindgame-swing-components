package org.mindgame.swing.components.wizard;

import java.awt.Component;
import java.awt.Container;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class WizardPage extends JPanel {
	
	public static final Object FINISH_IDENTIFIER = new Object();
    /**
     * The parent wizard
     */
    private Wizard wizard;
    /**
     * Id for this page
     */
    private Object pageId;
    
	public WizardPage(Object pageId) {
		this.pageId = pageId;
	}
    /**
     * Returns id for the page
     * @return - Id
     */
    public final Object getPageId() {
        return pageId;
    }
    /**
     * Enables the page for which this descriptor is written
     * @param enabled
     */
    public final void enablePage(boolean enabled) {
    	enableContainer(this, enabled);
    }
    
    private void enableContainer(Container container, boolean enabled) {
    	Component[] components = container.getComponents();
    	for (int i = 0; i < components.length; i++) {
    		if(components[i] instanceof Container) {
    			enableContainer((Container) components[i], enabled);
    		}
			components[i].setEnabled(enabled);
		}
    }

    /**
     * Sets owner wizard for the page for which this descriptor is configured
     * @param w - Owner wizard
     */
    final void setWizard(Wizard w) {
        wizard = w;
    }
    /**
     * Returns the wizard for the page for which this descriptor is configured 
     * @return - Wizard
     */
    public final Wizard getWizard() {
        return wizard;
    }   
    /**
     * Returns the model for the wizard, for the page for which this descriptor is 
     * configured 
     * @return - Wizard
     */
    public WizardModel getWizardModel() {
        return wizard.getModel();
    }
    
    /**
     * All the custom descriptors will override this method to provide the id of the page
     * that will be considered as next page. This method is called when the user clicks
     * next button. If this method returns NULL the wizard will simply disable the next button
     * This method is supposed to return FinishIdentfier if the button text
     * should change to 'Finish' and the dialog should end.
     * @return Page Id
     */    
    public abstract Object getNextPage();

    /**
     * All the custom descriptors will override this method to provide the id of the page
     * that will be considered as previous page. This method is called when the user clicks
     * Previous button. If this method returns NULL the wizard will simply disable the previous
     * button This method is supposed to return FinishIdentfier if the button text should 
     * change to 'Finish' and the dialog should end.
     * @return Page Id
     */    
    public abstract Object getPreviousPage();
    
    /**
     * This method is called when the page of this descriptor is about to get displayed.
     */    
    public abstract void aboutToDisplay(); 

    /**
     * This method is called when the page of this descriptor is just displayed.
     */    
    public abstract void displaying();
    
    /**
     * This method is called when the page of this descriptor is about to get hidden.
     */    
    public abstract void aboutToHide();
    /**
     * This method will be called to display the title for the page of this descriptor.
     * @return
     */
    public String getPageTitle() {
    	return "";
    }
}
