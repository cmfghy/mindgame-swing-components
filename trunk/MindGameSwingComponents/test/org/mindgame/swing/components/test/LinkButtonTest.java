package org.mindgame.swing.components.test;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.mindgame.swing.components.LinkButton;


public class LinkButtonTest {

	/**
	 * @param args
	 */
	@SuppressWarnings("serial")
	public static void main(String[] args) {
		final JFrame frame = new JFrame("Title");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(20, 20, 500, 500);
		frame.getContentPane().setLayout(null);
		LinkButton button = new LinkButton("Test");
		button.setAction(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame, "Testing Linkbutton");
			}
		});
		button.setBounds(0, 0, 100, 20);
		frame.add(button);
		
		frame.setVisible(true);
	}

}
