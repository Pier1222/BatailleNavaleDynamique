package model;

import java.io.IOException;
import java.io.Serializable;

public class Joueur implements Serializable {
	private final static String DEFAULT_NAME = "Invité";
	private final static String DEFAULT_IP = "localhost"; //Connecté sur son propre PC
	
	private int id;
	private String nom;
	private Equipe equipe;
	private Stats_Joueur statistiques;
	private Bataille_Client client;
	
	public Joueur() {
		this(DEFAULT_NAME);
	}
	
	public Joueur(String nom) {
		id           = -1;
		this.nom     = nom;
		equipe       = null;
		statistiques = new Stats_Joueur(); //Voir comment récupérer les stats dans ce cas
		client       = null;
	}
	
	//Constructeur par copie
	public Joueur(Joueur joueur) {
		id           = joueur.id;
		nom          = joueur.nom;
		equipe       = joueur.equipe;
		statistiques = joueur.statistiques;
		client       = joueur.client; //Pas sûr
	}
	
	public boolean creerPartie(int numeroPort) {
		try {
			Bataille_Server serveur = new Bataille_Server(numeroPort, this);
		    serveur.start();
		} catch (IOException e) {
			System.out.println("Problème demande connexion au serveur au port " + numeroPort + " : " + e.getMessage());
			e.printStackTrace();
			return false;
		}
		return rejoindrePartie(DEFAULT_IP, numeroPort);
	}
	
	/**
	 * Essaie de rejoindre une partie déjà créé
	 * @param adresseIp
	 * @param numeroPort
	 * @return Vrai si la connection a réussit, faux sinon
	 */
	public boolean rejoindrePartie(String adresseIp, int numeroPort) {
		try {
			client = new Bataille_Client(this, adresseIp, numeroPort);
		} catch (IOException e) {
			System.err.println("Erreur lors de la connexion à l'adressIP" + adresseIp + " au port " + numeroPort + ": \n " + e.getMessage());
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean isHote() {
		return id == Game.getID_HOTE();
	}
	
	/**
	 * Désactive la connexion du client avec le serveur
	 */
	public void quitterPartie() {
		client = null;
		id = -1;
	}
	
	public String getNom() {
		return nom;
	}

	public Equipe getEquipe() {
		return equipe;
	}
	
	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	public Stats_Joueur getStatistiques() {
		return statistiques;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Bataille_Client getClient() {
		return client;
	}
}
