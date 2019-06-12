package controller;

import model.Bataille_navale_model;
import view.View_accueil;

public class ControlMenu {

	protected Bataille_navale_model model;
	protected View_accueil view;
	
	public ControlMenu(Bataille_navale_model model, View_accueil view) {
		this.model = model;
		this.view = view;
	}

}
