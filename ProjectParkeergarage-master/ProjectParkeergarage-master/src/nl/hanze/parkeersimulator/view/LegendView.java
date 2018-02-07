package nl.hanze.parkeersimulator.view;

import java.awt.Color;
import java.awt.Graphics;

import nl.hanze.parkeersimulator.model.CarParkModel;

// TODO: Auto-generated Javadoc
/**
 * The Class LegendView.
 */
public class LegendView extends AbstractView {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 883482756602880606L;

	/**
	 * Instantiates a new legend view.
	 *
	 * @param simulatorModel the simulator model
	 */
	public LegendView(CarParkModel simulatorModel) {
		super(simulatorModel);
		setSize(150, 54);
	}

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.black);
		g.drawString("Geen abonnement", 40, 22);
		g.setColor(Color.red);
		g.fillRect(10, 10, 24, 16);
		g.setColor(Color.black);
		g.drawString("Met abonnement", 40, 42);
		g.setColor(Color.blue);
		g.fillRect(10, 30, 24, 16);
	}
	
	/* (non-Javadoc)
	 * @see nl.hanze.parkeersimulator.view.AbstractView#updateView()
	 */
	public void updateView() {
		
	}
}
