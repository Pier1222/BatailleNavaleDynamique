package model;

/**
 * C'est ici où seront testé le comportement des navires sur une seule Grille
 */
import static org.junit.Assert.*;
import org.junit.Test;
//import junit.framework.Assert;


public class NavireTest {
	
      //Test de placement
	  @Test
	  public void testPlaceAndCheckPositionHorizontal() {
		  System.out.println("testPlaceAndCheckPositionHorizontal");
	      Grille grille     = new Grille();
	      Cuirasse cuirasse = new Cuirasse(1);
	      int posXTete = 0;
	      int posYTete = 3;
	        
	      cuirasse.placeNavire(grille, posXTete, posYTete);
	      grille.printGrille(false);
	      PieceNavire pieceActu = cuirasse.getPieces()[0];
	      assertTrue(pieceActu.getPosition().getPositionX() == posXTete);
	      assertTrue(pieceActu.getPosition().getPositionY() == posYTete);
	        
	      pieceActu = cuirasse.getPieces()[1];
	      assertTrue(pieceActu.getPosition().getPositionX() == posXTete);
	      assertTrue(pieceActu.getPosition().getPositionY() == 2);
	        
	      pieceActu = cuirasse.getPieces()[2];
	      assertTrue(pieceActu.getPosition().getPositionX() == posXTete);
	      assertTrue(pieceActu.getPosition().getPositionY() == 1);
	        
	      pieceActu = cuirasse.getPieces()[3];
	      assertTrue(pieceActu.getPosition().getPositionX() == posXTete);
	      assertTrue(pieceActu.getPosition().getPositionY() == 0);
	        
	      assertTrue(cuirasse.checkPosition(grille, posXTete, posYTete));
	  }
	  
	  @Test
	  public void testPlaceAndCheckPositionVertical() {
		  System.out.println("testPlaceAndCheckPositionVertical");
	      Grille grille     = new Grille();
	      Cuirasse cuirasse = new Cuirasse(1);
	      cuirasse.tourne();
	      int posXTete = 2;
	      int posYTete = 0;
	        
	      cuirasse.placeNavire(grille, posXTete, posYTete);
	      grille.printGrille(false);
	      PieceNavire pieceActu = cuirasse.getPieces()[0];
	      assertTrue(pieceActu.getPosition().getPositionX() == posXTete);
	      assertTrue(pieceActu.getPosition().getPositionY() == posYTete);
	        
	      pieceActu = cuirasse.getPieces()[1];
	      assertTrue(pieceActu.getPosition().getPositionX() == 3);
	      assertTrue(pieceActu.getPosition().getPositionY() == posYTete);
	        
	      pieceActu = cuirasse.getPieces()[2];
	      assertTrue(pieceActu.getPosition().getPositionX() == 4);
	      assertTrue(pieceActu.getPosition().getPositionY() == posYTete);
	        
	      pieceActu = cuirasse.getPieces()[3];
	      assertTrue(pieceActu.getPosition().getPositionX() == 5);
	      assertTrue(pieceActu.getPosition().getPositionY() == posYTete);
	        
	      assertTrue(cuirasse.checkPosition(grille, posXTete, posYTete));
	  }
	  
	  @Test
	  public void testPlaceHorsGrilleHorizontal() {
		  System.out.println("testPlaceHorsGrilleHorizontal");
		  Grille grille     = new Grille();
		  Cuirasse cuirasse = new Cuirasse(1);
		  
		  //2 -> 1 -> 0 et la dernière pièce va déborder en -1
		  int[][] mauvaisesPositions = new int[][] {{0, 2}, {0, 10}, {-1, 3}, {11, 3}};
          int[] mauvaisePositionActu = null;
		  
		  for(int i = 0; i < mauvaisesPositions.length; i++) {
              mauvaisePositionActu = mauvaisesPositions[i];
              cuirasse.placeNavire(grille, mauvaisePositionActu[0], mauvaisePositionActu[1]);
              
		      //On vérifie qu'aucune pièce ne possède de position
		      for(int y = 0; y < cuirasse.getNBPieces(); y++) {
		    	  assertEquals(null, cuirasse.getPieces()[y].getPosition());
		      }
		  }
	  }
	  
