package org.mindgame.swing.components.forms;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.event.EventListenerList;

/**
 * This is a simple Form that contains two parts. First part is the main page
 * and second part is button bar. The button bar contains two buttons by default
 * Ok and Cancel. The two events viz. SubmitEvent and Cancel events are dispatched
 * on these buttons respectively. 
 *  
 * @author Mayuresh Halshikar
 *
 */
@SuppressWarnings("serial")
public abstract class Form extends JDialog {
	private EventListenerList listenerList = new EventListenerList();
	
	private Container buttonBar;
	
	
	public Form() {
		initialize();
	}
	
	private void initialize() {
		setModal(true);
		createButtonBar();
		setLayout(new BorderLayout());
		add(createUI(), BorderLayout.CENTER);
		add(this.buttonBar, BorderLayout.SOUTH);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				fireFormCancelled();
			}
		});
	}
	
	private void createButtonBar() {
		List<JButton> buttons = getAdditionalButtons();
		if(buttons == null) buttons = new ArrayList<JButton>();
		// Add ok
		JButton ok = new JButton("Ok");
		ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				fireFormSubmitted();
			}
		});
		buttons.add(ok);
		// Add cancel
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				fireFormCancelled();
			}
		});
		buttons.add(cancel);
		
		this.buttonBar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		
		
		// Append columns for additional buttons
		for (JButton button : buttons) {
			this.buttonBar.add(button);
		}
	}
	
	protected List<JButton> getAdditionalButtons() {
		return null;
	}
	
	protected abstract JComponent createUI();
	
	private void fireFormSubmitted() {
        // Guaranteed to return a non-null array
        Object[] listeners = listenerList.getListenerList();
        SubmitEvent e = null;
        // Process the listeners last to first, notifying
        // those that are interested in this event
        for (int i = listeners.length-2; i>=0; i-=2) {
            if (listeners[i]==SubmitListener.class) {
                // Lazily create the event:
                if (e == null) {
                	e = new SubmitEvent(this);
                }
                ((SubmitListener)listeners[i+1]).formSubmitted(e);
            }          
        }
	}

	private void fireFormCancelled() {
        // Guaranteed to return a non-null array
        Object[] listeners = listenerList.getListenerList();
        CancelEvent e = null;
        // Process the listeners last to first, notifying
        // those that are interested in this event
        for (int i = listeners.length-2; i>=0; i-=2) {
            if (listeners[i]==CancelListener.class) {
                // Lazily create the event:
                if (e == null) {
                	e = new CancelEvent(this);
                }
                ((CancelListener)listeners[i+1]).formCancelled(e);
            }          
        }
	}

	public void addSubmitListener(SubmitListener listener) {
		listenerList.add(SubmitListener.class, listener);
	}
	
	public void addCancelListener(CancelListener listener) {
		listenerList.add(CancelListener.class, listener);
	}
}
