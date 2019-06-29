package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Bataille_navale_model;
import view.View_matelot;

public class ControlTimer_matelot implements ActionListener {
	protected Bataille_navale_model model;
	protected View_matelot viewMatelot;
	
	public ControlTimer_matelot(Bataille_navale_model model, View_matelot viewMatelot) {
		this.model = model;
        this.viewMatelot = viewMatelot;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	    if(e.getSource() == viewMatelot.timerAffichage) {
	    	viewMatelot.changeVue();
	    } else if(e.getSource() == viewMatelot.timerDechargement) {
	    	model.reduitTempsDeChargementTirPartie();
	    }
	}
}
