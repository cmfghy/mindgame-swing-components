package org.mindgame.swing.components.test;

import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.tree.DefaultMutableTreeNode;

import org.mindgame.swing.components.TreeComboBox;

public class TreeComboBoxTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame t = new JFrame("TreeComboBoxTest");
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		t.getContentPane().setLayout(null);
		t.setBounds(20, 20, 300, 300);
		TreeComboBox comboBox = new TreeComboBox(createJTree());
		comboBox.setBounds(0, 0, 200, 20);
		t.getContentPane().add(comboBox);
		t.setVisible(true);
	}
	
	private static JTree createJTree() {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("ROOT");
		

		
		
		DefaultMutableTreeNode country1 = new DefaultMutableTreeNode("India");
		DefaultMutableTreeNode state11 = new DefaultMutableTreeNode("MH");
		DefaultMutableTreeNode state12 = new DefaultMutableTreeNode("GJ");
		DefaultMutableTreeNode state13 = new DefaultMutableTreeNode("TN");
		country1.add(state11);
		country1.add(state12);
		country1.add(state13);
		
		
		DefaultMutableTreeNode country2 = new DefaultMutableTreeNode("USA");
		DefaultMutableTreeNode state21 = new DefaultMutableTreeNode("NY");
		DefaultMutableTreeNode state22 = new DefaultMutableTreeNode("MS");
		DefaultMutableTreeNode state23 = new DefaultMutableTreeNode("OH");
		country2.add(state21);
		country2.add(state22);
		country2.add(state23);
		
		root.add(country1);
		root.add(country2);
		
		JTree tree = new JTree(root);
		return tree;
	}
}