	  @Test
	  public void testPlaceHorsGrilleVertical() {
		  System.out.println("testPlaceHorsGrilleVertical");
		  Grille grille     = new Grille();
		  Cuirasse cuirasse = new Cuirasse(1);
		  cuirasse.tourne();
		  
		  //7 -> 8 -> 9 et la dernière pièce va déborder en 10
		  int[][] mauvaisesPositions = new int[][] {{-1, 0}, {7, 0}, {3, -1}, {3, 11}};
          int[] mauvaisePositionActu = null;
		  
		  for(int i = 0; i < mauvaisesPositions.length; i++) {
              mauvaisePositionActu = mauvaisesPositions[i];
              cuirasse.placeNavire(grille, mauvaisePositionActu[0], mauvaisePositionActu[1]);
              
		      //On vérifie qu'aucune pièce ne possède de position
		      for(int y = 0; y < cuirasse.getNBPieces(); y++) {
		    	  assertEquals(null, cuirasse.getPieces()[y].getPosition());
		      }
		  }
	  }
	  
	  @Test
	  public void testPlaceCoteNavireHorizontal() {
		  System.out.println("testPlaceCoteNavireHorizontal");
		  Grille grille     = new Grille();
		  Cuirasse cuirasse = new Cuirasse(1);
		  Sous_Marin sousMarin = new Sous_Marin(1);
		  
		  //Le sous Marin va bloquer le positionnement du cuirassé
		  sousMarin.placeNavire(grille, 5, 5);
		  cuirasse.placeNavire(grille, 6, 7);
		  grille.printGrille(false);
		  
		  assertTrue(sousMarin.checkPosition(grille, 5, 5));  
	      //On vérifie qu'aucune pièce ne possède de position
	      for(int y = 0; y < cuirasse.getNBPieces(); y++) {
	          assertEquals(null, cuirasse.getPieces()[y].getPosition());
	      }
	  }
	  
	  @Test
	  public void testPlaceDerniereDiagonaleNavireHorizontal() {
		  System.out.println("testPlaceDerniereDiagonaleNavireHorizontal");
		  Grille grille     = new Grille();
		  Cuirasse cuirasse = new Cuirasse(1);
		  Sous_Marin sousMarin = new Sous_Marin(1);
		  
		  //Le sous Marin va bloquer le positionnement du cuirassé
		  sousMarin.placeNavire(grille, 1, 0);
		  cuirasse.placeNavire(grille, 0, 4);
		  grille.printGrille(false);
		  
		  assertTrue(sousMarin.checkPosition(grille, 1, 0));
	      //On vérifie qu'aucune pièce ne possède de position
	      for(int y = 0; y < cuirasse.getNBPieces(); y++) {
	          assertEquals(null, cuirasse.getPieces()[y].getPosition());
	      }
	  }
	  
	  @Test
	  public void testPlaceCoteNavireVertical() {
		  System.out.println("testPlaceCoteNavireVertical");
		  Grille grille     = new Grille();
		  Cuirasse cuirasse = new Cuirasse(1);
		  Sous_Marin sousMarin = new Sous_Marin(1);
		  cuirasse.tourne();
		  
		  //Le sous Marin va bloquer le positionnement du cuirassé
		  sousMarin.placeNavire(grille, 3, 2);
		  cuirasse.placeNavire(grille, 0, 3);
		  grille.printGrille(false);
		  
		  assertTrue(sousMarin.checkPosition(grille, 3, 2));  
	      //On vérifie qu'aucune pièce ne possède de position
	      for(int y = 0; y < cuirasse.getNBPieces(); y++) {
	          assertEquals(null, cuirasse.getPieces()[y].getPosition());
	      }
	  }
	  
