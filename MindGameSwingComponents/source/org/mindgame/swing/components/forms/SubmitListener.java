package org.mindgame.swing.components.forms;


import java.util.EventListener;

public interface SubmitListener extends EventListener {
	void formSubmitted(SubmitEvent event);
}
