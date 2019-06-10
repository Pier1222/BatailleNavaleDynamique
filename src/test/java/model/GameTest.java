package model;

import static org.junit.Assert.assertFalse;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * C'est ici que se déroule les tests nécessitant deux équipes (ceux comportant des tirs, les conditions de victoires et vérification que les deux équipes sont prêtes)
 */

public class GameTest {
	//On va à chaque fois créer une partie avec deux matelots (6 joueurs au total)
	private Game creationPartieTest(boolean doPrintCreation) {
		Joueur[] joueurs = new Joueur[] {new Joueur("PlayerTest1"), new Joueur("PlayerTest2"), new Joueur("PlayerTest3"), new Joueur("PlayerTest4"), new Joueur("PlayerTest5"), new Joueur("PlayerTest6")};
		Game game = new Game(joueurs[0]);
		Game.setDO_PRINT(doPrintCreation);
		for(int i = 0; i < joueurs.length; i++) {
			game.ajouteJoueur(joueurs[i]);
		}
		game.createTeams();
		return game;
	}
	
	@Test
	public void testCreationEquipeEquilibre() {
		System.out.println("testCreationEquipeEquilibre");
		Game game = creationPartieTest(true);
		String[][] infosEquipes = game.getNomsEquipesEtJoueurs();
		//Si les équipes sont équilibré, avec un nombre joueur pair, il doit avoir autant d'élements dans les cases 2 et 3 de ce tableau
		assertEquals(infosEquipes[1].length, infosEquipes[2].length);
	}
	
	//Test sur les tirs
	@Test
	public void testTirReussit() {
		System.out.println("testTirReussit");
		Game game = creationPartieTest(false);
		Equipe bleu  = game.getEquipeBleu();
		Equipe rouge = game.getEquipeRouge();
		Amiral amiralB = bleu.getAmiral();
		Amiral amiralR = rouge.getAmiral();
		Matelot matelotBAtt = bleu.getAMatelotDansListe(1);
		
		//L'amiral rouge va placer tous ses navires et annoncer qu'il est prêt
		amiralR.placeTousLesNavires();
		rouge.setEquipeToPret();
		
		//Placement de tous les navires, affectation et fin de préparation pour l'Amiral bleu
		amiralB.placeTousLesNavires();
		bleu.setEquipeToPret();
		Navire navireDeTir = bleu.getNavires()[0]; //Le premier navire de l'équipe: le cuirassé
		Navire navireCible = rouge.getNavires()[0];
	    Case positionCible = navireCible.getTete().getPosition(); //Comme les deux équipes ont placés leurs navires de la même manière, cela devrait fonctionner
	    amiralB.setNavireSelectionne(navireDeTir);
	    amiralB.setMatelotSelectionne(matelotBAtt);
	    amiralB.affecteRole(true);
	    amiralB.affecteNavire();
	    
	    //Choix du navire et tir
	    matelotBAtt.setNavireSelectionne(navireDeTir);
	    Navire navireTouche = matelotBAtt.tirAvecNavire(positionCible.getPositionX(), positionCible.getPositionY());
        bleu.getGrille().printGrille(true, matelotBAtt);
	    System.out.println("Equipe adverse: ");
	    rouge.getGrille().printGrille(true);
	    assertEquals(navireCible, navireTouche);
	}
	
	@Test
	public void testTirAvecDefenseur() {
		System.out.println("testTirAvecDefenseur");
		Game game = creationPartieTest(false);
		Equipe bleu  = game.getEquipeBleu();
		Equipe rouge = game.getEquipeRouge();
		Amiral amiralB = bleu.getAmiral();
		Amiral amiralR = rouge.getAmiral();
		Matelot matelotBDef = bleu.getAMatelotDansListe(1);
		
		//L'amiral rouge va placer tous ses navires et annoncer qu'il est prêt
		amiralR.placeTousLesNavires();
		rouge.setEquipeToPret();
		
		//Placement de tous les navires, affectation et fin de préparation pour l'Amiral bleu
		amiralB.placeTousLesNavires();
		bleu.setEquipeToPret();
		Navire navireDeTir = bleu.getNavires()[0]; //Le premier navire de l'équipe: le cuirassé
		Navire navireCible = rouge.getNavires()[0];
	    Case positionCible = navireCible.getTete().getPosition(); //Comme les deux équipes ont placés leurs navires de la même manière, cela devrait fonctionner
	    amiralB.setNavireSelectionne(navireDeTir);
	    amiralB.setMatelotSelectionne(matelotBDef);
	    amiralB.affecteRole(false);
	    amiralB.affecteNavire();
	    
	    //Choix du navire et tir qui doit rater car le matelot est un défenseur
	    matelotBDef.setNavireSelectionne(navireDeTir);
	    Navire navireTouche = matelotBDef.tirAvecNavire(positionCible.getPositionX(), positionCible.getPositionY());
        bleu.getGrille().printGrille(true, matelotBDef);
	    System.out.println("Equipe adverse: ");
	    rouge.getGrille().printGrille(true);
	    assertEquals(null, navireTouche);
	}
	
