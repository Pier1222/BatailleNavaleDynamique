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
    	
    	if(matelotSelectionne.possedeNavire(navireSelectionne)) {
    		System.out.println("Le matelot possède déjà le navire a affecter");
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
     * Retire le contrôle du navire sélectionné au matelot
     */
    public void desaffecteNavire() {
    	if(matelotSelectionne == null || navireSelectionne == null) {
    		System.out.println("Impossible de retirer un navire a un matelot si le navire ou le matelot est inexistant");
    		return;
    	}
    	matelotSelectionne.perdNavire(navireSelectionne);
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
    		System.out.println("Impossible de retirer la navire");
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
    
    /**
     * Il s'agit d'une méthode utilisée durant les tests pour placer tous les navires sur la première position où c'est possible
     */
    public void placeTousLesNavires() {
    	System.out.println("On place tous les navires:");
    	Navire[] naviresEquipe = getEquipe().getNavires();
    	Navire navireActu      = null;
    	int positionXActu      = 0;
    	int positionYActu      = 0;
    	for(int i = 0; i < naviresEquipe.length; i++) {
    		navireActu = naviresEquipe[i];
    		setNavireSelectionne(navireActu);
    		while(navireActu.getTete().getPosition() == null) { //Tant qu'on a pas réussit à placer le navire
    			placeNavire(positionXActu, positionYActu);
    			positionYActu++;
    			if(positionYActu == Grille.getColumns()) { //Si on atteint la dernière colonne existante, on saute une ligne
    				positionXActu++;
    				positionYActu = 0;
    			}
    		}
    	}
    	System.out.println("C'est bon, tous les navires sont placés");
    }

	public void setNavireSelectionne(Navire navireSelectionne) {
		//Si le Navire existe, qu'il n'est pas coulé, que l'Amiral a une équipe et que le Navire est dedans
		if(navireSelectionne == null) {
			System.out.println("Aucun navire sélectionné !");
		} else if(navireSelectionne.isEstCoule()) {
			System.out.println("Que veux-tu faire avec un navire coulé ?");
			return;
		} else if(getEquipe() == null) {
			System.out.println("L'amiral n'est pas dans une équipe, il ne peut donc pas sélectionner de navire");
			return;
		} else if(!getEquipe().dansFlotte(navireSelectionne)) {
			System.out.println("Le navire n'est pas dans l'équipe de l'amiral");
			return;
		}
		
		this.navireSelectionne = navireSelectionne;
	}
	
	public void setMatelotSelectionne(Matelot matelotSelectionne) {
		//Si le Matelot existe et que lui et l'Amiral ont une équipe et que c'est la même
		if(matelotSelectionne == null) {
			System.out.println("Aucun matelot à sélectionné !");
			return;
		} else if(getEquipe() == null || matelotSelectionne.getEquipe() == null) {
			System.out.println("L'amiral ou le matelot qu'il veut sélectionner n'ont pas d'équipe");
			return;
		} else if(getEquipe() != matelotSelectionne.getEquipe()) {
			System.out.println("L'amiral n'est pas dans la même équipe que le matelot qu'il veut sélectionner");
			return;
		}
		this.matelotSelectionne = matelotSelectionne;
	}
	
	public void deselectNavire(Navire navire) {
		if(navireSelectionne != null && navireSelectionne.equals(navire))
			deselectNavireSelectionne();
	}

	public void deselectNavireSelectionne() {
		navireSelectionne = null;
	}

	public void deselectMatelotSelectionne() {
		matelotSelectionne = null;
	}
}