	  @Test
	  public void testPlaceDerniereDiagonaleNavireVertical() {
		  System.out.println("testPlaceDerniereDiagonaleNavireVertical");
		  Grille grille     = new Grille();
		  Cuirasse cuirasse = new Cuirasse(1);
		  Sous_Marin sousMarin = new Sous_Marin(1);
		  cuirasse.tourne();
		  
		  //Le sous Marin va bloquer le positionnement du cuirassé
		  sousMarin.placeNavire(grille, 9, 9);
		  cuirasse.placeNavire(grille, 5, 8);
		  grille.printGrille(false);
		  
		  assertTrue(sousMarin.checkPosition(grille, 9, 9));
	      //On vérifie qu'aucune pièce ne possède de position
	      for(int y = 0; y < cuirasse.getNBPieces(); y++) {
	          assertEquals(null, cuirasse.getPieces()[y].getPosition());
	      }
	  }
	  
	  //Test de déplacement
	  @Test
	  public void testHorizontalUpValide() {
		  System.out.println("testHorizontalUpValide");
	      Grille grille     = new Grille();
	      Cuirasse cuirasse = new Cuirasse(1);
	      
	      int posY = 5;
	      cuirasse.placeNavire(grille, 1, posY);
	      grille.printGrille(false);
	      cuirasse.deplacementNavire(grille, 0, posY);
	      grille.printGrille(false);
	      assertTrue(cuirasse.checkPosition(grille, 0, posY));
	  }
	  
	  @Test
	  public void testHorizontalDownValide() {
		  System.out.println("testHorizontalDownValide");
	      Grille grille     = new Grille();
	      Cuirasse cuirasse = new Cuirasse(1);
	      
	      int posY = 5;
	      cuirasse.placeNavire(grille, 0, posY);
	      grille.printGrille(false);
	      cuirasse.deplacementNavire(grille, 1, posY);
	      grille.printGrille(false);
	      assertTrue(cuirasse.checkPosition(grille, 1, posY));
	  }
	  
	  @Test
	  public void testHorizontalRightValide() {
		  System.out.println("testHorizontalRightValide");
	      Grille grille     = new Grille();
	      Cuirasse cuirasse = new Cuirasse(1);
	      
	      int posX = 3;
	      cuirasse.placeNavire(grille, posX, 5);
	      grille.printGrille(false);
	      cuirasse.deplacementNavire(grille, posX, 6);
	      grille.printGrille(false);
	      assertTrue(cuirasse.checkPosition(grille, posX, 6));
	  }
	  
	  @Test
	  public void testHorizontalLeftValide() {
		  System.out.println("testHorizontalLeftValide");
	      Grille grille     = new Grille();
	      Cuirasse cuirasse = new Cuirasse(1);
	      
	      int posX = 3;
	      cuirasse.placeNavire(grille, posX, 5);
	      grille.printGrille(false);
	      cuirasse.deplacementNavire(grille, posX, 4);
	      grille.printGrille(false);
	      assertTrue(cuirasse.checkPosition(grille, posX, 4));
	  }
	  
	  @Test
	  public void testVerticalUpValide() {
		  System.out.println("testVerticalUpValide");
	      Grille grille     = new Grille();
	      Cuirasse cuirasse = new Cuirasse(1);
	      cuirasse.tourne();
	      
	      int posY = 2;
	      cuirasse.placeNavire(grille, 2, posY);
	      grille.printGrille(false);
	      cuirasse.deplacementNavire(grille, 3, posY);
	      grille.printGrille(false);
	      assertTrue(cuirasse.checkPosition(grille, 3, posY));
	  }
	  
	  @Test
	  public void testVerticalDownValide() {
		  System.out.println("testVerticalDownValide");
	      Grille grille     = new Grille();
	      Cuirasse cuirasse = new Cuirasse(1);
	      cuirasse.tourne();
	      
	      int posY = 2;
	      cuirasse.placeNavire(grille, 2, posY);
	      grille.printGrille(false);
	      cuirasse.deplacementNavire(grille, 3, posY);
	      grille.printGrille(false);
	      assertTrue(cuirasse.checkPosition(grille, 3, posY));
	  }
	  
