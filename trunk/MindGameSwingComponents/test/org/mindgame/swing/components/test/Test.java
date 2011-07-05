package org.mindgame.swing.components.test;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;


public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final JFrame frame = new JFrame("Title");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(20, 20, 500, 500);
		
		frame.getContentPane().setLayout(null);
		
		JButton button = new JButton("<html><u>Test</u></html>");
		button.setFont(button.getFont().deriveFont(Font.PLAIN));
		button.setForeground(Color.BLUE);
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
		button.setRolloverEnabled(true);
		button.setBounds(0, 0, 100, 20);
		
		frame.add(button);
		
		frame.setVisible(true);
	}

}
