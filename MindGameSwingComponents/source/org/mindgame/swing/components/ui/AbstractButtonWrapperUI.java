package org.mindgame.swing.components.ui;

import java.awt.Component.BaselineResizeBehavior;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.accessibility.Accessible;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.ButtonUI;

public abstract class AbstractButtonWrapperUI extends ButtonUI {
	
	private final ButtonUI wrappedUI;
	
	private final JButton button;
	
	public AbstractButtonWrapperUI(JButton button, ButtonUI wrappedUI) {
		this.wrappedUI = wrappedUI;
		this.button = button;
	}

	@Override
	public void installUI(JComponent c) {
		wrappedUI.installUI(c);
	}

	@Override
	public void uninstallUI(JComponent c) {
		wrappedUI.uninstallUI(c);
	}

	@Override
	public void paint(Graphics g, JComponent c) {
		wrappedUI.paint(g, c);
	}

	@Override
	public void update(Graphics g, JComponent c) {
		wrappedUI.update(g, c);
	}

	@Override
	public Dimension getPreferredSize(JComponent c) {
		return wrappedUI.getPreferredSize(c);
	}

	@Override
	public Dimension getMinimumSize(JComponent c) {
		return wrappedUI.getMinimumSize(c);
	}

	@Override
	public Dimension getMaximumSize(JComponent c) {
		return wrappedUI.getMaximumSize(c);
	}

	@Override
	public boolean contains(JComponent c, int x, int y) {
		return wrappedUI.contains(c, x, y);
	}

	@Override
	public int getBaseline(JComponent c, int width, int height) {
		return wrappedUI.getBaseline(c, width, height);
	}

	@Override
	public BaselineResizeBehavior getBaselineResizeBehavior(JComponent c) {
		return wrappedUI.getBaselineResizeBehavior(c);
	}

	@Override
	public int getAccessibleChildrenCount(JComponent c) {
		return wrappedUI.getAccessibleChildrenCount(c);
	}

	@Override
	public Accessible getAccessibleChild(JComponent c, int i) {
		return wrappedUI.getAccessibleChild(c, i);
	}

	public JButton getButton() {
		return button;
	}
}
