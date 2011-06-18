package org.mindgame.swing.components.models;

/**
 * This interface is used by BeanTableModel to extract different values
 * of the bean for which the table is initialised.
 * @author Mayuresh Halshikar
 * @param <M> - The bean
 */
public interface BeanTableDataExtractor<M> {
	/**
	 * Returns the column value for the specified index for the column from
	 * the specified bean
	 * @param columnIndex - Index of the column
	 * @param bean - Bean
	 * @return Value for the column
	 */
	Object getColumnValue(int columnIndex, M bean);
}
