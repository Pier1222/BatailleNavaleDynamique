package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;

import controller.ControlTimer_amiral;
import model.Amiral;
import model.Bataille_navale_model;
import model.Case;
import model.Equipe;
import model.Game;
import model.Grille;
import model.Matelot;
import model.Navire;
import model.PieceNavire;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import java.awt.Font;

public class View_amiral extends JFrame{
	private final static Color COULEUR_FOND_BOUTON      = Color.WHITE;
	private final static Color COULEUR_BORDURE_MATELOTS = Color.BLACK;
	private final static Color COULEUR_FOND_MATELOTS    = Color.WHITE;
	private final static Color COULEUR_FOND_NON_SELECT  = Color.LIGHT_GRAY;
	private final static Color COULEUR_FOND_SELECT      = Color.GREEN;
	
	private final static Color COULEUR_BORDURE_NAVIRE = new Color(0, 0, 255);
	
	private final static String TOURNE_HORIZONTAL = "Horizontal";
	private final static String TOURNE_VERTICAL   = "Vertical";
	
	protected Bataille_navale_model model;
    public Case[][] buttonsGrille;
    
    private String nomNavireSelectionne;
    public JButton[] buttonsNavire; //Pour placer les navires en début de partie
    
    public JButton retireNavire;
    public JButton tourneNavire;
    public JButton affecteNavire;
    
    public JButton affecteRoleAttaque;
    public JButton affecteRoleDefenseur;
    
    //Pour montrer les matelots
    public int nbMatelots;
    private int[] idMatelots;
    public JButton[] buttonsMatelots;
    protected JLabel[] labelsRoles;
    protected JLabel[] labelsNavires;
    
    private int placeMatelotSelect;
    public int idMatelotSelect;
    
    public JButton[] boutonsNavires;
    public JButton boutonPret;
    
    protected ControlTimer_amiral ct;
    public Timer timerDechargement; //Utilisé uniquement par l'hôte
    public Timer timerAffichage; //Permet de mettre à jour la vue
    
    /*L'idée est qu'à chaque tir, on donne la/les cases modifiés à la deuxième arrayList et on
     * va créer un nouveau timer, le controlTimer va donc rechercher le tableau de cases qui est à
     * la même index que la position où on a trouvé le timer pour retirer l'animation (avant de les retirer de leur liste) */
    public ArrayList<Timer> timersResultatsTirs;
    public ArrayList<Case[]> casesResultatsTirs; 

	public View_amiral(Bataille_navale_model model) {
        this.model           = model;
        nomNavireSelectionne = null;
        placeMatelotSelect   = -1;
        idMatelotSelect      = -1;
        initTimer();
		initialize();
	}
	
	private void initTimer() {
		ct = new ControlTimer_amiral(model, this);
		timerDechargement = new Timer(1000, ct); //Activé toutes les secondes si hôte
		timerAffichage = new Timer(500, ct);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//Récupérer la partie
		model.actualisePartieActu();
		Game gameDebut = model.getPartieActu();
		Amiral amiralTrouve = gameDebut.getAmiral(model.getUtilisateur().getId());
		if(amiralTrouve == null)
			return;
		
		Equipe equipeAmiral = amiralTrouve.getEquipe();
		
		Grille grilleDebut = equipeAmiral.getGrille();
		nbMatelots = equipeAmiral.getNBMatelots();
		
		
		setPreferredSize(new Dimension(1100, 800));
		setSize(new Dimension(1100, 800));
		setResizable(false);
		setTitle("Partie (amiral '" + amiralTrouve.getNom() + "' )");
		getContentPane().setLayout(null);
		
		JPanel general = new JPanel();
		general.setBounds(10, 74, 1010, 800);
		getContentPane().add(general);
		general.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 745, 593);
		general.add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel( );
		panel_2.setLayout(null);
		panel_2.setBounds(139, 0, 605, 580);
		panel_1.add(panel_2);
		
		JPanel panelGrille = new JPanel();
		panelGrille.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelGrille.setBounds(99, 48, 501, 478);
		panel_2.add(panelGrille);
		panelGrille.setLayout(new GridLayout(Grille.getLines(), Grille.getColumns(), 0, 0));
		
