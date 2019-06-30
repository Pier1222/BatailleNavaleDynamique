package model;

import java.io.*;
import java.net.*;

public class Bataille_Server_Requester extends Thread {
	
	private final static int SHOWING_ID     = 1;
	private final static int MOVING_ID      = 2;
	private final static int FIRE_ID        = 3;
	private final static int DISPLAY_ID     = 4;
	private final static int DESTROY_ID     = 5;
	
	private final static int PLAYERS_NAMES_ID = 6;
	private final static int CREATE_TEAMS_ID  = 7;
	private final static int INFOS_TEAMS_ID   = 8;
	private final static int DECHARGE_ID      = 9;
	private final static int REMOVE_ID         = 10;
	private final static int ROTATE_ID         = 11;
	private final static int AFFECTE_ROLE_ID   = 12;
	private final static int AFFECTE_NAVIRE_ID = 13;
	private final static int READY_ID          = 14;
	
    private Socket commReq;
	private Socket commInfo;
	private Game game;
	private int id;
	private ObjectInputStream ois; // utilisé avec commReq
	private ObjectOutputStream oos; // utilisé avec commReq
	private PrintStream ps; // utilisé avec commInfo (si on a le temps pour mettre un tchat)
	
	private StreamPool sp;
	
	public Bataille_Server_Requester(Socket commReq, Game game, StreamPool sp) {
		this.commReq = commReq;
		this.game = game;

	    this.sp = sp;
    }
	
	public void run() {
	    try {
	      ois = new ObjectInputStream(commReq.getInputStream());
	      oos = new ObjectOutputStream(commReq.getOutputStream());
	      oos.flush();
	      
	      commInfo = new Socket(commReq.getInetAddress(), commReq.getPort()+1);
	      
	      //Permet la communication
	      ps = new PrintStream(commInfo.getOutputStream());
	      sp.addStream(this, ps);

          //Première interaction avec le client
	      Joueur joueurClient = null;

	      try {
	        joueurClient = (Joueur) ois.readObject();
	      } catch (ClassNotFoundException e) {
	        System.out.println("Attends... Tu ne connais pas les chaînes de caractères ?");
	      }

	      game.ajouteJoueur(joueurClient);
	      int idClient = joueurClient.getId();
	      
	      //Permet de dire que tout s'est bien déroulé
	      oos.writeInt(idClient);
	      oos.flush();
	      
	      if(idClient < 0) {
	    	  commInfo.close();
	    	  return;
	      }

	      while(true) {
	        int requeteId = ois.readInt();
	        traiteRequete(requeteId);
	      }

	    }
	    catch(IOException e) {
	      System.out.println("Problème communication dans le thread "+id);

	      StackTraceElement[] s = e.getStackTrace();
	      for(int i = 0; i < s.length; i++) {
	        System.out.println(s[i].toString());
	      }

	    }
	}
	
	private void traiteRequete(int requeteId) {
		try {
	        if(requeteId == SHOWING_ID)
	        	requestShowing();
	        else if(requeteId == MOVING_ID)
	        	requestMoving();
	        else if(requeteId == FIRE_ID)
	        	requestFire();
	        else if(requeteId == DISPLAY_ID)
	        	requestDisplay();
	        else if(requeteId == DESTROY_ID)
	            requestDestroy();
	        else if(requeteId == PLAYERS_NAMES_ID)
	        	requestPlayersName();
	        else if(requeteId == CREATE_TEAMS_ID)
	        	requestCreateTeams();
	        else if(requeteId == INFOS_TEAMS_ID)
	        	requestTeamsInfos();
	        else if(requeteId == DECHARGE_ID)
	        	requestDecharge();
	        else if(requeteId == REMOVE_ID)
	        	requestRemove();
	        else if(requeteId == ROTATE_ID)
	        	requestRotate();
	        else if(requeteId == AFFECTE_ROLE_ID)
	        	requestAffecteRole();
	        else if(requeteId == AFFECTE_NAVIRE_ID)
	        	requestAffecteNavire();
	        else if(requeteId == READY_ID)
	        	requestReady();
		} catch(IOException e) {
			System.err.println("Il y a eu une erreur pendant le traitement de la requête avec l'id " + requeteId);
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.err.println("Il y a une erreur de type ClassNotFoundException pendant le traitement de la requête avec l'id " + requeteId);
			e.printStackTrace();
		}
	}
	