	@Test
	public void testTirEquipePasPrete() {
		System.out.println("testTirEquipePasPrete");
		Game game = creationPartieTest(false);
		Equipe bleu  = game.getEquipeBleu();
		Equipe rouge = game.getEquipeRouge();
		Amiral amiralB = bleu.getAmiral();
		Amiral amiralR = rouge.getAmiral();
		Matelot matelotBAtt = bleu.getAMatelotDansListe(1);
		
		//L'amiral rouge va placer tous ses navires et annoncer qu'il est prêt
		amiralR.placeTousLesNavires();
		rouge.setEquipeToPret();
		
		//Placement de tous les navires, affectation mais pas de fin de préparation
		amiralB.placeTousLesNavires();
		
		Navire navireDeTir = bleu.getNavires()[0]; //Le premier navire de l'équipe: le cuirassé
		Navire navireCible = rouge.getNavires()[0];
	    Case positionCible = navireCible.getTete().getPosition(); //Comme les deux équipes ont placés leurs navires de la même manière, cela devrait fonctionner
	    amiralB.setNavireSelectionne(navireDeTir);
	    amiralB.setMatelotSelectionne(matelotBAtt);
	    amiralB.affecteRole(true);
	    amiralB.affecteNavire();
	    
	    //Son tir doit échouer son équipe n'est pas prête
	    matelotBAtt.setNavireSelectionne(navireDeTir);
	    Navire navireTouche = matelotBAtt.tirAvecNavire(positionCible.getPositionX(), positionCible.getPositionY());
        bleu.getGrille().printGrille(true, matelotBAtt);
	    System.out.println("Equipe adverse: ");
	    rouge.getGrille().printGrille(true);
	    assertEquals(null, navireTouche);
	}
	
	@Test
	public void testTirEquipeAdversePasPrete() {
		System.out.println("testTirEquipeAdversePasPrete");
		Game game = creationPartieTest(false);
		Equipe bleu  = game.getEquipeBleu();
		Equipe rouge = game.getEquipeRouge();
		Amiral amiralB = bleu.getAmiral();
		Amiral amiralR = rouge.getAmiral();
		Matelot matelotBAtt = bleu.getAMatelotDansListe(1);
		
		//L'amiral rouge va placer tous ses navires mais ne pas annoncer qu'il est prêt
		amiralR.placeTousLesNavires();
		
		//Placement de tous les navires, affectation et fin de préparation en bleu
		amiralB.placeTousLesNavires();
		bleu.setEquipeToPret();
		Navire navireDeTir = bleu.getNavires()[0]; //Le premier navire de l'équipe: le cuirassé
		Navire navireCible = rouge.getNavires()[0];
	    Case positionCible = navireCible.getTete().getPosition(); //Comme les deux équipes ont placés leurs navires de la même manière, cela devrait fonctionner
	    amiralB.setNavireSelectionne(navireDeTir);
	    amiralB.setMatelotSelectionne(matelotBAtt);
	    amiralB.affecteRole(true);
	    amiralB.affecteNavire();
	    
	    //Son tir doit échouer l'équipe adverse n'est pas prête
	    matelotBAtt.setNavireSelectionne(navireDeTir);
	    Navire navireTouche = matelotBAtt.tirAvecNavire(positionCible.getPositionX(), positionCible.getPositionY());
        bleu.getGrille().printGrille(true, matelotBAtt);
	    System.out.println("Equipe adverse: ");
	    rouge.getGrille().printGrille(true);
	    assertEquals(null, navireTouche);
	}
	
