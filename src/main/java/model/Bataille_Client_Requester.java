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
	
	private final static String PLAYERS_NAME_REQ = "Names";
	private final static String CREATE_TEAMS_REQ = "CreateTeams";
	private final static String INFOS_TEAMS_REQ  = "InfosTeams";
	
	private final static int SHOWING_ID     = 1;
	private final static int MOVING_ID      = 2;
	private final static int FIRE_ID        = 3;
	private final static int DISPLAY_ID     = 4;
	private final static int DESTROY_ID     = 5;
	
	private final static int PLAYERS_NAMES_ID = 6;
	private final static int CREATE_TEAMS_ID  = 7;
	private final static int INFOS_TEAMS_ID   = 8;
	
	
	private String ipServeur;
	private int portServeur;
	private Joueur joueurClient;
	private Socket commReq;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	
	private String[] tabStringActu; //Si on a besoin de récupérer la liste dde chaîne de caractères
	private String[][] tabDeTabDeStringActu;
	
	public Bataille_Client_Requester(Joueur joueur, String ipServeur, int portServeur) throws IOException {
		this.joueurClient= joueur;
		this.ipServeur   = ipServeur;
		this.portServeur = portServeur;
		
		tabStringActu        = null;
		tabDeTabDeStringActu = null;
		
		//Connexion
		commReq = new Socket(ipServeur, portServeur);
		//Création d'un Tread pour les messages ?
		
		
		//Créations d'autres Threads
	    oos = new ObjectOutputStream(commReq.getOutputStream());
	    oos.flush();
	    ois = new ObjectInputStream(commReq.getInputStream());
	}
	
	public void handshake() throws IOException {
		oos.writeObject(joueurClient);
		oos.flush();
		
		//Permet d'être sûr que le serveur a finit sa moulinette et de recopier le changement qu'il a fait sur l'ID du joueur
		joueurClient.setId(ois.readInt());
		if(joueurClient.getId() < 0) {
			commReq.close();
			System.out.println("Désolé, vous ne pouvez pas rejoindre la partie");
			return;
		}
		System.out.println("Mon id est " + joueurClient.getId());
    }
	
	public void getRequete(String requete) {
		if(requete == null) {
			System.err.println("Requête invalide");
			return;
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
		    } else if(titreRequete.equals(PLAYERS_NAME_REQ)) {
		    	requestPlayersName();
		    } else if(titreRequete.equals(CREATE_TEAMS_REQ)) {
		    	requestCreateTeams();
		    } else if(titreRequete.equals(INFOS_TEAMS_REQ)) {
		    	requestTeamsInfos();
		    }
		    if(needToShow)
			    getRequete(SHOWING_REQ); //
		} catch(IOException e) {
			System.err.println("Une erreur de type IOException s'est déroulée durant le traitement de la requête '" + requete + "'");
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			System.err.println("Une classe non connue a été révélée durant le traitement de la requête '" + requete + "'");
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
		 * - Si l'amiral d'un camp abandonne, il envoie à chaque camp un message Showing comportant l'information: 
		 *   => "Perdant" au camp qui abandonne
		 *   => "Gagnant à l'autre camp
		 * - Eventuellemennt, mémorise l'état du jeu
		 */
		oos.writeInt(SHOWING_ID);
		
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
		oos.flush();
	}
	
	private void requestFire() throws IOException {
		/*
		 * Envoie au client destinataire cible connu de Vector, le message Fire donnant la
		 * position du tir
		 */
		oos.writeInt(FIRE_ID);
		oos.flush();
	}
	
	private void requestDisplay() throws IOException {
		/*
		 * Envoie au client destinataire cible et à l'Amiral, tous les 2 connus de Vector, le
		 * message Displaying donnant le résultat du tir ("Fail" ou "Touch")
		 */
		oos.writeInt(DISPLAY_ID);
		oos.flush();
	}
	
	private void requestDestroy(Navire navireADetruire) throws IOException {
	    /*
	     * Envoie au client destinataire cible et à l'Amiral, tous les 2 connus de Vector, le
	     * message Destroyed donnant la position du navire à effacer	
	     */
		oos.writeInt(DESTROY_ID);
		oos.flush();
	}
	
	private void requestPlayersName() throws IOException, ClassNotFoundException {
		/*
		 * Requête que j'ai créé pour récupérer le nom des joueurs
		 */
		oos.writeInt(PLAYERS_NAMES_ID);
		oos.flush();
		tabStringActu = (String[]) ois.readObject();
	}
	
	private void requestCreateTeams() throws IOException {
		/*
		 * Requête que j'ai créé pour créer les équipes
		 */
		oos.writeInt(CREATE_TEAMS_ID);
		oos.flush();
		ois.readBoolean();
		//Réagir si la création d'équipe peut faire une erreur
	}
	
	private void requestTeamsInfos() throws IOException, ClassNotFoundException {
	  /*
	   * Requête que j'ai créé pour récupérer le noms des équipes ainsi que ceux des joueurs prrésents
	   */
		oos.writeInt(INFOS_TEAMS_ID);
		oos.flush();
		tabDeTabDeStringActu = (String[][]) ois.readObject();
	}

	public static String getShowingReq() {
		return SHOWING_REQ;
	}

	public static String getMovingReq() {
		return MOVING_REQ;
	}

	public static String getFireReq() {
		return FIRE_REQ;
	}

	public static String getDisplayReq() {
		return DISPLAY_REQ;
	}

	public static String getDestroyReq() {
		return DESTROY_REQ;
	}

	public static String getPlayersNameReq() {
		return PLAYERS_NAME_REQ;
	}
	
	public static String getCreateTeamsReq() {
		return CREATE_TEAMS_REQ;
	}

	public static String getInfosTeamsReq() {
		return INFOS_TEAMS_REQ;
	}

	public String[] getTabStringActu() {
		return tabStringActu;
	}

	public String[][] getTabDeTabDeStringActu() {
		return tabDeTabDeStringActu;
	}
}