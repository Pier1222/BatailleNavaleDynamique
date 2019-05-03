package model;


public class Bataille_navale_model {

	Joueur utilisateur;
	boolean inAction; //Permet de dire si on ne arrêter toute action ou pas (à voir où on peut l'utiliser)
	
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

	public boolean isInAction() {
		return inAction;
	}

	public void setInAction(boolean inAction) {
		this.inAction = inAction;
	}
}
