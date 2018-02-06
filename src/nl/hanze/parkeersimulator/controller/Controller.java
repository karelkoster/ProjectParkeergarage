package nl.hanze.parkeersimulator.controller;

 import javax.swing.*;

import nl.hanze.parkeersimulator.model.CarParkModel;

import java.awt.event.*;

@SuppressWarnings("serial")
public class Controller extends AbstractController implements ActionListener{

	private JButton start = new JButton("Start");
	private JButton stop = new JButton("Stop");
	private JButton sluiten = new JButton("Sluiten");
	private JButton honderdstappen = new JButton("1 Uur vooruit");
	private JButton eenstap = new JButton("1 Minuut vooruit");
	
	public Controller(CarParkModel model) {
		super(model);
		
		add(start);
		add(stop);
		add(eenstap);
		add(honderdstappen);
		add(sluiten);
		
		start.addActionListener(this);
		stop.addActionListener(this);
		eenstap.addActionListener(this);
		honderdstappen.addActionListener(this);
		sluiten.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) { 
		if(e.getSource() == start){
			model.start();
			start.setEnabled(false);
			stop.setEnabled(true);
		}
		if(e.getSource() == stop) {
			model.stop();
			start.setEnabled(true);
			stop.setEnabled(false);
		}
		if(e.getSource() == sluiten) {
			model.close();
		}
			if(e.getSource() == eenstap) {
			model.stap(1);
		}
		if(e.getSource() == honderdstappen) {
			model.stap(60);
		}
	
	}

}
 
