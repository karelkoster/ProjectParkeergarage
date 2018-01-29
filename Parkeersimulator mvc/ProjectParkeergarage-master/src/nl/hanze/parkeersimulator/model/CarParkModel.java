package nl.hanze.parkeersimulator.model;

import nl.hanze.parkeersimulator.model.cars.Car;

public class CarParkModel {

	private int numberOfFloors;
	private int numberOfRows;
	private int numberOfPlaces;
	private int numberOfOpenSpots;
	private Car[][][] cars;

	/**
	 * @param numberOfFloors
	 * @param numberOfRows
	 * @param numberOfPlaces
	 */
	public CarParkModel(int numberOfFloors, int numberOfRows, int numberOfPlaces) {
		this.numberOfFloors = numberOfFloors;
		this.numberOfRows = numberOfRows;
		this.numberOfPlaces = numberOfPlaces;
		this.numberOfOpenSpots = numberOfFloors * numberOfRows * numberOfPlaces;

		this.cars = new Car[numberOfFloors][numberOfRows][numberOfPlaces];
	}

	public int getNumberOfFloors() {
		return numberOfFloors;
	}

	public void setNumberOfFloors(int numberOfFloors) {
		this.numberOfFloors = numberOfFloors;
	}

	public int getNumberOfRows() {
		return numberOfRows;
	}

	public void setNumberOfRows(int numberOfRows) {
		this.numberOfRows = numberOfRows;
	}

	public int getNumberOfPlaces() {
		return numberOfPlaces;
	}

	public void setNumberOfPlaces(int numberOfPlaces) {
		this.numberOfPlaces = numberOfPlaces;
	}

	public int getNumberOfOpenSpots() {
		return numberOfOpenSpots;
	}

	public void setNumberOfOpenSpots(int numberOfOpenSpots) {
		this.numberOfOpenSpots = numberOfOpenSpots;
	}

	public Car[][][] getCars() {
		return cars;
	}

	public void setCars(Car[][][] cars) {
		this.cars = cars;
	}

	public Car getCarAt(Location location) {
		if (!locationIsValid(location)) {
			return null;
		}
		Car car = cars[location.getFloor()][location.getRow()][location.getPlace()];
		return car;
	}

	public boolean setCarAt(Location location, Car car) {
		if (!locationIsValid(location)) {
			return false;
		}
		Car oldCar = getCarAt(location);
		if (oldCar == null) {
			cars[location.getFloor()][location.getRow()][location.getPlace()] = car;
			car.setLocation(location);
			setNumberOfOpenSpots(getNumberOfOpenSpots() - 1);
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
		setNumberOfOpenSpots(getNumberOfOpenSpots() + 1);
		return car;
	}

	private boolean locationIsValid(Location location) {
		int floor = location.getFloor();
		int row = location.getRow();
		int place = location.getPlace();
		if (floor < 0 || floor >= getNumberOfFloors() || row < 0 || row > getNumberOfRows() || place < 0
				|| place > getNumberOfPlaces()) {
			return false;
		}
		return true;
	}

}
