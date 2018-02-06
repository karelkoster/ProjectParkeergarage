
package nl.hanze.parkeersimulator.controller;

import javax.swing.JPanel;

import nl.hanze.parkeersimulator.model.CarParkModel;

@SuppressWarnings("serial")
public abstract class AbstractController extends JPanel {
	protected CarParkModel model;

	public AbstractController(CarParkModel model) {
		this.model = model;
	}
}
