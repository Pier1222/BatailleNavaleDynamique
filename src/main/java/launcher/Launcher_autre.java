package launcher;

import model.Son;
import model.Sous_Marin;
import model.Stats_Joueur;
import model.Torpilleur;
import view.View_amiral;
import view.View_matelot;
import controller.ControlGroup;
import controller.ControlGroup_amiral;
import controller.ControlGroup_matelot;
import model.Bataille_navale_model;
import model.Croiseur;
import model.Cuirasse;

public class Launcher_autre {

	public static void main(String[] args) {
		Bataille_navale_model model = new Bataille_navale_model();
				
		//ControlGroup controlgroup = new ControlGroup(model);
		
		//ControlGroup_amiral controlGroupAmiral = new ControlGroup_amiral(model);
		ControlGroup_matelot controlGroupMatelot = new ControlGroup_matelot(model);
		
		//View_amiral va = new View_amiral(model);
		//View_matelot vi = new View_matelot(model);
		
		//va.display();
		//vi.display();
		
		/* A chaque lancement de ce code, un fichier pour le joueur "Andy" va voir ses statistiques augmentés de la manière suivante:
		 * + 2 fois Amiral
		 * + 3 victoires
		 * + 1 défaite
		 * + 1 fois en Matelot attaque
		 * + 4 fois en Matelot défense
		 * + 5 tirs ratés
		 * + 10 tirs touchés
		 * + 2 utilisations de cuirassé
		 * + 6 utilisations de sous-marin
		 * + 1 utilisation de torpilleur
		 * + 10 utilisation de croiseur
		 */
		/*model.creerJoueur("Andy");
		Stats_Joueur statsTest = model.getUtilisateur().getStatistiques();
		statsTest.incrementeNbFoisAmiral();
		statsTest.incrementeNbFoisAmiral();
		statsTest.incrementeVictoire();
		statsTest.incrementeVictoire();
		statsTest.incrementeVictoire();
		statsTest.incrementeDefaite();
		statsTest.incrementeNbRoleAttaque();
		statsTest.incrementeNbRoleDefense();
		statsTest.incrementeNbRoleDefense();
		statsTest.incrementeNbRoleDefense();
		statsTest.incrementeNbRoleDefense();
		statsTest.incrementeNbTirsRates();
		statsTest.incrementeNbTirsRates();
		statsTest.incrementeNbTirsRates();
		statsTest.incrementeNbTirsRates();
		statsTest.incrementeNbTirsRates();
		statsTest.incrementeNbTirsTouches();
		statsTest.incrementeNbTirsTouches();
		statsTest.incrementeNbTirsTouches();
		statsTest.incrementeNbTirsTouches();
		statsTest.incrementeNbTirsTouches();
		statsTest.incrementeNbTirsTouches();
		statsTest.incrementeNbTirsTouches();
		statsTest.incrementeNbTirsTouches();
		statsTest.incrementeNbTirsTouches();
		statsTest.incrementeNbTirsTouches();
		statsTest.incrementeUtilisationsNavires(new Cuirasse(1));
		statsTest.incrementeUtilisationsNavires(new Cuirasse(1));
		statsTest.incrementeUtilisationsNavires(new Sous_Marin(1));
		statsTest.incrementeUtilisationsNavires(new Sous_Marin(1));
		statsTest.incrementeUtilisationsNavires(new Sous_Marin(1));
		statsTest.incrementeUtilisationsNavires(new Sous_Marin(1));
		statsTest.incrementeUtilisationsNavires(new Sous_Marin(1));
		statsTest.incrementeUtilisationsNavires(new Sous_Marin(1));
		statsTest.incrementeUtilisationsNavires(new Torpilleur(1));
		statsTest.incrementeUtilisationsNavires(new Croiseur(1));
		statsTest.incrementeUtilisationsNavires(new Croiseur(1));
		statsTest.incrementeUtilisationsNavires(new Croiseur(1));
		statsTest.incrementeUtilisationsNavires(new Croiseur(1));
		statsTest.incrementeUtilisationsNavires(new Croiseur(1));
		statsTest.incrementeUtilisationsNavires(new Croiseur(1));
		statsTest.incrementeUtilisationsNavires(new Croiseur(1));
		statsTest.incrementeUtilisationsNavires(new Croiseur(1));
		statsTest.incrementeUtilisationsNavires(new Croiseur(1));
		statsTest.incrementeUtilisationsNavires(new Croiseur(1));
		statsTest.saveStats();*/
	 
	}
}
