package model;

import java.util.ArrayList;

public class Game {
	
	private final static int ID_HOTE     = 0; //Pour être sûr que l'hôte de la partie ai un id spécifique
	private final static int ID_ERROR    = -1; //Quand un joueur essaie de rejoindre la partie alors qu'elle a déjà commencé
	private final static int MIN_JOUEURS = 2; //Nombre de joueurs minimum pour pouvoir... Jouer

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
		
		System.out.println("Liste des joueurs de la partie:");
		for(Joueur j: joueursEnAttentes) {
			System.out.println(j.getId() + ": " + j.getNom());
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
	 * l'ID et le nom des membres de l'équipe rouge et de même pour l'équipe bleu à la seconde case si on peut commencer la partie, null sinon
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
		
		equipeRouge = new Equipe(nomEquipeRouge, COULEUR_EQUIPE_ROUGE, amiralRouge);
		equipeBleu = new Equipe(nomEquipeBleu, COULEUR_EQUIPE_BLEUE, amiralBleu);
		
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

	public static int getID_HOTE() {
		return ID_HOTE;
	}

	public static int getMinJoueurs() {
		return MIN_JOUEURS;
	}

	public boolean isPeutCommencer() {
		return peutCommencer;
	}
}
