package model;

import java.io.*;
import java.net.*;

public class Bataille_Server extends Thread {
    ServerSocket conn;
    Socket comm;
	int port = -1;
	Game game = null;

	private StreamPool sp;

	public Bataille_Server(int port, Joueur hebergeur) throws IOException {
	  conn = new ServerSocket(port);
	  game = new Game(hebergeur);
	  sp = new StreamPool();
	}
	
	public void run() {
		mainLoop();
	}

	private void mainLoop() {
	  while (true) {
	      try {
		      comm = conn.accept();
		      Bataille_Server_Requester serverThread = new Bataille_Server_Requester(comm, game, sp);
		      serverThread.start();
	        } catch(IOException e) {
		        System.out.println("Problème lors de la demande connexion au serveur : " + e.getMessage());
	        }
	    }
    }
}