package model;

/**
 * C'est ici où seront testé les interactions entre membres de la même équipe (affectation et déplacement)
 * Cependant, pour les cas d'erreurs qui ne dépendent pas de l'Amiral et du Matelot (débordement, navire endommagé, etc...) voir NavireTest
 */
import static org.junit.Assert.*;
import org.junit.Test;

public class EquipeTest {
	private static String NOM_EQUIPE_TEST     = "Nom_Test_équipe";
	private static String COULEUR_EQUIPE_TEST = "Couleur_Test_équipe";
	
	@Test
	public void testAffectationRole() {
		System.out.println("testAffectationRole");
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
	
	@Test
	public void testAffectationRoleSansEquipe() {
		System.out.println("testAffectationRoleSansEquipe");
		Amiral amiral  = new Amiral();
		Matelot matelot = new Matelot();
		
		//Sélection du matelot qui doit échouer car l'amiral et le matelot ne sont pas dans une équipe
		amiral.setMatelotSelectionne(matelot);
		amiral.affecteRole(true);
		
		//Vérification du rôle
		assertEquals(Matelot.getRoleInconnu(), matelot.getRoleString());
	}
	
	@Test
	public void testAffectationRoleEquipesDifferentes() {
		System.out.println("testAffectationRoleEquipesDifferentes");
		Amiral amiral  = new Amiral();
		Amiral amiral2 = new Amiral(); //Ici pour initialiser la seconde équipe
		Matelot matelot = new Matelot();

		Equipe equipe   = new Equipe(NOM_EQUIPE_TEST, COULEUR_EQUIPE_TEST, amiral, null);
		Equipe equipe2 = new Equipe(NOM_EQUIPE_TEST, COULEUR_EQUIPE_TEST, amiral2, null);
		equipe2.ajouteMatelot(matelot);
		
		//Tentative de changement de rôle qui doit échouer
		amiral.setMatelotSelectionne(matelot);
		amiral.affecteRole(true);
		
		//Vérification du rôle
		assertEquals(Matelot.getRoleInconnu(), matelot.getRoleString());
	}
	
	@Test
	public void testAffectationRoleAvecNavirePossede() {
		System.out.println("testAffectationRoleAvecNavirePossede");
		Amiral amiral   = new Amiral();
		Matelot matelot = new Matelot();
		Equipe equipe   = new Equipe(NOM_EQUIPE_TEST, COULEUR_EQUIPE_TEST, amiral, null);
		equipe.ajouteMatelot(matelot);
		Navire navireAAffecter = equipe.getNavires()[0]; //Le premier navire de l'équipe: le cuirassé
		amiral.setNavireSelectionne(navireAAffecter);
		
		//La deuxième affectation du rôle doit échouer car le Matelot a déjà un navire
		amiral.setMatelotSelectionne(matelot);
		amiral.affecteRole(true);
		amiral.affecteNavire();
		amiral.affecteRole(false);
		
		//On vérifie que le nombre de navire contrôlé par le matelot est d'au moins 1 et qu'il est bien resté attaquant
		assertTrue(matelot.getNombreNaviresControles() >= 1);
		assertEquals(Matelot.getRoleAttaquant(), matelot.getRoleString());
	}
	
	@Test
	public void testAffectationDesaffectationNavire() {
		System.out.println("testAffectationDesaffectationNavire");
		Amiral amiral   = new Amiral();
		Matelot matelotAtt = new Matelot(); //Matelot en attaque
		Equipe equipe   = new Equipe(NOM_EQUIPE_TEST, COULEUR_EQUIPE_TEST, amiral, null);
		equipe.ajouteMatelot(matelotAtt);
		Navire navireAAffecter = equipe.getNavires()[0]; //Le premier navire de l'équipe: le cuirassé
		amiral.setNavireSelectionne(navireAAffecter);
		
		//Affectation du Rôle et du Navire
		amiral.setMatelotSelectionne(matelotAtt);
		amiral.affecteRole(true);
		amiral.affecteNavire();
		
		//On vérifie que le matelot possède le navire
		assertTrue(matelotAtt.possedeNavire(navireAAffecter));
		
		amiral.desaffecteNavire();
		//On vérfie que le matelot a perdu le navire
		assertFalse(matelotAtt.possedeNavire(navireAAffecter));
	}
	
	@Test
	public void testAffectationNavireDouble() {
		System.out.println("testAffectationNavireDouble");
		Amiral amiral   = new Amiral();
		Matelot matelotAtt = new Matelot(); //Matelot en attaque
		Matelot matelotDef = new Matelot(); //Matelot en défense
		Equipe equipe   = new Equipe(NOM_EQUIPE_TEST, COULEUR_EQUIPE_TEST, amiral, null);
		equipe.ajouteMatelot(matelotAtt);
		equipe.ajouteMatelot(matelotDef);
		Navire navireAAffecter = equipe.getNavires()[0]; //Le premier navire de l'équipe: le cuirassé
		amiral.setNavireSelectionne(navireAAffecter);
		
		//Affectation du Rôle et du Navire
		amiral.setMatelotSelectionne(matelotAtt);
		amiral.affecteRole(true);
		amiral.affecteNavire();
		amiral.setMatelotSelectionne(matelotDef);
		amiral.affecteRole(false);
		amiral.affecteNavire();
		
		//On vérifie que les deux matelots possèdent ce navire
		assertTrue(matelotAtt.possedeNavire(navireAAffecter));
		assertTrue(matelotDef.possedeNavire(navireAAffecter));
	}
	
	@Test
	public void testAffectationNavireMatelotSansRole() {
		System.out.println("testAffectationNavireMatelotSansRole");
		Amiral amiral   = new Amiral();
		Matelot matelot = new Matelot();
		Equipe equipe   = new Equipe(NOM_EQUIPE_TEST, COULEUR_EQUIPE_TEST, amiral, null);
		equipe.ajouteMatelot(matelot);
		Navire navireAAffecter = equipe.getNavires()[0]; //Le premier navire de l'équipe: le cuirassé
		amiral.setNavireSelectionne(navireAAffecter);
		
		//Affectation du Navire qui doit échouer car le matelo n'a pas de rôle
		amiral.setMatelotSelectionne(matelot);
		amiral.affecteNavire();
		
		//On vérifie que les deux Matelots possèdent ce navire
		assertFalse(matelot.possedeNavire(navireAAffecter));
	}
	
	@Test
	public void testAffectationNavireEquipesDifferentes() {
		System.out.println("testAffectationNavireEquipesDifferentes");
		Amiral amiral   = new Amiral();
		Amiral amiral2  = new Amiral(); //Ici pour initialiser la seconde équipe
		Matelot matelot = new Matelot();
		Equipe equipe   = new Equipe(NOM_EQUIPE_TEST, COULEUR_EQUIPE_TEST, amiral, null);
		Equipe equipe2  = new Equipe(NOM_EQUIPE_TEST, COULEUR_EQUIPE_TEST, amiral2, null);
		equipe.ajouteMatelot(matelot);
		Navire navireAAffecter = equipe2.getNavires()[0]; //Navire de l'autre équipe
		amiral.setNavireSelectionne(navireAAffecter);
		
		//Affectation du Navire qui doit échouer car il n'y a pas de navire sélectionné
		amiral.setMatelotSelectionne(matelot);
		amiral.affecteRole(true);
		amiral.affecteNavire();
		
		//On vérifie que le Matelot n'a pas ce navire
		assertFalse(matelot.possedeNavire(navireAAffecter));
	}
	
	@Test
	public void testAffectationNavireEquipesAmiralSansEquipe() {
		System.out.println("testAffectationNavireEquipesAmiralSansEquipe");
		Amiral amiral   = new Amiral();
		Matelot matelot = new Matelot();
		Equipe equipe   = new Equipe(NOM_EQUIPE_TEST, COULEUR_EQUIPE_TEST, amiral, null);
		equipe.ajouteMatelot(matelot);
		Navire navireAAffecter = new Cuirasse(1);
		amiral.setNavireSelectionne(navireAAffecter);
		
		//Affectation du Navire qui doit échouer car il n'y a pas de navire sélectionné
		amiral.setMatelotSelectionne(matelot);
		amiral.affecteRole(true);
		amiral.affecteNavire();
		
		//On vérifie que le Matelot n'a pas ce navire
		assertFalse(matelot.possedeNavire(navireAAffecter));
	}
	
	@Test
	public void testAffectationNavireDejaPossede() {
		System.out.println("testAffectationNavireDejaPossede");
		Amiral amiral   = new Amiral();
		Matelot matelot = new Matelot();
		Equipe equipe   = new Equipe(NOM_EQUIPE_TEST, COULEUR_EQUIPE_TEST, amiral, null);
		equipe.ajouteMatelot(matelot);
		Navire navireAAffecter = equipe.getNavires()[0]; //Le premier navire de l'équipe: le cuirassé
		amiral.setNavireSelectionne(navireAAffecter);
		
		//On essaie d'affecter deux fois le même navire au matelot, ce qui ne dois pas fonctionner
		amiral.setMatelotSelectionne(matelot);
		amiral.affecteRole(true);
		amiral.affecteNavire();
		amiral.affecteNavire();
		
		//On vérifier que la matelot n'a bien qu'un seul navire
		assertEquals(1, matelot.getNombreNaviresControles());
	}
	
	
	@Test
	public void testRemplaementNavire() {
		System.out.println("testRemplaementNavire");
		Amiral amiral   = new Amiral();
		Matelot matelotAtt = new Matelot(); //Matelot en attaque
		Matelot matelotAtt2 = new Matelot(); //Matelot en défense
		Equipe equipe   = new Equipe(NOM_EQUIPE_TEST, COULEUR_EQUIPE_TEST, amiral, null);
		equipe.ajouteMatelot(matelotAtt);
		equipe.ajouteMatelot(matelotAtt2);
		Navire navireAAffecter = equipe.getNavires()[0]; //Le premier navire de l'équipe: le cuirassé
		amiral.setNavireSelectionne(navireAAffecter);
		
		//La deuxième affectation retirera le navire que le matelot attaquant a eu à la première
		amiral.setMatelotSelectionne(matelotAtt);
		amiral.affecteRole(true);
		amiral.affecteNavire();
		amiral.setMatelotSelectionne(matelotAtt2);
		amiral.affecteRole(true);
		amiral.affecteNavire();
		
		//On vérifie que le premier matelot n'a pas le navire alors que le second en a un
		assertFalse(matelotAtt.possedeNavire(navireAAffecter));
		assertTrue(matelotAtt2.possedeNavire(navireAAffecter));
	}
	
	@Test
	public void testPlacementRetirementNavire() {
		System.out.println("testPlacementRetirementNavire");
		Amiral amiral   = new Amiral();
		Equipe equipe   = new Equipe(NOM_EQUIPE_TEST, COULEUR_EQUIPE_TEST, amiral, null);
		Navire navireAPlacer = equipe.getNavires()[0]; //Le premier navire de l'équipe: le cuirassé
		amiral.setNavireSelectionne(navireAPlacer);
		//L'Amiral ne fait que placer son Navire
		amiral.placeNavire(5, 5);
		assertTrue(navireAPlacer.checkPosition(equipe.getGrille(), 5, 5));
		
		amiral.retireNavire();
	    for(int x = 0; x < navireAPlacer.getNBPieces(); x++) {
	        assertEquals(null, navireAPlacer.getPieces()[x].getPosition());
	    }
	}
	
	@Test
	public void testPlacementRetirementNavireAvecEquipePrete() {
		System.out.println("testPlacementRetirementNavireAvecEquipePrete");
		Amiral amiral   = new Amiral();
		Equipe equipe   = new Equipe(NOM_EQUIPE_TEST, COULEUR_EQUIPE_TEST, amiral, null);
		//L'Amiral ne fait que placer son Navire
		amiral.placeTousLesNavires();
		/* On va enregistrer la position du premier navire pour avoir une position où on est sûr qu'on peut le placer sans avoir
		 un autre problème que "l'équipe est prête" avant de le placer a une autre position valide */
		Navire navireAReplacer = equipe.getNavires()[0]; //Le premier navire de l'équipe: le cuirassé
	    Case anciennePosition = navireAReplacer.getTete().getPosition();
		amiral.setNavireSelectionne(navireAReplacer);
		amiral.placeNavire(9, 9); //A l'autre bout de la grille
		assertTrue(navireAReplacer.checkPosition(equipe.getGrille(), 9, 9)); //On vérifie quand même que le navire a bien été replacé
		equipe.setEquipeToPret();
		amiral.placeNavire(anciennePosition.getPositionX(), anciennePosition.getPositionY());
		equipe.getGrille().printGrille(false);
		assertTrue(navireAReplacer.checkPosition(equipe.getGrille(), 9, 9)); //Le navire ne doit pas avoir bougé
	}
	
	@Test
	public void testTourneEtPlaceNavire() {
		System.out.println("testTourneEtPlaceNavire");
		Amiral amiral   = new Amiral();
		Equipe equipe   = new Equipe(NOM_EQUIPE_TEST, COULEUR_EQUIPE_TEST, amiral, null);
		Navire navireAPlacer = equipe.getNavires()[0]; //Le premier navire de l'équipe: le cuirassé
		amiral.setNavireSelectionne(navireAPlacer);
		//L'Amiral tourne le navire avant de le placer
		amiral.tourneNavire();
		//Cette position ne peut marcher que si le cuirasse est à la verticale
		amiral.placeNavire(0, 0);
		assertTrue(navireAPlacer.checkPosition(equipe.getGrille(), 0, 0));
	}
	
	@Test
	public void testEquipePrete() {
		System.out.println("testEquipePrete");
		Amiral amiral   = new Amiral();
		Equipe equipe   = new Equipe(NOM_EQUIPE_TEST, COULEUR_EQUIPE_TEST, amiral, null);
		amiral.placeTousLesNavires();
		equipe.getGrille().printGrille(false);
		//On vérifie que la préparation a abouti
		assertTrue(equipe.setEquipeToPret());
	}
	
	@Test
	public void testEquipeNonPrete() {
		System.out.println("testEquipeNonPrete");
		Amiral amiral   = new Amiral();
		Equipe equipe   = new Equipe(NOM_EQUIPE_TEST, COULEUR_EQUIPE_TEST, amiral, null);
		Navire navireAPlacer = equipe.getNavires()[0]; //Le premier navire de l'équipe: le cuirassé
		amiral.setNavireSelectionne(navireAPlacer);
        amiral.placeNavire(6, 7);
        //Un seul Navire est placé, ce n'est pas suffisant pour commencer une partie
		assertFalse(equipe.setEquipeToPret());
	}
	
	@Test
	public void testDeplacement() {
		System.out.println("testDeplacement");
		Amiral amiral   = new Amiral();
		Equipe equipe   = new Equipe(NOM_EQUIPE_TEST, COULEUR_EQUIPE_TEST, amiral, null);
		Matelot matelot = new Matelot();
		equipe.ajouteMatelot(matelot);
		//L'Amiral ne fait que placer son Navire
		amiral.placeTousLesNavires();
		/* On va enregistrer la position du premier navire pour avoir une position où on est sûr qu'on peut le déplacer sans avoir
		 un autre problème que "l'équipe n'est pas prête" */
		Navire navireADeplacer = equipe.getNavires()[0]; //Le premier navire de l'équipe: le cuirassé
	    Case anciennePosition = navireADeplacer.getTete().getPosition();
		equipe.setEquipeToPret();
		
		//On affecte le rôle de défenseur au matelot et le faire déplacer son navire
		amiral.setMatelotSelectionne(matelot);
		amiral.setNavireSelectionne(navireADeplacer);
		amiral.affecteRole(false);
		amiral.affecteNavire();
		matelot.setNavireSelectionne(navireADeplacer);
		matelot.deplaceNavire(anciennePosition.getPositionX()+1, anciennePosition.getPositionY());
		
		//Vision de l'amiral
		equipe.getGrille().printGrille(false);
		System.out.println();
		//Vision du matelot
		equipe.getGrille().printGrille(false, matelot);
		//On vérifie que le navire a bien été déplacé
		assertTrue(navireADeplacer.checkPosition(equipe.getGrille(), anciennePosition.getPositionX()+1, anciennePosition.getPositionY()));
	}
	
	@Test
	public void testDeplacementAvecAttaquant() {
		System.out.println("testDeplacementAvecAttaquant");
		Amiral amiral   = new Amiral();
		Equipe equipe   = new Equipe(NOM_EQUIPE_TEST, COULEUR_EQUIPE_TEST, amiral, null);
		Matelot matelot = new Matelot();
		equipe.ajouteMatelot(matelot);
		//L'Amiral ne fait que placer son Navire
		amiral.placeTousLesNavires();
		/* On va enregistrer la position du premier navire pour avoir une position où on est sûr qu'on peut le déplacer sans avoir
		 un autre problème que "l'équipe n'est pas prête" */
		Navire navireADeplacer = equipe.getNavires()[0]; //Le premier navire de l'équipe: le cuirassé
	    Case anciennePosition = navireADeplacer.getTete().getPosition();
		equipe.setEquipeToPret();
		
		//Le matelot ne pourra pas déplacer son navire comme il est attaquant
		amiral.setMatelotSelectionne(matelot);
		amiral.setNavireSelectionne(navireADeplacer);
		amiral.affecteRole(true);
		amiral.affecteNavire();
		matelot.setNavireSelectionne(navireADeplacer);
		matelot.deplaceNavire(anciennePosition.getPositionX()+1, anciennePosition.getPositionY());
		
		//Vision de l'amiral
		equipe.getGrille().printGrille(false);
		System.out.println();
		//Vision du matelot
		equipe.getGrille().printGrille(false, matelot);
		//On vérifie que le navire n'a pas bougé
		assertTrue(navireADeplacer.checkPosition(equipe.getGrille(), anciennePosition.getPositionX(), anciennePosition.getPositionY()));
	}
	
	@Test
	public void testDeplacementSurAllie() {
		System.out.println("testDeplacementSurAllie");
		Amiral amiral    = new Amiral();
		Equipe equipe    = new Equipe(NOM_EQUIPE_TEST, COULEUR_EQUIPE_TEST, amiral, null);
		Matelot matelot  = new Matelot();
		Matelot matelot2 = new Matelot();
		equipe.ajouteMatelot(matelot);
		//L'Amiral ne fait que placer son Navire
		amiral.placeTousLesNavires();
		/* On va enregistrer la position du premier et du second navire pour avoir une position où on est sûr qu'on peut le déplacer sans avoir
		 un autre problème que "l'équipe n'est pas prête" et comme ils sont logiquement à cause case de distance, le premier déplacera à droite et
		 le second à gauche */
		Navire navireDroite = equipe.getNavires()[0]; //Le premier navire de l'équipe: le cuirassé
		Navire navireGauche = equipe.getNavires()[1]; //Le deuxième navire de l'équipe: un croiseur
	    Case anciennePositionDroite = navireDroite.getTete().getPosition();
	    Case anciennePositionGauche = navireGauche.getTete().getPosition();
		equipe.setEquipeToPret();
		
		//On affecte le rôle de défenseur aux matelots dont le deuxième ne pourra pas se déplacer après le déplacement du premier
		amiral.setMatelotSelectionne(matelot);
		amiral.setNavireSelectionne(navireDroite);
		amiral.affecteRole(false);
		amiral.affecteNavire();
		amiral.setMatelotSelectionne(matelot2);
		amiral.setNavireSelectionne(navireGauche);
		amiral.affecteRole(false);
		amiral.affecteNavire();
		
		//Déplacement à droite qui doit réussir
		matelot.setNavireSelectionne(navireDroite);
		matelot.deplaceNavire(anciennePositionDroite.getPositionX(), anciennePositionDroite.getPositionY()+1);
		
		//Déplacement à gauche qui doit échouer
		matelot2.setNavireSelectionne(navireGauche);
		matelot2.deplaceNavire(anciennePositionGauche.getPositionX(), anciennePositionGauche.getPositionY()+1);
		
		
		
		//Vision de l'amiral
		equipe.getGrille().printGrille(false);
		System.out.println();
		//Vision des matelots
		equipe.getGrille().printGrille(false, matelot);
		equipe.getGrille().printGrille(false, matelot2);
		//On vérifie que le navire a bien été déplacé
		assertTrue(navireDroite.checkPosition(equipe.getGrille(), anciennePositionDroite.getPositionX(), anciennePositionDroite.getPositionY()+1));
		assertTrue(navireGauche.checkPosition(equipe.getGrille(), anciennePositionGauche.getPositionX(), anciennePositionGauche.getPositionY()));
	}
	
	@Test
	public void testDeplacementEquipePasPrete() {
		System.out.println("testDeplacementEquipePasPrete");
		Amiral amiral   = new Amiral();
		Equipe equipe   = new Equipe(NOM_EQUIPE_TEST, COULEUR_EQUIPE_TEST, amiral, null);
		Matelot matelot = new Matelot();
		equipe.ajouteMatelot(matelot);
		//L'Amiral ne fait que placer son Navire
		amiral.placeTousLesNavires();
		Navire navireADeplacer = equipe.getNavires()[0]; //Le premier navire de l'équipe: le cuirassé
		
		//On place le navire
		amiral.setNavireSelectionne(navireADeplacer);
		amiral.placeNavire(6, 3);
		
		//On affecte le rôle de défenseur au matelot qui va essayer de déplacer son navire
		amiral.setMatelotSelectionne(matelot);
		amiral.affecteRole(false);
		amiral.affecteNavire();
		matelot.setNavireSelectionne(navireADeplacer);
		matelot.deplaceNavire(5, 3);
		
		//Vision de l'amiral
		equipe.getGrille().printGrille(false);
		System.out.println();
		//Vision du matelot
		equipe.getGrille().printGrille(false, matelot);
		//On vérifie que le navire n'a pas bougé
		assertTrue(navireADeplacer.checkPosition(equipe.getGrille(), 6, 3));
	}
}
