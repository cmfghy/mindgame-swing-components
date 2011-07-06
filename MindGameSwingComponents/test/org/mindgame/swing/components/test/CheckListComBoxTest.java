package org.mindgame.swing.components.test;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import org.mindgame.swing.components.CheckList;
import org.mindgame.swing.components.CheckListComboBox;

public class CheckListComBoxTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final JFrame frame = new JFrame("CheckListTest");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setBounds(20, 20, 200, 200);
		final CheckList l = new CheckList(new String [] {"Mango","Watermelon","Banana","Orange","Apple"});
		frame.getContentPane().add(new CheckListComboBox(l),BorderLayout.NORTH);
		frame.setVisible(true);
	}

}
