package launcher;

import model.Son;
import controller.ControlGroup;
import model.Bataille_navale_model;

public class Launcher {

	public static void main(String[] args) {
		//Habituellement, j'utilise ces deux lignes-là
		Bataille_navale_model model = new Bataille_navale_model();
		ControlGroup controlgroup = new ControlGroup(model);
		
		//Bon après... Cette méthode fonctionne aussi mais... Je ne connais pas la différence...
		   /*javax.swing.SwingUtilities.invokeLater( new Runnable() {
	            public void run() {
	
					Bataille_navale_model model = new Bataille_navale_model();
					try {
						ControlGroup controlgroup = new ControlGroup(model);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
	
		    
	            }
	        }); */  
	 
	}
}