	//Voir la version "Bataille_Client_Requester pour les descriptions
	private void requestShowing() throws IOException {
		//Case envoye = game.getEquipeRouge().getGrille().getCases()[5][5];
		//oos.writeObject(envoye);
		
		oos.writeObject(game);
		//System.out.println("Envoyé:");
		//game.getEquipeRouge().getGrille().printGrille(true);
		
		
		Equipe rouge = game.getEquipeRouge();
		Equipe bleu  = game.getEquipeBleu();
		//Permet d'envoyer les orientations
		oos.writeObject(rouge.getOrientationNavires());
		oos.writeObject(bleu.getOrientationNavires());
		
		//Permet d'envoyer la référence aux pièces
		oos.writeObject(rouge.getGrille().getPiecesPosesTab());
		oos.writeObject(bleu.getGrille().getPiecesPosesTab());
		
		//Permet d'envoyer les rôles des matelots (car même ça, il ne réussit pas à envoyer correctement)
		oos.writeObject(rouge.getRoleMatelots());
		oos.writeObject(bleu.getRoleMatelots());
		
		//En fait, je me demande ce qu'il envoie correctement au final...
		oos.writeObject(rouge.getNaviresMatelots());
		oos.writeObject(bleu.getNaviresMatelots());
		
		oos.writeBoolean(rouge.isEstPret());
		oos.writeBoolean(bleu.isEstPret());
		oos.flush();
	}
	
	//Se charge du placement ET déplacement du navire (en fonction du type de joueur ayant créé la requête)
	private void requestMoving() throws IOException, ClassNotFoundException {
		int idJoueur      = ois.readInt();
		String nomNavire  = (String) ois.readObject();
		int positionX = ois.readInt();
		int positionY = ois.readInt();
		
		Matelot matelot = game.getMatelot(idJoueur);
		Amiral amiral   = game.getAmiral(idJoueur);
		if(matelot == null && amiral == null) {
			oos.writeObject("Aucun amiral ni matelot trouvé avec l'ID " + idJoueur);
			oos.flush();
			return;
		}
		
		Navire navire = null;
		if(matelot != null) {
		    navire = game.getNavire(matelot.getEquipe(), nomNavire);
		    if(navire == null) {
			    oos.writeObject("Navire '" + nomNavire + "' du matelot '" + matelot.getNom() + "' Non trouvé !");
			    oos.flush();
			    return;
		    }
		
		    //Pour le matelot, on essaie de deviner où l'utilisateur a voulu placer la tête
		    int[] nouvellePositionTete = navire.getCasePourDeplacementTete(positionX, positionY);
		    int positionXTete = nouvellePositionTete[0];
		    int positionYTete = nouvellePositionTete[1];
		    matelot.setNavireSelectionne(navire);
		    matelot.deplaceNavire(positionXTete, positionYTete);
		    oos.writeObject("Requête effectuée (matelot) [" + positionX + ", " + positionY + "] => [" + positionXTete + ", " + positionYTete + "]");
	    } else {
		    navire = game.getNavire(amiral.getEquipe(), nomNavire);
		    if(navire == null) {
		    	oos.writeObject("Navire '" + nomNavire + "' de l'amiral '" + amiral.getNom() + "' Non trouvé !");
			    oos.flush();
			    return;
		    }
		
		    amiral.setNavireSelectionne(navire);
		    amiral.placeNavire(positionX, positionY);
		    oos.writeObject("Requête effectuée (amiral)");
	    }
		oos.flush();
	}
	
	private void requestFire() throws IOException {
		oos.writeObject("A supprimer");
	}
	
	private void requestDisplay() throws IOException {
		oos.writeObject("A supprimer");
	}
	
	private void requestDestroy() throws IOException {
		oos.writeObject("A supprimer");
	}
	
	private void requestPlayersName() throws IOException {
		String[] nomsObtenus = game.getNomsJoueur();
		oos.writeObject(nomsObtenus);
		oos.flush();
	}
	
