package org.mindgame.swing.components.wizard;

import java.awt.event.ActionListener;

/**
 * This class acts as a controller for all the activities happening on the wizard. 
 * It reacts to events generated by clicking on any of the three buttons, 
 * 'Next', 'Previous', and 'Cancel.' Based on what button is pressed,
 * the controller will update the model to show a new panel and reset the state of
 * the buttons as necessary.
 */
public class WizardController implements ActionListener {
	/**
	 * Wizard for which this controller will be working
	 */
    private Wizard wizard;
    
    public WizardController(Wizard w) {
        wizard = w;
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        
        if (evt.getActionCommand().equals(Wizard.CANCEL_BUTTON_ACTION_COMMAND))
            cancelButtonPressed();
        else if (evt.getActionCommand().equals(Wizard.BACK_BUTTON_ACTION_COMMAND))
            backButtonPressed();
        else if (evt.getActionCommand().equals(Wizard.NEXT_BUTTON_ACTION_COMMAND))
            nextButtonPressed();
    }
    
    
    
    private void cancelButtonPressed() {
        wizard.close(Wizard.CANCEL_RETURN_CODE);
    }

    private void nextButtonPressed() {
        WizardModel model = wizard.getModel();
        WizardPage currentPage = model.getCurrentPage();
    	if(this.wizard.isForwardOnly()) {
    		// Disable the current page descriptor page
    		currentPage.enablePage(false);
    	}
        //  If it is a finishable page, close down the dialog. Otherwise,
        //  get the ID that the current panel identifies as the next panel,
        //  and display it.
        Object nextPage = currentPage.getNextPage();
        if (WizardPage.FINISH_IDENTIFIER == nextPage) {
            wizard.close(Wizard.FINISH_RETURN_CODE);
        } else {        
            wizard.setCurrentPage(nextPage);
        }
    }

    private void backButtonPressed() {
        WizardModel model = wizard.getModel();
        WizardPage page = model.getCurrentPage();
 
        //  Get the page that the current panel identifies as the previous
        //  panel, and display it.
        
        Object backPanelDescriptor = page.getPreviousPage();        
        wizard.setCurrentPage(backPanelDescriptor);
    }

    
    void resetButtonsToPanelRules() {
        //  Reset the buttons to support the original panel rules,
        //  including whether the next or back buttons are enabled or
        //  disabled, or if the panel is finishable.

    	WizardModel model = wizard.getModel();
        WizardPage page = model.getCurrentPage();
        
        model.setCancelButtonText("Cancel");
        
        //  Enable the back button if necessary
        model.setBackButtonText("Back");
        model.setBackButtonEnabled(page != null && page.getPreviousPage() != null);

        //  If the current page is the last page the button will be Finish else Next
        if (page != null && WizardPage.FINISH_IDENTIFIER == page.getNextPage()) {
            model.setNextFinishButtonText("Finish");
        } else {
            model.setNextFinishButtonText("Next");
        }
        //  Enable the next button if necessary
        if (page != null && page.getNextPage() != null)
            model.setNextFinishButtonEnabled(Boolean.TRUE);
        else
            model.setNextFinishButtonEnabled(Boolean.FALSE);
    }
}