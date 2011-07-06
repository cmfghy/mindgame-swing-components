package org.mindgame.swing.components;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.plaf.ComboBoxUI;

import org.mindgame.swing.components.ui.AbstractComboBoxUI;

@SuppressWarnings("serial")
public class CheckListComboBox extends JComboBox {
	private CheckList list;
	
	public CheckListComboBox(final CheckList list) {
		this.list = list;
		this.setModel(new CheckListComboBoxModel());
		setUI(null);
	}
	
	@Override
	public void setUI(ComboBoxUI ui) {
		super.setUI(new AbstractComboBoxUI() {
			@Override
			protected JComponent getPopUpComponent() {	
				JScrollPane pane = new JScrollPane(list);
				return pane;
			}
		});
	}
	
	/**
	 * Combobox model that in turn uses Table model for check list
	 * @author Mayuresh Halshikar
	 *
	 */
	private class CheckListComboBoxModel extends DefaultComboBoxModel {

		@Override
		public int getSize() {
			return list.getModel().getRowCount();
		}

		@Override
		public Object getElementAt(int index) {
			return list.getModel().getValueAt(index, 1);
		}
	}
	

}
