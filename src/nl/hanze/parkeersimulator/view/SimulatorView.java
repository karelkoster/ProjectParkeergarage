package nl.hanze.parkeersimulator.view;

import javax.swing.*;

import nl.hanze.parkeersimulator.model.Location;
import nl.hanze.parkeersimulator.model.CarParkModel;
import nl.hanze.parkeersimulator.model.cars.Car;

import java.awt.*;

public class SimulatorView extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1728200002303044273L;
	private CarParkModel carParkModel;
	private CarParkView carParkView;
	private Car[][][] cars;

	public SimulatorView(CarParkModel simulatorModel) {
		this.carParkModel = simulatorModel;

		// TODO cars verplaatsen naar simulatorModel
		cars = new Car[this.carParkModel.getNumberOfFloors()][this.carParkModel
				.getNumberOfRows()][this.carParkModel.getNumberOfPlaces()];

		carParkView = new CarParkView(simulatorModel);

		Container contentPane = getContentPane();
		contentPane.add(carParkView, BorderLayout.CENTER);
		pack();
		setVisible(true);

		updateView();
	}

	public void updateView() {
		carParkView.updateView();
	}

	public CarParkModel getCarParkModel() {
		return carParkModel;
	}

	public Car getCarAt(Location location) {
		if (!locationIsValid(location)) {
			return null;
		}
		return cars[location.getFloor()][location.getRow()][location.getPlace()];
	}

	public boolean setCarAt(Location location, Car car) {
		if (!locationIsValid(location)) {
			return false;
		}
		Car oldCar = getCarAt(location);
		if (oldCar == null) {
			cars[location.getFloor()][location.getRow()][location.getPlace()] = car;
			car.setLocation(location);
			this.carParkModel.setNumberOfOpenSpots(this.carParkModel.getNumberOfOpenSpots() - 1);
			return true;
		}
		return false;
	}

	public Car removeCarAt(Location location) {
		if (!locationIsValid(location)) {
			return null;
		}
		Car car = getCarAt(location);
		if (car == null) {
			return null;
		}
		cars[location.getFloor()][location.getRow()][location.getPlace()] = null;
		car.setLocation(null);
		this.carParkModel.setNumberOfOpenSpots(this.carParkModel.getNumberOfOpenSpots() + 1);
		return car;
	}

	public Location getFirstFreeLocation() {
		for (int floor = 0; floor < this.carParkModel.getNumberOfFloors(); floor++) {
			for (int row = 0; row < this.carParkModel.getNumberOfRows(); row++) {
				for (int place = 0; place < this.carParkModel.getNumberOfPlaces(); place++) {
					Location location = new Location(floor, row, place);
					if (getCarAt(location) == null) {
						return location;
					}
				}
			}
		}
		return null;
	}

	public Car getFirstLeavingCar() {
		for (int floor = 0; floor < this.carParkModel.getNumberOfFloors(); floor++) {
			for (int row = 0; row < this.carParkModel.getNumberOfRows(); row++) {
				for (int place = 0; place < this.carParkModel.getNumberOfPlaces(); place++) {
					Location location = new Location(floor, row, place);
					Car car = getCarAt(location);
					if (car != null && car.getMinutesLeft() <= 0 && !car.getIsPaying()) {
						return car;
					}
				}
			}
		}
		return null;
	}

	public void tick() {
		for (int floor = 0; floor < this.carParkModel.getNumberOfFloors(); floor++) {
			for (int row = 0; row < this.carParkModel.getNumberOfRows(); row++) {
				for (int place = 0; place < this.carParkModel.getNumberOfPlaces(); place++) {
					Location location = new Location(floor, row, place);
					Car car = getCarAt(location);
					if (car != null) {
						car.tick();
					}
				}
			}
		}
	}

	private boolean locationIsValid(Location location) {
		int floor = location.getFloor();
		int row = location.getRow();
		int place = location.getPlace();
		if (floor < 0 || floor >= this.carParkModel.getNumberOfFloors() || row < 0
				|| row > this.carParkModel.getNumberOfRows() || place < 0
				|| place > this.carParkModel.getNumberOfPlaces()) {
			return false;
		}
		return true;
	}

	private class CarParkView extends JPanel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 340979964327648584L;
		private Dimension size;
		private Image carParkImage;
		private CarParkModel simulatorModel;

		/**
		 * Constructor for objects of class CarPark
		 */
		public CarParkView(CarParkModel simulatorModel) {
			this.simulatorModel = simulatorModel;
			size = new Dimension(0, 0);
		}

		/**
		 * Overridden. Tell the GUI manager how big we would like to be.
		 */
		public Dimension getPreferredSize() {
			return new Dimension(800, 500);
		}

		/**
		 * Overridden. The car park view component needs to be redisplayed. Copy the
		 * internal image to screen.
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

		public void updateView() {
			// Create a new car park image if the size has changed.
			if (!size.equals(getSize())) {
				size = getSize();
				carParkImage = createImage(size.width, size.height);
			}
			Graphics graphics = carParkImage.getGraphics();
			for (int floor = 0; floor < this.simulatorModel.getNumberOfFloors(); floor++) {
				for (int row = 0; row < this.simulatorModel.getNumberOfRows(); row++) {
					for (int place = 0; place < this.simulatorModel.getNumberOfPlaces(); place++) {
						Location location = new Location(floor, row, place);
						Car car = getCarAt(location);
						Color color = car == null ? Color.white : car.getColor();
						drawPlace(graphics, location, color);
					}
				}
			}
			repaint();
		}

		/**
		 * Paint a place on this car park view in a given color.
		 */
		private void drawPlace(Graphics graphics, Location location, Color color) {
			graphics.setColor(color);
			graphics.fillRect(location.getFloor() * 260 + (1 + (int) Math.floor(location.getRow() * 0.5)) * 75
					+ (location.getRow() % 2) * 20, 60 + location.getPlace() * 10, 20 - 1, 10 - 1); // TODO use dynamic
																									// size or constants
		}
	}

}
