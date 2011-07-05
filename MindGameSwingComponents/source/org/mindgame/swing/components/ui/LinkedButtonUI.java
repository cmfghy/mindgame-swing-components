package org.mindgame.swing.components.ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.plaf.ButtonUI;

import org.mindgame.swing.components.LinkButton;

public class LinkedButtonUI extends AbstractButtonWrapperUI {

	public LinkedButtonUI(LinkButton button, ButtonUI wrappedUI) {
		super(button, wrappedUI);
	}
	
	@Override
	public void installUI(JComponent c) {
		super.installUI(c);
		
		getButton().addPropertyChangeListener(AbstractButton.TEXT_CHANGED_PROPERTY, new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				getButton().removePropertyChangeListener(AbstractButton.TEXT_CHANGED_PROPERTY, this);
				String text = getButton().getText();
				getButton().setText("<html><font><u>" + (text == null ? "" : text) + "</u></font></html>");				
				getButton().addPropertyChangeListener(AbstractButton.TEXT_CHANGED_PROPERTY, this);
			}
		});
		getButton().setFont(getButton().getFont().deriveFont(Font.PLAIN));
		getButton().setForeground(Color.BLUE);
		getButton().setBorderPainted(false);
		getButton().setContentAreaFilled(false);
		getButton().setRolloverEnabled(true);
		getButton().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				getButton().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
		});
	}
}