	//Ce type de test n'existe pas pour le déplacement car les navires sont déjà bloqués si ils sont touchés
	//Dans les deux cas, l'affectation a déjà eu lieu au moment où le navire a coulé (l'affectation alors que le navire est coulé concerne d'autres test)
	@Test
	public void testTirNavireCouleAvantSelection() {
		//Dans cette version le navire est coulé AVANT que le matelot le choisi
		System.out.println("testTirNavireCouleAvantSelection");
		Game game = creationPartieTest(false);
		Equipe bleu  = game.getEquipeBleu();
		Equipe rouge = game.getEquipeRouge();
		Amiral amiralB = bleu.getAmiral();
		Amiral amiralR = rouge.getAmiral();
		Matelot matelotBAtt = bleu.getAMatelotDansListe(0);
		Matelot matelotRAtt = rouge.getAMatelotDansListe(0);
		
		//L'amiral rouge va placer tous ses navires et annoncer qu'il est prêt
		amiralR.placeTousLesNavires();
		rouge.setEquipeToPret();
		
		//Placement de tous les navires, affectation et fin de préparation pour l'Amiral bleu
		amiralB.placeTousLesNavires();
		bleu.setEquipeToPret();
		Navire navireDeTir = bleu.getNavires()[0]; //Le premier navire de l'équipe: le cuirassé
		Navire navireCible = rouge.getNavires()[0];
	    Case positionCible = navireCible.getTete().getPosition(); //Comme les deux équipes ont placés leurs navires de la même manière, cela devrait fonctionner
	    amiralB.setNavireSelectionne(navireDeTir);
	    amiralB.setMatelotSelectionne(matelotBAtt);
	    amiralB.affecteRole(true);
	    amiralB.affecteNavire();
	    
		//Le navire rouge va détruire le navire bleu (positionné au même endroit)
		amiralR.placeTousLesNavires();
		rouge.setEquipeToPret();
		amiralR.setNavireSelectionne(navireCible);
		amiralR.setMatelotSelectionne(matelotRAtt);
		amiralR.affecteRole(true);
		amiralR.affecteNavire();
		matelotRAtt.setNavireSelectionne(navireCible);
		
		Case cibleActu = null;
		for(int i = 0; i < navireDeTir.getNBPieces(); i++) {
			cibleActu = navireDeTir.getPieces()[i].getPosition();
			matelotRAtt.tirAvecNavire(cibleActu.getPositionX(), cibleActu.getPositionY());
			navireCible.annuleTempsRechargement();
		}
	    
	    //Choix du navire et tir qui doit échouer
	    matelotBAtt.setNavireSelectionne(navireDeTir);
	    Navire navireTouche = matelotBAtt.tirAvecNavire(positionCible.getPositionX(), positionCible.getPositionY());
        bleu.getGrille().printGrille(true, matelotBAtt);
	    System.out.println("Equipe adverse: ");
	    rouge.getGrille().printGrille(true);
	    assertEquals(null, navireTouche);
	}
	
