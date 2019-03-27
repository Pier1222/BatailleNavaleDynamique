package model;

public class Croiseur extends Navire {
	public Croiseur(int numero) {
		super(numero);
	}

	@Override
	public int getNBPieces() {
		return 3;
	}

	@Override
	public String getDebutNom() {
		return "CR";
	}

}
