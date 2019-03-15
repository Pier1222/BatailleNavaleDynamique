import controller.ControlGroup;
import model.Bataille_navale_model;
import model.Game;

public class Launch {

	public static void main (String[] args){
		System.out.println("Hello World");
		
	     javax.swing.SwingUtilities.invokeLater(() -> {
	            try {
	                Bataille_navale_model model = new Bataille_navale_model();
	                Game game = new Game();
	                new ControlGroup(model,game);

	            } catch (Exception e) {
	                e.printStackTrace();
	            }
//	            System.exit(1);
	        });
		
	}
}
