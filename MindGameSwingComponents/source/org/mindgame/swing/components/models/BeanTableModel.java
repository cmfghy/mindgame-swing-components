package org.mindgame.swing.components.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import java.util.List;

import javax.swing.table.AbstractTableModel;


/**
 * This is a TableModel extension specifically designed to handle java beans.
 * It works for the bean specified as a template for the class. It has a property
 * called <code>dataProvider</code> which contains list of beans. Each bean represents
 * a row in a table.
 * @author Mayuresh Halshikar
 * @param <M> - The Bean
 */
public abstract class BeanTableModel<M> extends AbstractTableModel {
	private static final long serialVersionUID = 8865418800829313105L;
	/**
	 * A list containing beans that will be displayed as rows in the table 
	 */
	private List<M> dataProvider;
	
	public BeanTableModel() {}
	
	public BeanTableModel(List<M> dataProvider) {
		this.dataProvider = dataProvider;
	}
	
	@Override
	public int getRowCount() {
		return dataProvider == null ? 0 : dataProvider.size();
	}

	@Override
	public int getColumnCount() {
		// BeanTableModel expects the table to manage it's columns independently
		return 0;
	}
	/**
	 * Sets the list of beans as a dataProvider
	 * @param dataProvider - A list of beans used to populate rows in the table
	 */
	public void setDataProvider(List<M> dataProvider) {
		this.dataProvider = dataProvider;
		fireTableDataChanged();
	}
	/**
	 * Returns a list of beans used to populate rows in the table
	 * @return - A list
	 */
	public List<M> getDataProvider() {
		return this.dataProvider;
	}
	/**
	 * Inserts the given bean as a last row in the table
	 * @param rowData
	 */
	public void insertRow(M rowData) {
		if(this.dataProvider == null) {
			this.dataProvider = new ArrayList<M>();
		}
		this.dataProvider.add(rowData);
		fireTableRowsInserted(this.dataProvider.size(), this.dataProvider.size());
	}
	/**
	 * Deletes last row from the table
	 */
	public void deleteLastRow() {
		this.dataProvider.remove(getRowCount() - 1);
		fireTableRowsDeleted(this.dataProvider.size(), this.dataProvider.size());
	}
	/**
	 * Deletes row from the table having the specified index
	 * @param rowIndex - Index for the row to delete.
	 */
	public void deleteRow(int rowIndex) {
		if(rowIndex >= 0 && rowIndex < dataProvider.size()) {
			dataProvider.remove(rowIndex);
			fireTableRowsDeleted(rowIndex, rowIndex);
		}
	}
	/**
	 * Returns the bean bound to the row having the given index
	 * @param rowIndex
	 * @return - The bean
	 */
	public M getRowValue(int rowIndex) {
		if(this.dataProvider != null && rowIndex >= 0 && rowIndex < dataProvider.size()) {
			return this.dataProvider.get(rowIndex);
		}
		return null;
	}
	
	public boolean isSortingOn() {
		return true;
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return true;
	}

	@Override
	public final Object getValueAt(int rowIndex, int columnIndex) {
		M bean = getRowValue(rowIndex);
		if(bean != null) {
			return getColumnValue(columnIndex, bean);
		}
		return null;
	}
	
	public final void sort(final int columnIndex, final boolean ascending) {
		Collections.sort(this.dataProvider, new Comparator<M>() {
			@Override
			public int compare(M o1, M o2) {
				int result = compareObjects(columnIndex, o1, o2);
				if(result == 0) {
					return 0;
				} else if (ascending) {
					return result;
				} else {
					return (result * -1);
				}
			}
		});
		fireTableDataChanged();
	}

	protected abstract Object getColumnValue(int columnIndex, M bean);
	
	protected abstract int compareObjects(int columnIndex, M o1, M o2);
	
}