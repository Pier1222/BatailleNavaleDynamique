package model;

public class Grille {
	
    private final int LINES = 10;
    private final int COLUMNS = 10;
	
	private Case[][] cases;

    public Grille() {
        this.cases = new Case[LINES][COLUMNS];
    }
    
    public Case[][] getCases() {
        return this.cases;
    }
    
    public void effectueDeplacement(Navire navire, int posX, int posY) {
    	if(navire.estEndommage()) //Un navire endommag� ne peut pas faire de d�placement
    		return;
    	
    	
    	
    }
    

}
