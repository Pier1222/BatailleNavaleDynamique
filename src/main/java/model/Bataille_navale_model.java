package model;


public class Bataille_navale_model {

	private Joueur utilisateur;
	private Game partieActu; //Permet de récupérer la partie et d'en soutirer des informations pour les vues
	private boolean inAction; //Permet de dire si on ne arrêter toute action ou pas (à voir où on peut l'utiliser)
	
	public Bataille_navale_model() {
		utilisateur = null;
		partieActu  = null;
		inAction    = false;
	}
	
	public boolean createGame(String nomJoueur, int numeroPort) {
		creerJoueur(nomJoueur);
		return utilisateur.creerPartie(numeroPort);
	}
	
	public boolean joinGame(String nomJoueur, String adresseIp, int numeroPort) {
		creerJoueur(nomJoueur);
		return utilisateur.rejoindrePartie(adresseIp, numeroPort);
	}
	
	public void creerJoueur(String nomJoueur) {
		utilisateur = new Joueur(nomJoueur);
	}
	
	
	//Méthodes utilisant le serveur
	/**
	 * Va rechercher sur le serveur le nom des joueurs présents dans la partie
	 * @return Un tableau contenant le nom de ces joueurs (plus une indication pour dire qui est l'hôte)
	 */
	public String[] getNomsJoueursPartieActu() {
		utilisateur.getClient().envoieRequete(Bataille_Client_Requester.getPlayersNameReq());
		return utilisateur.getClient().getRequesterTabString();
	}
	
	/**
	 * Va demander au serveur de créer les équipes
	 */
	public void createTeams() {
		utilisateur.getClient().envoieRequete(Bataille_Client_Requester.getCreateTeamsReq());
	}
	
	public String[][] getInfosTeams() {
		utilisateur.getClient().envoieRequete(Bataille_Client_Requester.getInfosTeamsReq());
		return utilisateur.getClient().getRequesterTabDeTabDeString();
	}
	
	//Contrairement aux autres, cette information n'est pas directement retourné car cela permettera au modèle d'être utilisé si on souhaite faire une version 1 joueur du jeu
	public void actualisePartieActu() {
		utilisateur.getClient().envoieRequete(Bataille_Client_Requester.getShowingReq());
		partieActu = utilisateur.getClient().getRequesterEtatPartie();
	}
	
	public void placeDeplaceNavire(String nomNavire, int posX, int posY) {
		String requeteDep = Bataille_Client_Requester.getMovingReq() + " " + nomNavire + " " + posX + " " + posY;
		utilisateur.getClient().envoieRequete(requeteDep);
	    System.out.println("Résultat requête '" + requeteDep + "' " + utilisateur.getClient().getRequesterStringActu());
	}
	
	public void reduitTempsDeChargementTirPartie() {
		utilisateur.getClient().envoieRequete(Bataille_Client_Requester.getDechargeReq());
	}
	//Fin des méthodes utilisant le serveur

	public Game getPartieActu() {
		return partieActu;
	}

	public Joueur getUtilisateur() {
		return utilisateur;
	}

	public boolean isInAction() {
		return inAction;
	}

	public void setInAction(boolean inAction) {
		this.inAction = inAction;
	}
}
