package model;


public class Bataille_navale_model {

	Joueur utilisateur;
	
	public Bataille_navale_model() {
		utilisateur = null;
	}
	
	public boolean createGame(String nomJoueur, int numeroPort) {
		utilisateur = new Joueur(nomJoueur);
		return utilisateur.creerPartie(numeroPort);
	}
	
	public boolean joinGame(String nomJoueur, String adresseIp, int numeroPort) {
		utilisateur = new Joueur(nomJoueur);
		return utilisateur.rejoindrePartie(adresseIp, numeroPort);
	}
}
