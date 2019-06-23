package view;

import javax.swing.*;

import controller.ControlGroup_amiral;
import controller.ControlGroup_matelot;
import controller.ControlTimerAttente;

import java.awt.*;
import java.awt.event.ActionListener;

import model.Bataille_navale_model;
import model.Game;
import model.Joueur;
import model.Son;

public class ViewAttente extends JFrame {

	private final static String NOM_EQUIPE_ERREUR = "Erreur";
	
	protected Bataille_navale_model model;
	//Classe que j'ai créé moi-même pour faire du son
	public Son sonAttente; //Son joué de base
	public Son sonLancementPartie; //Son lors qu'on est sur le point de débuter une partie
	
    public JButton lancerPartie; //Uniquement pour celui qui a lancer la partie
    public JButton quitterPartie; //Pour tout le monde
    
    protected JPanel panelJoueursAttentes;
    protected JPanel panelEquipe1;
    protected JPanel panelEquipe2;
    
    protected JLabel messageDebutPartie;
    
    protected JPanel general;
    
    protected ControlTimerAttente ct;
    public Timer timerPanelJoueurs; //Permet de modifier régulièrement la liste des noms des joueurs
    public Timer timerPanelEquipes; //Permet de modifier les panels sur les équipes une fois qu'elles sont prêtes
    public Timer timerChangementFenetre; //S'occupe de la transition entre cette fenêtre et celle du jeu
    
    private boolean lanceVueAmiral; //Ce booléen permet de savoir si la vue à lancer à la fin de la préparation est celle de l'Amiral ou du Matelot
    
    
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
        
        display();
        sonAttente.jouerEnBoucle();
        timerPanelJoueurs.start();
        timerPanelEquipes.start();
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
	    timerPanelEquipes = new Timer(1000, ct);
	    timerChangementFenetre = new Timer(5000, ct);
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
    	panelEquipe1.setLayout(new BoxLayout(panelEquipe1, BoxLayout.Y_AXIS));
    	
    	panelEquipe2 = new JPanel();
    	panelEquipe2.setLayout(new BoxLayout(panelEquipe2, BoxLayout.Y_AXIS));
    	
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
    public void changePanelJoueursEnAttente() {
    	panelJoueursAttentes.removeAll(); //On retire tous les noms qu'il y avait
    	String[] nomsJoueursActu = model.getNomsJoueursPartieActu();
    	/*for(int i = 0; i < nomsJoueursActu.length; i++) {
    		panelJoueursAttentes.add(new JLabel(nomsJoueursActu[i])); //On place tous les noms récupérés
    	}*/
    	placeNomsJoueurInLayout(nomsJoueursActu, false, panelJoueursAttentes);
    	changeEtatBoutonLancerPartie();
    	setContentPane(general);
    }
    
    private void changeEtatBoutonLancerPartie() {
    	int nbJoueurs = panelJoueursAttentes.getComponentCount(); //En théorie, il y a autant de label que de joueurs
    	lancerPartie.setEnabled(nbJoueurs >= Game.getMinJoueurs()); //On ne peut pas cliquer sur ce bouton sur il n'y a qu'un joueur
    }
    
    public void launchGame() {
    	model.createTeams();
    }
    
