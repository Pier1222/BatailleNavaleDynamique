package model;

public class Case {

	PieceNavire piecePose;
	int positionX;
	int positionY;
	
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
	 * @return La lettre associée à la position de la case
	 */
	public String getStringPositionY() {
		if(positionY < 0 || positionY > 25)
			return "Err";
		return "A";
	}
}
