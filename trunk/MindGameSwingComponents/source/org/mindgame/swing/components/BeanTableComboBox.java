package org.mindgame.swing.components;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.plaf.ComboBoxUI;

import org.mindgame.swing.components.models.BeanTableModel;
import org.mindgame.swing.components.ui.AbstractComboBoxUI;

/**
 * This class is a combo box extension that provides a BeanTable as a popup for the
 * combo box
 * @author Mayuresh Halshikar
 *
 * @param <M>
 */
@SuppressWarnings("serial")
public class BeanTableComboBox<M> extends JComboBox {
	/** table to be displayed */
	private BeanTable<M> table;
	
	public BeanTableComboBox(final BeanTable<M> table) {
		this.table = table;
		this.table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setModel(new BeanTableComboBoxModel());
		this.table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(!e.isPopupTrigger()) {
					int row = table.rowAtPoint(e.getPoint());
					if(!table.getSelectionModel().isSelectedIndex(row)) {
						table.getSelectionModel().setSelectionInterval(row, row);
					}
					BeanTableComboBox.this.setSelectedItem(table.getRowValue(row));
					BeanTableComboBox.this.getUI().setPopupVisible(BeanTableComboBox.this, false);
				} else {
					table.getSelectionModel().clearSelection();
				}
			}
		});
		setUI(null);
	}
	
	@Override
	public void setUI(ComboBoxUI ui) {
		super.setUI(new AbstractComboBoxUI() {
			@Override
			protected JComponent getPopUpComponent() {
				return new JScrollPane(table);
			}
		});
	}
	
	/**
	 * Combobox model that in turn uses Table model
	 * @author Mayuresh Halshikar
	 *
	 */
	private class BeanTableComboBoxModel extends DefaultComboBoxModel {

		@Override
		public int getSize() {
			return table.getModel().getRowCount();
		}

		@SuppressWarnings("unchecked")
		@Override
		public Object getElementAt(int index) {
			return ((BeanTableModel<M>)table.getModel()).getRowValue(index);
		}
	}
}
