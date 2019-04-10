package model;

import java.util.ArrayList;

public class Game {

	private Equipe equipeRouge;
	private Equipe equipeBleu;
	private Joueur createur;
	private ArrayList<Joueur> joueursEnAttentes;
	
	public Game(Joueur createur) {
		equipeRouge = null;
		equipeBleu  = null;
		this.createur = createur;
		joueursEnAttentes = new ArrayList<Joueur>();
		ajouteJoueur(createur);
	}
	
	public synchronized void ajouteJoueur(Joueur joueur) {
		joueursEnAttentes.add(joueur);
	}
	
	
	
	
}
