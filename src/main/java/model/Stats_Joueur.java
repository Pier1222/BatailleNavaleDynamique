package model;

import java.net.URLDecoder;
import java.io.*;

public class Stats_Joueur implements Serializable {
	private static final String[] NOM_NAVIRES = new String[] {"Cuirassé", "Torpilleur", "Croiseur", "Sous-Marin"};
	private static final int NB_TYPE_NAVIRE      = NOM_NAVIRES.length;
	//Il s'agit du chemin permettant d'accéder au fichier .class de cet Objet
	private static final String PATH = Stats_Joueur.class.getProtectionDomain().getCodeSource().getLocation().getPath();
	private static final String NOM_DOSSIER_SAVE = "save";
	private static final String FIN_NOM_FICHIER  = "_stat.save";
	
	private String nomFichierSauvegarde;
	
	private int nbVictoires;
	private int nbDefaites;
	
	private int nbTirsTouches;
	private int nbTirsRates;
	
	private int nbFoisAmiral;
	private int nbRoleAttaque;
	private int nbRoleDefense;
	
	private int[] nbUtilisationsNavires; //4 cases, chaque case représente un type de navire
	
	
	public Stats_Joueur(String nomReference) {
		nomFichierSauvegarde = transformPath() + "/" + nomReference + FIN_NOM_FICHIER;
		
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
	    ObjectOutputStream oos = null;
        try {
			oos = new ObjectOutputStream(new FileOutputStream(nomFichierSauvegarde));
			oos.writeInt(nbVictoires);
			oos.writeInt(nbDefaites);
			
			oos.writeInt(nbTirsTouches);
			oos.writeInt(nbTirsRates);
			
			oos.writeInt(nbFoisAmiral);
			oos.writeInt(nbRoleAttaque);
			oos.writeInt(nbRoleDefense);
		} catch (FileNotFoundException e) {
			System.err.println("Le fichier " + nomFichierSauvegarde + " est introuvable pour la sauvegarde");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Il y a eu une erreur de type IOException pendant la sauvegarde dans le fichier " + nomFichierSauvegarde);
			e.printStackTrace();
		}
	}
	
	private void chargeStats() {
        ObjectInputStream ois = null;
        try {
			ois = new ObjectInputStream(new FileInputStream(nomFichierSauvegarde));
			nbVictoires   = ois.readInt();
			nbDefaites    = ois.readInt();
			
			nbTirsTouches = ois.readInt();
			nbTirsRates   = ois.readInt();
			
			nbFoisAmiral  = ois.readInt();
			nbRoleAttaque = ois.readInt();
			nbRoleDefense = ois.readInt();
        } catch (FileNotFoundException e) {
			System.err.println("Le fichier " + nomFichierSauvegarde + " est introuvable pour le chargement");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Il y a eu une erreur de type IOException pendant le chargement dans le fichier " + nomFichierSauvegarde);
			e.printStackTrace();
		}
	}
	
    /**
     * Permet de transformer le chemin PATH en chemin absolue menant au dossier des sauvegardes
     * @return Le chemin correspondant ou "" en cas d'erreur
     */
    private static String transformPath() {
        String toReturn = null;
        
        //Redirige le chemin vers le dossier des sauvegades (qui doit être situé plus haut dans l'arborescence)
        try {
            toReturn = URLDecoder.decode(PATH, "UTF-8");
            String dernierCharacter = toReturn.charAt(toReturn.length()-1) + "";
            if(!dernierCharacter.equals("/") && !dernierCharacter.equals("\\")) {
                toReturn += "/"; //Rajoute un / puisque si le fichier est un jar il ne le fait pas mais si c'est un dossier, c'est fait automatiquement
            }
            toReturn += ".." + "/" + NOM_DOSSIER_SAVE; //Permet de remonter d'un cran dans l'arborescence
        } catch (UnsupportedEncodingException e) {
            System.err.println("Problème avec le décodage de '" + PATH + "'");
            return "";
        }
        
        //Permet de se débarrasser du ".." qui n'est pas reconnu par toutes les méthodes exploitant des chemins
        try {
            File f = new File(toReturn);
            toReturn = f.getCanonicalPath();
        } catch (IOException e) {
            System.err.println("Erreur lors de la canonacalisation de " + toReturn);
            return "";
        }
        
        System.out.println("Chemin menant aux sauvegardes: " + toReturn);
        return toReturn;
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

	public static String[] getNomNavires() {
		return NOM_NAVIRES;
	}
}
