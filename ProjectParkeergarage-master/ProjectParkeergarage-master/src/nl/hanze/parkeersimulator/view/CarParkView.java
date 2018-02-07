package nl.hanze.parkeersimulator.view;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import nl.hanze.parkeersimulator.model.Location;

import nl.hanze.parkeersimulator.model.CarParkModel;
import nl.hanze.parkeersimulator.model.cars.Car;

// TODO: Auto-generated Javadoc
/**
 * The Class CarParkView.
 */
@SuppressWarnings("serial")
public class CarParkView extends AbstractView {

	/** The size. */
	private Dimension size;
	
	/** The car park image. */
	private Image carParkImage;

	/**
	 * Constructor for objects of class CarPark.
	 *
	 * @param model the model
	 */
	public CarParkView(CarParkModel model) {
		super(model);
		size = new Dimension(800, 500);
	}

	/**
	 * Overridden. Tell the GUI manager how big we would like to be.
	 *
	 * @return the preferred size
	 */
	public Dimension getPreferredSize() {
		return new Dimension(800, 500);
	}

	/**
	 * Overridden. The car park view component needs to be redisplayed. Copy the
	 * internal image to screen.
	 *
	 * @param g the g
	 */
	public void paintComponent(Graphics g) {

		if (carParkImage == null) {
			return;
		}

		Dimension currentSize = getSize();
		if (size.equals(currentSize)) {
			g.drawImage(carParkImage, 0, 0, null);
		} else {
			// Resize the previous image.
			g.drawImage(carParkImage, 0, 0, currentSize.width, currentSize.height, null);
		}
	}

	/* (non-Javadoc)
	 * @see nl.hanze.parkeersimulator.view.AbstractView#updateView()
	 */
	public void updateView() {
		// Create a new car park image if the size has changed.
		if (!size.equals(getSize())) {
			size = getSize();
			carParkImage = createImage(size.width, size.height);
		}
		Graphics graphics = carParkImage.getGraphics();
		for (int floor = 0; floor < model.getNumberOfFloors(); floor++) {
			for (int row = 0; row < model.getNumberOfRows(); row++) {
				for (int place = 0; place < model.getNumberOfPlaces(); place++) {
					Location location = new Location(floor, row, place);
					Car car = model.getCarAt(location);
					Color color = car == null ? Color.white : car.getColor();
					drawPlace(graphics, location, color);
				}
			}
		}
		repaint();
	}

	/**
	 * Paint a place on this car park view in a given color.
	 *
	 * @param graphics the graphics
	 * @param location the location
	 * @param color the color
	 */
	private void drawPlace(Graphics graphics, Location location, Color color) {
		graphics.setColor(color);
		graphics.fillRect(location.getFloor() * 260 + (1 + (int) Math.floor(location.getRow() * 0.5)) * 75
				+ (location.getRow() % 2) * 20, 60 + location.getPlace() * 10, 20 - 1, 10 - 1);
	}
}
