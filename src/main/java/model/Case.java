package model;

public class Case {

	private PieceNavire piecePose;
	private int positionX;
	private int positionY;
	
	public Case(int positionX, int positionY) {
		this.positionX = positionX;
		this.positionY = positionY;
		this.piecePose = null;
	}

	public PieceNavire getPiecePose() {
		return piecePose;
	}

	public void setPiecePose(PieceNavire piecePose) {
		this.piecePose = piecePose;
	}

	public int getPositionX() {
		return positionX;
	}

	public int getPositionY() {
		return positionY;
	}
	
	/**
	 * Retourne simplement le position X sous forme de String
	 * @return
	 */
	public String getStringPositionX() {
		return "" + positionX;
	}
	
	/**
	 * Transforme la position Y en lettre
	 * @return La lettre associciée la position de la case
	 */
	public String getStringPositionY() {
		return Grille.getlettreColonne(positionY);
	}
}
