package controller;

import model.Bataille_navale_model;
import model.Game;
import model.Son;
import view.View;

public class ControlGroup {
	
    private View view;
    public ControlButton controlButton;
    public ControlMenu controlMenu;
    private Son son;
    
    public ControlGroup(Bataille_navale_model model, Game game) throws Exception {

    	son = new Son("musique.mp3");
    	view = new View(model, game, son);

        controlButton = new ControlButton(model, game, view);
        view.display();
    }

}