	@Test
	public void testTirNavireCouleApresSelection() {
		//Dans cette version le navire est coulé APRES que le matelot le choisi
		System.out.println("testTirNavireCouleApresSelection");
		Game game = creationPartieTest(false);
		Equipe bleu  = game.getEquipeBleu();
		Equipe rouge = game.getEquipeRouge();
		Amiral amiralB = bleu.getAmiral();
		Amiral amiralR = rouge.getAmiral();
		Matelot matelotBAtt = bleu.getAMatelotDansListe(0);
		Matelot matelotRAtt = rouge.getAMatelotDansListe(0);
		
		//L'amiral rouge va placer tous ses navires et annoncer qu'il est prêt
		amiralR.placeTousLesNavires();
		rouge.setEquipeToPret();
		
		//Placement de tous les navires, affectation et fin de préparation pour l'Amiral bleu
		amiralB.placeTousLesNavires();
		bleu.setEquipeToPret();
		Navire navireDeTir = bleu.getNavires()[0]; //Le premier navire de l'équipe: le cuirassé
		Navire navireCible = rouge.getNavires()[0];
	    Case positionCible = navireCible.getTete().getPosition(); //Comme les deux équipes ont placés leurs navires de la même manière, cela devrait fonctionner
	    amiralB.setNavireSelectionne(navireDeTir);
	    amiralB.setMatelotSelectionne(matelotBAtt);
	    amiralB.affecteRole(true);
	    amiralB.affecteNavire();
	    
		//Le navire rouge va détruire le navire bleu (positionné au même endroit)
		amiralR.placeTousLesNavires();
		rouge.setEquipeToPret();
		amiralR.setNavireSelectionne(navireCible);
		amiralR.setMatelotSelectionne(matelotRAtt);
		amiralR.affecteRole(true);
		amiralR.affecteNavire();
		matelotRAtt.setNavireSelectionne(navireCible);
		
		//Le matelot choisi son navire avant sa destruction
	    matelotBAtt.setNavireSelectionne(navireDeTir);
		
		Case cibleActu = null;
		for(int i = 0; i < navireDeTir.getNBPieces(); i++) {
			cibleActu = navireDeTir.getPieces()[i].getPosition();
			matelotRAtt.tirAvecNavire(cibleActu.getPositionX(), cibleActu.getPositionY());
			navireCible.annuleTempsRechargement();
		}
	    
	    //Tir qui doit échouer
	    Navire navireTouche = matelotBAtt.tirAvecNavire(positionCible.getPositionX(), positionCible.getPositionY());
        bleu.getGrille().printGrille(true, matelotBAtt);
	    System.out.println("Equipe adverse: ");
	    rouge.getGrille().printGrille(true);
	    assertEquals(null, navireTouche);
	}
	
	//Test sur les conditions de victoires
	@Test
	public void testVictoireFlotteAdverseCoule() {
		System.out.println("testVictoireFlotteAdverseCoule");
		Game game = creationPartieTest(false);
		Equipe bleu  = game.getEquipeBleu();
		Equipe rouge = game.getEquipeRouge();
		Amiral amiralB = bleu.getAmiral();
		Amiral amiralR = rouge.getAmiral();
		Matelot matelotBAtt = bleu.getAMatelotDansListe(0);
		
		//L'amiral rouge va placer tous ses navires et annoncer qu'il est prêt
		amiralR.placeTousLesNavires();
		rouge.setEquipeToPret();
		
		//Placement de tous les navires, affectation et fin de préparation pour l'Amiral bleu
		amiralB.placeTousLesNavires();
		bleu.setEquipeToPret();
		
		//Choix du matelot et affectation du rôle
	    amiralB.setMatelotSelectionne(matelotBAtt);
	    amiralB.affecteRole(true);
	    
		//Le matelot attaquant va avoir le pouvoir sur tous les navires qui vont réduire à néant tous ceux de l'équipe adverse
	    Navire navireDeTirActu = null; //Navire de l'équipe bleu
	    Navire navireCibleActu = null; //Navire de l'équipe rouge
	    Case positionCibleActu = null;
	    amiralB.setMatelotSelectionne(matelotBAtt);
	    
	    for(int i = 0; i < bleu.getNavires().length; i++) {
	    	navireDeTirActu = bleu.getNavires()[i];
	    	navireCibleActu = rouge.getNavires()[i];
	    	
	    	//Affectation du navire bleu
		    amiralB.setNavireSelectionne(navireDeTirActu);
		    amiralB.affecteNavire();
		    
		    //Destruction du navire rouge
		    matelotBAtt.setNavireSelectionne(navireDeTirActu);
		    for(int y = 0; y < navireCibleActu.getNBPieces(); y++) {
		    	positionCibleActu = navireCibleActu.getPieces()[y].getPosition();
		    	matelotBAtt.tirAvecNavire(positionCibleActu.getPositionX(), positionCibleActu.getPositionY());
		    	navireDeTirActu.annuleTempsRechargement();
		    }
	    }
	    
        bleu.getGrille().printGrille(true, matelotBAtt);
	    System.out.println("Equipe adverse: ");
	    rouge.getGrille().printGrille(true);
	    assertEquals(bleu, game.getVainqueur()[0]); //On s'attends à ce que l'équipe vainqeur est la bleu
	}
	
