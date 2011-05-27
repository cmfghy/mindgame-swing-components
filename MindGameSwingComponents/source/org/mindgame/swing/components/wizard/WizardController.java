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
        WizardPageDescriptor descriptor = model.getCurrentPanelDescriptor();
        
        //  If it is a finishable panel, close down the dialog. Otherwise,
        //  get the ID that the current panel identifies as the next panel,
        //  and display it.
        
        Object nextPanelDescriptor = descriptor.getNextPageDescriptor();
        
        if (nextPanelDescriptor instanceof WizardPageDescriptor.FinishIdentifier) {
            wizard.close(Wizard.FINISH_RETURN_CODE);
        } else {        
            wizard.setCurrentPanel(nextPanelDescriptor);
        }
        
    }

    private void backButtonPressed() {
        WizardModel model = wizard.getModel();
        WizardPageDescriptor descriptor = model.getCurrentPanelDescriptor();
 
        //  Get the descriptor that the current panel identifies as the previous
        //  panel, and display it.
        
        Object backPanelDescriptor = descriptor.getBackPageDescriptor();        
        wizard.setCurrentPanel(backPanelDescriptor);
    }

    
    void resetButtonsToPanelRules() {
        //  Reset the buttons to support the original panel rules,
        //  including whether the next or back buttons are enabled or
        //  disabled, or if the panel is finishable.

    	WizardModel model = wizard.getModel();
        WizardPageDescriptor descriptor = model.getCurrentPanelDescriptor();
        
        model.setCancelButtonText("Cancel");
        
        //  Enable the back button if necessary
        model.setBackButtonText("Back");
        model.setBackButtonEnabled(descriptor != null && descriptor.getBackPageDescriptor() != null);

        //  If the current page is the last page the button will be Finish else Next
        if (descriptor != null && descriptor.getNextPageDescriptor() instanceof WizardPageDescriptor.FinishIdentifier) {
            model.setNextFinishButtonText("Finish");
        } else {
            model.setNextFinishButtonText("Next");
        }
        //  Enable the next button if necessary
        if (descriptor != null && descriptor.getNextPageDescriptor() != null)
            model.setNextFinishButtonEnabled(Boolean.TRUE);
        else
            model.setNextFinishButtonEnabled(Boolean.FALSE);
    }
}