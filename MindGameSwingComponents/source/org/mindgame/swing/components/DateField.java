package org.mindgame.swing.components;

import java.util.Calendar;
import java.util.Date;

import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

public class DateField extends JSpinner {

	private static final long serialVersionUID = 229266548783422567L;

	public DateField() {
		super(new SpinnerDateModel(new Date(),null,null,Calendar.DAY_OF_MONTH));
	}
	
	public Date getDate() {
		return ((SpinnerDateModel)getModel()).getDate();
	}
}
