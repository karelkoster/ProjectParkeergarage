package nl.hanze.parkeersimulator.view;

import javax.swing.JPanel;

import nl.hanze.parkeersimulator.model.CarParkModel;

// TODO: Auto-generated Javadoc
/**
 * The Class AbstractView.
 */
@SuppressWarnings("serial")
public abstract class AbstractView extends JPanel {
	
	/** The model. */
	protected CarParkModel model;
	
	/**
	 * Instantiates a new abstract view.
	 *
	 * @param simulatorModel the simulator model
	 */
	public AbstractView(CarParkModel simulatorModel) {
		this.model = simulatorModel;
		simulatorModel.addView(this);
		System.out.println("view added");
	}

	/**
	 * Gets the model.
	 *
	 * @return the model
	 */
	public CarParkModel getModel() {
		return model;
	}

	/**
	 * Update view.
	 */
	public void updateView() {
		repaint();
	}
}
