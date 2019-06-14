package model;

import java.util.ArrayList;

public class Matelot extends Joueur {
	
	private final static String ROLE_INCONNU   = "?"; 
	private final static String ROLE_ATTAQUANT = "A";
	private final static String ROLE_DEFENSEUR = "D";
	
	private ArrayList<Navire> naviresControles;
	private Navire navireSelectionne;
	
	private boolean aUnRole; //Permet de savoir si le matelot à un rôle ou pas (au lieu de toujours assumer par défaut qu'il est en défense)
	private boolean estAttaquant;
	
	public Matelot() {
		this(new Joueur());
	}
	
	public Matelot(Joueur joueur) {
		super(joueur);
		naviresControles  = new ArrayList<Navire>();
		navireSelectionne = null;
		aUnRole           = false;
		estAttaquant      = false;
	}
	
	//Méthodes pour faire des actions avec le/les navire(s) controle(s)
	//Faire cela avec un index (numéro de navire dans la liste) ou en checkant à chaque fois que le navire donné est dans cette liste ?
	//Aussi, comment gérer les rôles ? Faire deux sous-classes ou utiliser un booléen et mettre toutes les méthodes (attaque/mouvement) ici ?
	/*public Navire utiliseNavire(int posXChoisi, int posYChoisi) { //Finallement, c'est assez moyen vu les requête qu'on doit faire en ligne
	    if(navireSelectionne == null || !peutAgir()) {
	    	System.out.println("Vous ne pouvez pas utiliser le navire");
	    	return
	    }
	    
	    if(estAttaquant) {
	    	deplaceNavire(posXChoisi, posYChoisi);
	    	return null;
	    } else {
            return tirAvecNavire(posXChoisi, posYChoisi);
	    }
	}*/
	
	public void deplaceNavire(int posXTete, int posYTete) {
	    if(navireSelectionne == null) {
	    	System.out.println("Aucun navire selectionné");
	    	return;
	    }
	    if (!peutAgir()) {
	    	System.out.println("Vous ne pouvez pas agir");
	    	return;
	    }
	    if(estAttaquant) {
	    	System.out.println("Un attaquant ne déplace pas les navires");
	    	return;
	    }
		
	    Grille grilleDeplacement = getEquipe().getGrille();
    	navireSelectionne.deplacementNavire(grilleDeplacement, posXTete, posYTete);		
	}
	
	public Navire tirAvecNavire(int posXCible, int posYCible) {
	    if(navireSelectionne == null) {
	    	System.out.println("Aucun navire selectionné");
	    	return null;
	    }
	    if (!peutAgir()) {
	    	System.out.println("Vous ne pouvez pas agir");
	    	return null;
	    }
	    if(!estAttaquant) {
	    	System.out.println("Un défenseur ne tire pas");
	    	return null;
	    }
	    
		Equipe adversaires = getEquipe().getEquipeAdverse();
		if(adversaires == null) {
			System.out.println("Euh... Où est-ce que peux tirer le Matelot si son équipe n'a pas d'adversaire ?");
			return null;
		}
		
		Grille grilleCible = adversaires.getGrille();
		Navire navireTouche = navireSelectionne.tirer(grilleCible, posXCible, posYCible);
		//Permettre à l'équipe ennemi de retirer le navire si il est coulé
		if(navireTouche != null) {
			getStatistiques().incrementeNbTirsTouches();
		    if(navireTouche.isEstCoule()) {
			    adversaires.retireNavireATousMatelots(navireTouche);
		    }
		} else
			getStatistiques().incrementeNbTirsRates();
		return navireTouche;
	}

	/**
	 * Ajoute un navire à ceux que la matelot peut contrôler
	 * @param navire
	 * @return Vrai si l'opération s'est bien déroulé, false sinon
	 */
	public boolean addNavire(Navire navire) {
		//System.out.println("Role du matelot: " + getRoleString());
		if(!aUnRole) {
			System.out.println("Le matelot doit avoir un rôle avant de lui affecter un navire");
			return false;
		}
		if(possedeNavire(navire)) {
			System.out.println("Cela ne va pas aider le matelot d'avoir deux fois le même navire");
			return false;
		}
		naviresControles.add(navire);
		//System.out.println("Nombre de navire: " + getNombreNaviresControles() + " est ce que " + navire.getNom() + " est dedans ?: " + possedeNavire(navire));
		
		getStatistiques().incrementeUtilisationsNavires(navire);
		return true;
	}
	
