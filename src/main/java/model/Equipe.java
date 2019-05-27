package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Equipe implements Serializable {
	private static int NB_CUIRASSE    = 1;
	private static int NB_CROISEURS   = 2;
	private static int NB_TORPILLEURS = 3;
	private static int NB_SOUS_MARINS = 4;
	
	private String nom;
	private String couleur;
	
	private ArrayList<Matelot> matelots;
	private Amiral amiral;
	private Grille grille; //Celle contenant les navires de l'équipe
	private Navire[] navires;
	private boolean estPret; //Permet de savoir quand l'Amiral a donner son signal
	private boolean aAbandonne;
	private Game partie;
	
	public Equipe(String nom, String couleur, Amiral amiral, Game partie) {
		this.nom = nom;
		this.couleur = couleur;
		matelots = new ArrayList<Matelot>();
		this.amiral = amiral;
		amiral.setEquipe(this);
		this.partie = partie;
		grille = new Grille();
		initNavires();
		estPret = false;
		aAbandonne = false;
	}
	
	private void initNavires() {
		navires = new Navire[NB_CUIRASSE + NB_CROISEURS + NB_TORPILLEURS + NB_SOUS_MARINS];
		int numeroNavireActu = 0;
		for(int i = 0; i < NB_CUIRASSE; i++) {
			navires[numeroNavireActu] = new Cuirasse(i+1);
			numeroNavireActu++;
		}
		for(int i = 0; i < NB_CROISEURS; i++) {
			navires[numeroNavireActu] = new Croiseur(i+1);
			numeroNavireActu++;
		}
		for(int i = 0; i < NB_TORPILLEURS; i++) {
			navires[numeroNavireActu] = new Torpilleur(i+1);
			numeroNavireActu++;
		}
		for(int i = 0; i < NB_SOUS_MARINS; i++) {
			navires[numeroNavireActu] = new Sous_Marin(i+1);
		}
	}
	
	public void ajouteMatelot(Matelot matelot) {
		matelots.add(matelot);
		matelot.setEquipe(this);
	}
	
	public int getNBMatelots() {
		return matelots.size();
	}
	
	/**
	 * Cette méthode permet d'avoir l'ID et le nom de tous les membres de l'équipe (avec en premier l'Amiral)
	 * @return
	 */
	public String[] getIDEtNomsMembres() {
		String[] idEtNoms = new String[getNBMatelots() + 1]; //Plus l'Amiral
		int placeActu = 0;
		idEtNoms[placeActu] = amiral.getId() + " " + amiral.getNom();
		for(Matelot m: matelots) {
			placeActu++;
			idEtNoms[placeActu] = m.getId() + " " + m.getNom();
		}
		return idEtNoms;
	}
	
	/**
	 * N'effectue pas les dégâts en lui-même mais vérifie si un navire est coulé et si c'est le cas, retirer son contrôle à ses utilisateurs
	 * @param navireEndommage
	 */
	public void changeEtatApresDegat(Navire navireEndommage) {
		if(navireEndommage == null) {
			return;
		}
		if(navireEndommage.isEstCoule()) {
			retireNavireATousMatelots(navireEndommage);
		}
	}
	
	/**
	 * Permet de retirer le navire donné en paramètre à tous les matelots de l'équipe
	 * (en théorie, cela n'affecte que 2 matelots maximum)
	 * @param navireARetirer
	 */
	public void retireNavireATousMatelots(Navire navireARetirer) {
		for(Matelot m: matelots) {
			m.perdNavire(navireARetirer);
		}
	}
	
	/**
	 * Permet de retirer le navire donné en paramètre à tous les matelots avec un rôle précis
	 * (en théorie, cela n'affecte qu'un matelot maximum)
	 * @param navireARetirer
	 * @param retireAAttaquant Vrai si on souhaite retirer le navire à son attaquant, Faux si c'est au défenseur
	 */
	public void retireNavireATousMatelotsAvecRole(Navire navireARetirer, boolean retireAAttaquant) {
		for(Matelot m: matelots) {
			m.perdNavire(navireARetirer, retireAAttaquant);
		}
	}
	
	
	/**
	 * Permet de savoir si l'équipe a perdu la partie ou pas
	 * @return Vrai si elle a abandonnée ou que sa flotte a été réduit à néant, faux sinon
	 */
	public boolean verifieSiAPerdu() {
		//Faire une méthode qui utilise celle-là dans Game
		return (aAbandonne || flotteDetruite());
	}

	private boolean flotteDetruite() {
		for(int i = 0; i < navires.length; i++) {
			if(!navires[i].isEstCoule()) //Il reste encore un navire en état de continuer
				return false;
		}
		return true; //Tous les navire sont coulés
	}
	
	public Equipe getEquipeAdverse() {
		return partie.getAutreEquipe(this);
	}

	public String getNom() {
		return nom;
	}

	public String getCouleur() {
		return couleur;
	}

	public Grille getGrille() {
		return grille;
	}

	public Game getPartie() {
		return partie;
	}

	public Navire[] getNavires() {
		return navires;
	}

	public boolean isEstPret() {
		return estPret;
	}

	/**
	 * Vérifie si tous les navires ont une position et considère que l'équipe est prête si c'est le cas
	 * @return Vrai si l'équipe est désormais prête, faux sinon
	 */
	public boolean setEquipeToPret() {
	    for(int i = 0; i < navires.length; i++) {
	    	if(navires[i].getTete().getPosition() == null) { //Si la tête est placé, le reste du corps aussi
	    		System.out.println("L'équipe n'est pas encore prête... Le navire " + navires[i].getNom() + " n'est pas encore placé...");
	    		return false;
	    	}
	    }
		
		estPret = true;
	    return true;	
	}
	
	public void abandonne() {
		aAbandonne = true;
	}
}