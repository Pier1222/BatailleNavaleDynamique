package model;

import java.io.*;
import java.net.*;

public class Bataille_Server_Requester extends Thread {
	
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
	        System.out.println("TU NE CONNAIS PAS LES CHAINES DE CARACTERES ? TU TE FICHE DE MOI ?");
	      }

	      game.ajouteJoueur(joueurClient);
	      
	      //Permet de dire que tout s'est bien déroulé
	      oos.writeInt(joueurClient.getId());

	      oos.flush();

	      /*while(true) {
	        int requeteId = ois.readInt();
	        if(requeteId == 1)
	          requestMoveOf();
	        else if(requeteId == 2)
	          requestNeighbors();
	        else if(requeteId == 3)
	          requestMessage();
	      }*/

	    }
	    catch(IOException e) {
	      System.out.println("Problème communication dans le thread "+id);

	      StackTraceElement[] s = e.getStackTrace();
	      for(int i = 0; i < s.length; i++) {
	        System.out.println(s[i].toString());
	      }

	    }
	  }

}
