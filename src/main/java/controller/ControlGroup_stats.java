package controller;

import java.awt.Frame;

import model.Bataille_navale_model;
import view.View_stats;

public class ControlGroup_stats {

	protected Bataille_navale_model model;
	protected View_stats view;
    public ControlButton_stats controlButton;
    
    public ControlGroup_stats(Frame parent, Bataille_navale_model model) {
        //Affectation du modèle donnée en paramètre
    	this.model = model;
    	
    	//Utilisation de ce même modèle pour le vue
    	view = new View_stats(parent, model);
    	
    	//Utilisation de cette vue et de ce modèle dans tous les contrôleurs
        controlButton = new ControlButton_stats(model, view);
    }
    
    public void displayVue() {
    	view.display();
    }
}
