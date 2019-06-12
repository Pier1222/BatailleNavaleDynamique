package launcher;

import model.Son;
import view.View_amiral;
import view.View_interface;
import controller.ControlGroup;
import model.Bataille_navale_model;

public class Launcher_autre {

	public static void main(String[] args) {
		Bataille_navale_model model = new Bataille_navale_model();
		ControlGroup controlgroup = new ControlGroup(model);
		
		View_amiral va = new View_amiral();
		View_interface vi = new View_interface();
		
		va.display();
		vi.display();
	 
	}
}
