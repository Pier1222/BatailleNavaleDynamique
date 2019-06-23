package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;

import model.Bataille_navale_model;
import model.Case;
import model.Grille;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class View_amiral extends JFrame{
	
	protected Bataille_navale_model model;
    public Case[][] buttonsGrille;

	public View_amiral(Bataille_navale_model model) {
        this.model = model;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 74, 1010, 614);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 745, 593);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBounds(139, 0, 605, 580);
		panel_1.add(panel_2);
		
		JPanel panelGrille = new JPanel();
		panelGrille.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelGrille.setBounds(99, 48, 501, 478);
		panel_2.add(panelGrille);
		panelGrille.setLayout(new GridLayout(Grille.getLines(), Grille.getColumns(), 0, 0));
		
		
		//Tous les index de boutons dans la grille avec un matteBorder
		int[] numerosBoutonsMatterBorder = new int[] {2, 6, 12, 16, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 32, 36, 42, 46, 52, 56, 
				60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 72, 76, 82, 86, 92, 96};
		int numeroButtonActu = 0;
		buttonsGrille = new Case[Grille.getLines()][Grille.getColumns()];
		Case buttonActu = null;
		//Initialisation du tableau de boutons
		for(int x = 0; x < Grille.getLines(); x++) {
			for(int y = 0; y < Grille.getColumns(); y++) {
				buttonActu = new Case(x, y);
				
				if(nombreDansTabNombre(numeroButtonActu, numerosBoutonsMatterBorder))
					buttonActu.setBorder(new CompoundBorder(new LineBorder(new Color(153, 255, 255)), new MatteBorder(0, 0, 0, 5, (Color) new Color(0, 0, 0))));
				else
				    buttonActu.setBorder(new LineBorder(new Color(153, 255, 255)));
				
				
				buttonActu.setBackground(Color.WHITE);
				buttonActu.setAlignmentY(0.0f);
				panelGrille.add(buttonActu);
				buttonsGrille[x][y] = buttonActu;
				numeroButtonActu++;
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
		
		JLabel lblAlli = new JLabel("Allié");
		lblAlli.setHorizontalTextPosition(SwingConstants.CENTER);
		lblAlli.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlli.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAlli.setBounds(12, 13, 111, 16);
		panel_4.add(lblAlli);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new LineBorder(Color.RED, 5));
		panel_5.setLayout(null);
		panel_5.setBounds(0, 321, 135, 259);
		panel_1.add(panel_5);
		
		JLabel lblNewLabel = new JLabel("Adversaire");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(12, 13, 111, 16);
		panel_5.add(lblNewLabel);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBounds(746, 0, 264, 614);
		panel.add(panel_7);
		panel_7.setLayout(new GridLayout(0, 3, 0, 0));
		
		JPanel panel_6 = new JPanel();
		panel_7.add(panel_6);
		panel_6.setLayout(new GridLayout(24, 1, 0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("Matelot");
		lblNewLabel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_6.add(lblNewLabel_1);
		
		JPanel panel_8 = new JPanel();
		panel_7.add(panel_8);
		panel_8.setLayout(new GridLayout(24, 1, 0, 0));
		
		JLabel lblRle = new JLabel("Rôle");
		lblRle.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblRle.setHorizontalAlignment(SwingConstants.CENTER);
		panel_8.add(lblRle);
		
		JPanel panel_9 = new JPanel();
		panel_7.add(panel_9);
		panel_9.setLayout(new GridLayout(24, 1, 0, 0));
		
		JLabel lblNavire = new JLabel("Navire");
		lblNavire.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblNavire.setHorizontalAlignment(SwingConstants.CENTER);
		panel_9.add(lblNavire);
		
	}
	
	private boolean nombreDansTabNombre(int nombre, int[] tabNombre) {
		for(int i = 0; i < tabNombre.length; i++) {
			if(nombre == tabNombre[i])
				return true;
		}
		return false;
	}
	
	public void setControlButton(ActionListener listener) {
		for(int x = 0; x < buttonsGrille.length; x++) {
			for(int y = 0; y < buttonsGrille[x].length; y++) {
				buttonsGrille[x][y].addActionListener(listener);
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
	
	private void changeVue() {
		
		
	}

}
