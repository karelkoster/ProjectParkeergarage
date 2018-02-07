package nl.hanze.parkeersimulator.model.cars;

import java.awt.Color;
import java.util.Random;

// TODO: Auto-generated Javadoc
/**
 * The Class AdHocCar.
 */
public class AdHocCar extends Car {
	
	/** The Constant COLOR. */
	private static final Color COLOR = Color.red;

	/**
	 * Instantiates a new ad hoc car.
	 */
	public AdHocCar() {
		Random random = new Random();
		int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
		this.setColor(COLOR);
		this.setMinutesLeft(stayMinutes);
		this.setHasToPay(true);
	}

	/* (non-Javadoc)
	 * @see nl.hanze.parkeersimulator.model.cars.Car#getColor()
	 */
	public Color getColor() {
		return COLOR;
	}
}
