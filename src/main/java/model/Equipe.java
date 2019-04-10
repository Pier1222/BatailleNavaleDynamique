package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Equipe implements Serializable {
	private static int NB_CUIRASSE    = 1;
	private static int NB_CROISEURS   = 2;
	private static int NB_TORPILLEURS = 3;
	private static int NB_SOUS_MARINS = 4;
	
	private String nom;
	private String couleur;
	
	private ArrayList<Matelot> matelots;
	private Amiral amiral;
	private Grille grille;
	private Navire[] navires;
	
	public Equipe(String nom, String couleur, Amiral amiral) {
		this.nom = nom;
		this.couleur = couleur;
		matelots = new ArrayList<Matelot>();
		this.amiral = amiral;
		grille = new Grille();
		initNavires();
	}
	
	private void initNavires() {
		navires = new Navire[NB_CUIRASSE + NB_CROISEURS + NB_TORPILLEURS + NB_SOUS_MARINS];
		int numeroNavireActu = 0;
		for(int i = 0; i < NB_CUIRASSE; i++) {
			navires[numeroNavireActu] = new Cuirasse(i+1);
			numeroNavireActu++;
		}
		for(int i = 0; i < NB_CROISEURS; i++) {
			navires[numeroNavireActu] = new Croiseur(i+1);
			numeroNavireActu++;
		}
		for(int i = 0; i < NB_TORPILLEURS; i++) {
			navires[numeroNavireActu] = new Torpilleur(i+1);
			numeroNavireActu++;
		}
		for(int i = 0; i < NB_SOUS_MARINS; i++) {
			navires[numeroNavireActu] = new Sous_Marin(i+1);
		}
	}

	public String getNom() {
		return nom;
	}

	public String getCouleur() {
		return couleur;
	}

	public Grille getGrille() {
		return grille;
	}

	public Navire[] getNavires() {
		return navires;
	}
}