package nl.hanze.parkeersimulator.model;

public class CarParkModel {

	private int numberOfFloors;
	private int numberOfRows;
	private int numberOfPlaces;
	private int numberOfOpenSpots;

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

}
