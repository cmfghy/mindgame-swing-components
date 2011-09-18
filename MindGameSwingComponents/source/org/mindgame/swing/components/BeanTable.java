package org.mindgame.swing.components;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JTable;

import org.mindgame.swing.components.models.BeanTableModel;
/**
 * This is a JTable extension specifically designed to handle java beans.
 * It works for the bean specified as a template for the class. It has a property
 * called <code>dataProvider</code> which contains list of beans. Each bean represents
 * a row in a table.
 * @author Mayuresh Halshikar
 * @param <M>
 */
public class BeanTable<M> extends JTable {
	private static final long serialVersionUID = 1784947807689708700L;
	/**
	 * Model for the table
	 */
	private BeanTableModel<M> model;
	
	/**
	 * Default constructor
	 */
	public BeanTable() {
		this(null);
	}
	
	public BeanTable(BeanTableModel<M> model) {
		super(model);
		this.model = model;
	}
	
	/**
	 * Sets the list of beans as a dataProvider
	 * @param dataProvider - A list of beans used to populate rows in the table
	 */
	public void setDataProvider(List<M> dataProvider) {
		this.model.setDataProvider(dataProvider);
	}
	
	/**
	 * Returns a list of beans used to populate rows in the table
	 * @return - A list
	 */
	public List<M> getDataProvider() {
		return this.model.getDataProvider();
	}
	/**
	 * Inserts the given bean as a last row in the table
	 * @param rowData
	 */
	public void insertRow(M rowData) {
		this.model.insertRow(rowData);
	}
	/**
	 * Deletes last row from the table
	 */
	public void deleteLastRow() {
		this.model.deleteLastRow();
	}
	/**
	 * Returns the bean bound to the row having the given index
	 * @param rowIndex
	 * @return - The bean
	 */
	public M getRowValue(int rowIndex) {
		return this.model.getRowValue(rowIndex);
	}
	
    public Dimension getPreferredScrollableViewportSize() {
        return getPreferredSize();
    }
}
