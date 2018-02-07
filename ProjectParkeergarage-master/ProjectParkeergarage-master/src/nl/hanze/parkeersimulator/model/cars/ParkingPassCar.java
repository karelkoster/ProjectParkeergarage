package nl.hanze.parkeersimulator.model.cars;

import java.util.Random;
import java.awt.*;

// TODO: Auto-generated Javadoc
/**
 * The Class ParkingPassCar.
 */
public class ParkingPassCar extends Car {
	
	/** The Constant COLOR. */
	private static final Color COLOR = Color.blue;

	/**
	 * Instantiates a new parking pass car.
	 */
	public ParkingPassCar() {
		Random random = new Random();
		int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
		this.setColor(COLOR);
		this.setMinutesLeft(stayMinutes);
		this.setHasToPay(false);
	}

	/* (non-Javadoc)
	 * @see nl.hanze.parkeersimulator.model.cars.Car#getColor()
	 */
	public Color getColor() {
		return COLOR;
	}
}
