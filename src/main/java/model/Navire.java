package model;

import java.io.Serializable;

public abstract class Navire implements Serializable {

	private final static int TEMPS_RECHARGEMENT_MAX = 60; //1 minute entre 2 tirs
	
	private String nom;
	private PieceNavire[] pieces;
	private boolean estHorizontal;
	//Il s'agit de la pièce tout à droite du bateau quand il est à l'horizontal et tout en haut quand il est à la verticale
	private PieceNavire tete;
	private int tempsRechargement;
	private boolean estCoule;
	
	public Navire(int numero) {
		nom               = getDebutNom() + numero;
		initPieces();
		estHorizontal     = true; //Un navire est initialisé à l'horizontal
		tempsRechargement = 0;
		estCoule          = false;
	}
	
	/**
	 * Construit un set de pièces pour le navire et lui donne sa "tête"
	 */
	private void initPieces() {
		pieces = new PieceNavire[getNBPieces()];
		for(int i = 0; i < getNBPieces(); i++) {
			pieces[i] = new PieceNavire(this); //La navire peut ainsi connaître ses pièces et vice-versa
		}
		tete = pieces[0];
	}
	
	public void verifieEtat() {
		for(int i = 0; i < getNBPieces(); i++) {
			if(!pieces[i].isEstEndommage()) //Si une pièce n'est pas endommagé, on ne fait rien
				return;
		}
		estCoule = true; //Sinon, on coule le bateau
	}
	
	public boolean estEndommage() {
		for(int i = 0; i < getNBPieces(); i++) {
			if(pieces[i].isEstEndommage()) //Si une pièce est endommagé, le navire ne peut pas se d�placer
				return true;
		}
		return false;
	}
	
	/**
	 * Change l'orientation du Navire (peut seulement être utilisé par l'Amiral)
	 */
	public void tourne() {
		estHorizontal = !estHorizontal;
	}
	
	
	//Méthodes de changement de position
	/**
	 * Permet de "poser" un navire (utilisé par l'Amiral)
	 * @param grille
	 * @param posXTete
	 * @param posYTete
	 */
	public void placeNavire(Grille grille, int posXTete, int posYTete) {
		changePositionNavire(grille, posXTete, posYTete, false);
	}
	
	/**
	 * Retire toutes les pièces du Navire de la Case où ils sont situés (est utilisé par l'Amiral)
	 */
	public void retireNavire() {
		for(int i = 0; i < getNBPieces(); i++) {
			pieces[i].retirePiece();
		}
	}
	
	/**
	 * Permet de déplacer d'une case maximum le navire (utilisé par un Matelot défensif)
	 * @param grille
	 * @param posXTete
	 * @param posYTete
	 */
	public void deplacementNavire(Grille grille, int posXTete, int posYTete) {
		changePositionNavire(grille, posXTete, posYTete, true);
	}
	
	/**
	 * 
	 * @param grille
	 * @param posXTete
	 * @param posYTete
	 * @param deplacement Détermine si ce changement de position est pour l'Amiral (false) ou pour un Matelot Défensif (true)
	 */
	private void changePositionNavire(Grille grille, int posXTete, int posYTete, boolean deplacement) {
		if(!verificationChangementPosition(grille, posXTete, posYTete, deplacement))
			return;
		
		int posXActu = posXTete;
		int posYActu = posYTete;
		retireNavire(); //Empêche que les pièces du navire se gène (déplacement à gauche avec navire à l'horizontal par exemple)
		for(int i = 0; i < getNBPieces(); i++) {
			pieces[i].changePosition(grille.getCases()[posXActu][posYActu]);
			
			if(estHorizontal)
				posYActu--; //On décale d'un cran vers la droite
			else
				posXActu++; //On décale d'un cran vers le bas
			
		}
	}
	
	
	private boolean verificationChangementPosition(Grille grille, int posXTete, int posYTete, boolean deplacement) {
		//Vérification 1: Est-ce que le navire est endommagé ?
		if(estEndommage()) {
			System.out.println("Navire '" + nom + "' endommagé, déplacement impossible");
    		return false;
		}
		
    	if(deplacement) {
		    //Vérification 2: Est-ce qu'on ne se déplace que d'une seule case ?
    		Case positionTeteActu = tete.getPosition();
    		int positionXTeteActu = positionTeteActu.getPositionX();
    		int positionYTeteActu = positionTeteActu.getPositionY();
    		int differenceX = Math.abs(positionXTeteActu - posXTete);
    		int differenceY = Math.abs(positionYTeteActu - posYTete);
    		if(differenceX > 1 || differenceY > 1 || (differenceX == 1 && differenceY == 1)) { //Interdiction de se déplacer de plus d'une case en X/Y ou en diagonale
    			System.out.println("Déplacement de plus d'une case non autorisé: " + 
    		    "\nDépart: [" + positionXTeteActu + ", " + positionYTeteActu + "]. Arrivé: [" + posXTete + ", " + posYTete +"] menant à une différence de [" + differenceX + ", " + differenceY +"]");
    			return false;
    		}
    		
    	}
		
		int posXActu = posXTete;
		int posYActu = posYTete;
		PieceNavire pieceOccupante = null;
		
		//Vérification 3: Est-ce qu'on ne sort pas des limites de la grille et est-ce que aucune case n'est déjà occupé par une autre pièce ?
    	//+Vérification 4: si placement Amiral: est-ce qu'il y a aucun navire autour de la position donnée ?
		for(int i = 0; i < getNBPieces(); i++) {
    	    if(!grille.ifPostionValide(posXActu, posYActu)) {
    	    	System.out.println("Position [" + posXActu + ", " + posYActu + "] invalide");
    		    return false;
    	    }
    	    pieceOccupante = grille.getCases()[posXActu][posYActu].getPiecePose();
    	    if(pieceOccupante != null && !isPiecePresente(pieceOccupante)) {
    	    	System.out.println("La position [" + posXActu + ", " + posYActu + "] est déjà occupé par une pièce de '" + pieceOccupante.getNavireAttache().getNom() + "'");
    	    	return false;
    	    }
    	    
    	    if(!deplacement && grille.ifNavireAutourCase(posXActu, posYActu, this)) { //Si on souhaite placer le navire mais qu'il y en a déjà un autre autour
    	    	System.out.println("Il y a un navire autour de la position [" + posXActu + ", " + posYActu + " ] ");
    	    	return false;
    	    }
    	    
    	    if(estHorizontal)
				posYActu--; //On décale d'un cran vers la droite
			else
				posXActu++; //On décale d'un cran vers le bas
    	    
    	}
    	return true;
	}
	
