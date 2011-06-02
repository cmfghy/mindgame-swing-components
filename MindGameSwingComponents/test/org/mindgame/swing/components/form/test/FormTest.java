package org.mindgame.swing.components.form.test;

import java.awt.GridLayout;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.mindgame.swing.components.forms.CancelEvent;
import org.mindgame.swing.components.forms.CancelListener;
import org.mindgame.swing.components.forms.Form;
import org.mindgame.swing.components.forms.SubmitEvent;
import org.mindgame.swing.components.forms.SubmitListener;

@SuppressWarnings("serial")
public class FormTest extends Form {

	@Override
	protected JComponent createUI() {
		JPanel p = new JPanel(new GridLayout(0, 2));
		p.add(new JLabel("Name"));
		p.add(new JTextField());
		p.add(new JLabel("Address"));
		p.add(new JTextField());
		p.add(new JLabel("Age"));
		p.add(new JTextField());
		return p;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final FormTest t = new FormTest();
		t.setTitle("FormTest");
		t.setSize(300, 150);
		t.addSubmitListener(new SubmitListener() {
			@Override
			public void formSubmitted(SubmitEvent event) {
				JOptionPane.showMessageDialog(t, "Ok clicked");
			}
		});
		t.addCancelListener(new CancelListener() {
			@Override
			public void formCancelled(CancelEvent event) {
				JOptionPane.showMessageDialog(t, "Cancel clicked");
			}
		});
		t.setVisible(true);
	}

}
