
package nl.hanze.parkeersimulator.controller2;

import nl.hanze.parkeersimulator.model.*;
import javax.swing.*;

public abstract class AbstractController extends JPanel {
	private static final long serialVersionUID = 4941730006940737729L;
	protected Model model;
	
	public AbstractController(Model model) {
		this.model=model;
	}
}