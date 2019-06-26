package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Bataille_navale_model;
import view.View_amiral;

public class ControlTimer_amiral implements ActionListener {
	protected Bataille_navale_model model;
	protected View_amiral viewAmiral;
	
	public ControlTimer_amiral(Bataille_navale_model model, View_amiral viewAmiral) {
		this.model = model;
        this.viewAmiral = viewAmiral;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	    if(e.getSource() == viewAmiral.timerAffichage) {
	    	viewAmiral.changeVue();
	    } else if(e.getSource() == viewAmiral.timerDechargement) {
	    	model.reduitTempsDeChargementTirPartie();
	    }
	}
}