	/**
	 * Retire un navire si le matelot le contrôle
	 * @param navire
	 */
	public void perdNavire(Navire navire) {
	    boolean navireRetire = naviresControles.remove(navire);
	    if(navire.equals(navireSelectionne))
	    	deselectNavireSelectionne(); //Si il contrôlait ce navire, on lui enlève le contrôle
	    
	    if(navireRetire && naviresControles.isEmpty())
	    	aUnRole = false; //Le matelot perd son rôle si il a perdu un navire et qu'il en a plus
	}
	
	/**
	 * Deuxième manière de retirer un navire: seulement si il a un rôle précis
	 * @param navire
	 * @param retireAAttaquant Vrai si on souhaite retirer le navire à son attaquant, Faux si c'est au défenseur
	 */
	public void perdNavire(Navire navire, boolean retireAAttaquant) {
		if(aUnRole && estAttaquant == retireAAttaquant)
			perdNavire(navire);
	}
	
	public void changeRole(boolean estAttaquant) {
		if(!naviresControles.isEmpty()) {
			System.out.println("Ce matelot a déjà des navires, on ne peut pas lui affecter de rôle pour l'instant");
			return;
		}
		
		this.estAttaquant = estAttaquant;
		aUnRole = true;
		if(estAttaquant)
			getStatistiques().incrementeNbRoleAttaque();
		else
			getStatistiques().incrementeNbRoleDefense();
	}
	
	/**
	 * Permet d'obtenir la version chaîne de caractère du rôle du mattelot
	 * @return
	 */
	public String getRoleString() {
		if(!aUnRole)
			return ROLE_INCONNU; //Rôle inconnu
		if(estAttaquant)
			return ROLE_ATTAQUANT;
		return ROLE_DEFENSEUR; //Sinon, il est défenseur
	}
	
	/**
	 * Permet d'obtenir le nom des navires contrôlés par le matelot
	 * @return Le nom de tous les navires dans sa liste des navires séparé d'une virgule (ou "(Aucun)" si il n'a pas de navire)
	 */
	public String getNomsNaviresControles() {
		if(naviresControles.isEmpty())
			return "(Aucun)";
		
		boolean premierNavire = true;
		String naviresToString = "";
		for(Navire n: naviresControles) {
			if(premierNavire)
				premierNavire = false;
			else
				naviresToString += ", ";
			naviresToString += n.getNom();
		}
		return naviresToString;
	}
	
	public ArrayList<Navire> getNaviresControles() {
		return naviresControles;
	}

	/**
	 * Permet de savoir si le matelot peut déplacer/faire tirer un navire
	 * @return Vrai si son équipe est prête, que ce matelot a un rôle et que l'équipe adverse est prête (ou qu'elle n'existe pas)
	 */
	private boolean peutAgir() {
		Equipe equipeAdverse = getEquipe().getEquipeAdverse();
		return (equipeAdverse == null || equipeAdverse.isEstPret()) && getEquipe().isEstPret() && aUnRole;
	}

	/**
	 * Sélectionne la navire pour ce matelot si il le possède
	 * @param navireSelectionne
	 */
	public void setNavireSelectionne(Navire navireSelectionne) {
		if(!possedeNavire(navireSelectionne)) {
			System.out.println(getNom() + " ne possède pas le navire " + navireSelectionne.getNom());
			return;
		}
		this.navireSelectionne = navireSelectionne;
	}
	
	public void deselectNavireSelectionne() {
		navireSelectionne = null;
	}
	
	/**
	 * Vérifie si le matelot possède le navire donné en paramètre
	 * @param navire
	 * @return Vrai si le navire est dans la liste de navires que contrôle le matelot, faux sinon
	 */
	public boolean possedeNavire(Navire navire) {
		for(Navire n: naviresControles) {
			if(n.equals(navire))
				return true;
		}
		return false;
		//return naviresControles.contains(navireSelectionne); //Ne Fonctionne pas
	}
	
	public int getNombreNaviresControles() {
		return naviresControles.size();
	}

	public boolean isaUnRole() {
		return aUnRole;
	}

	public boolean isEstAttaquant() {
		return estAttaquant;
	}

	public static String getRoleInconnu() {
		return ROLE_INCONNU;
	}

	public static String getRoleAttaquant() {
		return ROLE_ATTAQUANT;
	}

	public static String getRoleDefenseur() {
		return ROLE_DEFENSEUR;
	}
}
