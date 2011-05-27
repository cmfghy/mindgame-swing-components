package org.mindgame.swing.components.wizard.test;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ResourceBundle;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class IntroductionPage extends JPanel {
 
	private JTextArea introductionText;
    private JLabel welcomeTitle;
    
    public IntroductionPage() {
        setLayout(new java.awt.BorderLayout());
        add(getContentPanel(), BorderLayout.CENTER);
    }
    
    
    private JPanel getContentPanel() {
    	ResourceBundle wizardBundle = ResourceBundle.getBundle("org.mindgame.swing.components.wizard.test.Wizard");

    	JPanel panel = new JPanel();
        welcomeTitle = new JLabel();
        introductionText = new JTextArea();
        introductionText.setEditable(false);
        introductionText.setOpaque(false);
        
        panel.setLayout(new java.awt.BorderLayout());

        welcomeTitle.setFont(new java.awt.Font("MS Sans Serif", Font.BOLD, 11));
        welcomeTitle.setText(wizardBundle.getString("introduction.title.text"));
        introductionText.setText(wizardBundle.getString("introduction.text"));
        
        panel.add(welcomeTitle, java.awt.BorderLayout.NORTH);
        panel.add(new JScrollPane(introductionText), java.awt.BorderLayout.CENTER);

        return panel;
    }
}
