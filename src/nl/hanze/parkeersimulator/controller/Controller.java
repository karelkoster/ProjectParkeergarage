package nl.hanze.parkeersimulator.controller;

 import javax.swing.*;

import nl.hanze.parkeersimulator.model.CarParkModel;

import java.awt.event.*;
  
public class Controller extends AbstractController implements ActionListener{

	private JButton start = new JButton("start");
	private JButton stop = new JButton("stop");
	private JButton sluiten = new JButton("sluiten");
	private JButton honderdstappen = new JButton("1 uur vooruit");
	private JButton eenstap = new JButton("1 minuut vooruit");
	
	public Controller(CarParkModel model) {
		super(model);
		
		add(start);
		add(stop);
		add(sluiten);
		add(eenstap);
		add(honderdstappen);

		
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
		}
		if(e.getSource() == stop) {
			model.stop();
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
 