	/**
	 * Permet de savoir l'instance de pièce donnée se situe dans le navire
	 * @param piece
	 * @return Vrai si l'instance de pièce appartient à ce navire, faux sinon
	 */
	public boolean isPiecePresente(PieceNavire piece) {
		for(int i = 0; i < pieces.length; i++) {
			if(pieces[i] == piece)
				return true;
		}
		return false;
	}
	
	
	/**
	 * Effectue un tir si il le peut et initialise son temps de rechargement
	 * @param cible Grille qui recevera le tir
	 * @param posXCible Position X où effectuer le tir sur la grille cible
	 * @param posYCible
	 * @return
	 */
	public Navire tirer(Grille cible, int posXCible, int posYCible) {
		if(!peutTirer()) {
			System.out.println("Impossible de tirer (cooldown restant: " + tempsRechargement);
			return null;
		}
		
		tempsRechargement = TEMPS_RECHARGEMENT_MAX; //Le tir est effectué, on remet son cooldown au maximum
		
		PieceNavire pieceTouche = cible.getCases()[posXCible][posYCible].getPiecePose();
		if(pieceTouche == null) {
			System.out.println("Tir Effectué, aucune pièce touchée");
			return null;
		}
		
		if(pieceTouche.isEstEndommage()) {
			System.out.println("La pièce touchée est déjà endommagé !");
			return null;
		}
		
		pieceTouche.recoitDommage();
		return pieceTouche.getNavireAttache();
	}
	
	/**
	 * Permet de vérifier si, à partir de la tête, les pièces du navire sont à la position donnée en paramètre
	 * @param grille
	 * @param positionXTete
	 * @param positionYTete
	 * @return Vrai si le navire est à cette position
	 */
	public boolean checkPosition(Grille grille, int positionXTete, int positionYTete) {
		int posXActu        = positionXTete;
		int posYActu        = positionYTete;
		
		//On effectue la vérification à double sens: Est-ce qu'une pièce à la bonne case de référence et est-ce que la case à la bonne pièce ?
		PieceNavire pieceActu           = null; //Du côté de la pièce du navire
		PieceNavire pieceCaseGrilleActu = null; //Du côté de la grille donnée en paramètre
		
		Case positionActu               = null; //Du côté de la pièce du navire
		Case caseGrilleActu             = null; //Du côté de la grille donnée en paramètre
		
		for(int i = 0; i < getNBPieces(); i++) {
			pieceActu      = pieces[i];
			caseGrilleActu = grille.getCases()[posXActu][posYActu];
			
			if(pieceActu != null) //Ne devrait pas arriver mais on ne sait jamais
			    positionActu = pieceActu.getPosition();
			else
				positionActu = null;
				
			if(caseGrilleActu != null) //Ne devrait pas arriver mais on ne sait jamais
			    pieceCaseGrilleActu = caseGrilleActu.getPiecePose();
			else
				pieceCaseGrilleActu = null;
			
			if(positionActu != caseGrilleActu || pieceActu != pieceCaseGrilleActu) //C'est censé être exactement le même objet Case et le même objet Piece
			    return false;
			
			if(estHorizontal)
				posYActu--; //On décale d'un cran vers la droite
			else
				posXActu++; //On décale d'un cran vers le bas
		}
		return true;
	}
	
	/**
	 * Vérifie si le navire peut tirer (il n'est pas coulé et son temps de chargement est écoulé)
	 * @return Vrai si il le peut, faux sinon
	 */
	private boolean peutTirer() {
		return (tempsRechargement <= 0 && !estCoule);
	}
	
	private void decrementeTempsRechargement() {
		tempsRechargement--;
	}
	
	public abstract int getNBPieces();
	
	public abstract String getDebutNom();
	
	/**
	 * Renvoie l'id du type de navire (sous-marin à cuirasse)  (pour créer des statistiques)
	 * @return L'id du type de navire
	 */
	public abstract int getIdTypeNavire();

	public String getNom() {
		return nom;
	}

	public PieceNavire[] getPieces() {
		return pieces;
	}

	public PieceNavire getTete() {
		return tete;
	}

	public boolean isEstCoule() {
		return estCoule;
	}
}
