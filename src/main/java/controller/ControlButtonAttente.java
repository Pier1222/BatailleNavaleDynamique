package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Bataille_navale_model;
import view.ViewAttente;

public class ControlButtonAttente implements ActionListener {

	protected Bataille_navale_model model;
	protected ViewAttente viewAttente;
	
	public ControlButtonAttente(Bataille_navale_model model, ViewAttente viewAttente) {
		this.model = model;
		this.viewAttente = viewAttente;
		viewAttente.setButtonControler(this);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == viewAttente.lancerPartie) {
			System.out.println("Lancer partie");
			viewAttente.launchGame();
			
		} else if(e.getSource() == viewAttente.quitterPartie) {
			System.out.println("Quitter partie");
			
		}
	}

}
