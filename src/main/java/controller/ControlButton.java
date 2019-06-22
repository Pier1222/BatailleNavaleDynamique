package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Bataille_navale_model;
import model.Son;
import view.View_accueil;

public class ControlButton implements ActionListener {
	
	protected Bataille_navale_model model;
	protected View_accueil view;
	

	public ControlButton(Bataille_navale_model model, View_accueil view) {
		this.model = model;
        this.view = view;
        
		view.setButtonControler(this);
	}
	
	public void actionPerformed(ActionEvent e){
		
		if(e.getSource() == view.btnStopperLaMusique) {
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
			
		} else if (e.getSource() == view.viewStats) {
			System.out.println("Affichage des statistiques");
			view.apparitionVueStats();
			
		}
		
	}
}
