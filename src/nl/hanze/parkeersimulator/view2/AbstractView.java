package nl.hanze.parkeersimulator.view2;

import javax.swing.*;

import nl.hanze.parkeersimulator.model.Model;

public abstract class AbstractView extends JPanel {
	private static final long serialVersionUID = -2767764579227738552L;
	protected Model model;

	public AbstractView(Model model) {
		this.model = model;
		model.addView(this);
	}

	public Model getModel() {
		return model;
	}

	public void updateView() {
		repaint();
	}
}
