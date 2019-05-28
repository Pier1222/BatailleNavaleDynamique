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



public class View2 extends JFrame {

	private JFrame frmBatailleNavale;
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
    
    protected JTextField textUser;
    protected JTextField textIp;
    protected JTextField textPort;
    
    protected JLabel nomLabel;
    protected JLabel adresseIpLabel;
    protected JLabel portLabel;
    
    public JButton launchInvite;
    public JButton launchCreateur;
    
    protected ControlGroupAttente groupAttente; //Permet de faire apparaître la fenêtre attente

	public View2(Bataille_navale_model model) {
		
		this.model = model;
		
		initialize();
		setResizable(false);
		
		setTitle("Bataille Navale");
		setBackground(new Color(240, 240, 240));
		setBounds(100, 100, 800, 439);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {



		JPanel panel = new JPanel();
		panel.setBounds(635, 197, 135, 118);
		frmBatailleNavale.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton testSon = new JButton("Son 1");
		testSon.setBounds(0, 0, 134, 38);
		panel.add(testSon);
		
		JButton testSon2 = new JButton("Son 2");
		testSon2.setBounds(0, 41, 134, 38);
		panel.add(testSon2);
		
		JButton testStop = new JButton("Stop");
		testStop.setBounds(0, 80, 134, 38);
		panel.add(testStop);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(12, 162, 192, 171);
		frmBatailleNavale.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		textUser = new JTextField();
		textUser.setBounds(68, 46, 116, 22);
		panel_1.add(textUser);
		textUser.setColumns(10);
		
		textIp = new JTextField();
		textIp.setBounds(68, 81, 116, 22);
		panel_1.add(textIp);
		textIp.setColumns(10);
		
		textPort = new JTextField();
		textPort.setBounds(68, 116, 116, 22);
		panel_1.add(textPort);
		textPort.setColumns(10);
		
		nomLabel = new JLabel("User :");
		nomLabel.setBounds(12, 49, 35, 16);
		panel_1.add(nomLabel);
		
		adresseIpLabel = new JLabel("IP :");
		adresseIpLabel.setBounds(12, 84, 20, 16);
		panel_1.add(adresseIpLabel);
		
		portLabel = new JLabel("Port :");
		portLabel.setBounds(12, 119, 32, 16);
		panel_1.add(portLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Bataille Navale");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Blackadder ITC", Font.PLAIN, 65));
		lblNewLabel_1.setBounds(0, 46, 770, 72);
		frmBatailleNavale.getContentPane().add(lblNewLabel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(216, 240, 152, 75);
		frmBatailleNavale.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JButton btnNewButton = new JButton("Rejoindre une partie");
		btnNewButton.setBounds(0, 39, 149, 25);
		panel_2.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Créer une partie");
		btnNewButton_1.setBounds(0, 0, 149, 25);
		panel_2.add(btnNewButton_1);
	}

	  public void creerDialogueErreur(String messageErreur, String titreErreur) {
	    	JOptionPane erreur = new JOptionPane();
	    	erreur.showMessageDialog(this, messageErreur, titreErreur, JOptionPane.ERROR_MESSAGE);
	    	JDialog fenErreur = erreur.createDialog(this, titreErreur);
	    }
	    
}
