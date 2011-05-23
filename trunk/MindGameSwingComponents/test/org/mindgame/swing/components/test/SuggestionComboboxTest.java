package org.mindgame.swing.components.test;

import javax.swing.JFrame;
import javax.swing.UIManager;

import org.mindgame.swing.components.SuggesionComboBox;

public class SuggestionComboboxTest extends JFrame {
	private static final long serialVersionUID = -5033950526168157861L;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SuggestionComboboxTest t = new SuggestionComboboxTest();
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		t.getContentPane().setLayout(null);

		t.setSize(150,100);
		
		String [] values = new String [] {"Mayuresh","Mithil","Anand","Tapas","Ganesh","Vinay","Rohit"};

		SuggesionComboBox combo = new SuggesionComboBox(values);
		
		combo.setBounds(5, 5, 100, 20);
		t.getContentPane().add(combo);
		t.setVisible(true);
	}

}
