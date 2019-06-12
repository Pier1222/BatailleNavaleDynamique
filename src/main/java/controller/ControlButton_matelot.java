package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Bataille_navale_model;
import model.Grille;
import view.View_matelot;

public class ControlButton_matelot implements ActionListener {
	protected Bataille_navale_model model;
	public View_matelot viewMatelot;
	
	public ControlButton_matelot(Bataille_navale_model model, View_matelot viewMatelot) {
		this.model = model;
		this.viewMatelot = viewMatelot;
		
		viewMatelot.setControlButton(this);
	}




	@Override
	public void actionPerformed(ActionEvent e) {
	    //Recherche si la source est un des boutons
		for(int x = 0; x < Grille.getLines(); x++) {
			for(int y = 0; y < Grille.getColumns(); y++) {
				if(e.getSource() == viewMatelot.buttonsGrilleEquipe[x][y]) {
					//Faire des trucs
				} else if(e.getSource() == viewMatelot.buttonsGrilleAdverse[x][y]) {
					//Faire d'autres trucs
				}
			}
		}
	}
}
