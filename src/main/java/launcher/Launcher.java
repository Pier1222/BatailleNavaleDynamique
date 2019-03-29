package launcher;

import model.Son;
import controller.ControlGroup;
import model.Bataille_navale_model;

public class Launcher {

	public static void main(String[] args) {
		Bataille_navale_model model = new Bataille_navale_model();
		
		   javax.swing.SwingUtilities.invokeLater( new Runnable() {
	            public void run() {
	
					Bataille_navale_model model = new Bataille_navale_model();
					try {
						ControlGroup controlgroup = new ControlGroup(model);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
	
		    
	            }
	        });
		   
		   
		   
	 
	}
}
