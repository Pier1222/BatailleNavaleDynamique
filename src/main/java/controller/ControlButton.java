package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Bataille_navale_model;
import model.Son;
import view.View;

public class ControlButton implements ActionListener {
	
	Bataille_navale_model model;
	View view;
	Son son;
	


	public ControlButton(Bataille_navale_model model, Son son, View view) {
		this.model = model;
        this.view = view;
        this.son = son;
        
        son.jouer();
        
		view.setButtonControler(this);
	
	}
	
	public void actionPerformed(ActionEvent e){
		
		if(e.getSource() == view.launchButton){
			
			 
		
		}
		
	}

}
