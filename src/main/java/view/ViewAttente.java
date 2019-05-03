package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import model.Bataille_navale_model;
import model.Son;

public class ViewAttente extends JFrame {

	protected Bataille_navale_model model;
	//Classe que j'ai créé moi-même pour faire du son
	public Son sonAttente; //Son joué de base
	public Son sonLancementPartie; //Son lors du compte à rebours avant de début de partie
	
    public JButton lancerPartie; //Uniquement pour celui qui a lancer la partie
    public JButton quitterPartie; //Pour tout le monde
    
    public ViewAttente(Bataille_navale_model model) {

        this.model = model;
        
        initAttribut();
        
        //Permet de mettre en place tous les éléments crées dans InitAttribut
        createView();
        setSize(700,800);
        setTitle("Menu de bataille navale");
        
        //Centrer la fenêtre
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((d.width-this.getWidth())/2, (d.height-this.getHeight())/2);
        
        setResizable(false);
        
        //Empêcher sa fermeture avec la croix (si on veut quitter, ce sera avec le bouton dédié)
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        sonAttente.jouerEnBoucle();
    }
    
	private void initAttribut() {
		sonAttente = new Son("Preparation.wav");
		sonLancementPartie = new Son("AreYouReady.wav");
		
		lancerPartie = new JButton("Lancer la partie");
		lancerPartie.setVisible(true); //Modifier pour le rendre invisible si ce n'est pas le créateur de la partie
	    quitterPartie = new JButton("Quitter");
	}
	
    public void setButtonControler(ActionListener listener) {
    	lancerPartie.addActionListener(listener);
    	quitterPartie.addActionListener(listener);
    }
	
    public void display() {
        setVisible(true);
    }

    public void undisplay() {
        setVisible(false);
    }
	
    private void createView() {
    	JPanel general = new JPanel();
    	
    	//Mettre les noms des autres joueurs...
    	
    	general.add(lancerPartie);
    	general.add(quitterPartie);
    	setContentPane(general);
    }
    
	
}