	@Test
	public void testVictoireAbandon() {
		System.out.println("testVictoireAbandon");
		Game game = creationPartieTest(false);
		Equipe bleu  = game.getEquipeBleu();
		Equipe rouge = game.getEquipeRouge();
		Amiral amiralB = bleu.getAmiral();
		Amiral amiralR = rouge.getAmiral();
		
		//L'amiral rouge va placer tous ses navires et annoncer qu'il est prêt
		amiralR.placeTousLesNavires();
		rouge.setEquipeToPret();
		
		//Placement de tous les navires, affectation et fin de préparation pour l'Amiral bleu
		amiralB.placeTousLesNavires();
		bleu.setEquipeToPret();
		
		bleu.abandonne();
	    assertEquals(rouge, game.getVainqueur()[0]); //On s'attends à ce que l'équipe vainqeur est la bleu
	}
	
	@Test
	public void testEgaliteAbandon() {
		System.out.println("testEgaliteAbandon");
		Game game = creationPartieTest(false);
		Equipe bleu  = game.getEquipeBleu();
		Equipe rouge = game.getEquipeRouge();
		Amiral amiralB = bleu.getAmiral();
		Amiral amiralR = rouge.getAmiral();
		
		//L'amiral rouge va placer tous ses navires et annoncer qu'il est prêt
		amiralR.placeTousLesNavires();
		rouge.setEquipeToPret();
		
		//Placement de tous les navires, affectation et fin de préparation pour l'Amiral bleu
		amiralB.placeTousLesNavires();
		bleu.setEquipeToPret();
		
		bleu.abandonne();
		rouge.abandonne();
	    assertEquals(2, game.getVainqueur().length); //On s'attends à ce que l'équipe vainqeur est la bleu
	}
	

	//Méthodes déjà testés dans EquipeTest mais où on a besoin de l'équipe adverse
	@Test
	public void testDeplacementEquipeAdversePasPrete() {
		System.out.println("testDeplacementEquipeAdversePasPrete");
		Game game = creationPartieTest(false);
		Equipe bleu  = game.getEquipeBleu();
		Equipe rouge = game.getEquipeRouge();
		Amiral amiralB = bleu.getAmiral();
		Amiral amiralR = rouge.getAmiral();
		Matelot matelotBDef = bleu.getAMatelotDansListe(1);
		
		//L'amiral rouge va placer tous ses navires mais ne pas dire qu'il est prêt
		amiralR.placeTousLesNavires();
		
		//Placement de tous les navires, affectation et fin de préparation pour l'Amiral bleu
		amiralB.placeTousLesNavires();
		bleu.setEquipeToPret();
		Navire navireADeplacer = bleu.getNavires()[0]; //Le premier navire de l'équipe: le cuirassé
	    Case anciennePosition = navireADeplacer.getTete().getPosition();
	    amiralB.setNavireSelectionne(navireADeplacer);
	    amiralB.setMatelotSelectionne(matelotBDef);
	    amiralB.affecteRole(false);
	    amiralB.affecteNavire();
	    
	    //Son déplacement doit échouer car l'équipe ennemi n'est pas prêt
	    matelotBDef.setNavireSelectionne(navireADeplacer);
	    matelotBDef.deplaceNavire(anciennePosition.getPositionX()+1, anciennePosition.getPositionY());
	    bleu.getGrille().printGrille(false);
	    bleu.getGrille().printGrille(false, matelotBDef);
	    assertTrue(navireADeplacer.checkPosition(bleu.getGrille(), anciennePosition.getPositionX(), anciennePosition.getPositionY()));
	}
	
	
	@Test
	public void testAffectationNavireCouleApresSelection() {
		//Dans cette variante la navire a été coulé APRES qu'il soit sélectionné
		System.out.println("testAffectationNavireCouleApresSelection");
		Game game = creationPartieTest(false);
		Equipe bleu  = game.getEquipeBleu();
		Equipe rouge = game.getEquipeRouge();
		Amiral amiralB = bleu.getAmiral();
		Amiral amiralR = rouge.getAmiral();
		Matelot matelotBAtt = bleu.getAMatelotDansListe(0);
		Matelot matelotBDef = bleu.getAMatelotDansListe(1);
		Matelot matelotRAtt = rouge.getAMatelotDansListe(0);
		Navire navireAAffecter = bleu.getNavires()[0]; //Le premier navire de l'équipe: le cuirassé
		
		//Les deux équipes placent tous leurs navires aux mêmes endroits
		amiralB.setNavireSelectionne(navireAAffecter);
		amiralB.placeTousLesNavires();
		bleu.setEquipeToPret();
		
		
		//Le navire rouge va détruire le navire bleu (positionné au même endroit)
		Navire destructeur = rouge.getNavires()[0];
		amiralR.placeTousLesNavires();
		rouge.setEquipeToPret();
		amiralR.setNavireSelectionne(destructeur);
		amiralR.setMatelotSelectionne(matelotRAtt);
		amiralR.affecteRole(true);
		amiralR.affecteNavire();
		matelotRAtt.setNavireSelectionne(destructeur);
		
		Case cibleActu = null;
		for(int i = 0; i < navireAAffecter.getNBPieces(); i++) {
			cibleActu = navireAAffecter.getPieces()[i].getPosition();
			matelotRAtt.tirAvecNavire(cibleActu.getPositionX(), cibleActu.getPositionY());
			destructeur.annuleTempsRechargement();
		}
		
		//Ces affectations ne doivent pas fonctionner car le navire est coulé
		amiralB.setMatelotSelectionne(matelotBAtt);
		amiralB.affecteRole(true);
		amiralB.affecteNavire();
		amiralB.setMatelotSelectionne(matelotBDef);
		amiralB.affecteRole(false);
		amiralB.affecteNavire();
		
		//Vision de l'amiral
		bleu.getGrille().printGrille(true);
		//Vision du matelot attaquant
		bleu.getGrille().printGrille(true, matelotBAtt);
		
		//On vérifier que les matelots n'ont pas obtenu le navire
		assertFalse(matelotBAtt.possedeNavire(navireAAffecter));
		assertFalse(matelotBDef.possedeNavire(navireAAffecter));
	}
	
