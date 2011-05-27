package org.mindgame.swing.components.wizard;

import java.awt.*;
import javax.swing.*;

/**
 * This class acts as a descriptor for the pages that will be displayed
 * in the wizard  
 * @author Mayuresh Halshiar
 *
 */
public abstract class WizardPageDescriptor {
    
    public static final FinishIdentifier FINISH = new FinishIdentifier();
    /**
     * The parent wizard
     */
    private Wizard wizard;
    /**
     * Component that will be displayed on the wizard 
     */
    private Component page;
    /**
     * Id for this page
     */
    private Object pageId;

    public WizardPageDescriptor(Object id) {
    	this(id,new JPanel());
    }
    
    public WizardPageDescriptor(Object id, Component panel) {
        pageId = id;
        page = panel;
    }
   
    /**
     * Returns the page for which this descriptor is configured
     * @return - The page
     */
    public final Component getPage() {
        return page;
    }
    /**
     * Sets the page for which this descriptor is configured
     * @param panel
     */
    public final void setPage(Component panel) {
        page = panel;
    }
    /**
     * Returns id for the page
     * @return - Id
     */
    public final Object getPageId() {
        return pageId;
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
    public abstract Object getNextPageDescriptor();

    /**
     * All the custom descriptors will override this method to provide the id of the page
     * that will be considered as previous page. This method is called when the user clicks
     * Previous button. If this method returns NULL the wizard will simply disable the previous
     * button This method is supposed to return FinishIdentfier if the button text should 
     * change to 'Finish' and the dialog should end.
     * @return Page Id
     */    
    public abstract Object getBackPageDescriptor();
    
    /**
     * This method is called when the page of this descriptor is about to get displayed.
     */    
    public abstract void aboutToDisplayPanel(); 

    /**
     * This method is called when the page of this descriptor is just displayed.
     */    
    public abstract void displayingPanel();
    
    /**
     * This method is called when the page of this descriptor is about to get hidden.
     */    
    public abstract void aboutToHidePanel();
    /**
     * This method will be called to display the title for the page of this descriptor.
     * @return
     */
    public String getPageTitle() {
    	return "Default page title";
    }
    
    static class FinishIdentifier {
        public static final String ID = "FINISH";
    }
}
