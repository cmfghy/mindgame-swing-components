package org.mindgame.swing.components.forms;

import java.awt.AWTEvent;

public class CancelEvent extends AWTEvent {
	private static final long serialVersionUID = 1342291913372424053L;

	public CancelEvent(Object source) {
		super(source, 0);
	}
}
