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
	        
	      cuirasse.placeNavire(grille, 0, 3);
	      grille.printGrille();
	      PieceNavire pieceActu = cuirasse.getPieces()[0];
	      assertTrue(pieceActu.getPosition().getPositionX() == 0);
	      assertTrue(pieceActu.getPosition().getPositionY() == 3);
	        
	      pieceActu = cuirasse.getPieces()[1];
	      assertTrue(pieceActu.getPosition().getPositionX() == 0);
	      assertTrue(pieceActu.getPosition().getPositionY() == 2);
	        
	      pieceActu = cuirasse.getPieces()[2];
	      assertTrue(pieceActu.getPosition().getPositionX() == 0);
	      assertTrue(pieceActu.getPosition().getPositionY() == 1);
	        
	      pieceActu = cuirasse.getPieces()[3];
	      assertTrue(pieceActu.getPosition().getPositionX() == 0);
	      assertTrue(pieceActu.getPosition().getPositionY() == 0);
	        
	      assertTrue(cuirasse.checkPosition(grille, posXTete, posYTete));
	  }
}
