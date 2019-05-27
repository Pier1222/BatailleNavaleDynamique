package model;

import java.io.IOException;

public class Bataille_Client {

	private Bataille_Client_Requester requester;
	Joueur joueur;
	String adresseIp;
	int numeroPort;
	
	public Bataille_Client(Joueur joueur, String adresseIp, int numeroPort) throws IOException {
        this.joueur    = joueur;
        this.adresseIp = adresseIp;
        this.numeroPort = numeroPort;
        requester = new Bataille_Client_Requester(joueur, adresseIp, numeroPort);
	    requester.handshake();
	    //requester.requestLoop();
	}
	
	public void envoieRequete(String requete) {
		requester.getRequete(requete);
	}
	
	public String[] getRequesterTabString() {
		return requester.getTabStringActu();
	}
	
	public String[][] getRequesterTabDeTabDeString() {
	    return requester.getTabDeTabDeStringActu();	
	}
}
