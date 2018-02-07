package nl.hanze.parkeersimulator.model.cars;

import java.awt.Color;


import nl.hanze.parkeersimulator.model.Location;

// TODO: Auto-generated Javadoc
/**
 * The Class Car.
 */
public abstract class Car {

	/** The location. */
	private Location location;
	
	/** The minutes left. */
	private int minutesLeft;
	
	/** The is paying. */
	private boolean isPaying;
	
	/** The has to pay. */
	private boolean hasToPay;
	
	/** The color. */
	private Color color;

	/**
	 * Constructor for objects of class Car.
	 */
	public Car() {

	}

	/**
	 * Gets the location.
	 *
	 * @return the location
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * Sets the location.
	 *
	 * @param location the new location
	 */
	public void setLocation(Location location) {
		this.location = location;
	}

	/**
	 * Gets the minutes left.
	 *
	 * @return the minutes left
	 */
	public int getMinutesLeft() {
		return minutesLeft;
	}

	/**
	 * Sets the minutes left.
	 *
	 * @param minutesLeft the new minutes left
	 */
	public void setMinutesLeft(int minutesLeft) {
		this.minutesLeft = minutesLeft;
	}

	/**
	 * Gets the checks if is paying.
	 *
	 * @return the checks if is paying
	 */
	public boolean getIsPaying() {
		return isPaying;
	}

	/**
	 * Sets the checks if is paying.
	 *
	 * @param isPaying the new checks if is paying
	 */
	public void setIsPaying(boolean isPaying) {
		this.isPaying = isPaying;
	}

	/**
	 * Gets the checks for to pay.
	 *
	 * @return the checks for to pay
	 */
	public boolean getHasToPay() {
		return hasToPay;
	}

	/**
	 * Sets the checks for to pay.
	 *
	 * @param hasToPay the new checks for to pay
	 */
	public void setHasToPay(boolean hasToPay) {
		this.hasToPay = hasToPay;
	}

	/**
	 * Tick.
	 */
	public void tick() {
		minutesLeft--;
	}

	/**
	 * Sets the color.
	 *
	 * @param color the new color
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * Gets the color.
	 *
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}
}