package nl.hanze.parkeersimulator.model;

// TODO: Auto-generated Javadoc
/**
 * The Class Location.
 */
public class Location {

	/** The floor. */
	private int floor;
	
	/** The row. */
	private int row;
	
	/** The place. */
	private int place;

	/**
	 * Constructor for objects of class Location.
	 *
	 * @param floor the floor
	 * @param row the row
	 * @param place the place
	 */
	public Location(int floor, int row, int place) {
		this.floor = floor;
		this.row = row;
		this.place = place;
	}

	/**
	 * Implement content equality.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	public boolean equals(Object obj) {
		if (obj instanceof Location) {
			Location other = (Location) obj;
			return floor == other.getFloor() && row == other.getRow() && place == other.getPlace();
		} else {
			return false;
		}
	}

	/**
	 * Return a string of the form floor,row,place.
	 * 
	 * @return A string representation of the location.
	 */
	public String toString() {
		return floor + "," + row + "," + place;
	}

	/**
	 * Use the 10 bits for each of the floor, row and place values. Except for very
	 * big car parks, this should give a unique hash code for each (floor, row,
	 * place) tupel.
	 * 
	 * @return A hashcode for the location.
	 */
	public int hashCode() {
		return (floor << 20) + (row << 10) + place;
	}

	/**
	 * Gets the floor.
	 *
	 * @return The floor.
	 */
	public int getFloor() {
		return floor;
	}

	/**
	 * Gets the row.
	 *
	 * @return The row.
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Gets the place.
	 *
	 * @return The place.
	 */
	public int getPlace() {
		return place;
	}

}