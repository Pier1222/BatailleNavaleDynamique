package model;

import java.util.ArrayList;

public class Matelot extends Joueur {
	
	ArrayList<Navire> naviresControles;
	
	public Matelot(Joueur joueur) {
		super(joueur);
		naviresControles = new ArrayList<Navire>();
	}
	
	//Méthodes pour faire des actions avec le/les navire(s) controle(s)
	//Faire cela avec un index (numéro de navire dans la liste) ou en checkant à chaque fois que le navire donné est dans cette liste ?
	//Aussi, comment gérer les rôles ? Faire deux sous-classes ou utiliser un booléen et mettre toutes les méthodes (attaque/mouvement) ici ?
	
	/**
	 * Ajoute un navire à ceux que la matelot peut contrôler
	 * @param navire
	 */
	public void addNavire(Navire navire) {
		naviresControles.add(navire);
	}
	
	/**
	 * Retire un navire si le matelot le contrôle
	 * @param navire
	 */
	public void perdNavire(Navire navire) {
	    naviresControles.remove(navire);	
	}
}
