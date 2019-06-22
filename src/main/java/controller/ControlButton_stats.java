package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Bataille_navale_model;
import view.View_stats;

public class ControlButton_stats implements ActionListener {

	protected Bataille_navale_model model;
	protected View_stats view;
	

	public ControlButton_stats(Bataille_navale_model model, View_stats view) {
		this.model = model;
        this.view = view;
        
		view.setButtonControler(this);
	}
	
	public void actionPerformed(ActionEvent e){
		
		if(e.getSource() == view.retour) {
			view.undisplay();
		}
		
	}
	
}
