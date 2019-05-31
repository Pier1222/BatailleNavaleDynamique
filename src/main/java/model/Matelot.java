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
	
	public Matelot(Joueur joueur) {
		super(joueur);
		naviresControles = new ArrayList<Navire>();
		navireSelectionne = null;
		aUnRole      = false;
		estAttaquant = false;
	}
	
	//Méthodes pour faire des actions avec le/les navire(s) controle(s)
	//Faire cela avec un index (numéro de navire dans la liste) ou en checkant à chaque fois que le navire donné est dans cette liste ?
	//Aussi, comment gérer les rôles ? Faire deux sous-classes ou utiliser un booléen et mettre toutes les méthodes (attaque/mouvement) ici ?
	public void utiliseNavire(int posXChoisi, int posYChoisi) {
	    if(navireSelectionne == null || !peutAgir()) {
	    	System.out.println("Vous ne pouvez pas utiliser le navire");
	    	return;
	    }
	    
	    if(estAttaquant) {
	    	deplaceNavire(posXChoisi, posYChoisi);
	    } else {
            tirAvecNavire(posXChoisi, posYChoisi);
	    }
	}
	
	private void deplaceNavire(int posXTete, int posYTete) {
	    Grille grilleDeplacement = getEquipe().getGrille();
    	navireSelectionne.deplacementNavire(grilleDeplacement, posXTete, posYTete);		
	}
	
	private Navire tirAvecNavire(int posXCible, int posYCible) {
		Equipe adversaires = getEquipe().getEquipeAdverse();
		Grille grilleCible = adversaires.getGrille();
		Navire navireTouche = navireSelectionne.tirer(grilleCible, posXCible, posYCible);
		//Envoyer infos à l'équipe
		return null;
	}
	
	/**
	 * Ajoute un navire à ceux que la matelot peut contrôler
	 * @param navire
	 */
	public boolean addNavire(Navire navire) {
		if(!aUnRole) {
			System.out.println("Le matelot doit avoir un rôle avant de lui affecter un rôle");
			return false;
		}
		naviresControles.add(navire);
		getStatistiques().incrementUtilisationsNavires(navire);
		return true;
	}
	
	/**
	 * Retire un navire si le matelot le contrôle
	 * @param navire
	 */
	public void perdNavire(Navire navire) {
	    naviresControles.remove(navire);
	    if(naviresControles.isEmpty())
	    	aUnRole = false; //Le matelot perd son rôle si il n'a plus aucun navire
	}
	
	/**
	 * Deuxième manière de retirer un navire: seulement si il a un rôle précis
	 * @param navire
	 * @param retireAAttaquant Vrai si on souhaite retirer le navire à son attaquant, Faux si c'est au défenseur
	 */
	public void perdNavire(Navire navire, boolean retireAAttaquant) {
		if(estAttaquant == retireAAttaquant)
			perdNavire(navire);
	}
	
	public void changeRole(boolean estAttaquant) {
		if(!naviresControles.isEmpty()) {
			System.out.println("Ce matelot à déjà des navires");
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
	public String getNaviresToString() {
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
	
	/**
	 * Permet de savoir si le matelot peut déplacer/faire tirer un navire
	 * @return Un booléen qui renvoie la réponse à cette question
	 */
	private boolean peutAgir() {
		return getEquipe().isEstPret() && aUnRole;
	}

	/**
	 * Sélectionne la navire pour ce matelot si il le possède
	 * @param navireSelectionne
	 */
	public void setNavireSelectionne(Navire navireSelectionne) {
		if(!naviresControles.contains(navireSelectionne)) {
			System.out.println(getNom() + " ne possède pas le navire " + navireSelectionne.getNom());
			return;
		}
		this.navireSelectionne = navireSelectionne;
	}

	
	public boolean isaUnRole() {
		return aUnRole;
	}

	public boolean isEstAttaquant() {
		return estAttaquant;
	}
}
