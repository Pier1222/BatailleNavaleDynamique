package controller;

import model.Bataille_navale_model;
import view.ViewAttente;

public class ControlGroupAttente {
	protected Bataille_navale_model model;
	public ViewAttente viewAttente;
    public ControlButtonAttente controlButtonAttente;
    
    public ControlGroupAttente(Bataille_navale_model model) {
        //Affectation du modèle donnée en paramètre
    	this.model = model;
    	
    	//Utilisation de ce même modèle pour le vue
    	viewAttente = new ViewAttente(model);
    	
    	//Utilisation de cette vue et de ce modèle dans tous les contrôleurs
        controlButtonAttente = new ControlButtonAttente(model, viewAttente);
    }
}
