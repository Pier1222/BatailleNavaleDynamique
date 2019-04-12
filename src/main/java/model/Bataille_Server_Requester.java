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

}
