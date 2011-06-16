package org.mindgame.swing.components.wizard;


import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

public class Wizard extends JDialog implements PropertyChangeListener {
	private static final long serialVersionUID = 5211635634640235876L;

	// Return codes for the buttons.
	public static final int FINISH_RETURN_CODE = 0;
	public static final int CANCEL_RETURN_CODE = 1;
	public static final int ERROR_RETURN_CODE = 2;

	// Action commands for the buttons
	public static final String NEXT_BUTTON_ACTION_COMMAND = "NextButtonActionCommand";
	public static final String BACK_BUTTON_ACTION_COMMAND = "BackButtonActionCommand";
	public static final String CANCEL_BUTTON_ACTION_COMMAND = "CancelButtonActionCommand";


	private WizardModel wizardModel;
	private WizardController wizardController;
	private boolean forwardOnly;

	private JPanel cardPanel;
	private CardLayout cardLayout;            
	private JButton backButton;
	private JButton nextButton;
	private JButton cancelButton;

	private int returnCode;
	
	private JLabel currentPageTitle;

	public Wizard() {
		this((Frame)null);
	}

	public Wizard(Dialog owner) {
		super(owner);
		initComponents();
	}

	public Wizard(Frame owner) {
		super(owner);		
		initComponents();
	}

	private void initComponents() {
		wizardModel = new WizardModel();
		wizardModel.addPropertyChangeListener(this);       

		wizardController = new WizardController(this);       

		getContentPane().setLayout(new BorderLayout(5,5));
		addWindowListener(new WindowAdapter() {
			/*
			 * If the user presses the close box on the dialog's window, treat it
			 * as a cancel.
			 */ 
			 public void windowClosing(WindowEvent e) {
				 returnCode = CANCEL_RETURN_CODE;
			 }
		});

		currentPageTitle = new JLabel();
		cardPanel = new JPanel();
		cardPanel.setBorder(new EmptyBorder(new Insets(5, 10, 5, 10)));       
		cardLayout = new CardLayout();
		cardPanel.setLayout(cardLayout);

		getContentPane().add(createTitleBar(), BorderLayout.NORTH);
		getContentPane().add(cardPanel, java.awt.BorderLayout.CENTER);
		getContentPane().add(createButtonPanel(), java.awt.BorderLayout.SOUTH);

		wizardController.resetButtonsToPanelRules();
	}
	
	public void resetButtonsToPanelRules() {
		wizardController.resetButtonsToPanelRules();
	}
	
	private JPanel createTitleBar() {
		JPanel titleBar = new JPanel(new GridLayout(0, 1));
		this.currentPageTitle = new JLabel();
		this.currentPageTitle.setFont(new java.awt.Font("MS Sans Serif", Font.BOLD, 13));
		titleBar.add(this.currentPageTitle);
		titleBar.add(new JSeparator());
		titleBar.setBorder(new EmptyBorder(new Insets(5, 10, 5, 10)));
		return titleBar;
	}

	private JPanel createButtonPanel() {

		//  Create the outer wizard panel, which is responsible for three buttons:
		//  Next, Back, and Cancel. It is also responsible a JPanel above them that
		//  uses a CardLayout layout manager to display multiple panels in the 
		//  same spot.

		JPanel buttonPanel = new JPanel();
		JSeparator separator = new JSeparator();
		Box buttonBox = new Box(BoxLayout.X_AXIS);

		// Create buttons        
		backButton = new JButton();
		nextButton = new JButton();
		cancelButton = new JButton();

		// Set Action commands to the buttons
		backButton.setActionCommand(BACK_BUTTON_ACTION_COMMAND);
		nextButton.setActionCommand(NEXT_BUTTON_ACTION_COMMAND);
		cancelButton.setActionCommand(CANCEL_BUTTON_ACTION_COMMAND);

		// Add wizard controller as action listener for the buttons
		backButton.addActionListener(wizardController);
		nextButton.addActionListener(wizardController);
		cancelButton.addActionListener(wizardController);


		//  on the east side of the panel with a small amount of space between
		//  the back and the next button, and a larger amount of space between
		//  the next button and the cancel button.

		buttonPanel.setLayout(new BorderLayout());

		// Add separator to the button panel above all the buttons
		buttonPanel.add(separator, BorderLayout.NORTH);

		// Create ButtonBox and add it to the right (EAST) side of the button panel
		buttonBox.setBorder(new EmptyBorder(new Insets(5, 10, 5, 10)));       
		buttonBox.add(backButton);
		buttonBox.add(Box.createHorizontalStrut(10));
		buttonBox.add(nextButton);
		buttonBox.add(Box.createHorizontalStrut(30));
		buttonBox.add(cancelButton);
		buttonPanel.add(buttonBox, java.awt.BorderLayout.EAST);

		return buttonPanel;
	}


	public int showAsModalDialog() {
		// Sets the dialog as Modal and displays it
		setModal(true);
		setVisible(true);
		return returnCode;
	}

	public WizardModel getModel() {
		return wizardModel;
	}

