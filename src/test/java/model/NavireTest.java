package model;

/**
 * C'est ici où seront testé le comportement des navires sur une seule Grille
 */
import static org.junit.Assert.*;

import org.junit.Test;


public class NavireTest {
	
	  @Test
	  public void testPlaceAndCheckPositionHorizontal() {
		  System.out.println("testPlaceAndCheckPositionHorizontal");
	      Grille grille     = new Grille();
	      Cuirasse cuirasse = new Cuirasse(1);
	      int posXTete = 0;
	      int posYTete = 3;
	        
	      cuirasse.placeNavire(grille, posXTete, posYTete);
	      grille.printGrille();
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
	      grille.printGrille();
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
		  grille.printGrille();
		  
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
		  grille.printGrille();
		  
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
		  grille.printGrille();
		  
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
		  grille.printGrille();
		  
		  assertTrue(sousMarin.checkPosition(grille, 9, 9));
	      //On vérifie qu'aucune pièce ne possède de position
	      for(int y = 0; y < cuirasse.getNBPieces(); y++) {
	          assertEquals(null, cuirasse.getPieces()[y].getPosition());
	      }
	  }
	  
	  @Test
	  public void testHorizontalUpValide() {
		  System.out.println("testHorizontalUpValide");
	      Grille grille     = new Grille();
	      Cuirasse cuirasse = new Cuirasse(1);
	      
	      int posY = 5;
	      cuirasse.placeNavire(grille, 1, posY);
	      grille.printGrille();
	      cuirasse.deplacementNavire(grille, 0, posY);
	      grille.printGrille();
	      assertTrue(cuirasse.checkPosition(grille, 0, posY));
	  }
	  
	  @Test
	  public void testHorizontalDownValide() {
		  System.out.println("testHorizontalDownValide");
	      Grille grille     = new Grille();
	      Cuirasse cuirasse = new Cuirasse(1);
	      
	      int posY = 5;
	      cuirasse.placeNavire(grille, 0, posY);
	      grille.printGrille();
	      cuirasse.deplacementNavire(grille, 1, posY);
	      grille.printGrille();
	      assertTrue(cuirasse.checkPosition(grille, 1, posY));
	  }
	  
	  @Test
	  public void testHorizontalRightValide() {
		  System.out.println("testHorizontalRightValide");
	      Grille grille     = new Grille();
	      Cuirasse cuirasse = new Cuirasse(1);
	      
	      int posX = 3;
	      cuirasse.placeNavire(grille, posX, 5);
	      grille.printGrille();
	      cuirasse.deplacementNavire(grille, posX, 6);
	      grille.printGrille();
	      assertTrue(cuirasse.checkPosition(grille, posX, 6));
	  }
	  
	  @Test
	  public void testHorizontalLeftValide() {
		  System.out.println("testHorizontalLeftValide");
	      Grille grille     = new Grille();
	      Cuirasse cuirasse = new Cuirasse(1);
	      
	      int posX = 3;
	      cuirasse.placeNavire(grille, posX, 5);
	      grille.printGrille();
	      cuirasse.deplacementNavire(grille, posX, 4);
	      grille.printGrille();
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
	      grille.printGrille();
	      cuirasse.deplacementNavire(grille, 3, posY);
	      grille.printGrille();
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
	      grille.printGrille();
	      cuirasse.deplacementNavire(grille, 3, posY);
	      grille.printGrille();
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
	      grille.printGrille();
	      cuirasse.deplacementNavire(grille, posX, 9);
	      grille.printGrille();
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
	      grille.printGrille();
	      cuirasse.deplacementNavire(grille, posX, 7);
	      grille.printGrille();
	      assertTrue(cuirasse.checkPosition(grille, posX, 7));
	  }
	  
	  @Test
	  public void testHorizontalDeplacementDeborde() {
		  System.out.println("testHorizontalDeplacementDeborde");
	      Grille grille     = new Grille();
	      Cuirasse cuirasse = new Cuirasse(1);
	      
	      cuirasse.placeNavire(grille, 0, 3);
	      cuirasse.deplacementNavire(grille, 0, 2);
	      grille.printGrille();
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
	      grille.printGrille();
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
          
          grille.printGrille();
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
          
          grille.printGrille();
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
}