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
		changeIdJoueur();
		getListJoueur();
	}
	
	private void changeIdJoueur() {
		int idActu = 1;
		for(Joueur j: joueursEnAttentes) {
			j.setId(idActu);
			idActu++;
		}
	}
	
	public synchronized void getListJoueur() {
		System.out.println("Liste des joueurs de la partie:");
		for(Joueur j: joueursEnAttentes) {
			System.out.println(j.getId() + ": " + j.getNom());
		}
	}
	
	
}
