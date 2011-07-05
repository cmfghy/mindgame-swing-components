package org.mindgame.swing.components;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.plaf.ButtonUI;

import org.mindgame.swing.components.ui.LinkedButtonUI;

public class LinkButton extends JButton {

	private static final long serialVersionUID = -1665988013716743352L;

	public LinkButton() {}

	public LinkButton(String text) {
		super(text);
	}

	public LinkButton(Action a) {
		super(a);
	}

	@Override
	public void setUI(ButtonUI ui) {
		super.setUI(new LinkedButtonUI(this, ui));
	}
}