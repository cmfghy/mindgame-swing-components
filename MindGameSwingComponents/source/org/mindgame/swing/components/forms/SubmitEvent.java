package org.mindgame.swing.components.forms;

import java.awt.AWTEvent;

public class SubmitEvent extends AWTEvent {
	private static final long serialVersionUID = -2954622859468614814L;
	
	public SubmitEvent(Object source) {
		super(source, 0);
	}
}
