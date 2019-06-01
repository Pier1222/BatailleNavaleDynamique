package model;

public class Amiral extends Joueur {
    private Navire navireSelectionne;
    private Matelot matelotSelectionne;
    
    public Amiral() {
    	this(new Joueur());
    }
    
    //Constructeur par copie
    public Amiral(Joueur joueur) {
		super(joueur);
		navireSelectionne  = null;
		matelotSelectionne = null;
    }
    
    /**
     * Permet de tourner le navire sélectionné si on est encore dans la phase de préparation
     */
    public void tourneNavire() {
    	if(navireSelectionne == null || !peutPreparer()) {
    		System.out.println("Impossible de tourner le navire actuellement");
    	}
    	navireSelectionne.tourne();
    }
    
    //Méthodes pour affecter des rôles et des navires aux matelots
    
    /**
     * Affecte le navire actuellement sélectionné au matelot sélectionné si c'est possible
     */
    public void affecteNavire() {
    	if(matelotSelectionne == null || navireSelectionne == null) {
    		System.out.println("Impossible de faire une affectation si on a pas de matelot ou de navire sélectionné");
    		return;
    	}
    	
    	if(navireSelectionne.isEstCoule()) {
    		System.out.println("Le navire '" + navireSelectionne.getNom() + "' ne peut plus faire grand chose maintenant... Donc...");
    		return;
    	}
    	
    	//Si le matelot sélectionné a un rôle
    	if(matelotSelectionne.isaUnRole()) {
    		//On retire le navire au potentiel matelot qui possède le même rôle dessus
    		getEquipe().retireNavireATousMatelotsAvecRole(navireSelectionne, matelotSelectionne.isEstAttaquant());
    		//On affecte le navire au matelot sélectionné
    		matelotSelectionne.addNavire(navireSelectionne);
    	}

    }
    
    /**
     * Affecte un rôle au matelot sélectionné
     * @param estAttaquant
     */
    public void affecteRole(boolean estAttaquant) {
    	if(matelotSelectionne == null) {
    		System.out.println("Aucun matelot à qui affecter un rôle !");
    		return;
    	}
    	matelotSelectionne.changeRole(estAttaquant);
    }
    
    /**
     * Permet à l'amiral de placer le navire sélectionné sur la grille de son équipe
     * @param posXTete Position X où on veut placer la tête du navire
     * @param posYTete Position Y où on veut placer la tête du navire
     */
    public void placeNavire(int posXTete, int posYTete) {
    	if(navireSelectionne == null || !peutPreparer()) {
    		System.out.println("Impossible de placer le navire");
    		return;
    	}
    	Grille grilleEquipe = getEquipe().getGrille();
    	navireSelectionne.placeNavire(grilleEquipe, posXTete, posYTete);
    }
    
    public void retireNavire() {
    	if(navireSelectionne == null || !peutPreparer()) {
    		System.out.println("Impossible de retirer la naire");
    		return;
    	}
    	navireSelectionne.retireNavire();
    }
    
    /**
     * Permet de savoir si cet amiral peut continuer de préparer la bataille
     * @return Vrai si c'est le cas, faux sinon
     */
    private boolean peutPreparer() {
    	return !getEquipe().isEstPret();
    }

	public void setNavireSelectionne(Navire navireSelectionne) {
		//Rechercher si il est dans l'équipe
		this.navireSelectionne = navireSelectionne;
	}
	
	public void setMatelotSelectionne(Matelot matelotSelectionne) {
		//Rechercher si il est dans l'équipe
		this.matelotSelectionne = matelotSelectionne;
	}

	public void deselectNavireSelectionne() {
		navireSelectionne = null;
	}

	public void deselectMatelotSelectionne() {
		matelotSelectionne = null;
	}
}
