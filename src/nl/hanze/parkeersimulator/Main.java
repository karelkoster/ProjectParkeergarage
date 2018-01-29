package nl.hanze.parkeersimulator;

import javax.swing.JFrame;
import javax.swing.JPanel;

import nl.hanze.parkeersimulator.model.CarParkModel;
import nl.hanze.parkeersimulator.model.SimulatorModel;
import nl.hanze.parkeersimulator.view.SimulatorView;

public class Main extends JFrame {

	private static final long serialVersionUID = 9123275098959761736L;

	public Main() {
		setTitle("Parkeergaragesimulator");
		// setSize(640,400);
		JPanel rootPanel = new JPanel();

		// JLabel header = new JLabel("Parkeerplekken", SwingConstants.CENTER);
		// rootPanel.add(header);

		// Model/Controller/View
		CarParkModel carParkModel = new CarParkModel(3, 6, 30);
		SimulatorView simulatorView = new SimulatorView(carParkModel);
		SimulatorModel simulatorModel = new SimulatorModel(simulatorView);
		simulatorModel.run();

		add(rootPanel);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		new Main();

	}

}
