package model;

import java.io.*;
import java.net.*;

public class Bataille_Server extends Thread {
    ServerSocket conn;
    Socket comm;
	int port = -1;
	Game map = null;

	private StreamPool sp;

	public Bataille_Server(int port, Joueur hebergeur) throws IOException {
	  conn = new ServerSocket(port);
	  map = new Game(hebergeur);
	  sp = new StreamPool();
	}
	
	public void run() {
		mainLoop();
	}

	private void mainLoop() {
	  while (true) {
	      try {
		      comm = conn.accept();
		      Bataille_Server_Requester serverThread = new Bataille_Server_Requester(comm, map, sp);
		      serverThread.start();
	        } catch(IOException e) {
		        System.out.println("Problème lors de la demande connexion au serveur : " + e.getMessage());
	        }
	    }
    }
}
