package nl.hanze.parkeersimulator.model.cars;

import java.awt.Color;
import java.util.Random;

public class AdHocCar extends Car {
	private static final Color COLOR = Color.red;

	public AdHocCar() {
		Random random = new Random();
		int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
		this.setColor(COLOR);
		this.setMinutesLeft(stayMinutes);
		this.setHasToPay(true);
	}

	public Color getColor() {
		return COLOR;
	}
}
