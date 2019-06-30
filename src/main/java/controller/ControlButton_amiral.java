package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Bataille_navale_model;
import model.Grille;
import model.Matelot;
import model.Navire;
import model.PieceNavire;
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
		if(e.getSource() == viewAmiral.tourneNavire) {
			viewAmiral.demandeTournoiement();
			return; //Afin qu'il n'effectue pas tous les for
		}
		
		if(e.getSource() == viewAmiral.retireNavire) {
			viewAmiral.demandeRetirement();
			return;
		}
		
		if(e.getSource() == viewAmiral.affecteRoleAttaque) {
			viewAmiral.demandeAffectationRole(Matelot.getRoleAttaquant());
			return;
		}
		
		if(e.getSource() == viewAmiral.affecteRoleDefenseur) {
			viewAmiral.demandeAffectationRole(Matelot.getRoleDefenseur());
			return;
		}
		
		if(e.getSource() == viewAmiral.affecteNavire) {
			viewAmiral.demandeAffectationNavire();
			return;
		}
		
		for(int i = 0; i < viewAmiral.boutonsNavires.length; i++) {
			if(e.getSource() == viewAmiral.boutonsNavires[i]) {
				viewAmiral.changeNomNavire(viewAmiral.boutonsNavires[i].getText());
				return;
			}
		}
		
		for(int i = 0; i < viewAmiral.nbMatelots; i++) {
			if(e.getSource() == viewAmiral.buttonsMatelots[i]) {
				viewAmiral.changeIdMatelot(i);
				System.out.println("Matelot n°" + i + " (id: " + viewAmiral.idMatelotSelect + ")");
				return;
			}
		}
		
		for(int x = 0; x < Grille.getLines(); x++) {
			for(int y = 0; y < Grille.getColumns(); y++) {
				if(e.getSource() == viewAmiral.buttonsGrille[x][y]) {
					System.out.println("Grille amiral: X = " + x + " Y = " + y);
					//On vérifie si il  y a un navire ou pas
					PieceNavire piecePose = viewAmiral.buttonsGrille[x][y].getPiecePose();
					Navire navirePresent = null;
					if(piecePose != null)
						navirePresent = piecePose.getNavireAttache();
					
					if(navirePresent == null) {
						//Avec aucun navire sur la case, on place celui qui est sélectionné
						System.out.println("On place le navire");
						viewAmiral.demandePlacement(x, y);
					} else {
						//Sinon, ce navire est sélectionné
						System.out.println("On change le navire sélectionné");
						viewAmiral.changeNomNavire(navirePresent.getNom());
					}
					return;
				}
			}
		}
		
		if(e.getSource() == viewAmiral.boutonPret) {
			viewAmiral.demandeFinPreparation();
		}
	}
}
