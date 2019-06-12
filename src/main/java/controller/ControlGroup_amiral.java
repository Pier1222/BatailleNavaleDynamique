package controller;

import model.Bataille_navale_model;
import view.View_amiral;

public class ControlGroup_amiral {
	protected Bataille_navale_model model;
	public View_amiral viewAmiral;
	public ControlButton_amiral controlButtonAmiral;
	
	public ControlGroup_amiral(Bataille_navale_model model) {
		//Affectation du modèle donnée en paramètre
    	this.model = model;
    	
    	//Utilisation de ce même modèle pour le vue
    	viewAmiral= new View_amiral(model);
    	
    	//Utilisation de cette vue et de ce modèle dans tous les contrôleurs
        controlButtonAmiral = new ControlButton_amiral(model, viewAmiral);
        viewAmiral.display();
	}
}
