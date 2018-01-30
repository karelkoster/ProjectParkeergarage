package nl.hanze.parkeersimulator.model;

import nl.hanze.parkeersimulator.controller.Controller;
import nl.hanze.parkeersimulator.controller.SimulatorController;
import nl.hanze.parkeersimulator.view.AbstractView;
import nl.hanze.parkeersimulator.view.CountView;
import nl.hanze.parkeersimulator.view.PieView;
import nl.hanze.parkeersimulator.view.SimulatorView;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MVCDDynamicModelThreadGeneralized extends JFrame {
	private Model model;
	private JFrame screen;
	private AbstractView countview;
	private AbstractView pieview;
	private Controller controller;
	private static final long serialVersionUID = 9123275098959761736L;
	
	public MVCDDynamicModelThreadGeneralized() {
		setTitle("Parkeergaragesimulator");
		// setSize(640,400);
		JPanel rootPanel = new JPanel();

		// JLabel header = new JLabel("Parkeerplekken", SwingConstants.CENTER);
		// rootPanel.add(header);

		// Model/Controller/View
		CarParkModel simulatorModel = new CarParkModel(3, 6, 30);
		
		SimulatorView simulatorView = new SimulatorView(simulatorModel);
		SimulatorController simulatorController = new SimulatorController(simulatorView);
		
		model=new Model();
		controller=new Controller(model);
		countview=new CountView(model);
		pieview=new PieView(model);
		simulatorModel.SetModel(model);
		screen=new JFrame("Model View Controller/Dynamic Model with thread");
		screen.setSize(450, 285);
		screen.setResizable(false);
		screen.setLayout(null);
		screen.getContentPane().add(countview);
		screen.getContentPane().add(pieview);
		screen.getContentPane().add(controller);
		countview.setBounds(10, 10, 200, 200);
		pieview.setBounds(230, 10, 200, 200);
		controller.setBounds(0, 210, 450, 50);
		screen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		add(rootPanel);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		screen.setVisible(true);
		simulatorController.run();
	}
	public Model GetModel() { 
		return model; 
	}
}
