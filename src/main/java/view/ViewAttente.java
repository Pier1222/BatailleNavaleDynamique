package view;

import javax.swing.*;

import controller.ControlTimerAttente;

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
    
    protected JPanel panelJoueursAttentes;
    protected JPanel panelEquipe1;
    protected JPanel panelEquipe2;
    
    protected JPanel general;
    
    protected ControlTimerAttente ct;
    public Timer timerPanelJoueurs; //Permet de modifier régulièrement la liste des noms des joueurs
    
    public ViewAttente(Bataille_navale_model model) {
        this.model = model;
        
        initAttribut();
        
        //Permet de mettre en place tous les éléments crées dans InitAttribut
        createView();
        System.out.println("c");
        setSize(700,800);
        setTitle("Menu de bataille navale");
        
        //Centrer la fenêtre
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((d.width-this.getWidth())/2, (d.height-this.getHeight())/2);
        
        setResizable(false);
        
        //Empêcher sa fermeture avec la croix (si on veut quitter, ce sera avec le bouton dédié)
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        display();
        sonAttente.jouerEnBoucle();
        timerPanelJoueurs.start();
    }
    
	private void initAttribut() {
		sonAttente = new Son("Preparation.wav");
		sonLancementPartie = new Son("AreYouReady.wav");
		
		lancerPartie = new JButton("Lancer la partie");
		lancerPartie.setVisible(model.getUtilisateur().isHote()); //Modifier pour le rendre invisible si ce n'est pas le créateur de la partie
		lancerPartie.setEnabled(false); //Dans tous les cas, il est désactivé durant ses premières secondes d'apparition
		quitterPartie = new JButton("Quitter");
	    
	    ct = new ControlTimerAttente(model, this);
	    timerPanelJoueurs = new Timer(1000, ct); //Toutes les secondes, on va regarder les joueurs existants
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
    	general = new JPanel();
    	general.setLayout(new BoxLayout(general, BoxLayout.Y_AXIS));
    	
    	JPanel panelsJoueurs = new JPanel(new GridLayout(1, 3));
    	
    	panelJoueursAttentes = new JPanel();
    	panelJoueursAttentes.setLayout(new BoxLayout(panelJoueursAttentes, BoxLayout.Y_AXIS));
    	panelJoueursAttentes.add(new JLabel("Veuillez patientez..."));
    	
    	panelEquipe1 = new JPanel();
    	
    	panelEquipe2 = new JPanel();
    	
    	panelsJoueurs.add(panelEquipe1);
    	panelsJoueurs.add(panelJoueursAttentes);
    	panelsJoueurs.add(panelEquipe2);
    	
    	general.add(new JLabel("Liste des joueurs:"));
    	general.add(panelsJoueurs);
    	general.add(lancerPartie);
    	general.add(quitterPartie);
    	setContentPane(general);
    }
    
    /**
     * Change le panel contenant la liste des joueurs en attentes
     */
    public void changePanelJoueurs() {
    	panelJoueursAttentes.removeAll(); //On retire tous les noms qu'il y avait
    	String[] nomsJoueursActu = model.getNomsJoueursPartieActu();
    	for(int i = 0; i < nomsJoueursActu.length; i++) {
    		panelJoueursAttentes.add(new JLabel(nomsJoueursActu[i])); //On place tous les noms récupérés
    	}
    	changeEtatBoutonLancerPartie();
    	setContentPane(general);
    }
    
    private void changeEtatBoutonLancerPartie() {
    	int nbJoueurs = panelJoueursAttentes.getComponentCount(); //En théorie, il y a autant de label que de joueurs
    	lancerPartie.setEnabled(nbJoueurs > 1); //On ne peut pas cliquer sur ce bouton sur il n'y a qu'un joueur
    }
    
    public void createViewReady() {
    	sonAttente.arreter();
    	sonLancementPartie.start();
    	timerPanelJoueurs.stop();
    	
    	//Changer la vue
    }
    
	
}