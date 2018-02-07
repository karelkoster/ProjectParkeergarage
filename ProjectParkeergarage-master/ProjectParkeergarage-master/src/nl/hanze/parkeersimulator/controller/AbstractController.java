
package nl.hanze.parkeersimulator.controller;

import javax.swing.JPanel;

import nl.hanze.parkeersimulator.model.CarParkModel;

// TODO: Auto-generated Javadoc
/**
 * The Class AbstractController.
 */
@SuppressWarnings("serial")
public abstract class AbstractController extends JPanel {
	
	/** The model. */
	protected CarParkModel model;

	/**
	 * Instantiates a new abstract controller.
	 *
	 * @param model the model
	 */
	public AbstractController(CarParkModel model) {
		this.model = model;
	}
}
