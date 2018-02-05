package nl.hanze.parkeersimulator.view;

import javax.swing.JPanel;
import nl.hanze.parkeergarage.model.CarParkModel;

@SuppressWarnings("serial")
public abstract class AbstractView extends JPanel {
	protected CarParkModel model;
	public AbstractView(CarParkModel simulatorModel) {
		this.model = simulatorModel;
		simulatorModel.addView(this);
		System.out.println("view added");
	}

	public CarParkModel getModel() {
		return model;
	}

	public void updateView() {
		repaint();
	}
}
