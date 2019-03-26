package model;

public class Grille {
	
	private Case[][] cases;

    public Grille(Case[][] cases) {
        this.cases = cases;
    }
    
    public Case[][] getCases() {
        return this.cases;
    }
    
    public void effectueDeplacement(Navire navire, int posX, int posY) {
    	if(navire.estEndommage()) //Un navire endommagé ne peut pas faire de déplacement
    		return;
    	
    	
    	
    }
    

}
