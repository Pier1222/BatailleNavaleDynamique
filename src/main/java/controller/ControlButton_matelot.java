package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Bataille_navale_model;
import model.Grille;
import model.Navire;
import model.PieceNavire;
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
					System.out.println("Alliés: X = " + x + " Y = " + y);
					//On vérifie si il  y a un navire ou pas
					PieceNavire piecePose = viewMatelot.buttonsGrilleEquipe[x][y].getPiecePose();
					Navire navirePresent = null;
					if(piecePose != null)
						navirePresent = piecePose.getNavireAttache();
					
					if(navirePresent == null) {
						//Avec aucun navire sur la case, on effectue une action (déplacement/tir)
						System.out.println("On place le navire");
						viewMatelot.demandeAction(x, y);
					} else {
						//Sinon, ce navire est sélectionné
						System.out.println("On change le navire sélectionné");
						viewMatelot.changeNomNavire(navirePresent.getNom());
					}
					return;
					
				} else if(e.getSource() == viewMatelot.buttonsGrilleAdverse[x][y]) {
					System.out.println("Ennemi: X = " + x + " Y = " + y);
					
				}
			}
		}
	}
}
