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
	
	private final static String PLAYERS_NAME_REQ   = "Names";
	private final static String CREATE_TEAMS_REQ   = "CreateTeams";
	private final static String INFOS_TEAMS_REQ    = "InfosTeams";
	private final static String DECHARGE_REQ       = "Decharge";
	private final static String REMOVE_REQ         = "Remove";
	private final static String ROTATE_REQ         = "Rotate";
	private final static String AFFECTE_ROLE_REQ   = "Affecte_role";
	private final static String AFFECTE_NAVIRE_REQ = "Affecte_navire";
	private final static String READY_REQ          = "Ready";
	
	private final static int SHOWING_ID     = 1;
	private final static int MOVING_ID      = 2;
	private final static int FIRE_ID        = 3;
	private final static int DISPLAY_ID     = 4;
	private final static int DESTROY_ID     = 5;
	
	private final static int PLAYERS_NAMES_ID  = 6;
	private final static int CREATE_TEAMS_ID   = 7;
	private final static int INFOS_TEAMS_ID    = 8;
	private final static int DECHARGE_ID       = 9;
	private final static int REMOVE_ID         = 10;
	private final static int ROTATE_ID         = 11;
	private final static int AFFECTE_ROLE_ID   = 12;
	private final static int AFFECTE_NAVIRE_ID = 13;
	private final static int READY_ID          = 14;
	
	
	private String ipServeur;
	private int portServeur;
	private Joueur joueurClient;
	private Socket commReq;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	
	private Game etatPartie; //Il s'agit d'un clone de la version serveur qui sera récupéré pour obtenir des informations
	
	private String[] tabStringActu; //Si on a besoin de récupérer la liste dde chaîne de caractères
	private String[][] tabDeTabDeStringActu;
	private String stringActu;
	private boolean booleenActu;
	
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
	    stringActu = "Aucun message à afficher...";
	    booleenActu = false;
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
			System.err.println("Requête null");
			return;
		}
		
		String[] requeteDecoupe = requete.split(" ");
		//String[] requeteDecoupe = requete.split(";");
		String titreRequete     = requeteDecoupe[0];
		
		boolean needToShow      = false; //Permet de savoir si on doit relancer une requête "Showing" juste après celle qu'on a exécuté
		
		try {
		    if(titreRequete.equals(SHOWING_REQ)) {
			    requestShowing();
		    } else if(titreRequete.equals(MOVING_REQ)) {
			    doMoving(requeteDecoupe);
			    needToShow = true;
		    } else if(titreRequete.equals(FIRE_REQ)) {
			    doFire(requeteDecoupe);
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
		    } else if(titreRequete.equals(DECHARGE_REQ)) {
		    	requestDecharge();
		    } else if(titreRequete.equals(REMOVE_REQ)) {
		    	doRemove(requeteDecoupe);
		    } else if(titreRequete.equals(ROTATE_REQ)) {
		    	doRotate(requeteDecoupe);
		    } else if(titreRequete.equals(AFFECTE_ROLE_REQ)) {
		    	doAffecteRole(requeteDecoupe);
		    } else if(titreRequete.equals(AFFECTE_NAVIRE_REQ)) {
		    	doAffecteNavire(requeteDecoupe);
		    } else if(titreRequete.equals(READY_REQ)) {
		    	requestReady();
		    }
		    if(needToShow)
			    getRequete(SHOWING_REQ);
		} catch(IOException e) {
			System.err.println("Une erreur de type IOException s'est déroulée durant le traitement de la requête '" + requete + "'");
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			System.err.println("Une classe non connue a été révélée durant le traitement de la requête '" + requete + "'");
			e.printStackTrace();
		} catch(NumberFormatException e) {
			System.err.println("Un des éléments de la '" + requete + "' n'est pas un entier alors qu'il devrait en être un");
			e.printStackTrace();
		}
	}
	
	private void requestShowing() throws IOException, ClassNotFoundException {
		/*
		 * - Envoie au client destinatre connu de Vector, le message Showing du nouvel état du jeu
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
		oos.flush();
		//Case recu = (Case) ois.readObject();
		etatPartie = (Game) ois.readObject();
		//System.out.println("Reçu: ");
		//etatPartie.getEquipeRouge().getGrille().printGrille(true);
		
		//Permet de récupérer les références que le serveur ne réussit pas à envoyer (parce que...)
		boolean[] orientationsRouges = (boolean[]) ois.readObject();
		boolean[] orientationsBleues = (boolean[]) ois.readObject();
		String[][] tetesRouges       = (String[][]) ois.readObject();
		String[][] tetesBleues       = (String[][]) ois.readObject();
		String[] rolesRouges         = (String[]) ois.readObject();
		String[] rolesBleu           = (String[]) ois.readObject();
		String[][] naviresRouges     = (String[][]) ois.readObject();
		String[][] naviresBleues     = (String[][]) ois.readObject();
		boolean pretRouge            = ois.readBoolean();
		boolean pretBleu             = ois.readBoolean();
		
		Equipe rouge = etatPartie.getEquipeRouge();
		Equipe bleu = etatPartie.getEquipeBleu();
		recreateOrientations(rouge, orientationsRouges);
		recreateOrientations(bleu, orientationsBleues);
		recreateGrille(rouge, tetesRouges);
		recreateGrille(bleu, tetesBleues);
		recreateAffectations(rouge, rolesRouges, naviresRouges);
		recreateAffectations(bleu, rolesBleu, naviresBleues);
		rouge.setEstPret(pretRouge);
		bleu.setEstPret(pretBleu);
		
		//etatPartie = new Game((Game) ois.readObject());
	}
	
	private void recreateOrientations(Equipe equipe, boolean[] orientations) {
		Navire[] navires = equipe.getNavires();
		for(int i = 0; i < navires.length; i++) {
			navires[i].setEstHorizontal(orientations[i]);
		}
	}
	
	/*Méthode créée pour détourner l'impossibilité pour le serveur d'envoyer
	 les pièces posés sur les cases (pour une raison inconnue) */
	private void recreateGrille(Equipe equipe, String[][] tetesPoses) {
		Navire[] navires = equipe.getNavires();
		Grille grille = equipe.getGrille();
		
		for(int x = 0; x < Grille.getLines(); x++) {
		    for(int y = 0; y < Grille.getColumns(); y++) {
		    	//Je ne sais pas pourquoi non plus, mais même quand des pièces ont étés retirés, il ne le prend pas en compte...
		    	grille.getCases()[x][y].setPiecePose(null);
		    }
		}
		
		int positionNavireActu = -1;
		for(int x = 0; x < Grille.getLines(); x++) {
		    for(int y = 0; y < Grille.getColumns(); y++) {
                positionNavireActu = getNavireAvecNom(navires, tetesPoses[x][y]);
                if(positionNavireActu >= 0)
                	navires[positionNavireActu].changePositionPieces(grille, x, y);
		    }
		}
	}
	
	private int getNavireAvecNom(Navire[] navires, String nomNavire) {
		for(int i = 0; i < navires.length; i++) {
	    	if(navires[i].getNom().equals(nomNavire))
	    		return i;
		}
		return -1;
	}
	
	private void recreateAffectations(Equipe equipe, String[] roles, String[][] affectations) {
		Matelot matelotActu = null;
		String roleActu     = null;
		String[] tabActu    = null;
		Navire navireActu   = null;
		for(int i = 0; i < equipe.getNBMatelots(); i++) {
			matelotActu = equipe.getAMatelotDansListe(i);
			roleActu = roles[i];
			matelotActu.perdTousLesNavires();
			
			//Récupération du rôle
			if(roleActu.equals(Matelot.getRoleAttaquant()))
				matelotActu.changeRole(true);
			else if(roleActu.equals(Matelot.getRoleDefenseur()))
				matelotActu.changeRole(false);
			
			//Récupération des navires
			tabActu = affectations[i];
			for(int y = 0; y < tabActu.length; y++) {
				navireActu = etatPartie.getNavire(equipe, tabActu[y]);
				if(navireActu != null)
					matelotActu.addNavire(navireActu);
			}
		}
	}
	
	private void doMoving(String[] requeteDecoupe) throws IOException, NumberFormatException, ClassNotFoundException {
		if(requeteDecoupe.length < 4) {
			stringActu = ("Imposibble de faire le déplacement, la requête est de longueur " + requeteDecoupe.length);
			return;
		}
		
		String nomNavire = requeteDecoupe[1];
		int positionXTete = Integer.parseInt(requeteDecoupe[2]);
		int positionYTete = Integer.parseInt(requeteDecoupe[3]);
		requestMoving(nomNavire, positionXTete, positionYTete);
	}
	
	private void requestMoving(String nomNavire, int positionXTete, int positionYTete) throws IOException, ClassNotFoundException {
		/*
		 * Envoie au client destinataire cible connu de Vector, le message Moving donnant
		 * la position du nouveau navire (éventuellement, sa position de départ en cas de
		 * mouvement)
		 */
		oos.writeInt(MOVING_ID);
		oos.writeInt(joueurClient.getId());
		oos.writeObject(nomNavire);
		oos.writeInt(positionXTete);
		oos.writeInt(positionYTete);
		oos.flush();
		stringActu = (String) ois.readObject();
	}
	
	private void doFire(String[] requeteDecoupe) {
		if(requeteDecoupe.length < 4) {
			return;
		}
	}
	
	private void requestFire(String nomNavire, int positionXCible, int positionYCible) throws IOException {
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
	
	private void requestDestroy(String nomNavireADetruire) throws IOException {
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
		 * Requête que j'ai créée pour créer les équipes
		 */
		oos.writeInt(CREATE_TEAMS_ID);
		oos.flush();
		ois.readBoolean();
		//Réagir si la création d'équipe peut faire une erreur
	}
	
	private void requestTeamsInfos() throws IOException, ClassNotFoundException {
	  /*
	   * Requête que j'ai créée pour récupérer le noms des équipes ainsi que ceux des joueurs prrésents
	   */
		oos.writeInt(INFOS_TEAMS_ID);
		oos.flush();
		tabDeTabDeStringActu = (String[][]) ois.readObject();
	}
	
	private void requestDecharge() throws IOException {
		/*
		 * Requête que j'ai créée pour que l'hôte envoie un signal pour décrementer la valeur de
		 * rechargement de tir des navires de 1
		 */
		oos.writeInt(DECHARGE_ID);
		oos.flush();
		ois.readBoolean();
	}
	
	private void doRemove(String[] requeteDecoupe) throws IOException {
		if(requeteDecoupe.length < 2) {
			stringActu = ("Imposibble de retirer un navire, la requête est de longueur " + requeteDecoupe.length);
			return;
		}
		
		String nomNavire = requeteDecoupe[1];
		requestRemove(nomNavire);
	}
	
	private void requestRemove(String nomNavire) throws IOException {
		/*
		 * Requête que j'ai créée pour retirer un navire
		 */
		oos.writeInt(REMOVE_ID);
		oos.writeInt(joueurClient.getId());
		oos.writeObject(nomNavire);
		oos.flush();
		booleenActu = ois.readBoolean();
	}
	
	private void doRotate(String[] requeteDecoupe) throws IOException {
		if(requeteDecoupe.length < 2) {
			stringActu = ("Imposibble de tourner un navire, la requête est de longueur " + requeteDecoupe.length);
			return;
		}
		
		String nomNavire = requeteDecoupe[1];
		requestRotate(nomNavire);
	}
	
	private void requestRotate(String nomNavire) throws IOException {
		/*
		 * Requête que j'ai créée pour tourner un Navire
		 */
		oos.writeInt(ROTATE_ID);
		oos.writeInt(joueurClient.getId());
		oos.writeObject(nomNavire);
		oos.flush();
		booleenActu = ois.readBoolean();
	}
	
	private void doAffecteRole(String[] requeteDecoupe) throws NumberFormatException, IOException {
		if(requeteDecoupe.length < 3) {
			stringActu = ("Imposible d'affecter un rôle, la requête est de longueur " + requeteDecoupe.length);
			return;
		}
		
		int idMatelot = Integer.parseInt(requeteDecoupe[1]);
		String stringRole = requeteDecoupe[2];
		//Par défaut, on assume qu'il souhaite le mettre en défenseur
		boolean estAttaquant = stringRole.equals(Matelot.getRoleAttaquant());
		requestAffecteRole(idMatelot, estAttaquant);
	}
	
	private void requestAffecteRole(int idMatelot, boolean estAttaquant) throws IOException {
		/*
		 * Requête que j'ai créée pour donner un rôle à un Matelot
		 */
		oos.writeInt(AFFECTE_ROLE_ID);
		oos.writeInt(joueurClient.getId());
		oos.writeInt(idMatelot);
		oos.writeBoolean(estAttaquant);
		oos.flush();
		booleenActu = ois.readBoolean();
	}
	
	private void doAffecteNavire(String[] requeteDecoupe) throws NumberFormatException, IOException {
		if(requeteDecoupe.length < 3) {
			stringActu = ("Imposibble d'affecter un navire, la requête est de longueur " + requeteDecoupe.length);
			return;
		}
		
		int idMatelot = Integer.parseInt(requeteDecoupe[1]);
		String nomNavire = requeteDecoupe[2];
		requestAffecteNavire(idMatelot, nomNavire);
	}
	
	private void requestAffecteNavire(int idMatelot, String nomNavire) throws IOException {
		/*
		 * Requête que j'ai créée pour affecter un navire à un Matelot
		 * (ou le retirer si il possède déjà le navire)
		 */
		oos.writeInt(AFFECTE_NAVIRE_ID);
		oos.writeInt(joueurClient.getId());
		oos.writeInt(idMatelot);
		oos.writeObject(nomNavire);
		oos.flush();
		booleenActu = ois.readBoolean();
	}
	
	private void requestReady() throws IOException {
		oos.writeInt(READY_ID);
		oos.writeInt(joueurClient.getId());
		oos.flush();
		booleenActu = ois.readBoolean();
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

	public static String getDechargeReq() {
		return DECHARGE_REQ;
	}

	public static String getRemoveReq() {
		return REMOVE_REQ;
	}

	public static String getRotateReq() {
		return ROTATE_REQ;
	}

	public static String getAffecteRoleReq() {
		return AFFECTE_ROLE_REQ;
	}

	public static String getAffecteNavireReq() {
		return AFFECTE_NAVIRE_REQ;
	}

	public static String getReadyReq() {
		return READY_REQ;
	}

	public String[] getTabStringActu() {
		return tabStringActu;
	}

	public String[][] getTabDeTabDeStringActu() {
		return tabDeTabDeStringActu;
	}

	public String getStringActu() {
		return stringActu;
	}

	public boolean isBooleenActu() {
		return booleenActu;
	}

	public Game getEtatPartie() {
		return etatPartie;
	}
}