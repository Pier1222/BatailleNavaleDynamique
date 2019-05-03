package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Bataille_navale_model;
import model.Son;
import view.View;

public class ControlButton implements ActionListener {
	
	protected Bataille_navale_model model;
	protected View view;
	

	public ControlButton(Bataille_navale_model model, View view) {
		this.model = model;
        this.view = view;
        
		view.setButtonControler(this);
	}
	
	public void actionPerformed(ActionEvent e){
		
		if(e.getSource() == view.testSon){
			System.out.println("Launch button");
			view.sonDeFond.jouerEnBoucle();
			
		} else if(e.getSource() == view.testSon2) {
			System.out.println("Launch button 2");
			view.sonAlternatif.jouerEnBoucle();
			
		} else if(e.getSource() == view.testStop) {
			System.out.println("Stop song");
			view.stopAllSong();
			
		} else if(e.getSource() == view.launchCreateur) {
			System.out.println("Création partie");
			//view.createPartie();
			view.threadCreation.execute();
			
		} else if(e.getSource() == view.launchInvite) {
			System.out.println("Rejoindre partie");
			//view.rejoindrePartie();
			view.threadJoin.execute();
			
		}
		
	}
}
