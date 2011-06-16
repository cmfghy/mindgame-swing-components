package org.mindgame.swing.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.AbstractCellEditor;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.ListModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

/**
 * This class provides a list having CheckBoxes. User can select/de-select any item
 * from the list. The class uses ListModel to have a list of data.
 * 
 * @author Mayuresh Halshikar
 */
@SuppressWarnings("serial")
public class CheckList extends JTable {
	
	/** Selected values in which selected items will be pushed */
	private List<Object> selectedValues;

	/**
	 * Default Constructor
	 */
	public CheckList() {
		this(new DefaultListModel());
	}

	/**
	 * Constructor that takes ListModel
	 * @param dataModel - ListModel
	 */
	public CheckList(final ListModel dataModel) {
		super(new DefaultTableModel(){
			@Override
			public int getRowCount() {return dataModel.getSize();}

			@Override
			public int getColumnCount() {return 1;}
			
			@Override
			public String getColumnName(int columnIndex) {return "";}
			
			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {return true;}
			
			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {return dataModel.getElementAt(rowIndex);}
		});
		init();
	}

	/**
	 * Constructor that gets an array of objects
	 * @param listData - Array of objects that are going to be displayed
	 */
	public CheckList(final Object[] listData) {
		this(new AbstractListModel() {
			@Override
			public int getSize() {return listData.length;}
			@Override
			public Object getElementAt(int index) {return listData[index];}
		});
	}
	/**
	 * Constructor that gets a vector of objects
	 * @param listData - Vector of objects that are going to be displayed
	 */
	public CheckList(final Vector<?> listData) {
		this(new AbstractListModel() {
			@Override
			public int getSize() {return listData.size();}
			@Override
			public Object getElementAt(int index) {return listData.get(index);}
		});
	}
	/*
	 * Non-java doc  
	 */
	private void init() {
		this.selectedValues = new ArrayList<Object>();
		this.setShowGrid(false);
		CellRendererCumEditor e = new CellRendererCumEditor();
		this.getColumnModel().getColumn(0).setCellEditor(e);
		this.getColumnModel().getColumn(0).setCellRenderer(e);
	}
	/**
	 * Returns list of values that are selected by the user 
	 * @return - List of selected values
	 */
	public List<Object> getSelectedValues() {
		return selectedValues;
	}
	/**
	 * Sets the selected values
	 * @param selectedValues - List of selected values
	 */
	public void setSelectedValues(List<Object> selectedValues) {
		this.selectedValues = selectedValues;
		// Need to repaint as selected values changed
		repaint();
	}
	/*
	 * Non-java doc  
	 */
	private class CellRendererCumEditor extends AbstractCellEditor implements TableCellRenderer, TableCellEditor {
		private Object cellEditorValue;
		@Override
		public Object getCellEditorValue() {
			return this.cellEditorValue;
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			this.cellEditorValue = value;
			JCheckBox box = new JCheckBox(String.valueOf(value));
			box.setSelected(CheckList.this.selectedValues.contains(value));
			box.setBackground(Color.white);
			box.setEnabled(CheckList.this.isEnabled()); 
			return box;
		}

		@Override
		public Component getTableCellEditorComponent(JTable table,
				final Object value, boolean isSelected, int row, int column) {
			JCheckBox box = (JCheckBox) getTableCellRendererComponent(table, value, isSelected, true, row, column);
			box.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					if(e.getStateChange() == ItemEvent.SELECTED) {
						selectedValues.add(value);
					} else {
						selectedValues.remove(value);
					}
				}
			});
			return box;
		}
	}
}
