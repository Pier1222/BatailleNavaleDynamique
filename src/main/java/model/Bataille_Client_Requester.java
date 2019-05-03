package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Bataille_Client_Requester {

	private final static String SHOWING_REQ = "Showing";
	private final static String MOVING_REQ  = "Moving";
	private final static String FIRE_REQ    = "Fire";
	private final static String DISPLAY_REQ = "Displaying";
	private final static String DESTROY_REQ = "Destroyed";
	
	private final static int SHOWING_ID     = 1;
	private final static int MOVING_ID      = 2;
	private final static int FIRE_ID        = 3;
	private final static int DISPLAY_ID     = 4;
	private final static int DESTROY_ID     = 5;
	
	
	private String ipServeur;
	private int portServeur;
	private Joueur joueur;
	private Socket commReq;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	
	public Bataille_Client_Requester(Joueur joueur, String ipServeur, int portServeur) throws IOException {
		this.joueur      = joueur;
		this.ipServeur   = ipServeur;
		this.portServeur = portServeur;
		
		//Connexion
		commReq = new Socket(ipServeur, portServeur);
		
		//Création d'un Tread pour les messages ?
		
		
		//Créations d'autres Threads
	    oos = new ObjectOutputStream(commReq.getOutputStream());
	    oos.flush();
	    ois = new ObjectInputStream(commReq.getInputStream());
	}
	
	public void handshake() throws IOException {
		oos.writeObject(joueur);
		oos.flush();
		
		//Permet d'être sûr que le serveur a finit sa moulinette et de recopier le changement qu'il a fait sur l'ID du joueur
		joueur.setId(ois.readInt());
		System.out.println("Mon id est " + joueur.getId());
    }
	
	public void requestLoop() throws IOException {
		
	}
	
	private void getRequete(String requete) {
		if(requete == null) {
			System.err.println("Requête invalide");
		}
		
		String[] requeteDecoupe = requete.split(" ");
		String titreRequete     = requeteDecoupe[0];
		
		boolean needToShow      = false; //Permet de savoir si on doit relancer une requête "Showing" juste après celle qu'on a exécuté
		
		try {
		    if(titreRequete.equals(SHOWING_REQ)) {
			    requestShowing();
		    } else if(titreRequete.equals(MOVING_REQ)) {
			    doMoving(requeteDecoupe);
			    needToShow = true;
		    } else if(titreRequete.equals(FIRE_REQ)) {
			    requestFire();
			    needToShow = true;
		    } else if(titreRequete.equals(DISPLAY_REQ)) {
			    requestDisplay();
			    needToShow = true;
		    } else if(titreRequete.equals(DESTROY_REQ)) {
			    //requestDestroy();
			    needToShow = true;
		    }
		    if(needToShow)
			    getRequete(SHOWING_REQ); //
		} catch(IOException e) {
			System.err.println("Une erreur s'est déroulé durant le traitement d'une requête");
			e.printStackTrace();
		}
	}
	
	private void requestShowing() throws IOException {
		/*
		 * - Envoie au client destnaitre connu de Vector, le message Showing du nouvel état du jeu
		 * - Compte le nombre de navires dans chaque camp
		 * - Si le nombre de navires d'un camp tombe à zéro navire, il envoie à chaque camp un message Showing comportant l'information:
		 *   => "Perdant" au camp qui est à zéro
		 *   => "Gagnant" à l'autre camp
		 * - Si l'amiral d'un camp abandonne, il envoie à cahque camp un message Showing comportant l'information: 
		 *   => "Perdant" au camp qui abandonne
		 *   => "Gagnant à l'autre camp
		 * - Eventuellemennt, mémorise l'état du jeu
		 */
		oos.writeObject("Showing");
		
	}
	
	private void doMoving(String[] requeteDecoupe) {
		if(requeteDecoupe.length < 4) {
			return;
		}
		
		
		
		//requestMoving();
	}
	
	private void requestMoving(Navire navire, int positionXTete, int positionYTete) throws IOException {
		/*
		 * Envoie au client destinataire cible connu de Vector, le message Moving donnant
		 * la position du nouveau navire (évntuellement, sa position de départ en cas de
		 * mouvement)
		 */
		oos.writeInt(MOVING_ID);
	}
	
	private void requestFire() throws IOException {
		/*
		 * Envoie au client destinataire cible connu de Vector, le message Fire donnant la
		 * position du tir
		 */
		oos.writeInt(FIRE_ID);
	}
	
	private void requestDisplay() throws IOException {
		/*
		 * Envoie au client destinataire cible et à l'Amiral, tous les 2 connus de Vector, le
		 * message Displaying donnant le résultat du tir ("Fail" ou "Touch")
		 */
		oos.writeInt(DISPLAY_ID);
	}
	
	private void requestDestroy(Navire navireADetruire) throws IOException {
	    /*
	     * Envoie au client destinataire cible et à l'Amiral, tous les 2 connus de Vector, le
	     * message Destroyed donnant la position du navire à effacer	
	     */
		oos.writeInt(DESTROY_ID);
	}
}
