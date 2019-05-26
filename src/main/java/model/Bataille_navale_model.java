package model;


public class Bataille_navale_model {

	private Joueur utilisateur;
	private boolean inAction; //Permet de dire si on ne arrêter toute action ou pas (à voir où on peut l'utiliser)
	
	public Bataille_navale_model() {
		utilisateur = null;
		inAction = false;
	}
	
	public boolean createGame(String nomJoueur, int numeroPort) {
		utilisateur = new Joueur(nomJoueur);
		return utilisateur.creerPartie(numeroPort);
	}
	
	public boolean joinGame(String nomJoueur, String adresseIp, int numeroPort) {
		utilisateur = new Joueur(nomJoueur);
		return utilisateur.rejoindrePartie(adresseIp, numeroPort);
	}
	
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
