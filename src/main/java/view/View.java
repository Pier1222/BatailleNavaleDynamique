package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import controller.ControlGroupAttente;
import controller.ControlMenu;

import java.io.IOException;

import model.Bataille_navale_model;
import model.Game;
import model.Son;

public class View extends JFrame {

	protected Bataille_navale_model model;

	public Son sonDeFond;
	public Son sonAlternatif;
	
	protected JPanel controlPanel;
	protected JPanel gridPanel; 

    public JButton testSon;
    public JButton testSon2;
    public JButton testStop;
    protected JButton resetButton;
    
    protected JLabel titleAmiral;
    protected JLabel title;
    
    //Les threads
    public ThreadCreationServ threadCreation;
    public ThreadJoinServ threadJoin;
    
    protected JMenuItem menuItem;

    //protected ControlMenu cm;
    
    protected JTextField nomField;
    protected JTextField adresseIpField;
    protected JTextField portField;
    protected JLabel nomLabel;
    protected JLabel adresseIpLabel;
    protected JLabel portLabel;
    public JButton launchInvite;
    public JButton launchCreateur;
    
    protected ControlGroupAttente groupAttente; //Permet de faire apparaître la fenêtre attente
    
    public View(Bataille_navale_model model) {

        this.model = model;
        
        initAttribut();
        createMenu();
        
        //Permet de mettre en place tous les éléments crées dans InitAttribut
        createView();
        setSize(1024,700);
        setResizable(false);
        setTitle("Menu de bataille navale");
        
        //Permet apparement de centrer l'ecran et de l'arrêter quand on le ferme
        addWindowListener(new FrameListener());

        setLocationRelativeTo(null);
    }
    

	private void initAttribut() {
        //Éléments de test
    	testSon       = new JButton("Test Son");
    	testSon2      = new JButton("Test Son numéro 2");
    	testStop      = new JButton("Stop");
    	sonAlternatif = new Son("AreYouReady.wav");
    	sonDeFond     = new Son("Hydrocity.wav"); //...Quoi ? Au moins cette musique possède l'eau comme thème (vous voyez... Bateau/Eau... Tout ça...)
    	
    	//Éléments pour créer/rejoindre Une Partie
        nomField       = new JTextField();
        adresseIpField = new JTextField();
        portField      = new JTextField();
        
        nomLabel       = new JLabel("Nom: ");
        adresseIpLabel = new JLabel("IP: ");
        portLabel      = new JLabel("Port: ");
        
        launchInvite   = new JButton("Rejoindre une partie");
        launchCreateur = new JButton("Créer une partie");
        
        creerThreadServeur();
        creerThreadJoin();
    }
	
    private void creerThreadServeur() {
    	threadCreation = new ThreadCreationServ(this);
    }
    
    private void creerThreadJoin() {
    	threadJoin = new ThreadJoinServ(this);
    }
	
    public void setButtonControler(ActionListener listener) {
    	testSon.addActionListener(listener);
    	testSon2.addActionListener(listener);
    	testStop.addActionListener(listener);
    	launchInvite.addActionListener(listener);
    	launchCreateur.addActionListener(listener);
    }
	
    /**
     * Permet d'obtenir le numéro de port donné par l'utilisateur
     * @return Le numéro de port obtenu ou -1 si l'utilisateur n'a pas donné de nombre
     */
    private int getPortDonnee() {
    	try {
    		return Integer.parseInt(portField.getText());
    	} catch(NumberFormatException e) {
    		return -1;
    	}
    }
    
    public String getIpDonnee() {
    	//Ajouter une vérification
    	return adresseIpField.getText();
    }
    
    public void useCreateThread() {
    	createPartie();
    	creerThreadServeur();
    }
	
