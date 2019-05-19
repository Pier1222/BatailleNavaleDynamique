package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Bataille_navale_model;
import view.View;
import view.ViewAttente;

public class ControlTimerAttente implements ActionListener {
	protected Bataille_navale_model model;
	protected ViewAttente viewAttente;
	
	public ControlTimerAttente(Bataille_navale_model model, ViewAttente viewAttente) {
		this.model = model;
        this.viewAttente = viewAttente;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == viewAttente.timerPanelJoueurs) {
			viewAttente.changePanelJoueursEnAttente();
		}
		
	}
}
