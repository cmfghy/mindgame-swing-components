package org.mindgame.swing.components.forms;

import java.util.EventListener;

public interface CancelListener extends EventListener {
	void formCancelled(CancelEvent event);
}
