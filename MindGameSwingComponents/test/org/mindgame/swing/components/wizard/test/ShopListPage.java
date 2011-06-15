package org.mindgame.swing.components.wizard.test;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

import org.mindgame.swing.components.wizard.WizardPage;

@SuppressWarnings("serial")
public class ShopListPage extends WizardPage {
	/*
	 * A List of Radio Buttons containing shops
	 * A Checkbox on the activity of which Next button will be enabled
	 * An information text 	
	 */
	
	
	private String option;
	
	private ResourceBundle wizardBundle;

	public final JRadioButton bookShop;

	public final JRadioButton wineShop;

	public final JRadioButton medicalShop;

	public final JRadioButton toysShop;
	
	public ShopListPage() {
		wizardBundle = ResourceBundle.getBundle("org.mindgame.swing.components.wizard.test.Wizard");
		bookShop = new JRadioButton("Book shops");
		wineShop = new JRadioButton("Wine shops");
		medicalShop = new JRadioButton("Medical shops");
		toysShop = new JRadioButton("Toys shops");
		init();		
	}
	
	private void init() {
		setLayout(new BorderLayout());
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
		
		bookShop.setActionCommand("BOOKS");
		group.add(bookShop);
		panel.add(bookShop);
		
		wineShop.setActionCommand("WINES");
		group.add(wineShop);
		panel.add(wineShop);		
		
		medicalShop.setActionCommand("MEDICINES");
		group.add(medicalShop);
		panel.add(medicalShop);
		
		toysShop.setActionCommand("TOYS");
		group.add(toysShop);
		panel.add(toysShop);
		
		ActionListener l = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JRadioButton source = (JRadioButton) e.getSource();
				setOption(source.getActionCommand());
			}
		};
		
		bookShop.addActionListener(l);
		wineShop.addActionListener(l);
		medicalShop.addActionListener(l);
		toysShop.addActionListener(l);
		
		return panel;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		String old = getOption();
		this.option = option;
		firePropertyChange("option", old, option);
	}

	@Override
	public boolean doValidate() {
		return true;
	}
}
