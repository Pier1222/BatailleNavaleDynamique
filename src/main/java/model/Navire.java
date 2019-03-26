package model;

public abstract class Navire {

	private static int NB_PIECES = 1;
	public static String DEBUT_NOM = "??"; //Inconnu
	private String nom;
	private PieceNavire[] pieces;
	private boolean estCoule;
	
	public Navire(int numero) {
		nom      = DEBUT_NOM + numero;
		initPieces();
		estCoule = false;
	}
	
	private void initPieces() {
		pieces = new PieceNavire[NB_PIECES];
		for(int i = 0; i < NB_PIECES; i++) {
			pieces[i] = new PieceNavire();
		}
	}
	
	public void verifieEtat() {
		for(int i = 0; i < NB_PIECES; i++) {
			if(!pieces[i].isEstEndommage()) //Si une pièce n'est pas endommagé, on ne fait rien
				return;
		}
		estCoule = true; //Sinon, on coule le bateau
	}
	
	public boolean estEndommage() {
		for(int i = 0; i < NB_PIECES; i++) {
			if(pieces[i].isEstEndommage()) //Si une pièce est endommagé, le navire ne peut pas se déplacer
				return true;
		}
		return false;
	}
	
	public void tourne() {
		
	}
	
	public void deplacement(Case destination) {
		
	}
	
	public void tirer(Case cible) {
		
	}
	
	private boolean peutTirer() {
		return false;
	}
	
	
	
	
}