	  @Test
	  public void testVerticalRightValide() {
		  System.out.println("testVerticalRightValide");
	      Grille grille     = new Grille();
	      Cuirasse cuirasse = new Cuirasse(1);
	      cuirasse.tourne();
	      
	      int posX = 0;
	      cuirasse.placeNavire(grille, posX, 8);
	      grille.printGrille(false);
	      cuirasse.deplacementNavire(grille, posX, 9);
	      grille.printGrille(false);
	      assertTrue(cuirasse.checkPosition(grille, posX, 9));
	  }
	  
	  @Test
	  public void testVerticalLeftValide() {
		  System.out.println("testVerticalLeftValide");
	      Grille grille     = new Grille();
	      Cuirasse cuirasse = new Cuirasse(1);
	      cuirasse.tourne();
	      
	      int posX = 0;
	      cuirasse.placeNavire(grille, posX, 8);
	      grille.printGrille(false);
	      cuirasse.deplacementNavire(grille, posX, 7);
	      grille.printGrille(false);
	      assertTrue(cuirasse.checkPosition(grille, posX, 7));
	  }
	  
	  @Test
	  public void testDeplacementSansEtrePlace() {
		  System.out.println("testDeplacementSansEtrePlace");
		  Grille grille     = new Grille();
		  Cuirasse cuirasse = new Cuirasse(1);
		  
		  //On tente de déplacer le navire sur une position valide alors qu'il n'est pas placé
		  cuirasse.deplacementNavire(grille, 5, 5);

		  //On vérifie qu'aucune pièce ne possède de position
		  for(int y = 0; y < cuirasse.getNBPieces(); y++) {
		      assertEquals(null, cuirasse.getPieces()[y].getPosition());
		  }
	  }
	  
	  @Test
	  public void testHorizontalDeplacementDeborde() {
		  System.out.println("testHorizontalDeplacementDeborde");
	      Grille grille     = new Grille();
	      Cuirasse cuirasse = new Cuirasse(1);
	      
	      cuirasse.placeNavire(grille, 0, 3);
	      cuirasse.deplacementNavire(grille, 0, 2);
	      grille.printGrille(false);
	      //On vérifie si le navire n'a pas bougé
	      assertTrue(cuirasse.checkPosition(grille, 0, 3));
	  }
	  
	  @Test
	  public void testVerticalDeplacementDeborde() {
		  System.out.println("testVerticalDeplacementDeborde");
	      Grille grille     = new Grille();
	      Cuirasse cuirasse = new Cuirasse(1);
	      cuirasse.tourne();
	      
	      cuirasse.placeNavire(grille, 6, 2);
	      cuirasse.deplacementNavire(grille, 7, 2);
	      grille.printGrille(false);
	      //On vérifie si le navire n'a pas bougé
	      assertTrue(cuirasse.checkPosition(grille, 6, 2));
	  }
	  
	  @Test
	  public void testDeplacementSurAutreNavireHorizontal() {
		  System.out.println("testDeplacementSurAutreNavireHorizontal");
		  Grille grille     = new Grille();
		  Cuirasse cuirasse = new Cuirasse(1);
		  Sous_Marin sousMarin = new Sous_Marin(1);
          
		  //Place les deux navires de manière correct
          sousMarin.placeNavire(grille, 5, 3);
          cuirasse.placeNavire(grille, 7, 5);
          
          //Pour essayer de percuter le Sous-Marin, on a besoin d'au moins deux déplacements
          cuirasse.deplacementNavire(grille, 6, 5);
          //Doit échouer
          cuirasse.deplacementNavire(grille, 5, 5);
          
          grille.printGrille(false);
          assertTrue(sousMarin.checkPosition(grille, 5, 3));
          assertTrue(cuirasse.checkPosition(grille, 6, 5));
	  }
	  
