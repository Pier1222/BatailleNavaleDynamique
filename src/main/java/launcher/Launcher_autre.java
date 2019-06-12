package launcher;

import model.Son;
import view.View_amiral;
import view.View_matelot;
import controller.ControlGroup;
import controller.ControlGroup_amiral;
import model.Bataille_navale_model;

public class Launcher_autre {

	public static void main(String[] args) {
		Bataille_navale_model model = new Bataille_navale_model();
		//ControlGroup controlgroup = new ControlGroup(model);
		
		ControlGroup_amiral controlGroupAmiral = new ControlGroup_amiral(model);
		
		//View_amiral va = new View_amiral(model);
		//View_matelot vi = new View_matelot(model);
		
		//va.display();
		//vi.display();
	 
	}
}
