package controller;

import model.Bataille_navale_model;
import model.Son;
import view.View;

public class ControlGroup {
	
	protected Bataille_navale_model model;
	protected View view;
    public ControlButton controlButton;
    public ControlMenu controlMenu;
    protected Son son;
    
    public ControlGroup(Bataille_navale_model model) throws Exception {

    	this.model = model;
    	son = new Son("aaa.wav");
    	
    	view = new View(model);
    	
        controlButton = new ControlButton(model,son,view);
        controlMenu = new ControlMenu(view);
        view.display();


    }

}
