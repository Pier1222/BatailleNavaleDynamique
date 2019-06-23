package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.border.LineBorder;
import java.awt.Color;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import model.Bataille_navale_model;
import model.Case;
import model.Grille;

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
		setPreferredSize(new Dimension(1600, 900));
		setSize(new Dimension(1600, 900));
		setResizable(false);
		setTitle("Interface graphique statique");
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
		
		buttonsGrilleEquipe = new Case[Grille.getLines()][Grille.getColumns()];
		for(int x = 0; x < Grille.getLines(); x++) {
			for(int y = 0; y < Grille.getColumns(); y++) {
				buttonsGrilleEquipe[x][y] = new Case(x, y);
				//panel_allie_button.add(buttonsGrilleEquipe[x][y]); à réactiver quand tous le reste sera viré
				buttonsGrilleEquipe[x][y].setAlignmentY(0.0f);
			}
		}
		
		buttonsGrilleAdverse = new Case[Grille.getLines()][Grille.getColumns()];
		for(int x = 0; x < Grille.getLines(); x++) {
			for(int y = 0; y < Grille.getColumns(); y++) {
				buttonsGrilleAdverse[x][y] = new Case(x, y);
				//panel_3.add(button_150); à réactiver quand tous le reste sera viré
				buttonsGrilleAdverse[x][y].setAlignmentY(0.0f);
			}
		}
		
		JButton button = new JButton("");
		button.setBackground(new Color(255, 255, 255));
		button.setBorder(new LineBorder(new Color(153, 255, 255)));
		panel_allie_button.add(button);
		button.setAlignmentY(0.0f);
		
		JButton button_2 = new JButton("");
		button_2.setBackground(new Color(255, 255, 255));
		button_2.setBorder(new LineBorder(new Color(153, 255, 255)));
		panel_allie_button.add(button_2);
		button_2.setAlignmentY(0.0f);
		
		JButton button_1 = new JButton("");
		button_1.setBackground(new Color(255, 255, 255));
		button_1.setBorder(new CompoundBorder(new LineBorder(new Color(153, 255, 255)), new MatteBorder(0, 0, 0, 5, (Color) new Color(0, 0, 0))));
		panel_allie_button.add(button_1);
		button_1.setAlignmentY(0.0f);
		
		JButton button_5 = new JButton("");
		button_5.setBackground(new Color(255, 255, 255));
		button_5.setBorder(new LineBorder(new Color(153, 255, 255)));
		panel_allie_button.add(button_5);
		button_5.setAlignmentY(0.0f);
		
		JButton button_3 = new JButton("");
		button_3.setBackground(new Color(255, 255, 255));
		button_3.setBorder(new LineBorder(new Color(153, 255, 255)));
		panel_allie_button.add(button_3);
		button_3.setAlignmentY(0.0f);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setBorder(new LineBorder(new Color(153, 255, 255)));
		panel_allie_button.add(btnNewButton);
		btnNewButton.setAlignmentY(0.0f);
		
		JButton button_4 = new JButton("");
		button_4.setBackground(new Color(255, 255, 255));
		button_4.setBorder(new CompoundBorder(new LineBorder(new Color(153, 255, 255)), new MatteBorder(0, 0, 0, 5, (Color) new Color(0, 0, 0))));
		panel_allie_button.add(button_4);
		button_4.setAlignmentY(0.0f);
		
		JButton button_6 = new JButton("");
		button_6.setBackground(new Color(255, 255, 255));
		button_6.setBorder(new LineBorder(new Color(153, 255, 255)));
		panel_allie_button.add(button_6);
		button_6.setAlignmentY(0.0f);
		
		JButton button_7 = new JButton("");
		button_7.setBackground(new Color(255, 255, 255));
		button_7.setBorder(new LineBorder(new Color(153, 255, 255)));
		panel_allie_button.add(button_7);
		button_7.setAlignmentY(0.0f);
		
		JButton button_8 = new JButton("");
		button_8.setBackground(new Color(255, 255, 255));
		button_8.setBorder(new LineBorder(new Color(153, 255, 255)));
		panel_allie_button.add(button_8);
		button_8.setAlignmentY(0.0f);
		
		JButton button_9 = new JButton("");
		button_9.setBackground(new Color(255, 255, 255));
		button_9.setBorder(new LineBorder(new Color(153, 255, 255)));
		panel_allie_button.add(button_9);
		button_9.setAlignmentY(0.0f);
		
		JButton button_10 = new JButton("");
		button_10.setBackground(new Color(255, 255, 255));
		button_10.setBorder(new LineBorder(new Color(153, 255, 255)));
		panel_allie_button.add(button_10);
		button_10.setAlignmentY(0.0f);
		
		JButton button_11 = new JButton("");
		button_11.setBackground(new Color(255, 255, 255));
		button_11.setBorder(new CompoundBorder(new LineBorder(new Color(153, 255, 255)), new MatteBorder(0, 0, 0, 5, (Color) new Color(0, 0, 0))));
		panel_allie_button.add(button_11);
		button_11.setAlignmentY(0.0f);
		
		JButton button_12 = new JButton("");
		button_12.setBackground(new Color(255, 255, 255));
		button_12.setBorder(new LineBorder(new Color(153, 255, 255)));
		panel_allie_button.add(button_12);
		button_12.setAlignmentY(0.0f);
		
		JButton button_13 = new JButton("");
		button_13.setBackground(new Color(255, 255, 255));
		button_13.setBorder(new LineBorder(new Color(153, 255, 255)));
		panel_allie_button.add(button_13);
		button_13.setAlignmentY(0.0f);
		
		JButton button_14 = new JButton("");
		button_14.setBackground(new Color(255, 255, 255));
		button_14.setBorder(new LineBorder(new Color(153, 255, 255)));
		panel_allie_button.add(button_14);
		button_14.setAlignmentY(0.0f);
		
		JButton button_15 = new JButton("");
		button_15.setBackground(new Color(255, 255, 255));
		button_15.setBorder(new CompoundBorder(new LineBorder(new Color(153, 255, 255)), new MatteBorder(0, 0, 0, 5, (Color) new Color(0, 0, 0))));
		panel_allie_button.add(button_15);
		button_15.setAlignmentY(0.0f);
		
		JButton button_16 = new JButton("");
		button_16.setBackground(new Color(255, 255, 255));
		button_16.setBorder(new LineBorder(new Color(153, 255, 255)));
		panel_allie_button.add(button_16);
		button_16.setAlignmentY(0.0f);
		
		JButton button_80 = new JButton("");
		button_80.setBackground(new Color(255, 255, 255));
		button_80.setBorder(new LineBorder(new Color(153, 255, 255)));
		panel_allie_button.add(button_80);
		button_80.setAlignmentY(0.0f);
		
		JButton button_81 = new JButton("");
		button_81.setBackground(new Color(255, 255, 255));
		button_81.setBorder(new LineBorder(new Color(153, 255, 255)));
		panel_allie_button.add(button_81);
		button_81.setAlignmentY(0.0f);
		
		JButton button_82 = new JButton("");
		button_82.setBackground(new Color(255, 255, 255));
		button_82.setBorder(new CompoundBorder(new LineBorder(new Color(153, 255, 255)), new MatteBorder(0, 0, 5, 0, (Color) new Color(0, 0, 0))));
		panel_allie_button.add(button_82);
		button_82.setAlignmentY(0.0f);
		
		JButton button_17 = new JButton("");
		button_17.setBackground(new Color(255, 255, 255));
		button_17.setBorder(new CompoundBorder(new LineBorder(new Color(153, 255, 255)), new MatteBorder(0, 0, 5, 0, (Color) new Color(0, 0, 0))));
		panel_allie_button.add(button_17);
		button_17.setAlignmentY(0.0f);
		
		JButton button_18 = new JButton("");
		button_18.setBackground(new Color(255, 255, 255));
		button_18.setBorder(new CompoundBorder(new LineBorder(new Color(153, 255, 255)), new MatteBorder(0, 0, 5, 5, (Color) new Color(0, 0, 0))));
		panel_allie_button.add(button_18);
		button_18.setAlignmentY(0.0f);
		
		JButton button_19 = new JButton("");
		button_19.setBackground(new Color(255, 255, 255));
		button_19.setBorder(new CompoundBorder(new LineBorder(new Color(153, 255, 255)), new MatteBorder(0, 0, 5, 0, (Color) new Color(0, 0, 0))));
		panel_allie_button.add(button_19);
		button_19.setAlignmentY(0.0f);
		
		JButton button_20 = new JButton("");
		button_20.setBackground(new Color(255, 255, 255));
		button_20.setBorder(new CompoundBorder(new LineBorder(new Color(153, 255, 255)), new MatteBorder(0, 0, 5, 0, (Color) new Color(0, 0, 0))));
		panel_allie_button.add(button_20);
		button_20.setAlignmentY(0.0f);
		
		JButton button_21 = new JButton("");
		button_21.setBackground(new Color(255, 255, 255));
		button_21.setBorder(new CompoundBorder(new LineBorder(new Color(153, 255, 255)), new MatteBorder(0, 0, 5, 0, (Color) new Color(0, 0, 0))));
		panel_allie_button.add(button_21);
		button_21.setAlignmentY(0.0f);
		
		JButton button_22 = new JButton("");
		button_22.setBackground(new Color(255, 255, 255));
		button_22.setBorder(new CompoundBorder(new LineBorder(new Color(153, 255, 255)), new MatteBorder(0, 0, 5, 5, (Color) new Color(0, 0, 0))));
		panel_allie_button.add(button_22);
		button_22.setAlignmentY(0.0f);
		
		JButton button_23 = new JButton("");
		button_23.setBackground(new Color(255, 255, 255));
		button_23.setBorder(new CompoundBorder(new LineBorder(new Color(153, 255, 255)), new MatteBorder(0, 0, 5, 0, (Color) new Color(0, 0, 0))));
		panel_allie_button.add(button_23);
		button_23.setAlignmentY(0.0f);
		
		JButton button_24 = new JButton("");
		button_24.setBackground(new Color(255, 255, 255));
		button_24.setBorder(new CompoundBorder(new LineBorder(new Color(153, 255, 255)), new MatteBorder(0, 0, 5, 0, (Color) new Color(0, 0, 0))));
		panel_allie_button.add(button_24);
		button_24.setAlignmentY(0.0f);
		
		JButton button_25 = new JButton("");
		button_25.setBackground(new Color(255, 255, 255));
		button_25.setBorder(new CompoundBorder(new LineBorder(new Color(153, 255, 255)), new MatteBorder(0, 0, 5, 0, (Color) new Color(0, 0, 0))));
		panel_allie_button.add(button_25);
		button_25.setAlignmentY(0.0f);
		
		JButton button_26 = new JButton("");
		button_26.setBackground(new Color(255, 255, 255));
		button_26.setBorder(new LineBorder(new Color(153, 255, 255)));
		panel_allie_button.add(button_26);
		button_26.setAlignmentY(0.0f);
		
		JButton button_27 = new JButton("");
		button_27.setBackground(new Color(255, 255, 255));
		button_27.setBorder(new LineBorder(new Color(153, 255, 255)));
		panel_allie_button.add(button_27);
		button_27.setAlignmentY(0.0f);
		
		JButton button_28 = new JButton("");
		button_28.setBackground(new Color(255, 255, 255));
		button_28.setBorder(new CompoundBorder(new LineBorder(new Color(153, 255, 255)), new MatteBorder(0, 0, 0, 5, (Color) new Color(0, 0, 0))));
		panel_allie_button.add(button_28);
		button_28.setAlignmentY(0.0f);
		
		JButton button_29 = new JButton("");
		button_29.setBackground(new Color(255, 255, 255));
		button_29.setBorder(new LineBorder(new Color(153, 255, 255)));
		panel_allie_button.add(button_29);
		button_29.setAlignmentY(0.0f);
		
		JButton button_30 = new JButton("");
		button_30.setBackground(new Color(255, 255, 255));
		button_30.setBorder(new LineBorder(new Color(153, 255, 255)));
		panel_allie_button.add(button_30);
		button_30.setAlignmentY(0.0f);
		
		JButton button_31 = new JButton("");
		button_31.setBackground(new Color(255, 255, 255));
		button_31.setBorder(new LineBorder(new Color(153, 255, 255)));
		panel_allie_button.add(button_31);
		button_31.setAlignmentY(0.0f);
		
		JButton button_32 = new JButton("");
		button_32.setBackground(new Color(255, 255, 255));
		button_32.setBorder(new CompoundBorder(new LineBorder(new Color(153, 255, 255)), new MatteBorder(0, 0, 0, 5, (Color) new Color(0, 0, 0))));
		panel_allie_button.add(button_32);
		button_32.setAlignmentY(0.0f);
		
		JButton button_33 = new JButton("");
		button_33.setBackground(new Color(255, 255, 255));
		button_33.setBorder(new LineBorder(new Color(153, 255, 255)));
		panel_allie_button.add(button_33);
		button_33.setAlignmentY(0.0f);
		
		JButton button_34 = new JButton("");
		button_34.setBackground(new Color(255, 255, 255));
		button_34.setBorder(new LineBorder(new Color(153, 255, 255)));
		panel_allie_button.add(button_34);
		button_34.setAlignmentY(0.0f);
		
		JButton button_35 = new JButton("");
		button_35.setBackground(new Color(255, 255, 255));
		button_35.setBorder(new LineBorder(new Color(153, 255, 255)));
		panel_allie_button.add(button_35);
		button_35.setAlignmentY(0.0f);
		
		JButton button_36 = new JButton("");
		button_36.setBackground(new Color(255, 255, 255));
		button_36.setBorder(new LineBorder(new Color(153, 255, 255)));
		panel_allie_button.add(button_36);
		button_36.setAlignmentY(0.0f);
		
		JButton button_87 = new JButton("");
		button_87.setBackground(new Color(255, 255, 255));
		button_87.setBorder(new LineBorder(new Color(153, 255, 255)));
		panel_allie_button.add(button_87);
		button_87.setAlignmentY(0.0f);
		
		JButton button_85 = new JButton("");
		button_85.setBackground(new Color(255, 255, 255));
		button_85.setBorder(new CompoundBorder(new LineBorder(new Color(153, 255, 255)), new MatteBorder(0, 0, 0, 5, (Color) new Color(0, 0, 0))));
		panel_allie_button.add(button_85);
		button_85.setAlignmentY(0.0f);
		
		JButton button_37 = new JButton("");
		button_37.setBackground(new Color(255, 255, 255));
		button_37.setBorder(new LineBorder(new Color(153, 255, 255)));
		panel_allie_button.add(button_37);
		button_37.setAlignmentY(0.0f);
		
		JButton button_38 = new JButton("");
		button_38.setBackground(new Color(255, 255, 255));
		button_38.setBorder(new LineBorder(new Color(153, 255, 255)));
		panel_allie_button.add(button_38);
		button_38.setAlignmentY(0.0f);
		
		JButton button_39 = new JButton("");
		button_39.setBackground(new Color(255, 255, 255));
		button_39.setBorder(new LineBorder(new Color(153, 255, 255)));
		panel_allie_button.add(button_39);
		button_39.setAlignmentY(0.0f);
		
		JButton button_86 = new JButton("");
		button_86.setBackground(new Color(255, 255, 255));
		button_86.setBorder(new CompoundBorder(new LineBorder(new Color(153, 255, 255)), new MatteBorder(0, 0, 0, 5, (Color) new Color(0, 0, 0))));
		panel_allie_button.add(button_86);
		button_86.setAlignmentY(0.0f);
		
		JButton button_89 = new JButton("");
		button_89.setBackground(new Color(255, 255, 255));
		button_89.setBorder(new LineBorder(new Color(153, 255, 255)));
		panel_allie_button.add(button_89);
		button_89.setAlignmentY(0.0f);
		
		JButton button_83 = new JButton("");
		button_83.setBackground(new Color(255, 255, 255));
		button_83.setBorder(new LineBorder(new Color(153, 255, 255)));
		panel_allie_button.add(button_83);
		button_83.setAlignmentY(0.0f);
		
		JButton button_88 = new JButton("");
		button_88.setBackground(new Color(255, 255, 255));
		button_88.setBorder(new LineBorder(new Color(153, 255, 255)));
		panel_allie_button.add(button_88);
		button_88.setAlignmentY(0.0f);
		
		JButton button_40 = new JButton("");
		button_40.setBackground(new Color(255, 255, 255));
		button_40.setBorder(new LineBorder(new Color(153, 255, 255)));
		panel_allie_button.add(button_40);
		button_40.setAlignmentY(0.0f);
		
		JButton button_41 = new JButton("");
		button_41.setBackground(new Color(255, 255, 255));
		button_41.setBorder(new LineBorder(new Color(153, 255, 255)));
		panel_allie_button.add(button_41);
		button_41.setAlignmentY(0.0f);
		
		JButton button_42 = new JButton("");
		button_42.setBackground(new Color(255, 255, 255));
		button_42.setBorder(new CompoundBorder(new LineBorder(new Color(153, 255, 255)), new MatteBorder(0, 0, 0, 5, (Color) new Color(0, 0, 0))));
		panel_allie_button.add(button_42);
		button_42.setAlignmentY(0.0f);
		
		JButton button_43 = new JButton("");
		button_43.setBackground(new Color(255, 255, 255));
		button_43.setBorder(new LineBorder(new Color(153, 255, 255)));
		panel_allie_button.add(button_43);
		button_43.setAlignmentY(0.0f);
		
		JButton button_84 = new JButton("");
		button_84.setBackground(new Color(255, 255, 255));
		button_84.setBorder(new LineBorder(new Color(153, 255, 255)));
		panel_allie_button.add(button_84);
		button_84.setAlignmentY(0.0f);
		
		JButton button_44 = new JButton("");
		button_44.setBackground(new Color(255, 255, 255));
		button_44.setBorder(new LineBorder(new Color(153, 255, 255)));
		panel_allie_button.add(button_44);
		button_44.setAlignmentY(0.0f);
		
		JButton button_45 = new JButton("");
		button_45.setBackground(new Color(255, 255, 255));
		button_45.setBorder(new CompoundBorder(new LineBorder(new Color(153, 255, 255)), new MatteBorder(0, 0, 0, 5, (Color) new Color(0, 0, 0))));
		panel_allie_button.add(button_45);
		button_45.setAlignmentY(0.0f);
		
		JButton button_46 = new JButton("");
		button_46.setBackground(new Color(255, 255, 255));
		button_46.setBorder(new LineBorder(new Color(153, 255, 255)));
		panel_allie_button.add(button_46);
		button_46.setAlignmentY(0.0f);
		
		JButton button_47 = new JButton("");
		button_47.setBackground(new Color(255, 255, 255));
		button_47.setBorder(new LineBorder(new Color(153, 255, 255)));
		panel_allie_button.add(button_47);
		button_47.setAlignmentY(0.0f);
		
		JButton button_48 = new JButton("");
		button_48.setBackground(new Color(255, 255, 255));
		button_48.setBorder(new LineBorder(new Color(153, 255, 255)));
		panel_allie_button.add(button_48);
		button_48.setAlignmentY(0.0f);
		
		JButton button_49 = new JButton("");
		button_49.setBackground(new Color(255, 255, 255));
		button_49.setBorder(new CompoundBorder(new LineBorder(new Color(153, 255, 255)), new MatteBorder(0, 0, 5, 0, (Color) new Color(0, 0, 0))));
		panel_allie_button.add(button_49);
		button_49.setAlignmentY(0.0f);
		
		JButton button_50 = new JButton("");
		button_50.setBackground(new Color(255, 255, 255));
		button_50.setBorder(new CompoundBorder(new LineBorder(new Color(153, 255, 255)), new MatteBorder(0, 0, 5, 0, (Color) new Color(0, 0, 0))));
		panel_allie_button.add(button_50);
		button_50.setAlignmentY(0.0f);
		
		JButton button_51 = new JButton("");
		button_51.setBackground(new Color(255, 255, 255));
		button_51.setBorder(new CompoundBorder(new LineBorder(new Color(153, 255, 255)), new MatteBorder(0, 0, 5, 5, (Color) new Color(0, 0, 0))));
		panel_allie_button.add(button_51);
		button_51.setAlignmentY(0.0f);
		
		JButton button_52 = new JButton("");
		button_52.setBackground(new Color(255, 255, 255));
		button_52.setBorder(new CompoundBorder(new LineBorder(new Color(153, 255, 255)), new MatteBorder(0, 0, 5, 0, (Color) new Color(0, 0, 0))));
		panel_allie_button.add(button_52);
		button_52.setAlignmentY(0.0f);
		
		JButton button_53 = new JButton("");
		button_53.setBackground(new Color(255, 255, 255));
		button_53.setBorder(new CompoundBorder(new LineBorder(new Color(153, 255, 255)), new MatteBorder(0, 0, 5, 0, (Color) new Color(0, 0, 0))));
		panel_allie_button.add(button_53);
		button_53.setAlignmentY(0.0f);
		
		JButton button_54 = new JButton("");
		button_54.setBackground(new Color(255, 255, 255));
		button_54.setBorder(new CompoundBorder(new LineBorder(new Color(153, 255, 255)), new MatteBorder(0, 0, 5, 0, (Color) new Color(0, 0, 0))));
		panel_allie_button.add(button_54);
		button_54.setAlignmentY(0.0f);
		
		JButton button_55 = new JButton("");
		button_55.setBackground(new Color(255, 255, 255));
		button_55.setBorder(new CompoundBorder(new LineBorder(new Color(153, 255, 255)), new MatteBorder(0, 0, 5, 5, (Color) new Color(0, 0, 0))));
		panel_allie_button.add(button_55);
		button_55.setAlignmentY(0.0f);
		
		JButton button_56 = new JButton("");
		button_56.setBackground(new Color(255, 255, 255));
		button_56.setBorder(new CompoundBorder(new LineBorder(new Color(153, 255, 255)), new MatteBorder(0, 0, 5, 0, (Color) new Color(0, 0, 0))));
		panel_allie_button.add(button_56);
		button_56.setAlignmentY(0.0f);
		
		JButton button_57 = new JButton("");
		button_57.setBackground(new Color(255, 255, 255));
		button_57.setBorder(new CompoundBorder(new LineBorder(new Color(153, 255, 255)), new MatteBorder(0, 0, 5, 0, (Color) new Color(0, 0, 0))));
		panel_allie_button.add(button_57);
		button_57.setAlignmentY(0.0f);
		
		JButton button_58 = new JButton("");
		button_58.setBackground(new Color(255, 255, 255));
		button_58.setBorder(new CompoundBorder(new LineBorder(new Color(153, 255, 255)), new MatteBorder(0, 0, 5, 0, (Color) new Color(0, 0, 0))));
		panel_allie_button.add(button_58);
		button_58.setAlignmentY(0.0f);
		
		JButton button_59 = new JButton("");
		button_59.setBackground(new Color(255, 255, 255));
		button_59.setBorder(new LineBorder(new Color(153, 255, 255)));
		panel_allie_button.add(button_59);
		button_59.setAlignmentY(0.0f);
		
		JButton button_60 = new JButton("");
		button_60.setBackground(new Color(255, 255, 255));
		button_60.setBorder(new LineBorder(new Color(153, 255, 255)));
		panel_allie_button.add(button_60);
		button_60.setAlignmentY(0.0f);
		
		JButton button_61 = new JButton("");
		button_61.setBackground(new Color(255, 255, 255));
		button_61.setBorder(new CompoundBorder(new LineBorder(new Color(153, 255, 255)), new MatteBorder(0, 0, 0, 5, (Color) new Color(0, 0, 0))));
		panel_allie_button.add(button_61);
		button_61.setAlignmentY(0.0f);
		
		JButton button_62 = new JButton("");
		button_62.setBackground(new Color(255, 255, 255));
		button_62.setBorder(new LineBorder(new Color(153, 255, 255)));
		panel_allie_button.add(button_62);
		button_62.setAlignmentY(0.0f);
		
		JButton button_90 = new JButton("");
		button_90.setBackground(new Color(255, 255, 255));
		button_90.setBorder(new LineBorder(new Color(153, 255, 255)));
		panel_allie_button.add(button_90);
		button_90.setAlignmentY(0.0f);
		
		JButton button_63 = new JButton("");
		button_63.setBackground(new Color(255, 255, 255));
		button_63.setBorder(new LineBorder(new Color(153, 255, 255)));
		panel_allie_button.add(button_63);
		button_63.setAlignmentY(0.0f);
		
		JButton button_64 = new JButton("");
		button_64.setBackground(new Color(255, 255, 255));
		button_64.setBorder(new CompoundBorder(new LineBorder(new Color(153, 255, 255)), new MatteBorder(0, 0, 0, 5, (Color) new Color(0, 0, 0))));
		panel_allie_button.add(button_64);
		button_64.setAlignmentY(0.0f);
		
		JButton button_65 = new JButton("");
		button_65.setBackground(new Color(255, 255, 255));
		button_65.setBorder(new LineBorder(new Color(153, 255, 255)));
		panel_allie_button.add(button_65);
		button_65.setAlignmentY(0.0f);
		
		JButton button_66 = new JButton("");
		button_66.setBackground(new Color(255, 255, 255));
		button_66.setBorder(new LineBorder(new Color(153, 255, 255)));
		panel_allie_button.add(button_66);
		button_66.setAlignmentY(0.0f);
		
		JButton button_67 = new JButton("");
		button_67.setBackground(new Color(255, 255, 255));
		button_67.setBorder(new LineBorder(new Color(153, 255, 255)));
		panel_allie_button.add(button_67);
		button_67.setAlignmentY(0.0f);
		
		JButton button_68 = new JButton("");
		button_68.setBackground(new Color(255, 255, 255));
		button_68.setBorder(new LineBorder(new Color(153, 255, 255)));
		panel_allie_button.add(button_68);
		button_68.setAlignmentY(0.0f);
		
		JButton button_69 = new JButton("");
		button_69.setBackground(new Color(255, 255, 255));
		button_69.setBorder(new LineBorder(new Color(153, 255, 255)));
		panel_allie_button.add(button_69);
		button_69.setAlignmentY(0.0f);
		
		JButton button_95 = new JButton("");
		button_95.setBackground(new Color(255, 255, 255));
		button_95.setBorder(new CompoundBorder(new LineBorder(new Color(153, 255, 255)), new MatteBorder(0, 0, 0, 5, (Color) new Color(0, 0, 0))));
		button_95.setAlignmentY(0.0f);
		panel_allie_button.add(button_95);
		
		JButton button_70 = new JButton("");
		button_70.setBackground(new Color(255, 255, 255));
		button_70.setBorder(new LineBorder(new Color(153, 255, 255)));
		panel_allie_button.add(button_70);
		button_70.setAlignmentY(0.0f);
		
		JButton button_91 = new JButton("");
		button_91.setBackground(new Color(255, 255, 255));
		button_91.setBorder(new LineBorder(new Color(153, 255, 255)));
		panel_allie_button.add(button_91);
		button_91.setAlignmentY(0.0f);
		
		JButton button_92 = new JButton("");
		button_92.setBackground(new Color(255, 255, 255));
		button_92.setBorder(new LineBorder(new Color(153, 255, 255)));
		panel_allie_button.add(button_92);
		button_92.setAlignmentY(0.0f);
		
		JButton button_71 = new JButton("");
		button_71.setBackground(new Color(255, 255, 255));
		button_71.setBorder(new CompoundBorder(new LineBorder(new Color(153, 255, 255)), new MatteBorder(0, 0, 0, 5, (Color) new Color(0, 0, 0))));
		panel_allie_button.add(button_71);
		button_71.setAlignmentY(0.0f);
		
		JButton button_72 = new JButton("");
		button_72.setBackground(new Color(255, 255, 255));
		button_72.setBorder(new LineBorder(new Color(153, 255, 255)));
		panel_allie_button.add(button_72);
		button_72.setAlignmentY(0.0f);
		
		JButton button_73 = new JButton("");
		button_73.setBackground(new Color(255, 255, 255));
		button_73.setBorder(new LineBorder(new Color(153, 255, 255)));
		panel_allie_button.add(button_73);
		button_73.setAlignmentY(0.0f);
		
		JButton button_74 = new JButton("");
		button_74.setBackground(new Color(255, 255, 255));
		button_74.setBorder(new LineBorder(new Color(153, 255, 255)));
		panel_allie_button.add(button_74);
		button_74.setAlignmentY(0.0f);
		
		JButton button_75 = new JButton("");
		button_75.setBackground(new Color(255, 255, 255));
		button_75.setBorder(new LineBorder(new Color(153, 255, 255)));
		panel_allie_button.add(button_75);
		button_75.setAlignmentY(0.0f);
		
		JButton button_76 = new JButton("");
		button_76.setBackground(new Color(255, 255, 255));
		button_76.setBorder(new LineBorder(new Color(153, 255, 255)));
		panel_allie_button.add(button_76);
		button_76.setAlignmentY(0.0f);
		
		JButton button_77 = new JButton("");
		button_77.setBackground(new Color(255, 255, 255));
		button_77.setBorder(new CompoundBorder(new LineBorder(new Color(153, 255, 255)), new MatteBorder(0, 0, 0, 5, (Color) new Color(0, 0, 0))));
		panel_allie_button.add(button_77);
		button_77.setAlignmentY(0.0f);
		
		JButton button_78 = new JButton("");
		button_78.setBackground(new Color(255, 255, 255));
		button_78.setBorder(new LineBorder(new Color(153, 255, 255)));
		panel_allie_button.add(button_78);
		button_78.setAlignmentY(0.0f);
		
		JButton button_79 = new JButton("");
		button_79.setBackground(new Color(255, 255, 255));
		button_79.setBorder(new LineBorder(new Color(153, 255, 255)));
		panel_allie_button.add(button_79);
		button_79.setAlignmentY(0.0f);
		
		JButton button_93 = new JButton("");
		button_93.setBackground(new Color(255, 255, 255));
		button_93.setBorder(new LineBorder(new Color(153, 255, 255)));
		button_93.setAlignmentY(0.0f);
		panel_allie_button.add(button_93);
		
		JButton button_94 = new JButton("");
		button_94.setBackground(new Color(255, 255, 255));
		button_94.setBorder(new CompoundBorder(new LineBorder(new Color(153, 255, 255)), new MatteBorder(0, 0, 0, 5, (Color) new Color(0, 0, 0))));
		button_94.setAlignmentY(0.0f);
		panel_allie_button.add(button_94);
		
		JButton button_96 = new JButton("");
		button_96.setBackground(new Color(255, 255, 255));
		button_96.setBorder(new LineBorder(new Color(153, 255, 255)));
		button_96.setAlignmentY(0.0f);
		panel_allie_button.add(button_96);
		
		JButton button_97 = new JButton("");
		button_97.setBackground(new Color(255, 255, 255));
		button_97.setBorder(new LineBorder(new Color(153, 255, 255)));
		button_97.setAlignmentY(0.0f);
		panel_allie_button.add(button_97);
		
		JButton button_98 = new JButton("");
		button_98.setBackground(new Color(255, 255, 255));
		button_98.setBorder(new LineBorder(new Color(153, 255, 255)));
		button_98.setAlignmentY(0.0f);
		panel_allie_button.add(button_98);
		
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
		
		JButton button_99 = new JButton("");
		button_99.setBorder(new LineBorder(new Color(153, 255, 255)));
		button_99.setBackground(Color.WHITE);
		button_99.setAlignmentY(0.0f);
		panel_3.add(button_99);
		
		JButton button_100 = new JButton("");
		button_100.setBorder(new LineBorder(new Color(153, 255, 255)));
		button_100.setBackground(Color.WHITE);
		button_100.setAlignmentY(0.0f);
		panel_3.add(button_100);
		
		JButton button_101 = new JButton("");
		button_101.setBorder(new CompoundBorder(new LineBorder(new Color(153, 255, 255)), new MatteBorder(0, 0, 0, 5, (Color) new Color(0, 0, 0))));
		button_101.setBackground(Color.WHITE);
		button_101.setAlignmentY(0.0f);
		panel_3.add(button_101);
		
		JButton button_102 = new JButton("");
		button_102.setBorder(new LineBorder(new Color(153, 255, 255)));
		button_102.setBackground(Color.WHITE);
		button_102.setAlignmentY(0.0f);
		panel_3.add(button_102);
		
		JButton button_103 = new JButton("");
		button_103.setBorder(new LineBorder(new Color(153, 255, 255)));
		button_103.setBackground(Color.WHITE);
		button_103.setAlignmentY(0.0f);
		panel_3.add(button_103);
		
		JButton button_104 = new JButton("");
		button_104.setBorder(new LineBorder(new Color(153, 255, 255)));
		button_104.setBackground(Color.WHITE);
		button_104.setAlignmentY(0.0f);
		panel_3.add(button_104);
		
		JButton button_105 = new JButton("");
		button_105.setBorder(new CompoundBorder(new LineBorder(new Color(153, 255, 255)), new MatteBorder(0, 0, 0, 5, (Color) new Color(0, 0, 0))));
		button_105.setBackground(Color.WHITE);
		button_105.setAlignmentY(0.0f);
		panel_3.add(button_105);
		
		JButton button_106 = new JButton("");
		button_106.setBorder(new LineBorder(new Color(153, 255, 255)));
		button_106.setBackground(Color.WHITE);
		button_106.setAlignmentY(0.0f);
		panel_3.add(button_106);
		
		JButton button_107 = new JButton("");
		button_107.setBorder(new LineBorder(new Color(153, 255, 255)));
		button_107.setBackground(Color.WHITE);
		button_107.setAlignmentY(0.0f);
		panel_3.add(button_107);
		
		JButton button_108 = new JButton("");
		button_108.setBorder(new LineBorder(new Color(153, 255, 255)));
		button_108.setBackground(Color.WHITE);
		button_108.setAlignmentY(0.0f);
		panel_3.add(button_108);
		
		JButton button_109 = new JButton("");
		button_109.setBorder(new LineBorder(new Color(153, 255, 255)));
		button_109.setBackground(Color.WHITE);
		button_109.setAlignmentY(0.0f);
		panel_3.add(button_109);
		
		JButton button_110 = new JButton("");
		button_110.setBorder(new LineBorder(new Color(153, 255, 255)));
		button_110.setBackground(Color.WHITE);
		button_110.setAlignmentY(0.0f);
		panel_3.add(button_110);
		
		JButton button_111 = new JButton("");
		button_111.setBorder(new CompoundBorder(new LineBorder(new Color(153, 255, 255)), new MatteBorder(0, 0, 0, 5, (Color) new Color(0, 0, 0))));
		button_111.setBackground(Color.WHITE);
		button_111.setAlignmentY(0.0f);
		panel_3.add(button_111);
		
		JButton button_112 = new JButton("");
		button_112.setBorder(new LineBorder(new Color(153, 255, 255)));
		button_112.setBackground(Color.WHITE);
		button_112.setAlignmentY(0.0f);
		panel_3.add(button_112);
		
		JButton button_113 = new JButton("");
		button_113.setBorder(new LineBorder(new Color(153, 255, 255)));
		button_113.setBackground(Color.WHITE);
		button_113.setAlignmentY(0.0f);
		panel_3.add(button_113);
		
		JButton button_114 = new JButton("");
		button_114.setBorder(new LineBorder(new Color(153, 255, 255)));
		button_114.setBackground(Color.WHITE);
		button_114.setAlignmentY(0.0f);
		panel_3.add(button_114);
		
		JButton button_115 = new JButton("");
		button_115.setBorder(new CompoundBorder(new LineBorder(new Color(153, 255, 255)), new MatteBorder(0, 0, 0, 5, (Color) new Color(0, 0, 0))));
		button_115.setBackground(Color.WHITE);
		button_115.setAlignmentY(0.0f);
		panel_3.add(button_115);
		
		JButton button_116 = new JButton("");
		button_116.setBorder(new LineBorder(new Color(153, 255, 255)));
		button_116.setBackground(Color.WHITE);
		button_116.setAlignmentY(0.0f);
		panel_3.add(button_116);
		
		JButton button_117 = new JButton("");
		button_117.setBorder(new LineBorder(new Color(153, 255, 255)));
		button_117.setBackground(Color.WHITE);
		button_117.setAlignmentY(0.0f);
		panel_3.add(button_117);
		
		JButton button_118 = new JButton("");
		button_118.setBorder(new LineBorder(new Color(153, 255, 255)));
		button_118.setBackground(Color.WHITE);
		button_118.setAlignmentY(0.0f);
		panel_3.add(button_118);
		
		JButton button_119 = new JButton("");
		button_119.setBorder(new CompoundBorder(new LineBorder(new Color(153, 255, 255)), new MatteBorder(0, 0, 5, 0, (Color) new Color(0, 0, 0))));
		button_119.setBackground(Color.WHITE);
		button_119.setAlignmentY(0.0f);
		panel_3.add(button_119);
		
		JButton button_120 = new JButton("");
		button_120.setBorder(new CompoundBorder(new LineBorder(new Color(153, 255, 255)), new MatteBorder(0, 0, 5, 0, (Color) new Color(0, 0, 0))));
		button_120.setBackground(Color.WHITE);
		button_120.setAlignmentY(0.0f);
		panel_3.add(button_120);
		
		JButton button_121 = new JButton("");
		button_121.setBorder(new CompoundBorder(new LineBorder(new Color(153, 255, 255)), new MatteBorder(0, 0, 5, 5, (Color) new Color(0, 0, 0))));
		button_121.setBackground(Color.WHITE);
		button_121.setAlignmentY(0.0f);
		panel_3.add(button_121);
		
		JButton button_122 = new JButton("");
		button_122.setBorder(new CompoundBorder(new LineBorder(new Color(153, 255, 255)), new MatteBorder(0, 0, 5, 0, (Color) new Color(0, 0, 0))));
		button_122.setBackground(Color.WHITE);
		button_122.setAlignmentY(0.0f);
		panel_3.add(button_122);
		
		JButton button_123 = new JButton("");
		button_123.setBorder(new CompoundBorder(new LineBorder(new Color(153, 255, 255)), new MatteBorder(0, 0, 5, 0, (Color) new Color(0, 0, 0))));
		button_123.setBackground(Color.WHITE);
		button_123.setAlignmentY(0.0f);
		panel_3.add(button_123);
		
		JButton button_124 = new JButton("");
		button_124.setBorder(new CompoundBorder(new LineBorder(new Color(153, 255, 255)), new MatteBorder(0, 0, 5, 0, (Color) new Color(0, 0, 0))));
		button_124.setBackground(Color.WHITE);
		button_124.setAlignmentY(0.0f);
		panel_3.add(button_124);
		
		JButton button_125 = new JButton("");
		button_125.setBorder(new CompoundBorder(new LineBorder(new Color(153, 255, 255)), new MatteBorder(0, 0, 5, 5, (Color) new Color(0, 0, 0))));
		button_125.setBackground(Color.WHITE);
		button_125.setAlignmentY(0.0f);
		panel_3.add(button_125);
		
		JButton button_126 = new JButton("");
		button_126.setBorder(new CompoundBorder(new LineBorder(new Color(153, 255, 255)), new MatteBorder(0, 0, 5, 0, (Color) new Color(0, 0, 0))));
		button_126.setBackground(Color.WHITE);
		button_126.setAlignmentY(0.0f);
		panel_3.add(button_126);
		
		JButton button_127 = new JButton("");
		button_127.setBorder(new CompoundBorder(new LineBorder(new Color(153, 255, 255)), new MatteBorder(0, 0, 5, 0, (Color) new Color(0, 0, 0))));
		button_127.setBackground(Color.WHITE);
		button_127.setAlignmentY(0.0f);
		panel_3.add(button_127);
		
		JButton button_128 = new JButton("");
		button_128.setBorder(new CompoundBorder(new LineBorder(new Color(153, 255, 255)), new MatteBorder(0, 0, 5, 0, (Color) new Color(0, 0, 0))));
		button_128.setBackground(Color.WHITE);
		button_128.setAlignmentY(0.0f);
		panel_3.add(button_128);
		
		JButton button_129 = new JButton("");
		button_129.setBorder(new LineBorder(new Color(153, 255, 255)));
		button_129.setBackground(Color.WHITE);
		button_129.setAlignmentY(0.0f);
		panel_3.add(button_129);
		
		JButton button_130 = new JButton("");
		button_130.setBorder(new LineBorder(new Color(153, 255, 255)));
		button_130.setBackground(Color.WHITE);
		button_130.setAlignmentY(0.0f);
		panel_3.add(button_130);
		
		JButton button_131 = new JButton("");
		button_131.setBorder(new CompoundBorder(new LineBorder(new Color(153, 255, 255)), new MatteBorder(0, 0, 0, 5, (Color) new Color(0, 0, 0))));
		button_131.setBackground(Color.WHITE);
		button_131.setAlignmentY(0.0f);
		panel_3.add(button_131);
		
		JButton button_132 = new JButton("");
		button_132.setBorder(new LineBorder(new Color(153, 255, 255)));
		button_132.setBackground(Color.WHITE);
		button_132.setAlignmentY(0.0f);
		panel_3.add(button_132);
		
		JButton button_133 = new JButton("");
		button_133.setBorder(new LineBorder(new Color(153, 255, 255)));
		button_133.setBackground(Color.WHITE);
		button_133.setAlignmentY(0.0f);
		panel_3.add(button_133);
		
		JButton button_134 = new JButton("");
		button_134.setBorder(new LineBorder(new Color(153, 255, 255)));
		button_134.setBackground(Color.WHITE);
		button_134.setAlignmentY(0.0f);
		panel_3.add(button_134);
		
		JButton button_135 = new JButton("");
		button_135.setBorder(new CompoundBorder(new LineBorder(new Color(153, 255, 255)), new MatteBorder(0, 0, 0, 5, (Color) new Color(0, 0, 0))));
		button_135.setBackground(Color.WHITE);
		button_135.setAlignmentY(0.0f);
		panel_3.add(button_135);
		
		JButton button_136 = new JButton("");
		button_136.setBorder(new LineBorder(new Color(153, 255, 255)));
		button_136.setBackground(Color.WHITE);
		button_136.setAlignmentY(0.0f);
		panel_3.add(button_136);
		
		JButton button_137 = new JButton("");
		button_137.setBorder(new LineBorder(new Color(153, 255, 255)));
		button_137.setBackground(Color.WHITE);
		button_137.setAlignmentY(0.0f);
		panel_3.add(button_137);
		
		JButton button_138 = new JButton("");
		button_138.setBorder(new LineBorder(new Color(153, 255, 255)));
		button_138.setBackground(Color.WHITE);
		button_138.setAlignmentY(0.0f);
		panel_3.add(button_138);
		
		JButton button_139 = new JButton("");
		button_139.setBorder(new LineBorder(new Color(153, 255, 255)));
		button_139.setBackground(Color.WHITE);
		button_139.setAlignmentY(0.0f);
		panel_3.add(button_139);
		
		JButton button_140 = new JButton("");
		button_140.setBorder(new LineBorder(new Color(153, 255, 255)));
		button_140.setBackground(Color.WHITE);
		button_140.setAlignmentY(0.0f);
		panel_3.add(button_140);
		
		JButton button_141 = new JButton("");
		button_141.setBorder(new CompoundBorder(new LineBorder(new Color(153, 255, 255)), new MatteBorder(0, 0, 0, 5, (Color) new Color(0, 0, 0))));
		button_141.setBackground(Color.WHITE);
		button_141.setAlignmentY(0.0f);
		panel_3.add(button_141);
		
		JButton button_142 = new JButton("");
		button_142.setBorder(new LineBorder(new Color(153, 255, 255)));
		button_142.setBackground(Color.WHITE);
		button_142.setAlignmentY(0.0f);
		panel_3.add(button_142);
		
		JButton button_143 = new JButton("");
		button_143.setBorder(new LineBorder(new Color(153, 255, 255)));
		button_143.setBackground(Color.WHITE);
		button_143.setAlignmentY(0.0f);
		panel_3.add(button_143);
		
		JButton button_144 = new JButton("");
		button_144.setBorder(new LineBorder(new Color(153, 255, 255)));
		button_144.setBackground(Color.WHITE);
		button_144.setAlignmentY(0.0f);
		panel_3.add(button_144);
		
		JButton button_145 = new JButton("");
		button_145.setBorder(new CompoundBorder(new LineBorder(new Color(153, 255, 255)), new MatteBorder(0, 0, 0, 5, (Color) new Color(0, 0, 0))));
		button_145.setBackground(Color.WHITE);
		button_145.setAlignmentY(0.0f);
		panel_3.add(button_145);
		
		JButton button_146 = new JButton("");
		button_146.setBorder(new LineBorder(new Color(153, 255, 255)));
		button_146.setBackground(Color.WHITE);
		button_146.setAlignmentY(0.0f);
		panel_3.add(button_146);
		
		JButton button_147 = new JButton("");
		button_147.setBorder(new LineBorder(new Color(153, 255, 255)));
		button_147.setBackground(Color.WHITE);
		button_147.setAlignmentY(0.0f);
		panel_3.add(button_147);
		
		JButton button_148 = new JButton("");
		button_148.setBorder(new LineBorder(new Color(153, 255, 255)));
		button_148.setBackground(Color.WHITE);
		button_148.setAlignmentY(0.0f);
		panel_3.add(button_148);
		
		JButton button_149 = new JButton("");
		button_149.setBorder(new LineBorder(new Color(153, 255, 255)));
		button_149.setBackground(Color.WHITE);
		button_149.setAlignmentY(0.0f);
		panel_3.add(button_149);
		
		JButton button_150 = new JButton("");
		button_150.setBorder(new LineBorder(new Color(153, 255, 255)));
		button_150.setBackground(Color.WHITE);
		button_150.setAlignmentY(0.0f);
		panel_3.add(button_150);
		
		JButton button_151 = new JButton("");
		button_151.setBorder(new CompoundBorder(new LineBorder(new Color(153, 255, 255)), new MatteBorder(0, 0, 0, 5, (Color) new Color(0, 0, 0))));
		button_151.setBackground(Color.WHITE);
		button_151.setAlignmentY(0.0f);
		panel_3.add(button_151);
		
		JButton button_152 = new JButton("");
		button_152.setBorder(new LineBorder(new Color(153, 255, 255)));
		button_152.setBackground(Color.WHITE);
		button_152.setAlignmentY(0.0f);
		panel_3.add(button_152);
		
		JButton button_153 = new JButton("");
		button_153.setBorder(new LineBorder(new Color(153, 255, 255)));
		button_153.setBackground(Color.WHITE);
		button_153.setAlignmentY(0.0f);
		panel_3.add(button_153);
		
		JButton button_154 = new JButton("");
		button_154.setBorder(new LineBorder(new Color(153, 255, 255)));
		button_154.setBackground(Color.WHITE);
		button_154.setAlignmentY(0.0f);
		panel_3.add(button_154);
		
		JButton button_155 = new JButton("");
		button_155.setBorder(new CompoundBorder(new LineBorder(new Color(153, 255, 255)), new MatteBorder(0, 0, 0, 5, (Color) new Color(0, 0, 0))));
		button_155.setBackground(Color.WHITE);
		button_155.setAlignmentY(0.0f);
		panel_3.add(button_155);
		
		JButton button_156 = new JButton("");
		button_156.setBorder(new LineBorder(new Color(153, 255, 255)));
		button_156.setBackground(Color.WHITE);
		button_156.setAlignmentY(0.0f);
		panel_3.add(button_156);
		
		JButton button_157 = new JButton("");
		button_157.setBorder(new LineBorder(new Color(153, 255, 255)));
		button_157.setBackground(Color.WHITE);
		button_157.setAlignmentY(0.0f);
		panel_3.add(button_157);
		
		JButton button_158 = new JButton("");
		button_158.setBorder(new LineBorder(new Color(153, 255, 255)));
		button_158.setBackground(Color.WHITE);
		button_158.setAlignmentY(0.0f);
		panel_3.add(button_158);
		
		JButton button_159 = new JButton("");
		button_159.setBorder(new CompoundBorder(new LineBorder(new Color(153, 255, 255)), new MatteBorder(0, 0, 5, 0, (Color) new Color(0, 0, 0))));
		button_159.setBackground(Color.WHITE);
		button_159.setAlignmentY(0.0f);
		panel_3.add(button_159);
		
		JButton button_160 = new JButton("");
		button_160.setBorder(new CompoundBorder(new LineBorder(new Color(153, 255, 255)), new MatteBorder(0, 0, 5, 0, (Color) new Color(0, 0, 0))));
		button_160.setBackground(Color.WHITE);
		button_160.setAlignmentY(0.0f);
		panel_3.add(button_160);
		
		JButton button_161 = new JButton("");
		button_161.setBorder(new CompoundBorder(new LineBorder(new Color(153, 255, 255)), new MatteBorder(0, 0, 5, 5, (Color) new Color(0, 0, 0))));
		button_161.setBackground(Color.WHITE);
		button_161.setAlignmentY(0.0f);
		panel_3.add(button_161);
		
		JButton button_162 = new JButton("");
		button_162.setBorder(new CompoundBorder(new LineBorder(new Color(153, 255, 255)), new MatteBorder(0, 0, 5, 0, (Color) new Color(0, 0, 0))));
		button_162.setBackground(Color.WHITE);
		button_162.setAlignmentY(0.0f);
		panel_3.add(button_162);
		
		JButton button_163 = new JButton("");
		button_163.setBorder(new CompoundBorder(new LineBorder(new Color(153, 255, 255)), new MatteBorder(0, 0, 5, 0, (Color) new Color(0, 0, 0))));
		button_163.setBackground(Color.WHITE);
		button_163.setAlignmentY(0.0f);
		panel_3.add(button_163);
		
		JButton button_164 = new JButton("");
		button_164.setBorder(new CompoundBorder(new LineBorder(new Color(153, 255, 255)), new MatteBorder(0, 0, 5, 0, (Color) new Color(0, 0, 0))));
		button_164.setBackground(Color.WHITE);
		button_164.setAlignmentY(0.0f);
		panel_3.add(button_164);
		
		JButton button_165 = new JButton("");
		button_165.setBorder(new CompoundBorder(new LineBorder(new Color(153, 255, 255)), new MatteBorder(0, 0, 5, 5, (Color) new Color(0, 0, 0))));
		button_165.setBackground(Color.WHITE);
		button_165.setAlignmentY(0.0f);
		panel_3.add(button_165);
		
		JButton button_166 = new JButton("");
		button_166.setBorder(new CompoundBorder(new LineBorder(new Color(153, 255, 255)), new MatteBorder(0, 0, 5, 0, (Color) new Color(0, 0, 0))));
		button_166.setBackground(Color.WHITE);
		button_166.setAlignmentY(0.0f);
		panel_3.add(button_166);
		
		JButton button_167 = new JButton("");
		button_167.setBorder(new CompoundBorder(new LineBorder(new Color(153, 255, 255)), new MatteBorder(0, 0, 5, 0, (Color) new Color(0, 0, 0))));
		button_167.setBackground(Color.WHITE);
		button_167.setAlignmentY(0.0f);
		panel_3.add(button_167);
		
		JButton button_168 = new JButton("");
		button_168.setBorder(new CompoundBorder(new LineBorder(new Color(153, 255, 255)), new MatteBorder(0, 0, 5, 0, (Color) new Color(0, 0, 0))));
		button_168.setBackground(Color.WHITE);
		button_168.setAlignmentY(0.0f);
		panel_3.add(button_168);
		
		JButton button_169 = new JButton("");
		button_169.setBorder(new LineBorder(new Color(153, 255, 255)));
		button_169.setBackground(Color.WHITE);
		button_169.setAlignmentY(0.0f);
		panel_3.add(button_169);
		
		JButton button_170 = new JButton("");
		button_170.setBorder(new LineBorder(new Color(153, 255, 255)));
		button_170.setBackground(Color.WHITE);
		button_170.setAlignmentY(0.0f);
		panel_3.add(button_170);
		
		JButton button_171 = new JButton("");
		button_171.setBorder(new CompoundBorder(new LineBorder(new Color(153, 255, 255)), new MatteBorder(0, 0, 0, 5, (Color) new Color(0, 0, 0))));
		button_171.setBackground(Color.WHITE);
		button_171.setAlignmentY(0.0f);
		panel_3.add(button_171);
		
		JButton button_172 = new JButton("");
		button_172.setBorder(new LineBorder(new Color(153, 255, 255)));
		button_172.setBackground(Color.WHITE);
		button_172.setAlignmentY(0.0f);
		panel_3.add(button_172);
		
		JButton button_173 = new JButton("");
		button_173.setBorder(new LineBorder(new Color(153, 255, 255)));
		button_173.setBackground(Color.WHITE);
		button_173.setAlignmentY(0.0f);
		panel_3.add(button_173);
		
		JButton button_174 = new JButton("");
		button_174.setBorder(new LineBorder(new Color(153, 255, 255)));
		button_174.setBackground(Color.WHITE);
		button_174.setAlignmentY(0.0f);
		panel_3.add(button_174);
		
		JButton button_175 = new JButton("");
		button_175.setBorder(new CompoundBorder(new LineBorder(new Color(153, 255, 255)), new MatteBorder(0, 0, 0, 5, (Color) new Color(0, 0, 0))));
		button_175.setBackground(Color.WHITE);
		button_175.setAlignmentY(0.0f);
		panel_3.add(button_175);
		
		JButton button_176 = new JButton("");
		button_176.setBorder(new LineBorder(new Color(153, 255, 255)));
		button_176.setBackground(Color.WHITE);
		button_176.setAlignmentY(0.0f);
		panel_3.add(button_176);
		
		JButton button_177 = new JButton("");
		button_177.setBorder(new LineBorder(new Color(153, 255, 255)));
		button_177.setBackground(Color.WHITE);
		button_177.setAlignmentY(0.0f);
		panel_3.add(button_177);
		
		JButton button_178 = new JButton("");
		button_178.setBorder(new LineBorder(new Color(153, 255, 255)));
		button_178.setBackground(Color.WHITE);
		button_178.setAlignmentY(0.0f);
		panel_3.add(button_178);
		
		JButton button_179 = new JButton("");
		button_179.setBorder(new LineBorder(new Color(153, 255, 255)));
		button_179.setBackground(Color.WHITE);
		button_179.setAlignmentY(0.0f);
		panel_3.add(button_179);
		
		JButton button_180 = new JButton("");
		button_180.setBorder(new LineBorder(new Color(153, 255, 255)));
		button_180.setBackground(Color.WHITE);
		button_180.setAlignmentY(0.0f);
		panel_3.add(button_180);
		
		JButton button_181 = new JButton("");
		button_181.setBorder(new CompoundBorder(new LineBorder(new Color(153, 255, 255)), new MatteBorder(0, 0, 0, 5, (Color) new Color(0, 0, 0))));
		button_181.setBackground(Color.WHITE);
		button_181.setAlignmentY(0.0f);
		panel_3.add(button_181);
		
		JButton button_182 = new JButton("");
		button_182.setBorder(new LineBorder(new Color(153, 255, 255)));
		button_182.setBackground(Color.WHITE);
		button_182.setAlignmentY(0.0f);
		panel_3.add(button_182);
		
		JButton button_183 = new JButton("");
		button_183.setBorder(new LineBorder(new Color(153, 255, 255)));
		button_183.setBackground(Color.WHITE);
		button_183.setAlignmentY(0.0f);
		panel_3.add(button_183);
		
		JButton button_184 = new JButton("");
		button_184.setBorder(new LineBorder(new Color(153, 255, 255)));
		button_184.setBackground(Color.WHITE);
		button_184.setAlignmentY(0.0f);
		panel_3.add(button_184);
		
		JButton button_185 = new JButton("");
		button_185.setBorder(new CompoundBorder(new LineBorder(new Color(153, 255, 255)), new MatteBorder(0, 0, 0, 5, (Color) new Color(0, 0, 0))));
		button_185.setBackground(Color.WHITE);
		button_185.setAlignmentY(0.0f);
		panel_3.add(button_185);
		
		JButton button_186 = new JButton("");
		button_186.setBorder(new LineBorder(new Color(153, 255, 255)));
		button_186.setBackground(Color.WHITE);
		button_186.setAlignmentY(0.0f);
		panel_3.add(button_186);
		
		JButton button_187 = new JButton("");
		button_187.setBorder(new LineBorder(new Color(153, 255, 255)));
		button_187.setBackground(Color.WHITE);
		button_187.setAlignmentY(0.0f);
		panel_3.add(button_187);
		
		JButton button_188 = new JButton("");
		button_188.setBorder(new LineBorder(new Color(153, 255, 255)));
		button_188.setBackground(Color.WHITE);
		button_188.setAlignmentY(0.0f);
		panel_3.add(button_188);
		
		JButton button_189 = new JButton("");
		button_189.setBorder(new LineBorder(new Color(153, 255, 255)));
		button_189.setBackground(Color.WHITE);
		button_189.setAlignmentY(0.0f);
		panel_3.add(button_189);
		
		JButton button_190 = new JButton("");
		button_190.setBorder(new LineBorder(new Color(153, 255, 255)));
		button_190.setBackground(Color.WHITE);
		button_190.setAlignmentY(0.0f);
		panel_3.add(button_190);
		
		JButton button_191 = new JButton("");
		button_191.setBorder(new CompoundBorder(new LineBorder(new Color(153, 255, 255)), new MatteBorder(0, 0, 0, 5, (Color) new Color(0, 0, 0))));
		button_191.setBackground(Color.WHITE);
		button_191.setAlignmentY(0.0f);
		panel_3.add(button_191);
		
		JButton button_192 = new JButton("");
		button_192.setBorder(new LineBorder(new Color(153, 255, 255)));
		button_192.setBackground(Color.WHITE);
		button_192.setAlignmentY(0.0f);
		panel_3.add(button_192);
		
		JButton button_193 = new JButton("");
		button_193.setBorder(new LineBorder(new Color(153, 255, 255)));
		button_193.setBackground(Color.WHITE);
		button_193.setAlignmentY(0.0f);
		panel_3.add(button_193);
		
		JButton button_194 = new JButton("");
		button_194.setBorder(new LineBorder(new Color(153, 255, 255)));
		button_194.setBackground(Color.WHITE);
		button_194.setAlignmentY(0.0f);
		panel_3.add(button_194);
		
		JButton button_195 = new JButton("");
		button_195.setBorder(new CompoundBorder(new LineBorder(new Color(153, 255, 255)), new MatteBorder(0, 0, 0, 5, (Color) new Color(0, 0, 0))));
		button_195.setBackground(Color.WHITE);
		button_195.setAlignmentY(0.0f);
		panel_3.add(button_195);
		
		JButton button_196 = new JButton("");
		button_196.setBorder(new LineBorder(new Color(153, 255, 255)));
		button_196.setBackground(Color.WHITE);
		button_196.setAlignmentY(0.0f);
		panel_3.add(button_196);
		
		JButton button_197 = new JButton("");
		button_197.setBorder(new LineBorder(new Color(153, 255, 255)));
		button_197.setBackground(Color.WHITE);
		button_197.setAlignmentY(0.0f);
		panel_3.add(button_197);
		
		JButton button_198 = new JButton("");
		button_198.setBorder(new LineBorder(new Color(153, 255, 255)));
		button_198.setBackground(Color.WHITE);
		button_198.setAlignmentY(0.0f);
		panel_3.add(button_198);
		
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
		for(int x = 0; x < buttonsGrilleEquipe.length; x++) {
			for(int y = 0; y < buttonsGrilleEquipe[x].length; y++) {
				buttonsGrilleEquipe[x][y].addActionListener(listener);
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
