package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.border.LineBorder;
import java.awt.Color;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import model.Amiral;
import model.Bataille_navale_model;
import model.Case;
import model.Game;
import model.Grille;
import model.Matelot;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class View_matelot extends JFrame{

	protected Bataille_navale_model model;
    public Case[][] buttonsGrilleEquipe;
    public Case[][] buttonsGrilleAdverse;
	
	public View_matelot(Bataille_navale_model model) {
		this.model = model;
		initialize();
		//display();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//Récupérer la partie
		model.actualisePartieActu();
		Game gameDebut = model.getPartieActu();
		Matelot matelotTrouve = gameDebut.getMatelot(model.getUtilisateur().getId());
		if(matelotTrouve == null)
			return;
		
		Grille grilleEquipeDebut = matelotTrouve.getEquipe().getGrille();
		Grille grilleAdverseDebut = matelotTrouve.getEquipe().getEquipeAdverse().getGrille();
		
		setPreferredSize(new Dimension(1600, 900));
		setSize(new Dimension(1600, 900));
		setResizable(false);
		setTitle("Partie (matelot)");
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 56, 772, 547);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_zone_grille = new JPanel();
		panel_zone_grille.setBounds(139, 0, 639, 615);
		panel.add(panel_zone_grille);
		panel_zone_grille.setLayout(null);
		
		JPanel panel_allie_button = new JPanel();
		panel_allie_button.setBounds(99, 48, 524, 504);
		panel_zone_grille.add(panel_allie_button);
		panel_allie_button.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_allie_button.setLayout(new GridLayout(10, 10, 0, 0));
		
		//Initialisation des tableaux de boutons
		buttonsGrilleEquipe = grilleEquipeDebut.getCases();
		Case caseActu = null;
		for(int x = 0; x < Grille.getLines(); x++) {
			for(int y = 0; y < Grille.getColumns(); y++) {
				caseActu = buttonsGrilleEquipe[x][y];
				caseActu.setBackground(Color.WHITE);
				caseActu.changeBorderToDefault();
				caseActu.setAlignmentY(0.0f);
				panel_allie_button.add(caseActu);
			}
		}
		
		JLabel lblA = new JLabel("A");
		lblA.setHorizontalTextPosition(SwingConstants.CENTER);
		lblA.setHorizontalAlignment(SwingConstants.CENTER);
		lblA.setBounds(102, 13, 53, 35);
		panel_zone_grille.add(lblA);
		
		JLabel lblB = new JLabel("B");
		lblB.setHorizontalTextPosition(SwingConstants.CENTER);
		lblB.setHorizontalAlignment(SwingConstants.CENTER);
		lblB.setBounds(152, 13, 53, 35);
		panel_zone_grille.add(lblB);
		
		JLabel lblC = new JLabel("C");
		lblC.setHorizontalTextPosition(SwingConstants.CENTER);
		lblC.setHorizontalAlignment(SwingConstants.CENTER);
		lblC.setBounds(201, 13, 53, 35);
		panel_zone_grille.add(lblC);
		
		JLabel lblD = new JLabel("D");
		lblD.setHorizontalTextPosition(SwingConstants.CENTER);
		lblD.setHorizontalAlignment(SwingConstants.CENTER);
		lblD.setBounds(253, 13, 53, 35);
		panel_zone_grille.add(lblD);
		
		JLabel lblE = new JLabel("E");
		lblE.setHorizontalTextPosition(SwingConstants.CENTER);
		lblE.setHorizontalAlignment(SwingConstants.CENTER);
		lblE.setBounds(309, 13, 53, 35);
		panel_zone_grille.add(lblE);
		
		JLabel lblF = new JLabel("F");
		lblF.setHorizontalTextPosition(SwingConstants.CENTER);
		lblF.setHorizontalAlignment(SwingConstants.CENTER);
		lblF.setBounds(363, 13, 53, 35);
		panel_zone_grille.add(lblF);
		
		JLabel lblG = new JLabel("G");
		lblG.setHorizontalTextPosition(SwingConstants.CENTER);
		lblG.setHorizontalAlignment(SwingConstants.CENTER);
		lblG.setBounds(410, 13, 53, 35);
		panel_zone_grille.add(lblG);
		
		JLabel lblH = new JLabel("H");
		lblH.setHorizontalTextPosition(SwingConstants.CENTER);
		lblH.setHorizontalAlignment(SwingConstants.CENTER);
		lblH.setBounds(462, 13, 53, 35);
		panel_zone_grille.add(lblH);
		
		JLabel lblI = new JLabel("I");
		lblI.setHorizontalTextPosition(SwingConstants.CENTER);
		lblI.setHorizontalAlignment(SwingConstants.CENTER);
		lblI.setBounds(515, 13, 53, 35);
		panel_zone_grille.add(lblI);
		
		JLabel lblJ = new JLabel("J");
		lblJ.setHorizontalTextPosition(SwingConstants.CENTER);
		lblJ.setHorizontalAlignment(SwingConstants.CENTER);
		lblJ.setBounds(569, 13, 53, 35);
		panel_zone_grille.add(lblJ);
		
		JLabel label = new JLabel("1");
		label.setHorizontalTextPosition(SwingConstants.CENTER);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(58, 47, 42, 51);
		panel_zone_grille.add(label);
		
		JLabel label_1 = new JLabel("2");
		label_1.setHorizontalTextPosition(SwingConstants.CENTER);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(58, 102, 42, 51);
		panel_zone_grille.add(label_1);
		
		JLabel label_2 = new JLabel("3");
		label_2.setHorizontalTextPosition(SwingConstants.CENTER);
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(58, 149, 42, 51);
		panel_zone_grille.add(label_2);
		
		JLabel label_3 = new JLabel("4");
		label_3.setHorizontalTextPosition(SwingConstants.CENTER);
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(58, 198, 42, 51);
		panel_zone_grille.add(label_3);
		
		JLabel label_4 = new JLabel("5");
		label_4.setHorizontalTextPosition(SwingConstants.CENTER);
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setBounds(58, 249, 42, 51);
		panel_zone_grille.add(label_4);
		
		JLabel label_5 = new JLabel("6");
		label_5.setHorizontalTextPosition(SwingConstants.CENTER);
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setBounds(58, 297, 42, 51);
		panel_zone_grille.add(label_5);
		
		JLabel label_6 = new JLabel("7");
		label_6.setHorizontalTextPosition(SwingConstants.CENTER);
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setBounds(58, 350, 42, 51);
		panel_zone_grille.add(label_6);
		
		JLabel label_7 = new JLabel("8");
		label_7.setHorizontalTextPosition(SwingConstants.CENTER);
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setBounds(58, 399, 42, 51);
		panel_zone_grille.add(label_7);
		
		JLabel label_8 = new JLabel("9");
		label_8.setHorizontalTextPosition(SwingConstants.CENTER);
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		label_8.setBounds(58, 446, 42, 51);
		panel_zone_grille.add(label_8);
		
		JLabel label_9 = new JLabel("10");
		label_9.setHorizontalTextPosition(SwingConstants.CENTER);
		label_9.setHorizontalAlignment(SwingConstants.CENTER);
		label_9.setBounds(58, 498, 42, 51);
		panel_zone_grille.add(label_9);
		
		JPanel panel_zone_bateau = new JPanel();
		panel_zone_bateau.setBorder(new LineBorder(new Color(0, 0, 255), 3));
		panel_zone_bateau.setBounds(0, 0, 135, 240);
		panel.add(panel_zone_bateau);
		panel_zone_bateau.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(796, 56, 798, 547);
		getContentPane().add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBounds(0, 0, 655, 615);
		panel_1.add(panel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setBounds(99, 48, 524, 504);
		panel_2.add(panel_3);
		panel_3.setLayout(new GridLayout(10, 10, 0, 0));
		
		buttonsGrilleAdverse = grilleAdverseDebut.getCases();
		Case caseAdverse = null;
		for(int x = 0; x < Grille.getLines(); x++) {
			for(int y = 0; y < Grille.getColumns(); y++) {
				caseAdverse = buttonsGrilleAdverse[x][y];
				caseAdverse.setBackground(Color.WHITE);
				caseAdverse.changeBorderToDefault();
				caseAdverse.setAlignmentY(0.0f);
				panel_3.add(caseAdverse);
			}
		}
		
		JLabel label_10 = new JLabel("A");
		label_10.setHorizontalTextPosition(SwingConstants.CENTER);
		label_10.setHorizontalAlignment(SwingConstants.CENTER);
		label_10.setBounds(102, 13, 53, 35);
		panel_2.add(label_10);
		
		JLabel label_11 = new JLabel("B");
		label_11.setHorizontalTextPosition(SwingConstants.CENTER);
		label_11.setHorizontalAlignment(SwingConstants.CENTER);
		label_11.setBounds(152, 13, 53, 35);
		panel_2.add(label_11);
		
		JLabel label_12 = new JLabel("C");
		label_12.setHorizontalTextPosition(SwingConstants.CENTER);
		label_12.setHorizontalAlignment(SwingConstants.CENTER);
		label_12.setBounds(201, 13, 53, 35);
		panel_2.add(label_12);
		
		JLabel label_13 = new JLabel("D");
		label_13.setHorizontalTextPosition(SwingConstants.CENTER);
		label_13.setHorizontalAlignment(SwingConstants.CENTER);
		label_13.setBounds(253, 13, 53, 35);
		panel_2.add(label_13);
		
		JLabel label_14 = new JLabel("E");
		label_14.setHorizontalTextPosition(SwingConstants.CENTER);
		label_14.setHorizontalAlignment(SwingConstants.CENTER);
		label_14.setBounds(309, 13, 53, 35);
		panel_2.add(label_14);
		
		JLabel label_15 = new JLabel("F");
		label_15.setHorizontalTextPosition(SwingConstants.CENTER);
		label_15.setHorizontalAlignment(SwingConstants.CENTER);
		label_15.setBounds(363, 13, 53, 35);
		panel_2.add(label_15);
		
		JLabel label_16 = new JLabel("G");
		label_16.setHorizontalTextPosition(SwingConstants.CENTER);
		label_16.setHorizontalAlignment(SwingConstants.CENTER);
		label_16.setBounds(410, 13, 53, 35);
		panel_2.add(label_16);
		
		JLabel label_17 = new JLabel("H");
		label_17.setHorizontalTextPosition(SwingConstants.CENTER);
		label_17.setHorizontalAlignment(SwingConstants.CENTER);
		label_17.setBounds(462, 13, 53, 35);
		panel_2.add(label_17);
		
		JLabel label_18 = new JLabel("I");
		label_18.setHorizontalTextPosition(SwingConstants.CENTER);
		label_18.setHorizontalAlignment(SwingConstants.CENTER);
		label_18.setBounds(515, 13, 53, 35);
		panel_2.add(label_18);
		
		JLabel label_19 = new JLabel("J");
		label_19.setHorizontalTextPosition(SwingConstants.CENTER);
		label_19.setHorizontalAlignment(SwingConstants.CENTER);
		label_19.setBounds(569, 13, 53, 35);
		panel_2.add(label_19);
		
		JLabel label_20 = new JLabel("1");
		label_20.setHorizontalTextPosition(SwingConstants.CENTER);
		label_20.setHorizontalAlignment(SwingConstants.CENTER);
		label_20.setBounds(58, 47, 42, 51);
		panel_2.add(label_20);
		
		JLabel label_21 = new JLabel("2");
		label_21.setHorizontalTextPosition(SwingConstants.CENTER);
		label_21.setHorizontalAlignment(SwingConstants.CENTER);
		label_21.setBounds(58, 102, 42, 51);
		panel_2.add(label_21);
		
		JLabel label_22 = new JLabel("3");
		label_22.setHorizontalTextPosition(SwingConstants.CENTER);
		label_22.setHorizontalAlignment(SwingConstants.CENTER);
		label_22.setBounds(58, 149, 42, 51);
		panel_2.add(label_22);
		
		JLabel label_23 = new JLabel("4");
		label_23.setHorizontalTextPosition(SwingConstants.CENTER);
		label_23.setHorizontalAlignment(SwingConstants.CENTER);
		label_23.setBounds(58, 198, 42, 51);
		panel_2.add(label_23);
		
		JLabel label_24 = new JLabel("5");
		label_24.setHorizontalTextPosition(SwingConstants.CENTER);
		label_24.setHorizontalAlignment(SwingConstants.CENTER);
		label_24.setBounds(58, 249, 42, 51);
		panel_2.add(label_24);
		
		JLabel label_25 = new JLabel("6");
		label_25.setHorizontalTextPosition(SwingConstants.CENTER);
		label_25.setHorizontalAlignment(SwingConstants.CENTER);
		label_25.setBounds(58, 297, 42, 51);
		panel_2.add(label_25);
		
		JLabel label_26 = new JLabel("7");
		label_26.setHorizontalTextPosition(SwingConstants.CENTER);
		label_26.setHorizontalAlignment(SwingConstants.CENTER);
		label_26.setBounds(58, 350, 42, 51);
		panel_2.add(label_26);
		
		JLabel label_27 = new JLabel("8");
		label_27.setHorizontalTextPosition(SwingConstants.CENTER);
		label_27.setHorizontalAlignment(SwingConstants.CENTER);
		label_27.setBounds(58, 399, 42, 51);
		panel_2.add(label_27);
		
		JLabel label_28 = new JLabel("9");
		label_28.setHorizontalTextPosition(SwingConstants.CENTER);
		label_28.setHorizontalAlignment(SwingConstants.CENTER);
		label_28.setBounds(58, 446, 42, 51);
		panel_2.add(label_28);
		
		JLabel label_29 = new JLabel("10");
		label_29.setHorizontalTextPosition(SwingConstants.CENTER);
		label_29.setHorizontalAlignment(SwingConstants.CENTER);
		label_29.setBounds(58, 498, 42, 51);
		panel_2.add(label_29);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(Color.RED, 3));
		panel_4.setLayout(null);
		panel_4.setBounds(660, 0, 135, 240);
		panel_1.add(panel_4);
		
		JLabel lblNewLabel = new JLabel("Le camp adverse");
		lblNewLabel.setBounds(1110, 616, 123, 16);
		getContentPane().add(lblNewLabel);
		
		JLabel lblVotreCamp = new JLabel("Votre camp");
		lblVotreCamp.setBounds(477, 616, 123, 16);
		getContentPane().add(lblVotreCamp);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{panel_allie_button, getContentPane()}));
	}
	
	public void setControlButton(ActionListener listener) {
		for(int x = 0; x < Grille.getLines(); x++) {
			for(int y = 0; y < Grille.getColumns(); y++) {
				buttonsGrilleEquipe[x][y].addActionListener(listener);
				buttonsGrilleAdverse[x][y].addActionListener(listener);
			}
		}
		//Autres éléments donner
	}
	
	 public void display() {
	     setVisible(true);
	 }

	 public void undisplay() {
	     setVisible(false);
	 }
}
