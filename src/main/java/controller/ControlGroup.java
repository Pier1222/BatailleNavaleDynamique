package controller;

import model.Bataille_navale_model;
import model.Son;
import view.View;

public class ControlGroup {
	
	protected Bataille_navale_model model;
	protected View view;
    public ControlButton controlButton;
    public ControlMenu controlMenu;
    
    public ControlGroup(Bataille_navale_model model) {
        //Affectation du modèle donnée en paramètre
    	this.model = model;
    	
    	//Utilisation de ce même modèle pour le vue
    	view = new View(model);
    	
    	//Utilisation de cette vue et de ce modèle dans tous les contrôleurs
        controlButton = new ControlButton(model, view);
        controlMenu = new ControlMenu(model, view);
        view.display();
    }
}
