package model;

public class PieceNavire {
	
	Case position;
	boolean estEndommage;
	Navire navireAttache; //C'est le navire
	
	public PieceNavire(Navire navireAttache) {
		position           = null;
		estEndommage       = false;
		this.navireAttache = navireAttache;
	}
	
	/**
	 * Modifie la case associé à la pièce et modifie AUSSI la pièce associé à la case donnée
	 * @param caseAAffecter
	 */
	public void changePosition(Case caseAAffecter) {
	    position = caseAAffecter;
	    caseAAffecter.setPiecePose(this);
	}
	
	/**
	 * Retire la référence de la Case à cette Pièce (et vice-versa)
	 */
	public void retirePiece() {
		if(position == null)
			return;
		position.setPiecePose(null);
		position = null;
	}
	
	public void recoitDommage() {
		estEndommage = true;
	}

	public boolean isEstEndommage() {
		return estEndommage;
	}

	public Case getPosition() {
		return position;
	}

	public Navire getNavireAttache() {
		return navireAttache;
	}
}
