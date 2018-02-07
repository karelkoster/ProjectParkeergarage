package nl.hanze.parkeersimulator.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.SystemColor;

import nl.hanze.parkeersimulator.model.CarParkModel;

// TODO: Auto-generated Javadoc
/**
 * The Class PieView.
 */
@SuppressWarnings("serial")
public class PieView extends AbstractView {
	
	/** The red. */
	private int red;
	
	/** The blue. */
	private int blue;

	/**
	 * Instantiates a new pie view.
	 *
	 * @param carParkModel the car park model
	 */
	public PieView(CarParkModel carParkModel) {
		super(carParkModel);
		setSize(200, 200);
	}

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	public void paintComponent(Graphics g) {
		int normaal = berekenPie(getModel().getNormaal());
		int reservering = berekenPie(getModel().getReservering());
		int totaal = normaal + reservering; 
		

		g.setColor(SystemColor.menu);
		g.fillRect(0, 0, 200, 200);
		
		g.setColor(Color.BLACK);
		g.fillArc(9, 9, 182, 182, 0, 360);
		g.setColor(Color.BLUE);
		g.fillArc(10, 10, 180, 180, normaal, reservering);
		g.setColor(Color.RED);
		g.fillArc(10, 10, 180, 180, 0, normaal);
		g.setColor(Color.WHITE);
		g.fillArc(10, 10, 180, 180, totaal, 360-totaal);
		
	}

	/**
	 * Gets the aantal red.
	 *
	 * @return the aantal red
	 */
	public int getAantalRed() {
		return red;
	}

	/**
	 * Gets the aantal blue.
	 *
	 * @return the aantal blue
	 */
	public int getAantalBlue() {
		return blue;
	}

	/**
	 * Gets the aantal blue pie.
	 *
	 * @return the aantal blue pie
	 */
	public int getAantalBluePie() {
		return red + blue;
	}

	/**
	 * Bereken pie.
	 *
	 * @param aantal the aantal
	 * @return the int
	 */
	private int berekenPie(int aantal) {
		return (int) (aantal / 1.5);
	}
	
}
