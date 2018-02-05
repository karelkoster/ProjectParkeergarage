package nl.hanze.parkeergarage.model;

import java.util.LinkedList;
import java.util.Queue;

import nl.hanze.parkeersimulator.cars.Car;

public class CarQueue {
	private Queue<Car> queue = new LinkedList<>();

	public boolean addCar(Car car) {
		return queue.add(car);
	}

	public Car removeCar() {
		return queue.poll();
	}

	public int carsInQueue() {
		return queue.size();
	}
}