    /**
     * Va vérifier si le jeu peut commencer et changer la vue si c'est le cas
     */
    public void preparationGame() {
    	String[][] contenuEquipe = model.getInfosTeams();
    	if(contenuEquipe == null) //si on a pas encore obtenu les informations sur l'équipe
    		return;
    	
    	timerPanelEquipes.stop(); //On a plus besoin de ce timer désormais
    	changePanelJoueursEnAttente();
    	timerPanelJoueurs.stop();
    	createViewReady(contenuEquipe);
    }
    
    
    private void createViewReady(String[][] idEtNomsJoueurs) {
    	if(idEtNomsJoueurs == null || idEtNomsJoueurs.length < 3 || idEtNomsJoueurs[0].length < 2) {
    		creerDialogueErreur("Impossible de faire apparaître les données de début de partie", "Erreur de début de partie");
    		return;
    	}
    	
    	String nomEquipeRouge = idEtNomsJoueurs[0][0];
    	String nomEquipeBleu  = idEtNomsJoueurs[0][1];
    	String[] idEtNomsRouge = idEtNomsJoueurs[1];
    	String[] idEtNomsBleu  = idEtNomsJoueurs[2];
    	Joueur joueur = model.getUtilisateur();
    	
    	sonAttente.arreter();
    	sonLancementPartie.jouer();
    	timerPanelJoueurs.stop();
    	
    	lancerPartie.setVisible(false);
    	quitterPartie.setVisible(false);
    	
    	int placeEquipeRouge = joueur.isInTeam(idEtNomsRouge);
    	int placeEquipeBleue = joueur.isInTeam(idEtNomsBleu);
    	int placeEquipe = -1;
    	
    	String nomEquipe = null;
    	if(placeEquipeRouge >= 0) {
    	    nomEquipe = nomEquipeRouge;
    	    placeEquipe = placeEquipeRouge;
    	} else if (placeEquipeBleue >= 0) {
    		nomEquipe = nomEquipeBleu;
    		placeEquipe = placeEquipeBleue;
    	} else
    		nomEquipe = NOM_EQUIPE_ERREUR;
    	
    	String role = "(Aucun)";
    	if(!nomEquipe.equals(NOM_EQUIPE_ERREUR)) {
    		if(placeEquipe == 0) {
    			role = "Amiral";
    		    lanceVueAmiral = true;
    		} else {
    			role = "Matelot";
    			lanceVueAmiral = false;
    		}
    	}
    	
    	JLabel statut = new JLabel("Vous êtes dans l'équipe '" + nomEquipe + "'.");
    	
    	placeNomsJoueurInLayout(idEtNomsRouge, true, panelEquipe1);
    	placeNomsJoueurInLayout(idEtNomsBleu, true, panelEquipe2);
    	
    	if(!nomEquipe.equals(NOM_EQUIPE_ERREUR)) {
    	    messageDebutPartie = new JLabel("La partie va commencer");
    	    timerChangementFenetre.start();
    	} else {
    		messageDebutPartie = new JLabel("Il y a eu un problème pendant la création des équipes...");
    	}
    	general.add(statut);
    	general.add(messageDebutPartie);
    	//Changer la vue
    	setContentPane(general);
    	
    }
    
    
    
    private void placeNomsJoueurInLayout(String[] nomsJoueurs, boolean contientID, JPanel panel) {
        //Penser à placer un split
    	if(nomsJoueurs == null || nomsJoueurs.length == 0)
    		return;
    	
    	String nomJoueurActu = null;
    	for(int i = 0; i < nomsJoueurs.length; i++) {
    		nomJoueurActu = nomsJoueurs[i];
            if(contientID)
            	nomJoueurActu = retireID(nomJoueurActu);
    		panel.add(new JLabel(nomJoueurActu));
    	}
    }
    
    /**
     * Permet de retirer l'ID dans une chaîne devant contenir le nom et l'ID,
     * tout en tenant compte que des personnes puissent avoir des noms avec des espaces
     * @param idEtNom
     * @return Une chaîne de caractère retirant le premier espace et tout ce qui est avant (donc normalement l'ID)
     */
    private String retireID(String idEtNom) {
    	String resultat = "";
    	String[] tabIdEtNom = idEtNom.split(" ");
    	if(tabIdEtNom.length < 2) //Si il n'avait aucun espace dans la chaîne donnée
    		return idEtNom; //C'est que cette méthode a mal été utilisé
    	
    	for(int i = 1; i < tabIdEtNom.length; i++) {
    		resultat += " " + tabIdEtNom[i];
    	}
    	return resultat;
    }
    
	public void apparitionVueCombat() {
		timerChangementFenetre.stop();
    	sonLancementPartie.arreter();
		undisplay(); //Faire disparaître cette fenêtre
		//System.out.println("Et c'est à ce moment que la nouvelle vue doit apparaître");
        if(lanceVueAmiral)
        	new ControlGroup_amiral(model);
        else
        	new ControlGroup_matelot(model);
	}
    
    public void creerDialogueErreur(String messageErreur, String titreErreur) {
    	JOptionPane erreur = new JOptionPane();
    	erreur.showMessageDialog(this, messageErreur, titreErreur, JOptionPane.ERROR_MESSAGE);
    	JDialog fenErreur = erreur.createDialog(this, titreErreur);
    }
}