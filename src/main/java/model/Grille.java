package model;

public class Grille {
	
    private final int LINES = 10;
    private final int COLUMNS = 10;
	
	private Case[][] cases;

    public Grille() {
        this.cases = new Case[LINES][COLUMNS];
    }
    
    public void verifContourCase() {
    	
    }
    
    public Case[][] getCases() {
        return this.cases;
    }
    
    public void printGrille() {
    	//Les noms des navires sont constitués de 3 caractères (2 premiers + un chiffre)
    	String nomNavireActu  = "";
    	Case caseActu         = null;
    	PieceNavire pieceActu = null;
    	for(int x = 0; x < LINES; x++) {
    		System.out.print("|");
    		for(int y = 0; y < COLUMNS; y++) {
    			caseActu = cases[x][y];
    			if(caseActu == null) {
    				nomNavireActu = "Err"; //Signifie qu'il y a un problème
    			} else {
    				pieceActu = caseActu.getPiecePose();
    				if(pieceActu != null)
    					nomNavireActu = pieceActu.getNavireAttache().getNom();
    				else
    					nomNavireActu = "   "; //Aucun Navire, la case est vide
    			}
    			System.out.print(" " + nomNavireActu + " |");
    		}
    		System.out.println(); //On change de ligne
    	}
    }
}
