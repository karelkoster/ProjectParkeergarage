/**
 * 
 */
package nl.hanze.parkeersimulator.view;

import javax.swing.JPanel;

import nl.hanze.parkeersimulator.controller.Controller;
import nl.hanze.parkeersimulator.model.Model;

/**
 * 
 *  TODO nog gebruiken
 */
public abstract class View extends JPanel {

	private static final long serialVersionUID = 4524337261502658423L;
	protected final Controller controller;

	public View(Controller controller) {
		this.controller = controller;
	}

	public View() {
		this(null);
	}

	abstract public void update(Model model);

}