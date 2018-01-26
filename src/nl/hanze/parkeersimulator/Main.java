package nl.hanze.parkeersimulator;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import nl.hanze.parkeersimulator.controller.SimulatorController;
import nl.hanze.parkeersimulator.view.SimulatorView;

public class Main extends JFrame {

	private static final long serialVersionUID = 9123275098959761736L;

	public Main() {
		setTitle("Parkeergaragesimulator");
//		setSize(640,400);
		JPanel rootPanel = new JPanel();
		
//		JLabel header = new JLabel("Parkeerplekken", SwingConstants.CENTER);
//		rootPanel.add(header);
		
		// Model/Controller/View
		// TODO Models
		SimulatorView simulatorView = new SimulatorView(3, 6, 30);
		SimulatorController simulatorController = new SimulatorController(simulatorView);
		simulatorController.run();
		
		add(rootPanel);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Main();
		
	}

}
