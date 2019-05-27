package controller;

import model.Bataille_navale_model;
import view.View;

public class ControlMenu {

	protected Bataille_navale_model model;
	protected View view;
	
	public ControlMenu(Bataille_navale_model model, View view) {
		this.model = model;
		this.view = view;
	}

}