	public void registerWizardPage(WizardPage page) {

		//  Add the incoming panel to our JPanel display that is managed by the CardLayout layout manager.
		cardPanel.add(page, page.getPageId());

		//  Set a callback to the current wizard.
		page.setWizard(this);

		//  Place a reference to it in the model. 
		wizardModel.registerPage(page);
	}  

	public void setCurrentPage(Object id) {

		// Get the hashtable reference to the panel that should be displayed. 
		// If the identifier passed in is null, then close the dialog.

		if (id == null)
			close(ERROR_RETURN_CODE);

		WizardPage oldPage = wizardModel.getCurrentPage();
		if (oldPage != null)
			oldPage.aboutToHide();

		wizardModel.setCurrentPage(id);
		wizardModel.getCurrentPage().aboutToDisplay();

		//  Show the page in the dialog.       
		currentPageTitle.setText(wizardModel.getCurrentPage().getPageTitle());
		cardLayout.show(cardPanel, id.toString());
		wizardModel.getCurrentPage().displaying();
		repaint();
	}

	public void propertyChange(PropertyChangeEvent evt) {         
		if (evt.getPropertyName().equals(WizardModel.CURRENT_PANEL_DESCRIPTOR_PROPERTY)) {
			// If the current panel is changed, reset the buttons
			wizardController.resetButtonsToPanelRules(); 
		} else if (evt.getPropertyName().equals(WizardModel.NEXT_FINISH_BUTTON_TEXT_PROPERTY)) {
			// Change text of next button
			nextButton.setText(evt.getNewValue().toString());
		} else if (evt.getPropertyName().equals(WizardModel.BACK_BUTTON_TEXT_PROPERTY)) {            
			// Change text of back button
			backButton.setText(evt.getNewValue().toString());
		} else if (evt.getPropertyName().equals(WizardModel.CANCEL_BUTTON_TEXT_PROPERTY)) {            
			// Change text of cancel button
			cancelButton.setText(evt.getNewValue().toString());
		} else if (evt.getPropertyName().equals(WizardModel.NEXT_FINISH_BUTTON_ENABLED_PROPERTY)) {
			// Enable the next button
			nextButton.setEnabled(((Boolean)evt.getNewValue()).booleanValue());
		} else if (evt.getPropertyName().equals(WizardModel.BACK_BUTTON_ENABLED_PROPERTY)) {            
			// Enable the back button
			backButton.setEnabled(((Boolean)evt.getNewValue()).booleanValue());
		} else if (evt.getPropertyName().equals(WizardModel.CANCEL_BUTTON_ENABLED_PROPERTY)) {            
			// Enable the cancel button
			cancelButton.setEnabled(((Boolean)evt.getNewValue()).booleanValue());
		}
	}

	/**
	 * Retrieves the last return code set by the dialog.
	 * @return An integer that identifies how the dialog was closed. See the *_RETURN_CODE
	 * constants of this class for possible values.
	 */    
	public int getReturnCode() {
		return returnCode;
	}

	/**
	 * Mirrors the WizardModel method of the same name.
	 * @return A boolean indicating if the button is enabled.
	 */  
	public boolean getBackButtonEnabled() {
		return wizardModel.getBackButtonEnabled().booleanValue();
	}

	/**
	 * Mirrors the WizardModel method of the same name.
	 * @param boolean newValue The new enabled status of the button.
	 */ 
	public void setBackButtonEnabled(boolean newValue) {
		wizardModel.setBackButtonEnabled(new Boolean(newValue));
	}

	/**
	 * Mirrors the WizardModel method of the same name.
	 * @return A boolean indicating if the button is enabled.
	 */ 
	public boolean getNextFinishButtonEnabled() {
		return wizardModel.getNextFinishButtonEnabled().booleanValue();
	}

	/**
	 * Mirrors the WizardModel method of the same name.
	 * @param boolean newValue The new enabled status of the button.
	 */ 
	public void setNextFinishButtonEnabled(boolean newValue) {
		wizardModel.setNextFinishButtonEnabled(new Boolean(newValue));
	}

	/**
	 * Mirrors the WizardModel method of the same name.
	 * @return A boolean indicating if the button is enabled.
	 */ 
	public boolean getCancelButtonEnabled() {
		return wizardModel.getCancelButtonEnabled().booleanValue();
	}

	/**
	 * Mirrors the WizardModel method of the same name.
	 * @param boolean newValue The new enabled status of the button.
	 */ 
	public void setCancelButtonEnabled(boolean newValue) {
		wizardModel.setCancelButtonEnabled(new Boolean(newValue));
	}

	/**
	 * Closes the dialog and sets the return code to the integer parameter.
	 * @param code The return code.
	 */    
	void close(int code) {
		returnCode = code;
		dispose();
	}
	
	/**
	 * Returns true if this wizard f forward only wizard 
	 * @return
	 */
	public boolean isForwardOnly() {
		return forwardOnly;
	}
	
	/**
	 * Makes the wizard forward only
	 * @param forwardOnly
	 */
	public void setForwardOnly(boolean forwardOnly) {
		this.forwardOnly = forwardOnly;
	}
}
