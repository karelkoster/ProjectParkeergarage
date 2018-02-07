package nl.hanze.parkeersimulator.controller;

 import javax.swing.*;

import nl.hanze.parkeersimulator.model.CarParkModel;

import java.awt.Color;
import java.awt.event.*;

@SuppressWarnings("serial")
public class Controller extends AbstractController implements ActionListener{

	private JButton start = new JButton("Start");
	private JButton stop = new JButton("Stop");
	private JButton sluiten = new JButton("Sluiten");
	private JButton honderdstappen = new JButton("1 Uur vooruit");
	private JButton eenstap = new JButton("1 Minuut vooruit");
	private JButton keerTwee = new JButton("X2");
	private JButton reset = new JButton("Reset snelheid");
	final JFrame frame = new JFrame(); 
	
	public Controller(CarParkModel model) {
		super(model);
		
		sluiten.setBackground(Color.RED);
		add(start);
		add(stop);
		add(eenstap);
		add(honderdstappen);
		add(keerTwee); 
		add(reset);
		add(sluiten);
		
		start.addActionListener(this);
		stop.addActionListener(this);
		eenstap.addActionListener(this);
		honderdstappen.addActionListener(this);
		sluiten.addActionListener(this);
		keerTwee.addActionListener(this);
		reset.addActionListener(this);
		

		stop.setEnabled(false);
		keerTwee.setEnabled(false);
		reset.setEnabled(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) { 
		if(e.getSource() == start){
			model.start();
			start.setEnabled(false);
			stop.setEnabled(true);
			keerTwee.setEnabled(true);
			reset.setEnabled(true);
		}
		if(e.getSource() == stop) {
			model.stop();
			start.setEnabled(true);
			stop.setEnabled(false);
			keerTwee.setEnabled(false);
			reset.setEnabled(false);
		}
		if(e.getSource() == sluiten) {
			Object[] options = {"Ja, afsluiten", "Nee",};
			int n = JOptionPane.showOptionDialog(frame,
					"Weet u zeker dat u wilt afsluiten?", "Pas op!" , JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.WARNING_MESSAGE,null, options, options[1]);
			if(n==0) { 
				System.out.println("Afsluiten....");
				model.close();
			} 
		}
		if(e.getSource() == eenstap) {
			model.stap(1);
		}
		if(e.getSource() == keerTwee) { 
			System.out.println("X2 pressed"); 
			model.setTickPause(2); 	
		}
		if(e.getSource() == honderdstappen) {
			model.stap(60);
		}
		if(e.getSource() == reset) { 
			model.resetTickPause();
		}
	
	}

}
 
