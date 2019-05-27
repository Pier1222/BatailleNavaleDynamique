package model;

public class Amiral extends Joueur {
    Navire navireSelectionne;
    Matelot matelotSelectonne;
    
    //Constructeur par copie
    public Amiral(Joueur joueur) {
		super(joueur);
    }
    
    //Méthodes pour affecter des rôles et des navires aux matelors

	public void setNavireSelectionne(Navire navireSelectionne) {
		this.navireSelectionne = navireSelectionne;
	}

	public void setMatelotSelectonne(Matelot matelotSelectonne) {
		this.matelotSelectonne = matelotSelectonne;
	}
}
