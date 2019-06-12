package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Bataille_navale_model;
import model.Grille;
import view.View_amiral;

public class ControlButton_amiral implements ActionListener {
	protected Bataille_navale_model model;
	public View_amiral viewAmiral;
	
	public ControlButton_amiral(Bataille_navale_model model, View_amiral viewAmiral) {
		this.model = model;
		this.viewAmiral = viewAmiral;
		
		viewAmiral.setControlButton(this);
	}




	@Override
	public void actionPerformed(ActionEvent e) {
	    //Recherche si la source est un des boutons
		for(int x = 0; x < Grille.getLines(); x++) {
			for(int y = 0; y < Grille.getColumns(); y++) {
				if(e.getSource() == viewAmiral.buttonsGrille[x][y]) {
					System.out.println("Grille amiral: X = " + x + " Y = " + y);
					//Faire des trucs
				}
			}
		}
	}
}
