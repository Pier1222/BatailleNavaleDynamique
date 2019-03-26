package model;

import java.util.ArrayList;

public class Equipe {
	private static int NB_CUIRASSE    = 1;
	private static int NB_CROISEURS   = 2;
	private static int NB_TORPILLEURS = 3;
	private static int NB_SOUS_MARINS = 4;
	
	private String nom;
	private String couleur;
	
	private ArrayList<Matelot> matelots;
	private Amiral amiral;
	private Navire[] navires;
	
	public Equipe(String nom, String couleur, Amiral amiral) {
		this.nom = nom;
		this.couleur = couleur;
		matelots = new ArrayList<Matelot>();
		this.amiral = amiral;
		initNavires();
	}
	
	private void initNavires() {
		navires = new Navire[NB_CUIRASSE + NB_CROISEURS + NB_TORPILLEURS + NB_SOUS_MARINS];
		//Initialiser les navires avec les boucles
	}

}
