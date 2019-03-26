package model;

public class PieceNavire {
	
	Case position;
	boolean estEndommage;
	
	public PieceNavire() {
		position     = null;
		estEndommage = false;
	}
	
	public void changePosition(Case caseAAffecter) {
	    position = caseAAffecter;
	    caseAAffecter.setPiecePose(this);
	}
	
	public void recoitDommage() {
		estEndommage = true;
	}

	public boolean isEstEndommage() {
		return estEndommage;
	}

}