		buttonsGrille = grilleDebut.getCases();
		Case buttonActu = null;
		//Initialisation du tableau de boutons
		for(int x = 0; x < Grille.getLines(); x++) {
			for(int y = 0; y < Grille.getColumns(); y++) {
				buttonActu = buttonsGrille[x][y];
				buttonActu.changeBorderToDefault();
				
				buttonActu.setBackground(COULEUR_FOND_BOUTON);
				buttonActu.setAlignmentY(0.0f);
				panelGrille.add(buttonActu);
			}
		}
		
		JLabel label = new JLabel("A");
		label.setHorizontalTextPosition(SwingConstants.CENTER);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(102, 13, 53, 35);
		panel_2.add(label);
		
		JLabel label_1 = new JLabel("B");
		label_1.setHorizontalTextPosition(SwingConstants.CENTER);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(152, 13, 53, 35);
		panel_2.add(label_1);
		
		JLabel label_2 = new JLabel("C");
		label_2.setHorizontalTextPosition(SwingConstants.CENTER);
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(201, 13, 53, 35);
		panel_2.add(label_2);
		
		JLabel label_3 = new JLabel("D");
		label_3.setHorizontalTextPosition(SwingConstants.CENTER);
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(250, 13, 53, 35);
		panel_2.add(label_3);
		
		JLabel label_4 = new JLabel("E");
		label_4.setHorizontalTextPosition(SwingConstants.CENTER);
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setBounds(301, 13, 53, 35);
		panel_2.add(label_4);
		
		JLabel label_5 = new JLabel("F");
		label_5.setHorizontalTextPosition(SwingConstants.CENTER);
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setBounds(349, 13, 53, 35);
		panel_2.add(label_5);
		
		JLabel label_6 = new JLabel("G");
		label_6.setHorizontalTextPosition(SwingConstants.CENTER);
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setBounds(398, 13, 53, 35);
		panel_2.add(label_6);
		
		JLabel label_7 = new JLabel("H");
		label_7.setHorizontalTextPosition(SwingConstants.CENTER);
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setBounds(445, 13, 53, 35);
		panel_2.add(label_7);
		
		JLabel label_8 = new JLabel("I");
		label_8.setHorizontalTextPosition(SwingConstants.CENTER);
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		label_8.setBounds(500, 13, 53, 35);
		panel_2.add(label_8);
		
		JLabel label_9 = new JLabel("J");
		label_9.setHorizontalTextPosition(SwingConstants.CENTER);
		label_9.setHorizontalAlignment(SwingConstants.CENTER);
		label_9.setBounds(552, 13, 41, 35);
		panel_2.add(label_9);	
		
		JLabel label_10 = new JLabel("1");
		label_10.setHorizontalTextPosition(SwingConstants.CENTER);
		label_10.setHorizontalAlignment(SwingConstants.CENTER);
		label_10.setBounds(58, 47, 42, 51);
		panel_2.add(label_10);
		
		JLabel label_11 = new JLabel("2");
		label_11.setHorizontalTextPosition(SwingConstants.CENTER);
		label_11.setHorizontalAlignment(SwingConstants.CENTER);
		label_11.setBounds(58, 96, 42, 51);
		panel_2.add(label_11);
		
		JLabel label_12 = new JLabel("3");
		label_12.setHorizontalTextPosition(SwingConstants.CENTER);
		label_12.setHorizontalAlignment(SwingConstants.CENTER);
		label_12.setBounds(58, 142, 42, 51);
		panel_2.add(label_12);
		
		JLabel label_13 = new JLabel("4");
		label_13.setHorizontalTextPosition(SwingConstants.CENTER);
		label_13.setHorizontalAlignment(SwingConstants.CENTER);
		label_13.setBounds(58, 188, 42, 51);
		panel_2.add(label_13);
		
		JLabel label_14 = new JLabel("5");
		label_14.setHorizontalTextPosition(SwingConstants.CENTER);
		label_14.setHorizontalAlignment(SwingConstants.CENTER);
		label_14.setBounds(58, 236, 42, 51);
		panel_2.add(label_14);
		
