package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Game implements Serializable {
	
	private final static int ID_HOTE     = 0; //Pour être sûr que l'hôte de la partie ai un id spécifique
	private final static int ID_ERROR    = -1; //Quand un joueur essaie de rejoindre la partie alors qu'elle a déjà commencé
	private final static int MIN_JOUEURS = 4; //Nombre de joueurs minimum pour pouvoir... Jouer
	
	private static boolean DO_PRINT = true; //A désactiver durant la majorité des tests afin d'éviter de toujours avoir les mêmes 50 lignes de textes inutiles qui apparaissent dans GameTest

	private final static String NOM_DEFAUT_EQUIPE_ROUGE = "Blood team";
	private final static String COULEUR_EQUIPE_ROUGE    = "Rouge";
	
	private final static String NOM_DEFAUT_EQUIPE_BLEUE = "Marins d'eau douce";
	private final static String COULEUR_EQUIPE_BLEUE    = "Bleue";
	
	private Equipe equipeRouge;
	private Equipe equipeBleu;
	private Joueur hote;
	private ArrayList<Joueur> joueursEnAttentes;
	private boolean peutCommencer; //Permet de signaler aux autres joueurs que l'hôte a fait débuter la partie (et aussi pour empêcher d'autres personnes de la rejoindre quand tout est prêt)
	
	public Game(Joueur createur) {
		equipeRouge = null;
		equipeBleu  = null;
		hote        = null;
		//hote = createur;
		joueursEnAttentes = new ArrayList<Joueur>();
		peutCommencer = false;
		//ajouteJoueur(createur);
	}
	
	public synchronized void ajouteJoueur(Joueur joueur) {
		if(peutCommencer) {
			joueur.setId(ID_ERROR);
		} else {
		//Comme le joueur ayant créé la partie la rejoint tout de suite après
		if(joueursEnAttentes.isEmpty())
			hote = joueur;
		
		    joueursEnAttentes.add(joueur);	
		    changeIdJoueur();
		    getNomsJoueur();
		}
	}
	
	private synchronized void changeIdJoueur() {
		int idActu = ID_HOTE + 1;
		for(Joueur j: joueursEnAttentes) {
			if(j == hote)
				j.setId(ID_HOTE);
			else {
			    j.setId(idActu);
			    idActu++;
			}
		}
	}
	
	/**
	 * Permet de montrer la liste des joueurs existants
	 * @return Un tableau contenant le nom de tous les joueurs de la partie
	 */
	public synchronized String[] getNomsJoueur() {
		String[] nomsJoueurs = new String[joueursEnAttentes.size()];
		int placeActu = 0;
		
		doPrintLn("Liste des joueurs de la partie:");
		for(Joueur j: joueursEnAttentes) {
			doPrintLn(j.getId() + ": " + j.getNom());
			nomsJoueurs[placeActu] = j.getNom();
			if(j == hote)
				nomsJoueurs[placeActu] += (" (Hôte)");
			placeActu++;
		}
		return nomsJoueurs;
	}
	
	/**
	 * 
	 * @return Un premier tableau contenant tout d'abord le nom des deux équipes puis
	 * l'ID et le nom des membres de l'équipe rouge à la deuxième case et de même pour l'équipe bleu à la troisième case si on peut commencer la partie, null sinon
	 */
	public synchronized String[][] getNomsEquipesEtJoueurs() {
		if(peutCommencer)
			return getTabStringEquipes();
		return null;
	}
	
	/**
	 * Va créer aléatoirement les équipes
	 * @return Vrai si l'opération s'est bien déroulé, faux sinon
	 */
	public synchronized boolean createTeams() {
		
		//On va prendre un joueur aléatoirement, le retirer des joueurs en attentes, sachant que le premier sera l'amial de son équipe
		Amiral amiralRouge = new Amiral(removeRandomJoueur());
		Amiral amiralBleu  = new Amiral(removeRandomJoueur());
		
		String nomEquipeRouge = trouveNomEquipe(amiralRouge.getNom());
		String nomEquipeBleu = trouveNomEquipe(amiralBleu.getNom());
		if(nomEquipeRouge == null)
			nomEquipeRouge = NOM_DEFAUT_EQUIPE_ROUGE;
		if(nomEquipeBleu == null)
			nomEquipeBleu = NOM_DEFAUT_EQUIPE_BLEUE;
		
		//Histoire qu'on puisse différencier les deux équipes si on tombe sur le même nom
		if(nomEquipeRouge.equals(nomEquipeBleu))
			nomEquipeBleu += " (2)";
		
		equipeRouge = new Equipe(nomEquipeRouge, COULEUR_EQUIPE_ROUGE, amiralRouge, this);
		equipeBleu = new Equipe(nomEquipeBleu, COULEUR_EQUIPE_BLEUE, amiralBleu, this);
		
		Matelot matelotActu   = null;
		int tailleRougeActu = 0;
		int tailleBleuActu  = 0;
		int RNGActu         = 0;
		//Créer les matelots avec les autres membres de l'équipe
		while(!joueursEnAttentes.isEmpty()) {
	        matelotActu = new Matelot(removeRandomJoueur());
	        tailleRougeActu = equipeRouge.getNBMatelots();
	        tailleBleuActu  = equipeBleu.getNBMatelots();
	        
	        //50% de chance si les deux équipes ont la même taille
            RNGActu = (int) Math.round(Math.random() * ((100) - 0) + 0);
	        
	        if(tailleRougeActu < tailleBleuActu || (tailleRougeActu == tailleBleuActu && (RNGActu < 50))) {
	        	equipeRouge.ajouteMatelot(matelotActu);
	        } else {
	        	equipeBleu.ajouteMatelot(matelotActu);
	        }
	        
		}
		peutCommencer = true;
		return true;
	}
	
	private synchronized String[][] getTabStringEquipes() {
		return new String[][]{new String[]{equipeRouge.getNom(), equipeBleu.getNom()}, equipeRouge.getIDEtNomsMembres(), equipeBleu.getIDEtNomsMembres()};
	}
	
	private synchronized Joueur removeRandomJoueur() {
		if(joueursEnAttentes == null || joueursEnAttentes.isEmpty()) {
			System.err.println("Erreur, il est impossible de retirer un joueur de la liste");
			return null;
		}
		//Pour créer un nombre random = Math.round(Math.random() * ((MAX) - MIN) + MIN);
        int index = (int) Math.round(Math.random() * ((joueursEnAttentes.size() - 1) - 0) + 0);
        return joueursEnAttentes.remove(index);
	}
	
	//Si jamais on trouve marrant de mettre des noms d'équipes easter eggs en fonction du nom de l'amiral
	//Genre "LE PROJET" pour "Emmanuel Macron", "Gotta Go Fast" pour Sonic ou encore "Agents de la paix" pour "Sofian Gherabi"
	private synchronized String trouveNomEquipe(String nomAmiral) {
		return null;
	}
	
	/**
	 * Permet de connaître l'équipe adverse
	 * @param equipe
	 * @return L'autre équipe que celle donné en paramètre (ou null si l'équipe en question n'existe pas dans la partie)
	 */
	public synchronized Equipe getAutreEquipe(Equipe equipe) {
		if(equipe == equipeBleu)
			return equipeRouge;
		else if(equipe == equipeRouge)
			return equipeBleu;
		else {
			System.out.println("L'équipe '" + equipe.getNom() + "' n'est pas dans cette partie...");
			return null;
		}
	}
	
	/**
	 * Permet d'obtenir le vainqueur de la partie
	 * @return Un tableau contenant une équipe gagnante, les deux équipes si il y a égalité ou null si la partie n'est pas finit
	 */
	public Equipe[] getVainqueur() {
		boolean rougeAPerdu = equipeRouge.verifieSiAPerdu();
		boolean bleuAPerdu  = equipeBleu.verifieSiAPerdu();
		if(rougeAPerdu && bleuAPerdu) //Égalité
			return new Equipe[] {equipeRouge, equipeBleu};
		else if(rougeAPerdu) //Bleu a gagné
			return new Equipe[] {equipeBleu};
		else if(bleuAPerdu) //Rouge a gagné
			return new Equipe[] {equipeRouge};
		else //Personne n'a gagné pour l'instant
			return null;
	}
	
	/**
	 * Permet de rechercher un Matelot qui est dans la partie via son id
	 * @param idMatelot
	 * @return Le matelot si il a été trouvé ou null
	 */
	public synchronized Matelot getMatelot(int idMatelot) {
		Matelot matelotTrouve = equipeBleu.getAMatelotParId(idMatelot);
		if(matelotTrouve != null)
			return matelotTrouve;
		return equipeRouge.getAMatelotParId(idMatelot);
	}
	
	/**
	 * Permet de rechercher un Navire qui est dans la partie via son équipe et son nom
	 * @param equipeNavire
	 * @param nomNavire
	 * @return Le navire correspondant ou null si l'équipe donnée n'est pas dans la partie ou que le navire n'est pas à l'intérieur
	 */
	public synchronized Navire getNavire(Equipe equipeNavire, String nomNavire) {
		if(equipeNavire.equals(equipeBleu))
			return equipeBleu.getANavireParNom(nomNavire);
		else if(equipeNavire.equals(equipeRouge))
			return equipeRouge.getANavireParNom(nomNavire);
		return null;
	}
	
	private void doPrintLn(String message) {
		if(DO_PRINT)
			System.out.println(message);
	}

	public static int getID_HOTE() {
		return ID_HOTE;
	}

	public static int getMinJoueurs() {
		return MIN_JOUEURS;
	}

	public boolean isPeutCommencer() {
		return peutCommencer;
	}

	public Equipe getEquipeRouge() {
		return equipeRouge;
	}

	public Equipe getEquipeBleu() {
		return equipeBleu;
	}

	public static void setDO_PRINT(boolean doPrint) {
		DO_PRINT = doPrint;
	}
}
