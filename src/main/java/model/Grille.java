package model;

import java.awt.Color;
import java.io.Serializable;

import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

public class Grille implements Serializable {
	
    private final static int LINES = 10;
    private final static int COLUMNS = 10;
    private final static int CODE_ASCII_A = 65;
    
    /*private final static int[] NUMERO_MATTER_ALL = new int[] {2, 6, 12, 16, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 32, 36, 42, 46, 52, 56, 
			60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 72, 76, 82, 86, 92, 96};*/
	private final static int[] NUMERO_MATTER_BORDER_DROITE = new int[] {2, 6, 12, 16, 42, 46, 52, 56, 82, 86, 92, 96};
	
	private final static int[] NUMERO_MATTER_BORDER_BAS = new int[] {20, 21, 24, 25, 28, 29, 60, 61, 64, 65, 68, 69};
	
	private final static int[] NUMERO_MATTER_BORDER_BAS_DROITE = new int[] {22, 26, 62, 66};
	
	private static int[] NUMERO_MATTER_BORDER_GAUCHE      = getNumeroBorderAutre(NUMERO_MATTER_BORDER_DROITE, 1);
	private static int[] NUMERO_MATTER_BORDER_HAUT        = getNumeroBorderAutre(NUMERO_MATTER_BORDER_BAS, LINES);
	private static int[] NUMERO_MATTER_BORDER_BAS_GAUCHE  = getNumeroBorderAutre(NUMERO_MATTER_BORDER_BAS_DROITE, 1);
	private static int[] NUMERO_MATTER_BORDER_HAUT_DROITE = getNumeroBorderAutre(NUMERO_MATTER_BORDER_BAS_DROITE, LINES);
	private static int[] NUMERO_MATTER_BORDER_HAUT_GAUCHE = getNumeroBorderAutre(NUMERO_MATTER_BORDER_BAS_GAUCHE, LINES);
	
	/**
	 * Permet d'avoir, par exemple, les index des cases qui doivent avoir une bordure à gauche par rapport aux index de celles qui sont à droite
	 * ou encore en haut par rapport à celles en bas
	 * @param borderOriginal
	 * @param aAjouter Vaut normalement 1 ou Grille.LINES (pour sauter de 1 case à droite ou d'une colonne)
	 * @return 
	 */
	private static int[] getNumeroBorderAutre(int[] borderOriginal, int aAjouter) {
		int[] numeroBorderAutre = new int[borderOriginal.length];
		for(int i = 0; i < borderOriginal.length; i++) {
			numeroBorderAutre[i] = borderOriginal[i] + aAjouter;
		}
		return numeroBorderAutre;
	}
	
	private Case[][] cases;

    public Grille() {
        initCases();
    }
    
    private void initCases() {
    	int numeroCaseActu = 0;
    	int numeroBordureActu = 0;
    	cases = new Case[LINES][COLUMNS];
    	for(int x = 0; x < LINES; x++) {
    		for(int y = 0; y < COLUMNS; y++) {
    			numeroBordureActu = Case.getBORDURE_SANS();
    			if(nombreDansTabNombre(numeroCaseActu, NUMERO_MATTER_BORDER_DROITE))
    				numeroBordureActu = Case.getBORDURE_DROITE();
    			else if(nombreDansTabNombre(numeroCaseActu, NUMERO_MATTER_BORDER_GAUCHE))
    				numeroBordureActu = Case.getBORDURE_GAUCHE();
    			else if(nombreDansTabNombre(numeroCaseActu, NUMERO_MATTER_BORDER_BAS))
    				numeroBordureActu = Case.getBORDURE_BAS();
    			else if(nombreDansTabNombre(numeroCaseActu, NUMERO_MATTER_BORDER_HAUT))
    				numeroBordureActu = Case.getBORDURE_HAUT();
    			else if(nombreDansTabNombre(numeroCaseActu, NUMERO_MATTER_BORDER_BAS_DROITE))
    				numeroBordureActu = Case.getBORDURE_BAS_DROITE();
    			else if(nombreDansTabNombre(numeroCaseActu, NUMERO_MATTER_BORDER_BAS_GAUCHE))
    				numeroBordureActu = Case.getBORDURE_BAS_GAUCHE();
    			else if(nombreDansTabNombre(numeroCaseActu, NUMERO_MATTER_BORDER_HAUT_DROITE))
    				numeroBordureActu = Case.getBORDURE_HAUT_DROITE();
    			else if(nombreDansTabNombre(numeroCaseActu, NUMERO_MATTER_BORDER_HAUT_GAUCHE))
    				numeroBordureActu = Case.getBORDURE_HAUT_GAUCHE();
    			
        		cases[x][y] = new Case(x, y, numeroBordureActu);
    			numeroCaseActu++;
    		}
    	}
    }
    
