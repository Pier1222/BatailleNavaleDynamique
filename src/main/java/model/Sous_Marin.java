package model;

public class Sous_Marin extends Navire {
	public Sous_Marin(int numero) {
		super(numero);
	}

	@Override
	public int getNBPieces() {
		return 1;
	}

	@Override
	public String getDebutNom() {
		return "SM";
	}
	
	@Override
    public int getIdTypeNavire() {
		return 0;
	}   
}
