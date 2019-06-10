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
	
    private Socket commReq;
	private Socket commInfo;
	private Game game;
	private int id;
	private ObjectInputStream ois; // used on commReq
	private ObjectOutputStream oos; // used on commReq
	private PrintStream ps; // used on commInfo
	
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
		} catch(IOException e) {
			System.err.println("Il y a eu une erreur pendant le traitement de la requête avec l'id " + requeteId);
			e.printStackTrace();
		}
	}
	
	//Voir la version "Bateille_Client_Requester pour les descriptions
	private void requestShowing() throws IOException {
		oos.writeObject(game);
		oos.flush();
	}
	
	private void requestMoving() throws IOException {
		oos.writeObject("A virer");
	}
	
	private void requestFire() throws IOException {
		oos.writeObject("A virer");
	}
	
	private void requestDisplay() throws IOException {
		oos.writeObject("A virer");
	}
	
	private void requestDestroy() throws IOException {
		oos.writeObject("A virer");
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
}
