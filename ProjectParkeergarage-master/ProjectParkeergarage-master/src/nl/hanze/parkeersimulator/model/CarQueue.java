package nl.hanze.parkeersimulator.model;

import java.util.LinkedList;
import java.util.Queue;

import nl.hanze.parkeersimulator.model.cars.Car;

// TODO: Auto-generated Javadoc
/**
 * The Class CarQueue.
 */
public class CarQueue {
	
	/** The queue. */
	private Queue<Car> queue = new LinkedList<>();

	/**
	 * Adds the car.
	 *
	 * @param car the car
	 * @return true, if successful
	 */
	public boolean addCar(Car car) {
		return queue.add(car);
	}

	/**
	 * Removes the car.
	 *
	 * @return the car
	 */
	public Car removeCar() {
		return queue.poll();
	}

	/**
	 * Cars in queue.
	 *
	 * @return the int
	 */
	public int carsInQueue() {
		return queue.size();
	}
}
