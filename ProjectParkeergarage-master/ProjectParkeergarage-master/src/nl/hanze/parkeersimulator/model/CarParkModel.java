package nl.hanze.parkeersimulator.model;

import java.awt.Color;
import java.util.Random;

import nl.hanze.parkeersimulator.model.cars.AdHocCar;
import nl.hanze.parkeersimulator.model.cars.Car;
import nl.hanze.parkeersimulator.model.cars.ParkingPassCar;

// TODO: Auto-generated Javadoc
/**
 * The Class CarParkModel.
 */
public class CarParkModel extends AbstractModel implements Runnable {
	
	/** The run. */
	public static boolean run;

	/** The entrance car queue. */
	private CarQueue entranceCarQueue;
	
	/** The entrance pass queue. */
	private CarQueue entrancePassQueue;
	
	/** The payment car queue. */
	private CarQueue paymentCarQueue;
	
	/** The exit car queue. */
	private CarQueue exitCarQueue;

	/** The time model. */
	private TimeModel timeModel;

	/** The number of floors. */
	private int numberOfFloors;
	
	/** The number of rows. */
	private int numberOfRows;
	
	/** The number of places. */
	private int numberOfPlaces;
	
	/** The number of open spots. */
	private int numberOfOpenSpots;
	
	/** The cars. */
	private Car[][][] cars;

	/** The Constant AD_HOC. */
	private static final String AD_HOC = "1";
	
	/** The Constant PASS. */
	private static final String PASS = "2";

	/** The normaal. */
	private int normaal;
	
	/** The reservering. */
	private int reservering;

	/** The tick snelheid. */
	private int tickSnelheid = 1; 
	
	/** The tick pause. */
	private int tickPause = 100;

	/** The day. */
	private int day = 0;
	
	/** The hour. */
	private int hour = 0;
	
	/** The minute. */
	private int minute = 0;

	/** The week day arrivals. */
	int weekDayArrivals = 100; // average number of arriving cars per hour
	
	/** The weekend arrivals. */
	int weekendArrivals = 200; // average number of arriving cars per hour
	
	/** The week day pass arrivals. */
	int weekDayPassArrivals = 50; // average number of arriving cars per hour
	
	/** The weekend pass arrivals. */
	int weekendPassArrivals = 5; // average number of arriving cars per hour

	/** The enter speed. */
	int enterSpeed = 3; // number of cars that can enter per minute
	
	/** The payment speed. */
	int paymentSpeed = 7; // number of cars that can pay per minute
	
	/** The exit speed. */
	int exitSpeed = 5; // number of cars that can leave per minute

	/** The cars visited. */
	private int carsVisited = 0;

	/**
	 * Instantiates a new car park model.
	 *
	 * @param numberOfFloors the number of floors
	 * @param numberOfRows the number of rows
	 * @param numberOfPlaces the number of places
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
	
	/**
	 * Sets the tick pause.
	 *
	 * @param multiplier the new tick pause
	 */
	public void setTickPause(int multiplier) { 
		if (tickSnelheid <= 64) { 
		System.out.println("ThickPause before: "+tickPause);
		this.tickPause = tickPause / multiplier; 
		System.out.println("ThickPause after: "+tickPause);
		this.tickSnelheid = tickSnelheid * 2; 
		} 
	}
	
	/**
	 * Reset tick pause.
	 */
	public void resetTickPause() { 
		System.out.println("ThickPause before: "+tickPause);
		this.tickPause = 100; 
		tickSnelheid = 1; 
		
		System.out.println("ThickPause after: "+tickPause);
	}
	
	/**
	 * Gets the tick speed.
	 *
	 * @return the tick speed
	 */
	public int getTickSpeed() { 
		return tickSnelheid; 
	}
	
	/**
	 * Gets the number of floors.
	 *
	 * @return the number of floors
	 */
	public int getNumberOfFloors() {
		return numberOfFloors;
	}

	/**
	 * Sets the number of floors.
	 *
	 * @param numberOfFloors the new number of floors
	 */
	public void setNumberOfFloors(int numberOfFloors) {
		this.numberOfFloors = numberOfFloors;
	}
	
	/**
	 * Stap.
	 *
	 * @param getal the getal
	 */
	public void stap(int getal) {
        for(int i=getal; i>0; i--) {
        	oldtick();
    		handleExit();
    		advanceTime();
    		notifyViews();
    		handleEntrance();
    		try {
    			Thread.sleep(5);
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    			}
       		}
	}

	/**
	 * Gets the day.
	 *
	 * @return the day
	 */
	public int getDay() {
		return day;
	}

	/**
	 * Gets the hour.
	 *
	 * @return the hour
	 */
	public int getHour() {
		return hour;
	}

	/**
	 * Gets the minute.
	 *
	 * @return the minute
	 */
	public int getMinute() {
		return minute;
	}

	/**
	 * Gets the number of rows.
	 *
	 * @return the number of rows
	 */
	public int getNumberOfRows() {
		return numberOfRows;
	}

	/**
	 * Sets the number of rows.
	 *
	 * @param numberOfRows the new number of rows
	 */
	public void setNumberOfRows(int numberOfRows) {
		this.numberOfRows = numberOfRows;
	}

	/**
	 * Gets the number of places.
	 *
	 * @return the number of places
	 */
	public int getNumberOfPlaces() {
		return numberOfPlaces;
	}

	/**
	 * Sets the number of places.
	 *
	 * @param numberOfPlaces the new number of places
	 */
	public void setNumberOfPlaces(int numberOfPlaces) {
		this.numberOfPlaces = numberOfPlaces;
	}

