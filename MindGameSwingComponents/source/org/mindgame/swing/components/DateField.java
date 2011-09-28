package org.mindgame.swing.components;

import java.util.Calendar;
import java.util.Date;

import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

public class DateField extends JSpinner {

	private static final long serialVersionUID = 229266548783422567L;

	public DateField() {
		super(new SpinnerDateModel(new Date(),null,null,Calendar.DAY_OF_MONTH));
		setEditor(new JSpinner.DateEditor(this, "dd/MM/yyyy"));
	}
	
	public Date getDate() {
		return ((SpinnerDateModel)getModel()).getDate();
	}
	
	public void setDate(Date date) {
		if(date == null) date = new Date();
		((SpinnerDateModel)getModel()).setValue(date);
	}
}
