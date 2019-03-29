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
	protected Son son;
	
	protected JPanel controlPanel;
	protected JPanel gridPanel; 
	protected GridLayout gridLayout;

    public JButton launchButton;
    protected JButton resetButton;
    
    protected JLabel titleAmiral;
    protected JLabel title;
    
    protected JMenuItem menuItem;

    protected MouseListener mL;
    protected ActionListener aL;

    protected ControlMenu cm;
    
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
    

	public void initAttribut() {
    	
    	launchButton = new JButton("haha");
   
    }
	
	public void initAttributAmiral() {
    	
    	launchButton = new JButton("haha");
   
    }
    
    public void createMenu() {
    	
    		
    }
    
    public void createView(){
    	  JPanel pWidget = new JPanel();
    	  
    	  pWidget.add(launchButton);
    	  
    	  setContentPane(pWidget);
    
    }
	
    public void setButtonControler(ActionListener listener) {
    	launchButton.addActionListener(listener);
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