	/**
	 * Gets the total number of spots.
	 *
	 * @return the total number of spots
	 */
	public int getTotalNumberOfSpots() {
		int total = getNumberOfFloors() * getNumberOfRows() * getNumberOfPlaces();
		return total;
	}

	/**
	 * Gets the number of open spots.
	 *
	 * @return the number of open spots
	 */
	public int getNumberOfOpenSpots() {
		return numberOfOpenSpots;
	}

	/**
	 * Sets the number of open spots.
	 *
	 * @param numberOfOpenSpots the new number of open spots
	 */
	public void setNumberOfOpenSpots(int numberOfOpenSpots) {
		this.numberOfOpenSpots = numberOfOpenSpots;
	}

	/**
	 * Gets the normaal.
	 *
	 * @return the normaal
	 */
	public int getNormaal() {
		return normaal;
	}

	/**
	 * Gets the reservering.
	 *
	 * @return the reservering
	 */
	public int getReservering() {
		return reservering;
	}

	/**
	 * Gets the total cars.
	 *
	 * @return the total cars
	 */
	public int getTotalCars() {
		int total = getNormaal() + getReservering();
		return total;
	}

	/**
	 * Gets the cars visited.
	 *
	 * @return the carsVisited
	 */
	public int getCarsVisited() {
		return carsVisited;
	}

	/**
	 * Sets the cars visited.
	 *
	 * @param carsVisited            the carsVisited to set
	 */
	public void setCarsVisited(int carsVisited) {
		this.carsVisited = carsVisited;
	}

	/**
	 * Gets the cars.
	 *
	 * @return the cars
	 */
	public Car[][][] getCars() {
		return cars;
	}

	/**
	 * Sets the cars.
	 *
	 * @param cars the new cars
	 */
	public void setCars(Car[][][] cars) {
		this.cars = cars;
	}

	/**
	 * Gets the car at.
	 *
	 * @param location the location
	 * @return the car at
	 */
	public Car getCarAt(Location location) {
		if (!locationIsValid(location)) {
			return null;
		}
		Car car = cars[location.getFloor()][location.getRow()][location.getPlace()];
		return car;
	}

	/**
	 * Sets the car at.
	 *
	 * @param location the location
	 * @param car the car
	 * @return true, if successful
	 */
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

	/**
	 * Removes the car at.
	 *
	 * @param location the location
	 * @return the car
	 */
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

	/**
	 * Location is valid.
	 *
	 * @param location the location
	 * @return true, if successful
	 */
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

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		for (int i = 0; i < 100000000 && run; i++) {
			tick();
		}

	}

	/**
	 * Start.
	 */
	public void start() {
		if (!run) {
			run = true;
			new Thread(this).start();
		}
	}

	/**
	 * Tick.
	 */
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

	/**
	 * Advance time.
	 */
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

	/**
	 * Handle entrance.
	 */
	private void handleEntrance() {
		carsArriving();
		carsEntering(entrancePassQueue);
		carsEntering(entranceCarQueue);
	}

	/**
	 * Handle exit.
	 */
	private void handleExit() {
		carsReadyToLeave();
		carsPaying();
		carsLeaving();
	}

	/**
	 * Cars arriving.
	 */
	private void carsArriving() {
		int numberOfCars = getNumberOfCars(weekDayArrivals, weekendArrivals);
		addArrivingCars(numberOfCars, AD_HOC);
		numberOfCars = getNumberOfCars(weekDayPassArrivals, weekendPassArrivals);
		addArrivingCars(numberOfCars, PASS);
	}

	/**
	 * Cars entering.
	 *
	 * @param queue the queue
	 */
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

	/**
	 * Cars ready to leave.
	 */
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

	/**
	 * Cars paying.
	 */
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

	/**
	 * Cars leaving.
	 */
	private void carsLeaving() {
		// Let cars leave.
		int i = 0;
		while (exitCarQueue.carsInQueue() > 0 && i < exitSpeed) {
			exitCarQueue.removeCar();
			setCarsVisited(getCarsVisited() + 1);
			i++;
		}
	}

	/**
	 * Gets the number of cars.
	 *
	 * @param weekDay the week day
	 * @param weekend the weekend
	 * @return the number of cars
	 */
	private int getNumberOfCars(int weekDay, int weekend) {
		Random random = new Random();

		// Get the average number of cars that arrive per hour.
		int averageNumberOfCarsPerHour = this.timeModel.getDay() < 5 ? weekDay : weekend;

		// Calculate the number of cars that arrive this minute.
		double standardDeviation = averageNumberOfCarsPerHour * 0.3;
		double numberOfCarsPerHour = averageNumberOfCarsPerHour + random.nextGaussian() * standardDeviation;
		return (int) Math.round(numberOfCarsPerHour / 60);
	}

	/**
	 * Adds the arriving cars.
	 *
	 * @param numberOfCars the number of cars
	 * @param type the type
	 */
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

	/**
	 * Car leaves spot.
	 *
	 * @param car the car
	 */
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

	/**
	 * Gets the first free location.
	 *
	 * @return the first free location
	 */
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

	/**
	 * Gets the first leaving car.
	 *
	 * @return the first leaving car
	 */
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

	/**
	 * Oldtick.
	 */
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

	/**
	 * Stop.
	 */
	public void stop() {
		run = false;
	}

	/**
	 * Close.
	 */
	public void close() {
		System.exit(0);
	}

}
