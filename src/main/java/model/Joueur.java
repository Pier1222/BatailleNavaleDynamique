package model;

import java.io.Serializable;

public class Joueur implements Serializable {
	private final static String DEFAULT_NAME = "Invité";
	
	private int id;
	private String nom;
	private Equipe equipe;
	private Stats_Joueur statistiques;
	
	public Joueur() {
		id     = -1;
		nom    = DEFAULT_NAME;
		equipe = null;
		statistiques = new Stats_Joueur();
	}
	
	public boolean creerPartie(String adresseIp, int numeroPort) {
		return false;
	}
	
	public boolean rejoindrePartie(String adresseIp, int numeroPort) {
		Bataille_Client client = new Bataille_Client(this, adresseIp, numeroPort);
		return client.connect();
	}
	
	public String getNom() {
		return nom;
	}

	public Equipe getEquipe() {
		return equipe;
	}

	public Stats_Joueur getStatistiques() {
		return statistiques;
	}
}
