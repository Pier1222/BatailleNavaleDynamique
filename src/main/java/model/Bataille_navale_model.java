package model;


public class Bataille_navale_model {

	public Bataille_navale_model() {
		//Test placementNavire:
		Equipe uneEquipe = new Equipe("patate", "rouge", null);
		Grille grilleEquipe = uneEquipe.getGrille();
		grilleEquipe.printGrille();
		uneEquipe.getNavires()[0].placeNavire(grilleEquipe, 0, 5);
		grilleEquipe.printGrille();
		uneEquipe.getNavires()[0].deplacementNavire(grilleEquipe, 1, 5);
		grilleEquipe.printGrille();
		uneEquipe.getNavires()[0].retireNavire();
		grilleEquipe.printGrille();
	}
}
