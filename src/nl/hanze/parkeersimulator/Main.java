package nl.hanze.parkeersimulator;

import javax.swing.JFrame;
import javax.swing.JPanel;

import nl.hanze.parkeersimulator.model.CarParkModel;
import nl.hanze.parkeersimulator.model.Model;
import nl.hanze.parkeersimulator.model.SimulatorModel;
import nl.hanze.parkeersimulator.view2.AbstractView;
import nl.hanze.parkeersimulator.view2.PieView;
import nl.hanze.parkeersimulator.view2.SimulatorView;

public class Main extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Main() {

		setTitle("Parkeergaragesimulator");
		// setSize(640,400);
		JPanel rootPanel = new JPanel();

		// JLabel header = new JLabel("Parkeerplekken", SwingConstants.CENTER);
		// rootPanel.add(header);

		// Model/Controller/View
		CarParkModel simulatorModel = new CarParkModel(3, 6, 30);

		SimulatorView simulatorView = new SimulatorView(simulatorModel);
		SimulatorModel simulatorController = new SimulatorModel(simulatorView);

		Model model = new Model();
		AbstractView pieview = new PieView(model);
		// door ries toegevoegd
		simulatorModel.SetModel(model);
		simulatorController.SetModel(model);
		// ---
		JFrame screen = new JFrame("Model View Controller/Dynamic Model with thread");
		screen.setSize(450, 285);
		screen.setResizable(false);
		screen.setLayout(null);
		screen.getContentPane().add(pieview);
		pieview.setBounds(230, 10, 200, 200);
		screen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		add(rootPanel);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		screen.setVisible(true);
		simulatorController.run();
	}

	public static void main(String[] args) {
		new Main();
	}

}
