package nl.hanze.parkeersimulator.model;

// TODO: Auto-generated Javadoc
/**
 * The Class TimeModel.
 */
public class TimeModel {

	/** The day. */
	private int day = 0;
	
	/** The hour. */
	private int hour = 0;
	
	/** The minute. */
	private int minute = 0;

	/**
	 * Instantiates a new time model.
	 */
	public TimeModel() {

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
	 * Sets the day.
	 *
	 * @param day the new day
	 */
	public void setDay(int day) {
		this.day = day;
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
	 * Sets the hour.
	 *
	 * @param hour the new hour
	 */
	public void setHour(int hour) {
		this.hour = hour;
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
	 * Sets the minute.
	 *
	 * @param minute the new minute
	 */
	public void setMinute(int minute) {
		this.minute = minute;
	}

	/**
	 * Advance time by one minute.
	 */
	public void advanceTimeByOneMinute() {
		// Advance the time by one minute.
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
}