	private void createPartie() {
		String nom     = nomField.getText();
		int numeroPort = getPortDonnee();
		
		if(nom == null) {
			creerDialogueErreur("Aucun nom n'a été trouvé.", "Erreur de nom");
			return;
		}
		if(numeroPort < 0) {
			creerDialogueErreur("Le numéro de port est incorrect.", "Mauvais numéro de port");
			return;
		}
		
		if(!model.createGame(nom, numeroPort)) {
			creerDialogueErreur("Il y a eu une erreur lors de la création de partie.", "Impossible de créer une partie");
			return;
		}
		apparitionVueAttente();
	}
	
	public void useJoinThread() {
		rejoindrePartie();
		creerThreadJoin();
	}
	
	/**
	 * Va créer si possible la partie avant de la rejoindre (la machine fera donc office de serveur et de client en même temps)
	 */
	private void rejoindrePartie() {
		String nom     = nomField.getText();
		int numeroPort = getPortDonnee();
		String ip      = getIpDonnee();
		
		if(nom == null) {
			creerDialogueErreur("Aucun nom n'a été trouvé.", "Erreur de nom");
			return;
		}
		if(numeroPort < 0) {
			creerDialogueErreur("Le numéro de port est incorrect", "Mauvais numéro de port");
			return;
		}
		if(ip == null) {
			creerDialogueErreur("Aucune adresse IP n'a été trouvée.", "Erreur d'IP");
			return;
		}
		
		if(!model.joinGame(nom, ip, numeroPort)) {
			creerDialogueErreur("Impossible de rejoindre une partie au port " + numeroPort + " à l'adresse '" + ip + "'.", "Impossible de rejoindre la partie");
		    return;
		}
		apparitionVueAttente();
	}
	
	private void apparitionVueAttente() {
		stopAllSong(); //On arrête toutes les musiques jouées actuellement
		undisplay(); //Faire disparaître cette fenêtre
        groupAttente = new ControlGroupAttente(model);
		//groupAttente.viewAttente.display();
	}
	
	
	public void initAttributAmiral() {
    	
    	testSon = new JButton("haha");
   
    }
    
    private void createMenu() {
    	
    		
    }
    
    public void stopAllSong() {
    	sonDeFond.arreter();
    	sonAlternatif.arreter();
    }
    
    private void createView() {
    	  JPanel menu = new JPanel();
    	  menu.setLayout(new GridLayout(5, 5));

    	  JPanel ligneConnexion = new JPanel(new FlowLayout());
    	  
    	  JPanel ligneTest = new JPanel();
    	  ligneTest.setLayout(new BoxLayout(ligneTest, BoxLayout.X_AXIS));
    	  
    	  JPanel ligneBouttons = new JPanel();
    	  ligneBouttons.setLayout(new BoxLayout(ligneBouttons, BoxLayout.X_AXIS));
    	  
    	  ligneTest.add(testSon);
    	  ligneTest.add(testSon2);
    	  ligneTest.add(testStop);
    	  
    	  ligneConnexion.add(nomLabel);
    	  ligneConnexion.add(nomField);
    	  ligneConnexion.add(portLabel);
    	  ligneConnexion.add(portField);
    	  ligneConnexion.add(adresseIpLabel);
    	  ligneConnexion.add(adresseIpField);
    	  
    	  ligneBouttons.add(launchCreateur);
    	  ligneBouttons.add(new JPanel());
    	  ligneBouttons.add(launchInvite);
    	  
    	  menu.add(ligneTest);
    	  menu.add(ligneConnexion);
    	  menu.add(ligneBouttons);
    	  
    	  setContentPane(menu);
    }
	
    public void display() {
        setVisible(true);
    }

    public void undisplay() {
        setVisible(false);
    }
    
    public void creerDialogueErreur(String messageErreur, String titreErreur) {
    	JOptionPane erreur = new JOptionPane();
    	erreur.showMessageDialog(this, messageErreur, titreErreur, JOptionPane.ERROR_MESSAGE);
    	JDialog fenErreur = erreur.createDialog(this, titreErreur);
    }
    
    class FrameListener extends WindowAdapter
    {
       public void windowClosing(WindowEvent e)
      {
        System.exit(0);
      }
    }
}
