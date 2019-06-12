package model;

public class Cuirasse extends Navire {
	public Cuirasse(int numero) {
		super(numero);
	}

	@Override
	public int getNBPieces() {
		return 4;
	}

	@Override
	public String getDebutNom() {
		return "CU"; //Cuirassé
	}
	
	@Override
    public int getIdTypeNavire() {
		return 3;
	}
}