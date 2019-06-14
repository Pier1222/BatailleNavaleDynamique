package model;

import java.io.Serializable;

public class Stats_Joueur implements Serializable {
	private static final int NB_TYPE_NAVIRE = 4;
	
	private int nbVictoires;
	private int nbDefaites;
	
	private int nbTirsTouches;
	private int nbTirsRates;
	
	private int nbFoisAmiral;
	private int nbRoleAttaque;
	private int nbRoleDefense;
	
	private int[] nbUtilisationsNavires; //4 cases, chaque case représente un type de navire 
	
	
	public Stats_Joueur() {
		nbVictoires   = 0;
		nbDefaites    = 0;
		
		nbTirsTouches = 0;
		nbTirsRates   = 0;
		
		nbFoisAmiral  = 0;
		nbRoleAttaque = 0;
		nbRoleDefense = 0;
		
		nbUtilisationsNavires = new int[NB_TYPE_NAVIRE];
		for(int i = 0; i < NB_TYPE_NAVIRE; i++) {
			nbUtilisationsNavires[i] = 0;
		}
		
		chargeStats();
	}
	
	public void saveStats() {
		
	}
	
	private void chargeStats() {
		
	}
	
	public void incrementeUtilisationsNavires(Navire navireUtilise) {
		int idAAugmenter = navireUtilise.getIdTypeNavire();
		if(idAAugmenter < 0 || idAAugmenter >= NB_TYPE_NAVIRE || nbUtilisationsNavires == null)
			return; //Type de navire non valide
		nbUtilisationsNavires[idAAugmenter]++;
	}
	
	public void incrementeVictoire() {
		nbVictoires++;
	}
	
	public void incrementeDefaite() {
		nbDefaites++;
	}
	
	public void incrementeNbTirsTouches() {
		nbTirsTouches++;
	}
	
	public void incrementeNbTirsRates() {
		nbTirsRates++;
	}
	
	public void incrementeNbFoisAmiral() {
		nbFoisAmiral++;
	}
	
	public void incrementeNbRoleAttaque() {
		nbRoleAttaque++;
	}
	
	public void incrementeNbRoleDefense() {
		nbRoleDefense++;
	}

	public int getNbVictoires() {
		return nbVictoires;
	}

	public int getNbDefaites() {
		return nbDefaites;
	}

	public int getNbTirsTouches() {
		return nbTirsTouches;
	}

	public int getNbTirsRates() {
		return nbTirsRates;
	}

	public int getNbFoisAmiral() {
		return nbFoisAmiral;
	}

	public int getNbRoleAttaque() {
		return nbRoleAttaque;
	}

	public int getNbRoleDefense() {
		return nbRoleDefense;
	}

	public int[] getNbUtilisationsNavire() {
		return nbUtilisationsNavires;
	}
}
