package model;

public class Torpilleur extends Navire {
	public Torpilleur(int numero) {
		super(numero);
	}

	@Override
	public int getNBPieces() {
		return 2;
	}

	@Override
	public String getDebutNom() {
		return "TO"; //Torpilleur
	}
	
	@Override
    public int getIdTypeNavire() {
		return 1;
	}
}
