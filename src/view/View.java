package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

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
	protected Son son;
	
	protected JPanel controlPanel;
	protected JPanel gridPanel; 
	protected GridLayout gridLayout;

    protected JButton launchButton;
    protected JButton resetButton;
    
    protected JLabel title;
    
    protected JMenuItem menuItem;

    protected MouseListener mL;
    protected ActionListener aL;

    protected ControlMenu cm;
		
    private final int LINES = 8;
    private final int COLUMNS = 8;
    
    public View(Bataille_navale_model model, Game game, Son son) {

        this.model = model;
        this.game = game;
        this.son = son;
        
        initAttribut();
        pack();
        setResizable(false);
        setTitle("Jeu de bataille navale");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    

	public void initAttribut() {
    	
    	

    }
    
    public void createMenu() {
    	
    		
    }
	
    public void display() {
        setVisible(true);
    }

    public void undisplay() {
        setVisible(false);
    }
}
