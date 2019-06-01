package model;

/**
 * C'est ici où seront testé les interactions entre membresde la même équipe (affectation et déplacement)
 * Cependant, pour les cas d'erreurs qui ne dépendent pas de l'Amiral et du Matelot (débordement, navire endommagé, etc...) voir NavireTest
 */
import static org.junit.Assert.*;
import org.junit.Test;

public class EquipeTest {
	private static String NOM_EQUIPE_TEST     = "Nom_Test_équipe";
	private static String COULEUR_EQUIPE_TEST = "Couleur_Test_équipe";
	
	@Test
	public void testAffectationRole() {
		System.out.println("testAffectationNavire");
		Amiral amiral   = new Amiral();
		Matelot matelotAtt = new Matelot(); //Matelot en attaque
		Matelot matelotDef = new Matelot(); //Matelot en défense
		Matelot matelotInc = new Matelot(); //Matelot sans rôle
		Equipe equipe   = new Equipe(NOM_EQUIPE_TEST, COULEUR_EQUIPE_TEST, amiral, null);
		equipe.ajouteMatelot(matelotAtt);
		equipe.ajouteMatelot(matelotDef);
		equipe.ajouteMatelot(matelotInc);
		
		//Sélection des éléments
		amiral.setMatelotSelectionne(matelotAtt);
		amiral.affecteRole(true);
		amiral.setMatelotSelectionne(matelotDef);
		amiral.affecteRole(false);
		
		//Vérification des rôles
		assertEquals(Matelot.getRoleAttaquant(), matelotAtt.getRoleString());
		assertEquals(Matelot.getRoleDefenseur(), matelotDef.getRoleString());
		assertEquals(Matelot.getRoleInconnu(), matelotInc.getRoleString());
	}
	
	//Tester quand on essaie d'affecter à un matelot ou navire qui n'est pas dans la même équipe, quand l'amiral n'a pas d'équipe, etc...

}
