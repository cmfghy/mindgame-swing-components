package org.mindgame.swing.components.test;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.table.TableColumn;

import org.mindgame.swing.components.BeanTable;
import org.mindgame.swing.components.models.BeanTableModel;

public class BeanTableTest extends JFrame {

	private static final long serialVersionUID = 2717970857270451551L;
	/**
	 * @param args
	 */
	@SuppressWarnings("serial")
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
		
		BeanTableModel<TestBean> m = new BeanTableModel<TestBean>(){

			@Override
			protected Object getColumnValue(int columnIndex, TestBean bean) {
				switch (columnIndex) {
					case 0: return bean.getName();
					case 1: return bean.getAge();
					case 2: return bean.getCity();
					default: return null;
				}
			}

			@Override
			protected int compareObjects(int columnIndex, TestBean o1, TestBean o2) {
				if(o1 != null && o2 != null) {
					switch (columnIndex) {
						case 0: 
							if(o1.getName() != null && o2.getName() != null) {
								return o1.getName().compareTo(o2.getName());
							}
						case 1:
							if(o1.getAge() != null && o2.getAge() != null) {
								return o1.getAge().compareTo(o2.getAge());
							}
						case 2: 
							if(o1.getCity() != null && o2.getCity() != null) {
								return o1.getCity().compareTo(o2.getCity());
							}
						default: return 0;
					}
				}
				return 0;
			}
		};
		
		for (int i = 0; i < 5; i++) {
			m.insertRow(new TestBean("TEST" + (i+1), ""+(26+i), "City" + (i+1)));
		}
		
		BeanTable<TestBean> table = new BeanTable<TestBean>();
		table.setModel(m);
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
		t.getContentPane().add(new JScrollPane(table),BorderLayout.CENTER);
		t.setVisible(true);
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
		String getAge() {
			return age;
		}
		String getCity() {
			return city;
		}
	}
}