	private boolean nombreDansTabNombre(int nombre, int[] tabNombre) {
		for(int i = 0; i < tabNombre.length; i++) {
			if(nombre == tabNombre[i])
				return true;
		}
		return false;
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
    
    public void printGrille(boolean montreEtat) {
    	printGrille(montreEtat, null);
    }
    
    /**
     * Cette méthode permet de sortir à coups de System.out les navires qui sont sur la grille et éventuellement leur état 
     * @param montreEtat Permet d'ajouter une indication sur l'état des pièces "(O)" = pièce intacte "(X)" = pièce endommagée "(D") = navire de la pièce coulé
     * @param matelot Si différent de null, cette méthode ne montrera que les navires contrôlés par ce matelot
     */
    public void printGrille(boolean montreEtat, Matelot matelot) {
    	//Les noms des navires sont constitués de 3 caractères (2 premiers + un chiffre) / 7 si on souhaite montrer l'état du navire
    	System.out.println();
    	if(matelot != null)
    		System.out.println("Navires de " + matelot.getNom() + " (" + matelot.getRoleString() + ")");
    	String nomNavireActu  = "";
    	String etatActu       = "";
    	Case caseActu         = null;
    	PieceNavire pieceActu = null;
    	
    	//Placer les lettres
    	System.out.print(" " + " |");
    	for(int l = 0; l < COLUMNS; l++) {
    		if(montreEtat)
    			System.out.print("    " + getlettreColonne(l) + "    |");
    		else
		        System.out.print("  " + getlettreColonne(l) + "  |");
    	}
    	
    	System.out.println();
    	
    	for(int x = 0; x < LINES; x++) {
    		System.out.print(x + " |");
    		for(int y = 0; y < COLUMNS; y++) {
    			caseActu = cases[x][y];
    			if(caseActu == null) {
    				nomNavireActu = "Err"; //Signifie qu'il y a un problème
    				if(montreEtat)
    					etatActu = "(E)";
    			} else {
    				pieceActu = caseActu.getPiecePose();
    				//Si il existe une pièce sur la case et si on recherche par matelot, ce dernier possède le navire qui y est attaché
    				if(pieceActu != null && (matelot == null || matelot.possedeNavire(pieceActu.getNavireAttache()))) {
    					nomNavireActu = pieceActu.getNavireAttache().getNom();
    					if(montreEtat) {
    						if(pieceActu.getNavireAttache().isEstCoule())
    							etatActu = "(D)"; //D pour "Dead"
    						else if(pieceActu.isEstEndommage())
    							etatActu = "(X)"; //La pièce est endommagée
    						else
    							etatActu = "(O)"; //La pièce est en bon état
    					}
    				} else {
    					nomNavireActu = "   "; //Aucun Navire, la case est vide
    					if(montreEtat)
    					    etatActu = "   ";
    				}
    			}
    			//Un espace avant de montrer chaque état
    			if(montreEtat)
    			    etatActu = " " + etatActu;
    			System.out.print(" " + nomNavireActu + etatActu + " |");
    		}
    		System.out.println(); //On change de ligne
    	}
    	System.out.println();
    }
    
    /**
     * Permet d'obtenir la lettre du numéro de colonne donnée en paramètre
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

	public static int getLines() {
		return LINES;
	}

	public static int getColumns() {
		return COLUMNS;
	}
	
    public Case[][] getCases() {
        return this.cases;
    }
	
	public Case getUneCase(int positionX, int positionY) {
		return cases[positionX][positionY];
	}
}
