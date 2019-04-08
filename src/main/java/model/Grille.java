package model;

public class Grille {
	
    private final static int LINES = 10;
    private final static int COLUMNS = 10;
    private final static int CODE_ASCII_A = 65;
	
	private Case[][] cases;

    public Grille() {
        initCases();
    }
    
    private void initCases() {
    	cases = new Case[LINES][COLUMNS];
    	for(int x = 0; x < LINES; x++) {
    		for(int y = 0; y < COLUMNS; y++) {
    			cases[x][y] = new Case(x, y);
    		}
    	}
    }
    
    /**
     * Vérifie si il existe au moins un navire autour de la case qui se situe à la position X/Y donnée.
     * Attention, cette méthode ne vérifie QUE le contour de la position donnée et pas le contenu de la position elle-même
     * @param posX
     * @param posY
     * @param navireAutorise Navire étant accepté autour de la case
     * @return Vrai si on a trouvé au moins un navire, faux sinon
     */
    public boolean ifNavireAutourCase(int posX, int posY, Navire navireAutorise) {
    	int[][] positionsEntourantes = new int[][] {
    		{posX-1, posY},   //Au dessus
    		{posX-1, posY+1}, //Diagonal haut/droite
    		{posX, posY+1},   //A droite
    		{posX+1, posY+1}, //Diagonal bas/droite
    		{posX+1, posY},   //En dessous
    		{posX+1, posY-1}, //Diagonal bas/gauche
    		{posX, posY-1},   //A gauche
    		{posX-1, posY-1}  //Diagonal haut/gauche
    	};
    	Case caseEntouranteActu = null;
    	int posXActu = -1;
    	int posYActu = -1;
    	for(int i = 0; i < positionsEntourantes.length; i++) {
    		posXActu = positionsEntourantes[i][0];
    		posYActu = positionsEntourantes[i][1];
    		if(ifPostionValide(posXActu, posYActu))
    			caseEntouranteActu = cases[posXActu][posYActu];
    		else
    			caseEntouranteActu = null; //Cela signifie que la case d'origine est au bord du plateau
    		
    		//Si il y a un navire autour de la case et que ce navire n'est pas le navire autorisé
    		if(caseEntouranteActu != null && caseEntouranteActu.getPiecePose() != null && navireAutorise != null && !navireAutorise.isPiecePresente(caseEntouranteActu.getPiecePose()))
    			return true; //Une pièce à été trouvée
    	}
    	
    	return false;
    }
    
    /**
     * Vérifie si la position X/Y donnée peut bien faire partiede la grille
     * @param posX
     * @param posY
     * @return Vrai si posX et posY sont valides, faux sinon
     */
    public boolean ifPostionValide(int posX, int posY) {
    	return (posX < LINES && posY < COLUMNS && posX >= 0 && posY >= 0);
    }
    
    public Case[][] getCases() {
        return this.cases;
    }
    
    public void printGrille() {
    	//Les noms des navires sont constitués de 3 caractères (2 premiers + un chiffre)
    	System.out.println();
    	String nomNavireActu  = "";
    	Case caseActu         = null;
    	PieceNavire pieceActu = null;
    	
    	//Placer les lettres
    	System.out.print(" " + " |");
    	for(int l = 0; l < COLUMNS; l++) {
    		System.out.print("  " + getlettreColonne(l) + "  |");
    	}
    	
    	System.out.println();
    	
    	for(int x = 0; x < LINES; x++) {
    		System.out.print(x + " |");
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
    	System.out.println();
    }
    
    /**
     * Permet d'obtenir la lettre du numéro de colonne donné en paramètre
     * @param numeroColonne
     * @return Une chaine de caractère contenant seulement le caractère trouvé
     */
    public static String getlettreColonne(int numeroColonne) {
    	if(numeroColonne < 0 || numeroColonne >= COLUMNS)
    		return "ERR"; //Numéro de colonne non valide
    	
    	int codeAscii = CODE_ASCII_A + numeroColonne;
    	char lettre = (char) codeAscii;
    	return "" + lettre;
    }
}