		JLabel label_15 = new JLabel("6");
		label_15.setHorizontalTextPosition(SwingConstants.CENTER);
		label_15.setHorizontalAlignment(SwingConstants.CENTER);
		label_15.setBounds(58, 287, 42, 51);
		panel_2.add(label_15);
		
		JLabel label_16 = new JLabel("7");
		label_16.setHorizontalTextPosition(SwingConstants.CENTER);
		label_16.setHorizontalAlignment(SwingConstants.CENTER);
		label_16.setBounds(58, 334, 42, 51);
		panel_2.add(label_16);
		
		JLabel label_17 = new JLabel("8");
		label_17.setHorizontalTextPosition(SwingConstants.CENTER);
		label_17.setHorizontalAlignment(SwingConstants.CENTER);
		label_17.setBounds(58, 379, 42, 51);
		panel_2.add(label_17);
		
		JLabel label_18 = new JLabel("9");
		label_18.setHorizontalTextPosition(SwingConstants.CENTER);
		label_18.setHorizontalAlignment(SwingConstants.CENTER);
		label_18.setBounds(58, 428, 42, 51);
		panel_2.add(label_18);
		
		JLabel label_19 = new JLabel("10");
		label_19.setHorizontalTextPosition(SwingConstants.CENTER);
		label_19.setHorizontalAlignment(SwingConstants.CENTER);
		label_19.setBounds(58, 477, 42, 51);
		panel_2.add(label_19);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(Color.BLUE, 5));
		panel_4.setLayout(null);
		panel_4.setBounds(0, 0, 135, 256);
		panel_1.add(panel_4);
		
		JLabel lblAlli = new JLabel(equipeAmiral.getNom());
		lblAlli.setHorizontalTextPosition(SwingConstants.CENTER);
		lblAlli.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlli.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblAlli.setBounds(12, 13, 111, 16);
		panel_4.add(lblAlli);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new LineBorder(Color.RED, 5));
		panel_5.setLayout(null);
		panel_5.setBounds(0, 321, 135, 259);
		panel_1.add(panel_5);
		
		JLabel lblNewLabel = new JLabel(equipeAmiral.getEquipeAdverse().getNom());
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(12, 13, 111, 16);
		panel_5.add(lblNewLabel);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBounds(746, 0, 264, 614);
		general.add(panel_7);
		panel_7.setLayout(new GridLayout(0, 3, 0, 0));
		
		JPanel colonneMatelot = new JPanel();
		panel_7.add(colonneMatelot);
		colonneMatelot.setLayout(new GridLayout(24, 1, 0, 0));
		
		
		//Création de la liste des matelots
	    idMatelots = new int[nbMatelots];
	    buttonsMatelots = new JButton[nbMatelots];
	    labelsRoles = new JLabel[nbMatelots];
	    labelsNavires = new JLabel[nbMatelots];
		
		JLabel lblNewLabel_1 = new JLabel("Matelot");
		lblNewLabel_1.setBorder(new LineBorder(COULEUR_BORDURE_MATELOTS));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		colonneMatelot.add(lblNewLabel_1);
		
		JPanel colonneRole = new JPanel();
		panel_7.add(colonneRole);
		colonneRole.setLayout(new GridLayout(24, 1, 0, 0));
		
		JLabel lblRle = new JLabel("Rôle");
		lblRle.setBorder(new LineBorder(COULEUR_BORDURE_MATELOTS));
		lblRle.setHorizontalAlignment(SwingConstants.CENTER);
		colonneRole.add(lblRle);
		
		JPanel colonneNavire = new JPanel();
		panel_7.add(colonneNavire);
		colonneNavire.setLayout(new GridLayout(24, 1, 0, 0));
		
		JLabel lblNavire = new JLabel("Navire");
		lblNavire.setBorder(new LineBorder(COULEUR_BORDURE_MATELOTS));
		lblNavire.setHorizontalAlignment(SwingConstants.CENTER);
		colonneNavire.add(lblNavire);
		
		Matelot matelotActu = null;
		for(int i = 0; i < nbMatelots; i++) {
			matelotActu = equipeAmiral.getAMatelotDansListe(i);
			//Initialisation
			idMatelots[i]      = matelotActu.getId();
			buttonsMatelots[i] = new JButton(matelotActu.getNom());
			labelsRoles[i]     = new JLabel(matelotActu.getRoleString(), SwingConstants.CENTER);
			labelsNavires[i]   = new JLabel(matelotActu.getNomsNaviresControles(), SwingConstants.CENTER);
			
			//Mise en forme
			buttonsMatelots[i].setBorder(new LineBorder(COULEUR_BORDURE_MATELOTS));
			buttonsMatelots[i].setBackground(COULEUR_FOND_NON_SELECT);
			labelsRoles[i].setBorder(new LineBorder(COULEUR_BORDURE_MATELOTS));
			labelsRoles[i].setBackground(COULEUR_FOND_MATELOTS);
			labelsNavires[i].setBorder(new LineBorder(COULEUR_BORDURE_MATELOTS));
			labelsNavires[i].setBackground(COULEUR_FOND_MATELOTS);
			
			//Placement
			colonneMatelot.add(buttonsMatelots[i]);
			colonneRole.add(labelsRoles[i]);
			colonneNavire.add(labelsNavires[i]);
		}
		
		JPanel panelNavirePlacement = new JPanel();
		panelNavirePlacement.setLayout(new BoxLayout(panelNavirePlacement, BoxLayout.X_AXIS));
		panelNavirePlacement.setBounds(0, 600, general.getWidth(), 20);
		general.add(panelNavirePlacement);
		Navire[] navires = equipeAmiral.getNavires();
		boutonsNavires = new JButton[navires.length];
		for(int i = 0; i < navires.length; i++) {
			//Si on a le temps, modifier la taille du bouton en fonction du type du navire
			boutonsNavires[i] = new JButton(navires[i].getNom());
			boutonsNavires[i].setBackground(COULEUR_FOND_NON_SELECT);
			panelNavirePlacement.add(boutonsNavires[i]);
		}
		retireNavire = new JButton("Retirer Navire");
		panelNavirePlacement.add(retireNavire);
		tourneNavire = new JButton(TOURNE_HORIZONTAL);
		panelNavirePlacement.add(tourneNavire);
		affecteNavire = new JButton("Affecter navire au matelot");
		panelNavirePlacement.add(affecteNavire);
		
		
		boutonPret = new JButton("Prêt à commencer la partie");
		boutonPret.setBounds(0, 625, 200, 50);
		general.add(boutonPret);
		
		affecteRoleAttaque = new JButton("Affectation attaque");
		affecteRoleAttaque.setBounds(210, 625, 200, 50);
		general.add(affecteRoleAttaque);
		affecteRoleDefenseur = new JButton("Affectation défense");
		affecteRoleDefenseur.setBounds(420, 625, 200, 50);
		general.add(affecteRoleDefenseur);
		
		
		launchTimers(amiralTrouve.getId());
	}
	
	private void launchTimers(int idJoueur) {
		if(idJoueur == Game.getID_HOTE())
			timerDechargement.start();
		timerAffichage.start();
	}
	
	public void setControlButton(ActionListener listener) {
		for(int x = 0; x < buttonsGrille.length; x++) {
			for(int y = 0; y < buttonsGrille[x].length; y++) {
				buttonsGrille[x][y].addActionListener(listener);
			}
		}
		for(int i = 0; i < nbMatelots; i++) {
			buttonsMatelots[i].addActionListener(listener);
		}
		for(int i = 0; i < boutonsNavires.length; i++) {
			boutonsNavires[i].addActionListener(listener);
		}
		boutonPret.addActionListener(listener);
		
		retireNavire.addActionListener(listener);
		tourneNavire.addActionListener(listener);
		affecteNavire.addActionListener(listener);
		
		affecteRoleAttaque.addActionListener(listener);
		affecteRoleDefenseur.addActionListener(listener);
		//Autres éléments donner
	}
	
	public void display() {
	    setVisible(true);
	}

	public void undisplay() {
	    setVisible(false);
	}
	
	public void changeIdMatelot(int numeroBouton) {
		//On en profite pour changer la couleur des boutons
		if(placeMatelotSelect >= 0)
		    buttonsMatelots[placeMatelotSelect].setBackground(COULEUR_FOND_NON_SELECT);
		idMatelotSelect = idMatelots[numeroBouton];
		buttonsMatelots[numeroBouton].setBackground(COULEUR_FOND_SELECT);
		placeMatelotSelect = numeroBouton;
	}
	
	public void changeNomNavire(String nomNavire) {
		if(nomNavire.equals(""))
			return;
		nomNavireSelectionne = nomNavire;
		
		model.actualisePartieActu();
		Game partie = model.getPartieActu();
		Amiral amiralTrouve = partie.getAmiral(model.getUtilisateur().getId());
		if(amiralTrouve == null)
			return;
		changeAffichageNavire(amiralTrouve.getEquipe().getGrille());
	}
	
	public void changeVue() {
		model.actualisePartieActu();
		Game partie = model.getPartieActu();
		Amiral amiralTrouve = partie.getAmiral(model.getUtilisateur().getId());
		if(amiralTrouve == null)
			return;
		
		//amiralTrouve.placeTousLesNavires();
		//System.out.println("Equipe adverse");
		//amiralTrouve.getEquipe().getEquipeAdverse().getGrille().printGrille(true);
		changeAffichageNavire(amiralTrouve.getEquipe().getGrille());
		//Autres modifications
	}
	
	private void changeAffichageNavire(Grille grille) {
		//grille.printGrille(true);
		for(int i = 0; i < boutonsNavires.length; i++) {
			if(boutonsNavires[i].getText().equals(nomNavireSelectionne))
				boutonsNavires[i].setBackground(COULEUR_FOND_SELECT);
			else
				boutonsNavires[i].setBackground(COULEUR_FOND_NON_SELECT);
		}
		
		//Modification de la grille de boutons (inspiré de printGrille)
		Navire navireActu      = null;
    	String etatActu        = "";
    	Case caseGrilleActu    = null;
    	Case caseGraphiqueActu = null;
    	PieceNavire pieceActu  = null;
    	
    	for(int x = 0; x < Grille.getLines(); x++) {
    		for(int y = 0; y < Grille.getColumns(); y++) {
    			caseGraphiqueActu = buttonsGrille[x][y];
    			caseGrilleActu    = grille.getCases()[x][y];
    			
    			if(caseGrilleActu == null) {
    				navireActu = null;
    			} else {
    				pieceActu = caseGrilleActu.getPiecePose();
    				//Si il existe une pièce sur la case et si on recherche par matelot, ce dernier possède le navire qui y est attaché
    				if(pieceActu != null) {
    					navireActu = pieceActu.getNavireAttache();
    					if(pieceActu.getNavireAttache().isEstCoule())
    						etatActu = "(D)"; //D pour "Dead"
    					else if(pieceActu.isEstEndommage())
    						etatActu = "(X)"; //La pièce est endommagée
    					else
    						etatActu = "(O)"; //La pièce est en bon état
    				} else {
    					navireActu = null; //Aucun Navire, la case est vide
    				}
    			}
    			
    			//Modification
    			if(navireActu == null) {
    				caseGraphiqueActu.setText("");
    				caseGraphiqueActu.changeBorderToDefault();
			    	caseGraphiqueActu.setBackground(COULEUR_FOND_BOUTON);
    			} else {
    				caseGraphiqueActu.setText(navireActu.getNom() + " " + etatActu);
    				caseGraphiqueActu.setBorder(new CompoundBorder(new LineBorder(COULEUR_BORDURE_NAVIRE), new MatteBorder(2, 2, 2, 2, (Color) COULEUR_BORDURE_NAVIRE)));	
    			    if(navireActu.getNom().equals(nomNavireSelectionne))
    			    	caseGraphiqueActu.setBackground(COULEUR_FOND_SELECT); //Met en évidence le bouton sélectionné
    			    else
    			    	caseGraphiqueActu.setBackground(COULEUR_FOND_BOUTON);
    			}
    		}
    	}
	}

	public void demandePlacement(int posX, int posY) {
		model.placeDeplaceNavire(nomNavireSelectionne, posX, posY);
	}
}
