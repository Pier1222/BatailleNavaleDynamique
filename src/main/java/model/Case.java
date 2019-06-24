package model;

import java.awt.Color;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

public class Case extends JButton implements Serializable {
	
	private final static int TAILLE_MATTER_BORDER = 5;

	private final static int BORDURE_SANS        = 0;
	private final static int BORDURE_DROITE      = 1;
	private final static int BORDURE_GAUCHE      = 2;
	private final static int BORDURE_HAUT        = 3;
	private final static int BORDURE_BAS         = 4;
	
	private final static int BORDURE_BAS_GAUCHE  = 42; //BAS et DROITE = 4 et 2
	private final static int BORDURE_BAS_DROITE  = 41;
	
	private final static int BORDURE_HAUT_GAUCHE = 32;
	private final static int BORDURE_HAUT_DROITE = 31;
	
	
	private PieceNavire piecePose;
	private int positionX;
	private int positionY;
	
	private int typeBordure; //Pour créer les MatterBorders sur la vue
	
	public Case(int positionX, int positionY) {
		this(positionX, positionY, BORDURE_SANS);
	}
	
	public Case(int positionX, int positionY, int typeBordure) {
		super("");
		this.positionX   = positionX;
		this.positionY   = positionY;
		this.piecePose   = null;
		this.typeBordure = typeBordure;
	}

	public PieceNavire getPiecePose() {
		return piecePose;
	}

	public void setPiecePose(PieceNavire piecePose) {
		this.piecePose = piecePose;
	}

	public int getPositionX() {
		return positionX;
	}

	public int getPositionY() {
		return positionY;
	}
	
	/**
	 * Permet de changer la bordure de la case en fonction de son type
	 */
	public void changeBorderToDefault() {
		Color couleurDeBaseBordure = new Color(153, 255, 255);
		Border defaultBorder = null;
	    if(typeBordure == BORDURE_SANS)
	    	defaultBorder = new LineBorder(couleurDeBaseBordure);
	    else if(typeBordure == BORDURE_DROITE)
	    	defaultBorder = new CompoundBorder(new LineBorder(couleurDeBaseBordure), new MatteBorder(0, 0, 0, TAILLE_MATTER_BORDER, (Color) couleurDeBaseBordure));
	    else if(typeBordure == BORDURE_GAUCHE)
	    	defaultBorder = new CompoundBorder(new LineBorder(couleurDeBaseBordure), new MatteBorder(0, TAILLE_MATTER_BORDER, 0, 0, (Color) couleurDeBaseBordure));
	    else if(typeBordure == BORDURE_HAUT)
	    	defaultBorder = new CompoundBorder(new LineBorder(couleurDeBaseBordure), new MatteBorder(TAILLE_MATTER_BORDER, 0, 0, 0, (Color) couleurDeBaseBordure));
	    else if(typeBordure == BORDURE_BAS)
	    	defaultBorder = new CompoundBorder(new LineBorder(couleurDeBaseBordure), new MatteBorder(0, 0, TAILLE_MATTER_BORDER, 0, (Color) couleurDeBaseBordure));
	    else if(typeBordure == BORDURE_HAUT_DROITE)
	    	defaultBorder = new CompoundBorder(new LineBorder(couleurDeBaseBordure), new MatteBorder(TAILLE_MATTER_BORDER, 0, 0, TAILLE_MATTER_BORDER, (Color) couleurDeBaseBordure));
	    else if(typeBordure == BORDURE_HAUT_GAUCHE)
	    	defaultBorder = new CompoundBorder(new LineBorder(couleurDeBaseBordure), new MatteBorder(TAILLE_MATTER_BORDER, TAILLE_MATTER_BORDER, 0, 0, (Color) couleurDeBaseBordure));
	    else if(typeBordure == BORDURE_BAS_DROITE)
	    	defaultBorder = new CompoundBorder(new LineBorder(couleurDeBaseBordure), new MatteBorder(0, 0, TAILLE_MATTER_BORDER, TAILLE_MATTER_BORDER, (Color) couleurDeBaseBordure));
	    else if(typeBordure == BORDURE_BAS_GAUCHE)
	    	defaultBorder = new CompoundBorder(new LineBorder(couleurDeBaseBordure), new MatteBorder(0, TAILLE_MATTER_BORDER, TAILLE_MATTER_BORDER, 0, (Color) couleurDeBaseBordure));
	    
	    
	    if(defaultBorder != null)
	    	setBorder(defaultBorder);
	}
	
	/**
	 * Retourne simplement le position X sous forme de String
	 * @return
	 */
	public String getStringPositionX() {
		return "" + positionX;
	}
	
	/**
	 * Transforme la position Y en lettre
	 * @return La lettre associciée la position de la case
	 */
	public String getStringPositionY() {
		return Grille.getlettreColonne(positionY);
	}

	public static int getBORDURE_SANS() {
		return BORDURE_SANS;
	}

	public static int getBORDURE_DROITE() {
		return BORDURE_DROITE;
	}

	public static int getBORDURE_GAUCHE() {
		return BORDURE_GAUCHE;
	}

	public static int getBORDURE_HAUT() {
		return BORDURE_HAUT;
	}

	public static int getBORDURE_BAS() {
		return BORDURE_BAS;
	}

	public static int getBORDURE_BAS_GAUCHE() {
		return BORDURE_BAS_GAUCHE;
	}

	public static int getBORDURE_BAS_DROITE() {
		return BORDURE_BAS_DROITE;
	}

	public static int getBORDURE_HAUT_GAUCHE() {
		return BORDURE_HAUT_GAUCHE;
	}

	public static int getBORDURE_HAUT_DROITE() {
		return BORDURE_HAUT_DROITE;
	}
}