	  @Test
	  public void testDeplacementSurAutreNavireVertical() {
		  System.out.println("testDeplacementSurAutreNavireVertical");
		  Grille grille     = new Grille();
		  Cuirasse cuirasse = new Cuirasse(1);
		  Sous_Marin sousMarin = new Sous_Marin(1);
          cuirasse.tourne();
		  
		  //Place les deux navires de manière correct
          sousMarin.placeNavire(grille, 2, 7);
          cuirasse.placeNavire(grille, 0, 9);
          
          //Pour essayer de percuter le Sous-Marin, on a besoin d'au moins deux déplacements
          cuirasse.deplacementNavire(grille, 0, 8);
          //Doit échouer
          cuirasse.deplacementNavire(grille, 0, 7);
          
          grille.printGrille(false);
          assertTrue(sousMarin.checkPosition(grille, 2, 7));
          assertTrue(cuirasse.checkPosition(grille, 0, 8));
	  }
	  
	  @Test
	  public void testDeplacementPlus2CasesHorizontal() {
		  System.out.println("testDeplacementPlus2CasesHorizontal");
		  Grille grille     = new Grille();
		  Torpilleur torpilleur = new Torpilleur(1); //A beaucoup moins de chance de déborder
		  
		  int posXOriginal = 5;
		  int posYOriginal = 5;
		  
		  int[][] mauvaisDeplacement = new int[][] {{posXOriginal+2, posYOriginal}, {posXOriginal, posYOriginal+2}, {posXOriginal-2, posYOriginal}, {posXOriginal, posYOriginal-2}};
          int[] mauvaiseDeplacement = null;
          
		  torpilleur.placeNavire(grille, posXOriginal, posYOriginal);
          
		  for(int i = 0; i < mauvaisDeplacement.length; i++) {
              mauvaiseDeplacement = mauvaisDeplacement[i];
              torpilleur.deplacementNavire(grille, mauvaiseDeplacement[0], mauvaiseDeplacement[1]);
              
		      //On vérifie que le navire n'a pas bougé
              assertTrue(torpilleur.checkPosition(grille, posXOriginal, posYOriginal));		      
		  }
	  }
	  
	  @Test
	  public void testDeplacementDiagCasesHorizontal() {
		  System.out.println("testDeplacementDiagCasesHorizontal");
		  Grille grille     = new Grille();
		  Torpilleur torpilleur = new Torpilleur(1); //A beaucoup moins de chance de déborder
		  
		  int posXOriginal = 5;
		  int posYOriginal = 5;
		  
		  int[][] mauvaisDeplacement = new int[][] {{posXOriginal+1, posYOriginal+1}, {posXOriginal+1, posYOriginal-1}, {posXOriginal-1, posYOriginal+1}, {posXOriginal-1, posYOriginal-1}};
          int[] mauvaiseDeplacement = null;
          
		  torpilleur.placeNavire(grille, posXOriginal, posYOriginal);
          
		  for(int i = 0; i < mauvaisDeplacement.length; i++) {
              mauvaiseDeplacement = mauvaisDeplacement[i];
              torpilleur.deplacementNavire(grille, mauvaiseDeplacement[0], mauvaiseDeplacement[1]);
              
		      //On vérifie que le navire n'a pas bougé
              assertTrue(torpilleur.checkPosition(grille, posXOriginal, posYOriginal));
		  }
	  }
	  
	  @Test
	  public void testDeplacementPlus2CasesVertical() {
		  System.out.println("testDeplacementPlus2CasesVertical");
		  Grille grille     = new Grille();
		  Torpilleur torpilleur = new Torpilleur(1); //A beaucoup moins de chance de déborder
		  torpilleur.tourne();
		  
		  int posXOriginal = 1;
		  int posYOriginal = 3;
		  
		  int[][] mauvaisDeplacement = new int[][] {{posXOriginal+2, posYOriginal}, {posXOriginal, posYOriginal+2}, {posXOriginal-2, posYOriginal}, {posXOriginal, posYOriginal-2}};
          int[] mauvaiseDeplacement = null;
          
		  torpilleur.placeNavire(grille, posXOriginal, posYOriginal);
          
		  for(int i = 0; i < mauvaisDeplacement.length; i++) {
              mauvaiseDeplacement = mauvaisDeplacement[i];
              torpilleur.deplacementNavire(grille, mauvaiseDeplacement[0], mauvaiseDeplacement[1]);
              
		      //On vérifie que le navire n'a pas bougé
              assertTrue(torpilleur.checkPosition(grille, posXOriginal, posYOriginal));
		  }
	  }
	  
