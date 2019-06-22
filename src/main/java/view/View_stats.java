package view;

import javax.swing.*;

import controller.ControlGroup_stats;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import model.Bataille_navale_model;
import model.Son;
import model.Stats_Joueur;

public class View_stats extends JDialog {
	
	private final static String[] TEXTES_STATISTIQUES = new String[] {"Nombre de victoires", "Nombre de défaites",
			"Nombre de tirs touchés", "Nombre de tirs ratés", "Nombre de partie jouées en Amiral", 
			"Nombre d'affectations en Matelot attaquant", "Nombre d'affectations en Matelot défenseur",
			"Nombre de fois qu'un navire vous a été affectés"};
	
	protected JLabel[] labelsStatistiques;
	protected Bataille_navale_model model;
	protected Son sonStats;
	public JButton retour;
	protected JPanel general;
	
    public View_stats(Frame parent, Bataille_navale_model model) {
		super(parent, true); //Il devient impossible d'intérargir avec la parent tant que cette fenêtre est visible
        this.model = model;
        
        setSize(500,800);
        initAttribut();
        
        //Centrer la fenêtre
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((d.width-this.getWidth())/2, (d.height-this.getHeight())/2);
        
        setResizable(false);
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
    
    private void initAttribut() {
    	retour = new JButton("Retourner au menu");
    	sonStats = new Son("Statistiques_Advances.wav");
    }
    
    private void changeViewStats() {
    	Stats_Joueur statistiquesAAfficher = model.getUtilisateur().getStatistiques();
    	labelsStatistiques = new JLabel[TEXTES_STATISTIQUES.length];
    	int[] statsValeurs = new int[] {statistiquesAAfficher.getNbVictoires(), statistiquesAAfficher.getNbDefaites(),
    			statistiquesAAfficher.getNbTirsTouches(), statistiquesAAfficher.getNbTirsRates(), statistiquesAAfficher.getNbFoisAmiral(),
    			statistiquesAAfficher.getNbRoleAttaque(), statistiquesAAfficher.getNbRoleDefense()};
    	for(int i = 0; i < statsValeurs.length; i++) {
    		labelsStatistiques[i] = new JLabel(TEXTES_STATISTIQUES[i] + ": " + statsValeurs[i]);
    	}
    	
        //Le seul cas spécial est celui du nombre d'affectaton de navire
    	String[] nomsDesNavires = Stats_Joueur.getNomNavires();
    	String dernierTexte = "<html><br/>" + TEXTES_STATISTIQUES[TEXTES_STATISTIQUES.length - 1] + ": ";
    	for(int i = 0; i < nomsDesNavires.length; i++) {
    		dernierTexte += "<br/>&nbsp;&nbsp;" + nomsDesNavires[i] + ": " + statistiquesAAfficher.getNbUtilisationsNavire()[i];
    	}
    	
    	dernierTexte += "<html/>";
    	labelsStatistiques[labelsStatistiques.length - 1] = new JLabel(dernierTexte);
    }
    
    public void setButtonControler(ActionListener listener) {
        retour.addActionListener(listener);
    }
    
    private void createView() {
    	general = new JPanel();
    	general.setLayout(new BoxLayout(general, BoxLayout.Y_AXIS));
    	for(int i = 0; i < labelsStatistiques.length; i++) {
    		general.add(labelsStatistiques[i]);
    	}
    	general.add(retour);
    	setContentPane(general);
    }
    
    public void display() {
        sonStats.jouerEnBoucle();
        setTitle("Statistiques de '" + model.getUtilisateur().getNom() + "'");
        changeViewStats();
        createView();
        setVisible(true);
    }

    public void undisplay() {
    	sonStats.arreter();
        setVisible(false);
    }

}
