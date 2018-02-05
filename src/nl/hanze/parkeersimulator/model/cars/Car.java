package nl.hanze.parkeersimulator.model.cars;

import java.awt.Color;


import nl.hanze.parkeersimulator.model.Location;

public abstract class Car {

	private Location location;
	private int minutesLeft;
	private boolean isPaying;
	private boolean hasToPay;
	private Color color;

	/**
	 * Constructor for objects of class Car
	 */
	public Car() {

	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public int getMinutesLeft() {
		return minutesLeft;
	}

	public void setMinutesLeft(int minutesLeft) {
		this.minutesLeft = minutesLeft;
	}

	public boolean getIsPaying() {
		return isPaying;
	}

	public void setIsPaying(boolean isPaying) {
		this.isPaying = isPaying;
	}

	public boolean getHasToPay() {
		return hasToPay;
	}

	public void setHasToPay(boolean hasToPay) {
		this.hasToPay = hasToPay;
	}

	public void tick() {
		minutesLeft--;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
}