	@Test
	public void testAffectationNavireCouleAvantSelection() {
		//Dans cette variante la navire a été coulé AVANT qu'il soit sélectionné
		System.out.println("testAffectationNavireCouleAvantSelection");
		Game game = creationPartieTest(false);
		Equipe bleu  = game.getEquipeBleu();
		Equipe rouge = game.getEquipeRouge();
		Amiral amiralB = bleu.getAmiral();
		Amiral amiralR = rouge.getAmiral();
		Matelot matelotBAtt = bleu.getAMatelotDansListe(0);
		Matelot matelotBDef = bleu.getAMatelotDansListe(1);
		Matelot matelotRAtt = rouge.getAMatelotDansListe(0);
		Navire navireAAffecter = bleu.getNavires()[0]; //Le premier navire de l'équipe: le cuirassé
		
		//Les deux équipes placent tous leurs navires aux mêmes endroits
		amiralB.setNavireSelectionne(navireAAffecter);
		amiralB.placeTousLesNavires();
		amiralB.deselectNavireSelectionne(); //On déselectionne la navire...
		bleu.setEquipeToPret();
		
		
		//Le navire rouge va détruire le navire bleu (positionné au même endroit)
		Navire destructeur = rouge.getNavires()[0];
		amiralR.placeTousLesNavires();
		rouge.setEquipeToPret();
		amiralR.setNavireSelectionne(destructeur);
		amiralR.setMatelotSelectionne(matelotRAtt);
		amiralR.affecteRole(true);
		amiralR.affecteNavire();
		matelotRAtt.setNavireSelectionne(destructeur);
		
		Case cibleActu = null;
		for(int i = 0; i < navireAAffecter.getNBPieces(); i++) {
			cibleActu = navireAAffecter.getPieces()[i].getPosition();
			matelotRAtt.tirAvecNavire(cibleActu.getPositionX(), cibleActu.getPositionY());
			destructeur.annuleTempsRechargement();
		}
		amiralB.setNavireSelectionne(navireAAffecter); //...Pour le réselectionner après sa destruction
		
		//Ces affectations ne doivent pas fonctionner car le navire est coulé
		amiralB.setMatelotSelectionne(matelotBAtt);
		amiralB.affecteRole(true);
		amiralB.affecteNavire();
		amiralB.setMatelotSelectionne(matelotBDef);
		amiralB.affecteRole(false);
		amiralB.affecteNavire();
		
		//Vision de l'amiral
		bleu.getGrille().printGrille(true);
		//Vision du matelot attaquant
		bleu.getGrille().printGrille(true, matelotBAtt);
		
		//On vérifier que les matelots n'ont pas obtenu le navire
		assertFalse(matelotBAtt.possedeNavire(navireAAffecter));
		assertFalse(matelotBDef.possedeNavire(navireAAffecter));
	}
	
}
