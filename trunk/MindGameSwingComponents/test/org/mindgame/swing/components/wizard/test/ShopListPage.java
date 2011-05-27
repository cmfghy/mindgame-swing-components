package org.mindgame.swing.components.wizard.test;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ResourceBundle;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class ShopListPage extends JPanel {
	/*
	 * A title 
	 * A List of Radio Buttons containing shops
	 * A Checkbox on the activity of which Next button will be enabled
	 * An information text 	
	 */
	
	private JLabel title;
	
	private ResourceBundle wizardBundle;
	
	public ShopListPage() {
		wizardBundle = ResourceBundle.getBundle("org.mindgame.swing.components.wizard.test.Wizard");
		init();		
	}
	
	private void init() {
		setLayout(new BorderLayout());
		title = new JLabel(wizardBundle.getString("shoplist.title.text"));
		add(title, BorderLayout.NORTH);
		add(createShopListPanel(), BorderLayout.CENTER);
		add(createInfoPanel(), BorderLayout.SOUTH);
	}
	
	private JPanel createInfoPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		JTextArea infoArea = new JTextArea();
		infoArea.setText(wizardBundle.getString("shoplist.information.text"));
		panel.add(infoArea, BorderLayout.NORTH);
		infoArea.setEditable(false);
		infoArea.setOpaque(false);
		return panel;
	}
	
	private JPanel createShopListPanel() {
		JPanel panel = new JPanel(new GridLayout(0, 1));
		ButtonGroup group = new ButtonGroup();
		
		JRadioButton bookShop = new JRadioButton("Book shops");
		group.add(bookShop);
		panel.add(bookShop);
		bookShop.setSelected(true);
		
		JRadioButton wineShop = new JRadioButton("Wine shops");
		group.add(wineShop);
		panel.add(wineShop);		
		JRadioButton medicalShop = new JRadioButton("Medical shops");
		group.add(medicalShop);
		panel.add(medicalShop);
		JRadioButton toysShop = new JRadioButton("Toys shops");
		group.add(toysShop);
		panel.add(toysShop);
		return panel;
	}
}