	private void requestCreateTeams() throws IOException {
		boolean reponseObtenu = game.createTeams();
		oos.writeBoolean(reponseObtenu);
		oos.flush();
	}
	
	private void requestTeamsInfos() throws IOException {
		String[][] idEtNomsEquipes = game.getNomsEquipesEtJoueurs();
		oos.writeObject(idEtNomsEquipes);
		oos.flush();
		//sp.broadcast("OK", false, this);
	}
	
    private void requestDecharge() throws IOException {
    	game.decrementeRechargementTirs();
    	oos.writeBoolean(true);
    	oos.flush();
    }
    
    private void requestRemove() throws IOException, ClassNotFoundException {
    	int idAmiral     = ois.readInt();
    	String nomNavire = (String) ois.readObject();
    	Amiral amiral = game.getAmiral(idAmiral);
    	if(amiral == null) {
    		oos.writeBoolean(false);
    		oos.flush();
    		return;
    	}
    	Navire navireToRemove = game.getNavire(amiral.getEquipe(), nomNavire);
    	if(navireToRemove == null) {
    		oos.writeBoolean(false);
    		oos.flush();
    		return;
    	}
    	amiral.setNavireSelectionne(navireToRemove);
    	amiral.retireNavire();
    	oos.writeBoolean(true);
    	oos.flush();
    }
    
    private void requestRotate() throws IOException, ClassNotFoundException {
    	int idAmiral     = ois.readInt();
    	String nomNavire = (String) ois.readObject();
    	Amiral amiral = game.getAmiral(idAmiral);
    	if(amiral == null) {
    		oos.writeBoolean(false);
    		oos.flush();
    		return;
    	}
    	Navire navireToRemove = game.getNavire(amiral.getEquipe(), nomNavire);
    	if(navireToRemove == null) {
    		oos.writeBoolean(false);
    		oos.flush();
    		return;
    	}
    	amiral.setNavireSelectionne(navireToRemove);
    	amiral.tourneNavire();
    	oos.writeBoolean(true);
    	oos.flush();
    }
    
    private void requestAffecteRole() throws IOException {
    	int idAmiral         = ois.readInt();
    	int idMatelot        = ois.readInt();
    	boolean estAttaquant = ois.readBoolean();
    	Amiral amiral        = game.getAmiral(idAmiral);
    	Matelot matelot      = game.getMatelot(idMatelot);
    	if(amiral == null || matelot == null) {
    		oos.writeBoolean(false);
    		oos.flush();
    		return;
    	}
    	amiral.setMatelotSelectionne(matelot);
    	amiral.affecteRole(estAttaquant);
    	oos.writeBoolean(true);
    	oos.flush();
    }
    
    private void requestAffecteNavire() throws IOException, ClassNotFoundException {
    	int idAmiral     = ois.readInt();
    	int idMatelot    = ois.readInt();
    	String nomNavire = (String) ois.readObject();
    	Amiral amiral    = game.getAmiral(idAmiral);
    	Matelot matelot  = game.getMatelot(idMatelot);
    	if(amiral == null || matelot == null) {
    		oos.writeBoolean(false);
    		oos.flush();
    		return;
    	}
    	Navire navireToAffect = game.getNavire(amiral.getEquipe(), nomNavire);
    	if(navireToAffect == null) {
    		oos.writeBoolean(false);
    		oos.flush();
    		return;
    	}
    	amiral.setNavireSelectionne(navireToAffect);
    	
    	//Permet d'éviter de recréer une requête juste pour la désaffectation
    	amiral.setMatelotSelectionne(matelot);
    	if(!matelot.possedeNavire(navireToAffect))
    	    amiral.affecteNavire();
    	else
    		amiral.desaffecteNavire();
    	oos.writeBoolean(true);
    	oos.flush();
    }
    
    private void requestReady() throws IOException {
    	int idAmiral = ois.readInt();
    	Amiral amiral = game.getAmiral(idAmiral);
    	if(amiral == null) {
    		oos.writeBoolean(false);
    		oos.flush();
    		return;
    	}
    	//Pour un coup que c'est une méthode qu'on a déjà conçu avec un booléen en retour
    	oos.writeBoolean(amiral.getEquipe().setEquipeToPret());
    	oos.flush();
    }
}