	  @Test
	  public void testDeplacementDiagCasesVertical() {
		  System.out.println("testDeplacementDiagCasesVertical");
		  Grille grille     = new Grille();
		  Torpilleur torpilleur = new Torpilleur(1); //A beaucoup moins de chance de déborder
		  torpilleur.tourne();
		  
		  int posXOriginal = 1;
		  int posYOriginal = 3;
		  
		  int[][] mauvaisDeplacement = new int[][] {{posXOriginal+1, posYOriginal+1}, {posXOriginal+1, posYOriginal-1}, {posXOriginal-1, posYOriginal+1}, {posXOriginal-1, posYOriginal-1}};
          int[] mauvaiseDeplacement = null;
          
		  torpilleur.placeNavire(grille, posXOriginal, posYOriginal);
          
		  for(int i = 0; i < mauvaisDeplacement.length; i++) {
              mauvaiseDeplacement = mauvaisDeplacement[i];
              torpilleur.deplacementNavire(grille, mauvaiseDeplacement[0], mauvaiseDeplacement[1]);
              
		      //On vérifie que le navire n'a pas bougé
              assertTrue(torpilleur.checkPosition(grille, posXOriginal, posYOriginal));
		  }
	  }
	  
	  @Test
	  public void testDeplacementNavireTouche() {
		  System.out.println("testDeplacementNavireTouche");
	      Grille grille1 = new Grille();
	      Grille grille2 = new Grille();
	      
	      Sous_Marin sousMarin = new Sous_Marin(1);
	      Torpilleur torpilleur = new Torpilleur(1);
	      
	      sousMarin.placeNavire(grille1, 2, 2);
	      torpilleur.placeNavire(grille2, 5, 5);
	      
	      //On est sûr de le toucher en visant la tête
	      sousMarin.tirer(grille2, 5, 5);
	      torpilleur.deplacementNavire(grille2, 5, 4);
	      grille2.printGrille(true);
	      //On vérifie que le torpilleur est au même endroit qu'au début
          assertTrue(torpilleur.checkPosition(grille2, 5, 5));
	  }
	  
	  
	  //Test de tir
	  @Test
	  public void testTirRate() {
		  System.out.println("testTirRate");
	      Grille grille1 = new Grille();
	      Grille grille2 = new Grille();
	      
	      Sous_Marin sousMarin = new Sous_Marin(1);
	      Torpilleur torpilleur = new Torpilleur(1);
	      
	      sousMarin.placeNavire(grille1, 2, 2);
	      torpilleur.placeNavire(grille2, 5, 5);
	      
	      //Normalement si la tête est en 5,5 à l'horizontal, la seconde pièce est en 5,4 et pas en 5,3
	      Navire navireTouche = sousMarin.tirer(grille2, 5, 3);
	      
	      grille2.printGrille(true);
	      //On vérifie que le torpilleur est intact et que le navire touché vaut null
	      PieceNavire[] pieces = torpilleur.getPieces();
	      for(int i = 0; i < pieces.length; i++) {
	    	  assertFalse(pieces[i].isEstEndommage());
	      }
	      assertEquals(null, navireTouche);
	  }
	  
	  @Test
	  public void testTirReussit() {
		  System.out.println("testTirReussit");
	      Grille grille1 = new Grille();
	      Grille grille2 = new Grille();
	      
	      Sous_Marin sousMarin = new Sous_Marin(1);
	      Torpilleur torpilleur = new Torpilleur(1);
	      
	      sousMarin.placeNavire(grille1, 2, 2);
	      torpilleur.placeNavire(grille2, 5, 5);
	      
	      //Normalement si la tête est en 5,5 à l'horizontal, la seconde pièce est en 5,4
	      Navire navireTouche = sousMarin.tirer(grille2, 5, 4);
	      
	      grille2.printGrille(true);
	      //On vérifie que le torpilleur est touché et qu'il s'agit du navire retourné
	      assertTrue(torpilleur.getPieces()[1].isEstEndommage());
	      assertEquals(torpilleur, navireTouche);
	  }
	  
