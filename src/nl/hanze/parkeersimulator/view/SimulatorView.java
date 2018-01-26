package nl.hanze.parkeersimulator.view;

import javax.swing.*;

import nl.hanze.parkeersimulator.model.Location;
import nl.hanze.parkeersimulator.model.SimulatorModel;
import nl.hanze.parkeersimulator.model.cars.Car;

import java.awt.*;

public class SimulatorView extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1728200002303044273L;
	private SimulatorModel simulatorModel;
	private CarParkView carParkView;
	// private int numberOfFloors;
	// private int numberOfRows;
	// private int numberOfPlaces;
	// private int numberOfOpenSpots;
	private Car[][][] cars;

	public SimulatorView(SimulatorModel simulatorModel) {
		this.simulatorModel = simulatorModel;
		// this.numberOfFloors = numberOfFloors;
		// this.numberOfRows = numberOfRows;
		// this.numberOfPlaces = numberOfPlaces;
		// this.numberOfOpenSpots =numberOfFloors*numberOfRows*numberOfPlaces;
		
		// TODO cars ook verplaatsen naar simulatorModel
		cars = new Car[this.simulatorModel.getNumberOfFloors()][this.simulatorModel
				.getNumberOfRows()][this.simulatorModel.getNumberOfPlaces()];

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

	// TODO tijdelijke manier om bij aantal open spots te komen
	public SimulatorModel getSimulatorModel() {
		return simulatorModel;
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
			// TODO mooiere methode voor maken?
			this.simulatorModel.setNumberOfOpenSpots(this.simulatorModel.getNumberOfOpenSpots() - 1);
			// numberOfOpenSpots--;
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
		// TODO mooiere methode voor maken?
		this.simulatorModel.setNumberOfOpenSpots(this.simulatorModel.getNumberOfOpenSpots() + 1);
		// numberOfOpenSpots++;
		return car;
	}

	public Location getFirstFreeLocation() {
		for (int floor = 0; floor < this.simulatorModel.getNumberOfFloors(); floor++) {
			for (int row = 0; row < this.simulatorModel.getNumberOfRows(); row++) {
				for (int place = 0; place < this.simulatorModel.getNumberOfPlaces(); place++) {
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
		for (int floor = 0; floor < this.simulatorModel.getNumberOfFloors(); floor++) {
			for (int row = 0; row < this.simulatorModel.getNumberOfRows(); row++) {
				for (int place = 0; place < this.simulatorModel.getNumberOfPlaces(); place++) {
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
		for (int floor = 0; floor < this.simulatorModel.getNumberOfFloors(); floor++) {
			for (int row = 0; row < this.simulatorModel.getNumberOfRows(); row++) {
				for (int place = 0; place < this.simulatorModel.getNumberOfPlaces(); place++) {
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
		if (floor < 0 || floor >= this.simulatorModel.getNumberOfFloors() || row < 0
				|| row > this.simulatorModel.getNumberOfRows() || place < 0
				|| place > this.simulatorModel.getNumberOfPlaces()) {
			return false;
		}
		return true;
	}

	// TODO Eigen class van maken
	private class CarParkView extends JPanel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 340979964327648584L;
		private Dimension size;
		private Image carParkImage;
		private SimulatorModel simulatorModel;

		/**
		 * Constructor for objects of class CarPark
		 */
		public CarParkView(SimulatorModel simulatorModel) {
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