package org.mindgame.swing.components.test;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.table.TableColumn;

import org.mindgame.swing.components.BeanTable;
import org.mindgame.swing.components.BeanTableDataExtractor;
import org.mindgame.swing.components.BeanTableModel;

public class BeanTableTest extends JFrame {

	private static final long serialVersionUID = 2717970857270451551L;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BeanTableTest t = new BeanTableTest();
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		t.getContentPane().setLayout(new BorderLayout());
		t.setTitle("BeanTableTest");
		t.setSize(250,200);		
		
		BeanTableDataExtractor<TestBean> ext = new TestBeanExtracter();
		BeanTableModel<TestBean> m = new BeanTableModel<TestBean>(ext);
		
		for (int i = 0; i < 5; i++) {
			m.insertRow(new TestBean("TEST" + (i+1), ""+(26+i), "City" + (i+1)));
		}
		
		BeanTable<TestBean> table = new BeanTable<TestBean>(m);
		table.setBounds(5, 5, 100, 100);
		
		
		TableColumn name = new TableColumn(0);
		name.setHeaderValue("Name");
		TableColumn age = new TableColumn(1);
		age.setHeaderValue("Age");
		TableColumn city = new TableColumn(2);
		city.setHeaderValue("City");
		table.addColumn(name);
		table.addColumn(age);
		table.addColumn(city);
		t.getContentPane().add(table,BorderLayout.CENTER);
		t.setVisible(true);
	}
	private static class TestBeanExtracter implements BeanTableDataExtractor<TestBean> {
		@Override
		public Object getColumnValue(int columnIndex, TestBean bean) {
			switch (columnIndex) {
				case 0: return bean.getName();
				case 1: return bean.getAge();
				case 2: return bean.getCity();
				default: return null;
			}
		}
	}
	private static class TestBean {
		String name;
		String age;
		String city;
		TestBean(String name, String age, String city) {
			super();
			this.name = name;
			this.age = age;
			this.city = city;
		}
		String getName() {
			return name;
		}
		void setName(String name) {
			this.name = name;
		}
		String getAge() {
			return age;
		}
		void setAge(String age) {
			this.age = age;
		}
		String getCity() {
			return city;
		}
		void setCity(String city) {
			this.city = city;
		}
	}
}