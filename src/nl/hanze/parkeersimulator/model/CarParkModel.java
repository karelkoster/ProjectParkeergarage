package nl.hanze.parkeersimulator.model;

import java.awt.Color;
import java.util.Random;

import nl.hanze.parkeersimulator.cars.AdHocCar;
import nl.hanze.parkeersimulator.cars.Car;
import nl.hanze.parkeersimulator.cars.ParkingPassCar;

public class CarParkModel extends AbstractModel implements Runnable {
	public static boolean run;

	private CarQueue entranceCarQueue;
	private CarQueue entrancePassQueue;
	private CarQueue paymentCarQueue;
	private CarQueue exitCarQueue;

	private TimeModel timeModel;

	private int numberOfFloors;
	private int numberOfRows;
	private int numberOfPlaces;
	private int numberOfOpenSpots;
	private Car[][][] cars;
	

	private static final String AD_HOC = "1";
	private static final String PASS = "2";

	private int normaal;
	private int reservering;

	private int tickPause = 100;
	
	private int day = 0;
	private int hour = 0;
	private int minute = 0;

	int weekDayArrivals = 100; // average number of arriving cars per hour
	int weekendArrivals = 200; // average number of arriving cars per hour
	int weekDayPassArrivals = 50; // average number of arriving cars per hour
	int weekendPassArrivals = 5; // average number of arriving cars per hour

	int enterSpeed = 3; // number of cars that can enter per minute
	int paymentSpeed = 7; // number of cars that can pay per minute
	int exitSpeed = 5; // number of cars that can leave per minute

	/**
	 * @param numberOfFloors
	 * @param numberOfRows
	 * @param numberOfPlaces
	 */
	public CarParkModel(int numberOfFloors, int numberOfRows, int numberOfPlaces) {
		this.entranceCarQueue = new CarQueue();
		this.entrancePassQueue = new CarQueue();
		this.paymentCarQueue = new CarQueue();
		this.exitCarQueue = new CarQueue();
		this.timeModel = new TimeModel();

		this.numberOfFloors = numberOfFloors;
		this.numberOfRows = numberOfRows;
		this.numberOfPlaces = numberOfPlaces;
		this.numberOfOpenSpots = numberOfFloors * numberOfRows * numberOfPlaces;

		cars = new Car[numberOfFloors][numberOfRows][numberOfPlaces];
	}
	

	public int getNumberOfFloors() {
		return numberOfFloors;
	}

	public void setNumberOfFloors(int numberOfFloors) {
		this.numberOfFloors = numberOfFloors;
	}
	 public int getDay() {
	        return day;
	    }

	    public int getHour() {
	        return hour;
	    }

	    public int getMinute() {
	        return minute;
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

	public int getNormaal() {
		return normaal;
	}

	public int getReservering() {
		return reservering;
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

	public void run() {
		for (int i = 0; i < 10000 && run; i++) {
			tick();
		}
		
	}
	public void start() {
		if(!run) {
			run = true;
			new Thread(this).start();
		}
	}
	private void tick() {
		oldtick();
		handleExit();
		advanceTime();
		notifyViews();
		handleEntrance();
		try {
			Thread.sleep(tickPause);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	 private void advanceTime() {
	        minute++;
	        while (minute > 59) {
	            minute -= 60;
	            hour++;
	        }
	        while (hour > 23) {
	            hour -= 24;
	            day++;
	        }
	        while (day > 6) {
	            day -= 7;
	        }
	    }

	private void handleEntrance() {
		carsArriving();
		carsEntering(entrancePassQueue);
		carsEntering(entranceCarQueue);
	}

	private void handleExit() {
		carsReadyToLeave();
		carsPaying();
		carsLeaving();
	}

	private void carsArriving() {
		int numberOfCars = getNumberOfCars(weekDayArrivals, weekendArrivals);
		addArrivingCars(numberOfCars, AD_HOC);
		numberOfCars = getNumberOfCars(weekDayPassArrivals, weekendPassArrivals);
		addArrivingCars(numberOfCars, PASS);
	}

	private void carsEntering(CarQueue queue) {
		int i = 0;
		// Remove car from the front of the queue and assign to a parking space.
		while (queue.carsInQueue() > 0 && getNumberOfOpenSpots() > 0 && i < enterSpeed) {
			Car car = queue.removeCar();
			Location freeLocation = getFirstFreeLocation();
			setCarAt(freeLocation, car);
			i++;
		}
	}

	private void carsReadyToLeave() {
		// Add leaving cars to the payment queue.
		Car car = getFirstLeavingCar();
		while (car != null) {
			if (car.getHasToPay()) {
				car.setIsPaying(true);
				paymentCarQueue.addCar(car);
			} else {
				carLeavesSpot(car);
			}
			car = getFirstLeavingCar();
		}
	}

	private void carsPaying() {
		// Let cars pay.
		int i = 0;
		while (paymentCarQueue.carsInQueue() > 0 && i < paymentSpeed) {
			Car car = paymentCarQueue.removeCar();
			// TODO Handle payment.
			carLeavesSpot(car);
			i++;
		}
	}

	private void carsLeaving() {
		// Let cars leave.
		int i = 0;
		while (exitCarQueue.carsInQueue() > 0 && i < exitSpeed) {
			exitCarQueue.removeCar();
			i++;
		}
	}

	private int getNumberOfCars(int weekDay, int weekend) {
		Random random = new Random();

		// Get the average number of cars that arrive per hour.
		int averageNumberOfCarsPerHour = this.timeModel.getDay() < 5 ? weekDay : weekend;

		// Calculate the number of cars that arrive this minute.
		double standardDeviation = averageNumberOfCarsPerHour * 0.3;
		double numberOfCarsPerHour = averageNumberOfCarsPerHour + random.nextGaussian() * standardDeviation;
		return (int) Math.round(numberOfCarsPerHour / 60);
	}

	private void addArrivingCars(int numberOfCars, String type) {
		// Add the cars to the back of the queue.
		switch (type) {
		case AD_HOC:
			for (int i = 0; i < numberOfCars; i++) {
				entranceCarQueue.addCar(new AdHocCar());
				normaal++;
			}
			break;
		case PASS:
			for (int i = 0; i < numberOfCars; i++) {
				entrancePassQueue.addCar(new ParkingPassCar());
				reservering++;
			}
			break;
		}
	}

	private void carLeavesSpot(Car car) {
		Color COLORRED = Color.red;
		removeCarAt(car.getLocation());
		if (car.getColor() == COLORRED) {
			normaal--;
		} else {
			reservering--;
		}
		exitCarQueue.addCar(car);
	}

	public Location getFirstFreeLocation() {
		for (int floor = 0; floor < getNumberOfFloors(); floor++) {
			for (int row = 0; row < getNumberOfRows(); row++) {
				for (int place = 0; place < getNumberOfPlaces(); place++) {
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
		for (int floor = 0; floor < getNumberOfFloors(); floor++) {
			for (int row = 0; row < getNumberOfRows(); row++) {
				for (int place = 0; place < getNumberOfPlaces(); place++) {
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

	public void oldtick() {
		for (int floor = 0; floor < getNumberOfFloors(); floor++) {
			for (int row = 0; row < getNumberOfRows(); row++) {
				for (int place = 0; place < getNumberOfPlaces(); place++) {
					Location location = new Location(floor, row, place);
					Car car = getCarAt(location);
					if (car != null) {
						car.tick();
					}
				}
			}
		}
	}

	public void stop() {
		run = false;
	}
	public void close() {
                System.exit(0);
        }

}