	  @Test
	  public void testTirSansEtreRecharge() {
		  System.out.println("testTirSansEtreRecharge");
	      Grille grille1 = new Grille();
	      Grille grille2 = new Grille();
	      
	      Sous_Marin sousMarin = new Sous_Marin(1);
	      Torpilleur torpilleur = new Torpilleur(1);
	      
	      sousMarin.placeNavire(grille1, 2, 2);
	      torpilleur.placeNavire(grille2, 5, 5);
	      
	      sousMarin.tirer(grille2, 5, 5);
	      sousMarin.decrementeTempsRechargement();
	      //Ce tir doit être annulé car le sousMarin n'a pas eu le temps de recharger
	      Navire navireTouche = sousMarin.tirer(grille2, 5, 4);
	      
	      grille2.printGrille(true);
	      //On vérifie que seul la première pièce visé du torpilleur a été touché et qu'aucun navire a été touché au second tir
	      assertTrue(torpilleur.getPieces()[0].isEstEndommage());
	      assertFalse(torpilleur.getPieces()[1].isEstEndommage());
	      assertEquals(null, navireTouche);
	  }
	  
	  @Test
	  public void testTirPieceDejaTouche() {
		  System.out.println("testTirPieceDejaTouche");
	      Grille grille1 = new Grille();
	      Grille grille2 = new Grille();
	      
	      Sous_Marin sousMarin = new Sous_Marin(1);
	      Torpilleur torpilleur = new Torpilleur(1);
	      
	      sousMarin.placeNavire(grille1, 2, 2);
	      torpilleur.placeNavire(grille2, 5, 5);
	      
	      sousMarin.tirer(grille2, 5, 5);
	      sousMarin.annuleTempsRechargement();
	      //Tout de suite après son rechargement, le sousMarin va tirer au même endroit, or, on ne peut pas toucher deux fois la même pièce
	      Navire navireTouche = sousMarin.tirer(grille2, 5, 5);
	      
	      grille2.printGrille(true);
	      //On vérifie que seul la première pièce visé du torpilleur a été touché et qu'aucun navire a été touché au second tir
	      assertTrue(torpilleur.getPieces()[0].isEstEndommage());
	      assertFalse(torpilleur.getPieces()[1].isEstEndommage());
	      assertEquals(null, navireTouche);
	  }
	  
	  @Test
	  public void testTirQuiCoule() {
		  System.out.println("testTirQuiCoule");
	      Grille grille1 = new Grille();
	      Grille grille2 = new Grille();
	      
	      Sous_Marin sousMarin = new Sous_Marin(1);
	      //Comme on a déjà fait des test de tirs sur un navire à l'horizontal, on va le faire sur un navire à la vertical
	      Cuirasse cuirasse = new Cuirasse(1);
	      cuirasse.tourne();
	      
	      sousMarin.placeNavire(grille1, 2, 2);
	      cuirasse.placeNavire(grille2, 2, 3);
	      
	      //Le Sous-marin va détruire le cuirasse
	      Navire[] tabTouche = new Navire[4];
	      tabTouche[0] = sousMarin.tirer(grille2, 2, 3);
	      sousMarin.annuleTempsRechargement();
	      tabTouche[1] = sousMarin.tirer(grille2, 3, 3);
	      sousMarin.annuleTempsRechargement();
	      tabTouche[2] = sousMarin.tirer(grille2, 4, 3);
	      sousMarin.annuleTempsRechargement();
	      tabTouche[3] = sousMarin.tirer(grille2, 5, 3);
	      
	      grille2.printGrille(true);
	      //On vérifie que le cuirasse est bien coulé et que c'est bien lui qui a été touché à chaque tir
	      assertTrue(cuirasse.isEstCoule());
	      for(int i = 0; i < tabTouche.length; i++) {
	    	  assertEquals(cuirasse, tabTouche[i]);
	      }

	  }
}