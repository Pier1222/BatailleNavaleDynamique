package controller;
import model.Bataille_navale_model;
import view.View_matelot;

public class ControlGroup_matelot {
	protected Bataille_navale_model model;
	public View_matelot viewMatelot;
	public ControlButton_matelot controlButtonMatelot;
	
	public ControlGroup_matelot(Bataille_navale_model model) {
		//Affectation du modèle donnée en paramètre
    	this.model = model;
    	
    	//Utilisation de ce même modèle pour le vue
    	viewMatelot= new View_matelot(model);
    	
    	//Utilisation de cette vue et de ce modèle dans tous les contrôleurs
        controlButtonMatelot = new ControlButton_matelot(model, viewMatelot);
        viewMatelot.display();
	}
}
