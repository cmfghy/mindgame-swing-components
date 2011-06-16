package org.mindgame.swing.components.test;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.mindgame.swing.components.CheckList;

public class CheckListTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final JFrame frame = new JFrame("CheckListTest");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setBounds(20, 20, 200, 200);
		final CheckList l = new CheckList(new String [] {"Mango","Watermelon","Banana","Orange","Apple"});
		frame.getContentPane().add(l,BorderLayout.CENTER);
//		l.setEnabled(false);
		JButton btn = new JButton("Test");
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame, l.getSelectedValues());
			}
		});
		frame.getContentPane().add(btn, BorderLayout.SOUTH);
		frame.setVisible(true);
	}

}
