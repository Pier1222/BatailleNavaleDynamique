package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import java.awt.Dimension;
import javax.swing.JPanel;
import java.awt.Rectangle;
import javax.swing.BorderFactory;
import javax.swing.border.EtchedBorder;

import controller.ControlMenu;

import javax.swing.JLabel;
import java.awt.Color;
import java.io.IOException;

import model.Bataille_navale_model;
import model.Game;
import model.Son;

public class View extends JFrame {

	protected Bataille_navale_model model;
	protected Game game;
	public Son sonDeFond;
	public Son sonAlternatif;
	
	protected JPanel controlPanel;
	protected JPanel gridPanel; 
	protected GridLayout gridLayout;

    public JButton testSon;
    public JButton testSon2;
    public JButton testStop;
    protected JButton resetButton;
    
    protected JLabel titleAmiral;
    protected JLabel title;
    
    protected JMenuItem menuItem;

    //protected MouseListener mL;
    //protected ActionListener aL;

    //protected ControlMenu cm;
    
    protected JTextField nomField;
    protected JTextField adresseIpField;
    protected JTextField portField;
    public JButton launchInvite;
    public JButton launchCreateur;
    
    public View(Bataille_navale_model model) {

        this.model = model;
        
        initAttribut();
        createMenu();
        createView();
        setSize(1024,700);
        setResizable(false);
        setTitle("Jeu de bataille navale");
        addWindowListener(new FrameListener());

        setLocationRelativeTo(null);
    }
    

	private void initAttribut() {
        //Éléments de test
    	testSon       = new JButton("Test Son");
    	testSon2      = new JButton("Test Son numéro 2");
    	testStop      = new JButton("Stop");
    	sonAlternatif = new Son("aaa.wav");
    	sonDeFond     = new Son("Hydrocity.wav"); //...Quoi ? Au moins cette musique possède l'eau comme thème (vous voyez... Bateau/Eau... Tout ça...)
    	
    	//Éléments pour créer/rejoindre Une Partie
        nomField       = new JTextField();
        adresseIpField = new JTextField();
        portField      = new JTextField();
        launchInvite   = new JButton("Rejoindre une partie");
        launchCreateur = new JButton("Créer une partie");
   
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
	
	public void createPartie() {
		String nom     = nomField.getText();
		int numeroPort = getPortDonnee();
		
		if(nom == null) {
			creerDialogueErreur("Aucun nom n'a été trouvé.", "Erreur de nom");
			return;
		}
		if(numeroPort < 0) {
			creerDialogueErreur("Le numéro de port est incorrect", "Mauvais numéro de port");
			return;
		}
		
		model.createGame(nom, numeroPort);
		
	}
	
	
	public void rejoindrePartie() {
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
		
		model.joinGame(nom, ip, numeroPort);
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
    
    private void createView(){
    	  JPanel pWidget = new JPanel(new GridLayout(10, 1));
    	  
    	  JPanel ligneTest = new JPanel();
    	  ligneTest.setLayout(new BoxLayout(ligneTest, BoxLayout.X_AXIS));
    	  
    	  JPanel ligneChamps = new JPanel();
    	  ligneChamps.setLayout(new BoxLayout(ligneChamps, BoxLayout.X_AXIS)); 
    	  
    	  JPanel ligneBouttons = new JPanel();
    	  ligneBouttons.setLayout(new BoxLayout(ligneBouttons, BoxLayout.X_AXIS));
    	  
    	  ligneTest.add(testSon);
    	  ligneTest.add(testSon2);
    	  ligneTest.add(testStop);
    	  
    	  ligneChamps.add(nomField);
    	  ligneChamps.add(portField);
    	  ligneChamps.add(adresseIpField);
    	  
    	  ligneBouttons.add(launchCreateur);
    	  ligneBouttons.add(launchInvite);
    	  
    	  pWidget.add(ligneTest);
    	  pWidget.add(ligneChamps);
    	  pWidget.add(ligneBouttons);
    	  
    	  setContentPane(pWidget);
    
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
