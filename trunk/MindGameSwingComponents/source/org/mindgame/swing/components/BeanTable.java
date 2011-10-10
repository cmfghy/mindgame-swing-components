package org.mindgame.swing.components;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

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
	
	private int sortColumn;
	
	private HashMap<Integer, Boolean> sortMap;
	
	/**
	 * Default constructor
	 */
	public BeanTable() {
		this(null);
	}
	
	public BeanTable(BeanTableModel<M> model) {
		super(model);
		this.sortMap = new HashMap<Integer, Boolean>();
		init();
	}
	
	private Image getScaledImage(Image srcImg, int w, int h) {
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = resizedImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();
        return resizedImg;
    }	

	
	private void init() {
		getTableHeader().setDefaultRenderer(new BeanTableHeaderRenderer());
		
		getTableHeader().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				sortColumn = getTableHeader().columnAtPoint(e.getPoint());	
				sort();
			}
		});
	}
	
	private synchronized boolean isAscending() {
		Boolean ascending = sortMap.get(sortColumn);
		return ascending == null ? false : ascending;
	}
	
	private synchronized void sort() {
		Boolean ascending = !isAscending();
		sortMap.put(sortColumn, ascending);
		getModelAsBeanTableModel().sort(sortColumn, ascending);
	}

	/**
	 * Sets the list of beans as a dataProvider
	 * @param dataProvider - A list of beans used to populate rows in the table
	 */
	public void setDataProvider(List<M> dataProvider) {
		this.getModelAsBeanTableModel().setDataProvider(dataProvider);
	}
	
	/**
	 * Returns a list of beans used to populate rows in the table
	 * @return - A list
	 */
	public List<M> getDataProvider() {
		return this.getModelAsBeanTableModel().getDataProvider();
	}
	/**
	 * Inserts the given bean as a last row in the table
	 * @param rowData
	 */
	public void insertRow(M rowData) {
		this.getModelAsBeanTableModel().insertRow(rowData);
	}
	/**
	 * Deletes last row from the table
	 */
	public void deleteLastRow() {
		this.getModelAsBeanTableModel().deleteLastRow();
	}

	/**
	 * Returns the bean bound to the row having the given index
	 * @param rowIndex
	 * @return - The bean
	 */
	public M getRowValue(int rowIndex) {
		return this.getModelAsBeanTableModel().getRowValue(rowIndex);
	}
	
    public Dimension getPreferredScrollableViewportSize() {
        return getPreferredSize();
    }
    
    @SuppressWarnings("unchecked")
	private BeanTableModel<M> getModelAsBeanTableModel() {
    	return (BeanTableModel<M>) super.getModel();
    }
    
    @SuppressWarnings("serial")
	private class BeanTableHeaderRenderer extends JLabel implements TableCellRenderer {

    	private BeanTableHeaderRenderer() {
			this.setBorder(BorderFactory.createEtchedBorder());
		}
    	
		@Override
		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			Icon icon = null;
			if(column == BeanTable.this.sortColumn) {
				URL url = isAscending() ? 
						getClass().getClassLoader().getResource("org/mindgame/swing/components/UpArrow.jpg") : 
						getClass().getClassLoader().getResource("org/mindgame/swing/components/DownArrow.jpg") ;
				try {
					Image image = ImageIO.read(url);					
					icon = new ImageIcon(getScaledImage(image, 15, 15));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			this.setIcon(icon);
			this.setText(String.valueOf(value));
			return this;
		}
    }
}
