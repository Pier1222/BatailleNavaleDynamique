package model;

import java.io.IOException;

public class Bataille_Client {

	private Bataille_Client_Requester requester;
	Joueur joueur;
	String adresseIp;
	int numeroPort;
	
	public Bataille_Client(Joueur joueur, String adresseIp, int numeroPort) {
        this.joueur    = joueur;
        this.adresseIp = adresseIp;
        this.numeroPort = numeroPort;
	}
	
	public boolean connect() {
		try {
			requester = new Bataille_Client_Requester(joueur, adresseIp, numeroPort);
		} catch(IOException e) {
			System.err.println("Erreur lors de la connexion à l'adressIP" + adresseIp + " au port " + numeroPort + ": \n " + e.getMessage());
			return false;
		}
		return true;
	}
	
}
