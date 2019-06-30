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
		//System.out.println("Début demande actualisation");
		utilisateur.getClient().envoieRequete(Bataille_Client_Requester.getShowingReq());
		partieActu = utilisateur.getClient().getRequesterEtatPartie();
		//return utilisateur.getClient().getRequesterEtatPartie();
		//System.out.println("Fin demandeActualisation");
	}
	
	public void placeDeplaceNavire(String nomNavire, int posX, int posY) {
		String requeteDep = Bataille_Client_Requester.getMovingReq() + " " + nomNavire + " " + posX + " " + posY;
		utilisateur.getClient().envoieRequete(requeteDep);
	    System.out.println("Résultat requête '" + requeteDep + "' " + utilisateur.getClient().getRequesterStringActu());
	}
	
	public void reduitTempsDeChargementTirPartie() {
		utilisateur.getClient().envoieRequete(Bataille_Client_Requester.getDechargeReq());
	}
	
	public void retireNavire(String nomNavire) {
		String requeteRem = Bataille_Client_Requester.getRemoveReq() + " " + nomNavire;
		utilisateur.getClient().envoieRequete(requeteRem);
		System.out.println("Résultat requête '" + requeteRem + "': " + utilisateur.getClient().getRequesterBooleenActu());
	}
	
	public void tourneNavire(String nomNavire) {
		String requeteTourne = Bataille_Client_Requester.getRotateReq() + " " + nomNavire;
		utilisateur.getClient().envoieRequete(requeteTourne);
		System.out.println("Résultat requête '" + requeteTourne + "': " + utilisateur.getClient().getRequesterBooleenActu());
	}
	
	public void affecteRole(int idMatelot, String role) {
		String requeteRole = Bataille_Client_Requester.getAffecteRoleReq() + " " + idMatelot + " " + role;
		utilisateur.getClient().envoieRequete(requeteRole);
		System.out.println("Résultat requête '" + requeteRole + "': " + utilisateur.getClient().getRequesterBooleenActu());
	}
	
	public void affecteNavire(int idMatelot, String nomNavire) {
		String requeteNav = Bataille_Client_Requester.getAffecteNavireReq() + " " + idMatelot + " " + nomNavire;
		utilisateur.getClient().envoieRequete(requeteNav);
		System.out.println("Résultat requête '" + requeteNav + "': " + utilisateur.getClient().getRequesterBooleenActu());
	}
	
	public void rendEquipePrete() {
		String requeteReady = Bataille_Client_Requester.getReadyReq();
		utilisateur.getClient().envoieRequete(requeteReady);
		System.out.println("Résultat requête '" + requeteReady + "': " + utilisateur.getClient().getRequesterBooleenActu());
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
