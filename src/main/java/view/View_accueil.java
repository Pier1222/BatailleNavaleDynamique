package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import controller.ControlGroupAttente;
import controller.ControlMenu;

import java.io.IOException;

import model.Bataille_navale_model;
import model.Game;
import model.Son;

import java.awt.event.ActionEvent;


public class View_accueil extends JFrame {

	// Les JPanels
	protected JPanel panel_view;
	protected JPanel panel_champs;
	protected JPanel panel_button;
	
	// Le model
	protected Bataille_navale_model model;

	// Le son
	public Son sonDeFond;
    
    //Les threads
    public ThreadCreationServ threadCreation;
    public ThreadJoinServ threadJoin;
    
    protected JMenuItem menuItem;

    //protected ControlMenu cm;
    
    // Les champs
    protected JTextField textUser;
    protected JTextField textIp;
    protected JTextField textPort;
    
    // Les labels
    protected JLabel nomLabel;
    protected JLabel adresseIpLabel;
    protected JLabel portLabel;
        
    // les boutons connexions
    public JButton launchCreateur;
    public JButton launchInvite;
    
    //bouton pour stopper la musique;
    public JButton btnStopperLaMusique;
    
    
    protected ControlGroupAttente groupAttente; //Permet de faire apparaître la fenêtre attente
    

	public View_accueil(Bataille_navale_model model) {
		
		this.model = model;
		
		initialize();
        createMenu();

		setResizable(false);
		
		setTitle("Bataille Navale");
		setBackground(new Color(240, 240, 240));
		setBounds(100, 100, 800, 439);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {


		panel_view = new JPanel();
		panel_view.setBounds(12, 162, 538, 409);
		panel_view.setLayout(null);
		
		panel_champs = new JPanel();
		panel_champs.setBounds(119, 205, 260, 169);
		panel_view.add(panel_champs);
		panel_champs.setLayout(null);
		
		panel_button = new JPanel();
		panel_button.setBounds(451, 205, 199, 169);
		panel_view.add(panel_button);
		panel_button.setLayout(null);
		
		textPort = new JTextField();
		textPort.setBounds(91, 105, 116, 22);
		panel_champs.add(textPort);
		textPort.setColumns(10);
		
		textIp = new JTextField();
		textIp.setBounds(91, 70, 116, 22);
		panel_champs.add(textIp);
		textIp.setColumns(10);
		
		textUser = new JTextField();
		textUser.setBounds(91, 35, 116, 22);
		panel_champs.add(textUser);
		textUser.setColumns(10);
		
		nomLabel = new JLabel("User :");
		nomLabel.setBounds(35, 38, 35, 16);
		panel_champs.add(nomLabel);
		
		adresseIpLabel = new JLabel("IP :");
		adresseIpLabel.setBounds(35, 73, 20, 16);
		panel_champs.add(adresseIpLabel);
		
		portLabel = new JLabel("Port :");
		portLabel.setBounds(35, 108, 32, 16);
		panel_champs.add(portLabel);
				
		launchCreateur = new JButton("Créer une partie");
		launchCreateur.setBounds(27, 33, 149, 25);
		panel_button.add(launchCreateur);
		
		launchInvite = new JButton("Rejoindre une partie");
		launchInvite.setBounds(27, 71, 149, 25);
		panel_button.add(launchInvite);
		
		btnStopperLaMusique = new JButton("Stopper la musique");
		btnStopperLaMusique.setBounds(27, 124, 149, 25);
		panel_button.add(btnStopperLaMusique);
		
		JLabel lblBatailleNavale = new JLabel("La Bataille Navale");
		lblBatailleNavale.setHorizontalAlignment(SwingConstants.CENTER);
		lblBatailleNavale.setFont(new Font("Script MT Bold", Font.BOLD, 52));
		lblBatailleNavale.setBounds(12, 13, 770, 205);
		panel_view.add(lblBatailleNavale);
		
		JLabel lblNewLabel_1 = new JLabel("Bataille Navale");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Blackadder ITC", Font.PLAIN, 65));
		lblNewLabel_1.setBounds(0, 46, 770, 72);
		
		creerThreadServeur();
        creerThreadJoin();
        
        setContentPane(panel_view);
	}
	
    private void creerThreadServeur() {
    	threadCreation = new ThreadCreationServ(this);
    }
    
    private void creerThreadJoin() {
    	threadJoin = new ThreadJoinServ(this);
    }
	
    public void setButtonControler(ActionListener listener) {

    	btnStopperLaMusique.addActionListener(listener);
    	launchInvite.addActionListener(listener);
    	launchCreateur.addActionListener(listener);
    }

	public void creerDialogueErreur(String messageErreur, String titreErreur) {
	    	JOptionPane erreur = new JOptionPane();
	    	erreur.showMessageDialog(this, messageErreur, titreErreur, JOptionPane.ERROR_MESSAGE);
	    	JDialog fenErreur = erreur.createDialog(this, titreErreur);
	}
	
	  /**
     * Permet d'obtenir le numéro de port donné par l'utilisateur
     * @return Le numéro de port obtenu ou -1 si l'utilisateur n'a pas donné de nombre
     */
    private int getPortDonnee() {
    	try {
    		return Integer.parseInt(textPort.getText());
    	} catch(NumberFormatException e) {
    		return -1;
    	}
    }
    
    public String getIpDonnee() {
    	//Ajouter une vérification
    	return textIp.getText();
    }
    
    public void useCreateThread() {
    	createPartie();
    	creerThreadServeur();
    }
	
	private void createPartie() {
		String nom     = textUser.getText();
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
		String nom     = textUser.getText();
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
		//stopAllSong(); //On arrête toutes les musiques jouées actuellement
		undisplay(); //Faire disparaître cette fenêtre
        groupAttente = new ControlGroupAttente(model);
		//groupAttente.viewAttente.display();
	}
	
    private void createMenu() {
    	
    		
    }
    
    public void stopAllSong() {
    	sonDeFond.arreter();
    }
	
    public void display() {
        setVisible(true);
    }

    public void undisplay() {
        setVisible(false);
    }
    
    class FrameListener extends WindowAdapter
    {
       public void windowClosing(WindowEvent e)
      {
        System.exit(0);
      }
    }
}
