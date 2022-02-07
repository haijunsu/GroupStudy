package navysu.spothero.dto;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import navysu.spothero.entity.SpotRate;

/**
 * Make the SpotRate object easy to use. It provides functions which can help
 * business logics.
 * 
 * @author navysu
 *
 */
public class SpotRateDto {

	private Logger logger = LoggerFactory.getLogger(SpotRateDto.class);

	/**
	 * The SpotRate which is handled.
	 */
	private SpotRate spotRate;

	/**
	 * Week days in the rate.
	 */
	private Set<DayOfWeek> dayOfWeek;

	/**
	 * Start time of the time slot
	 */
	private int startTime;

	/**
	 * End time of the time slot
	 */
	private int endTime;
	
	/**
	 * Construct instance and initial data from string to java variables
	 * @param spotRate
	 */
	public SpotRateDto(SpotRate spotRate) {
		this.spotRate = spotRate;

		// parse week days
		String[] allDays = this.spotRate.getDays().split(",");
		this.dayOfWeek = new HashSet<>();
		for (String weekDay : allDays) {
			this.dayOfWeek.add(getDayOfWeek(weekDay.trim()));
		}
		// convert time slots to int value for compare.
		this.startTime = getStartTime(this.spotRate.getTimes());
		this.endTime = getEndTime(this.spotRate.getTimes());
	}

	/**
	 * Start time as integer from times value
	 * @param timeslot
	 * @return
	 */
	private int getStartTime(String timeslot) {
		return Integer.valueOf(timeslot.substring(0, 4));
	}

	/**
	 * End time as integer from times value
	 * @param timeslot
	 * @return
	 */
	private int getEndTime(String timeslot) {
		return Integer.valueOf(timeslot.substring(timeslot.length() - 4));
	}
	
	/**
	 * Converts day as day of week Enum.
	 * @param day
	 * @return
	 */
	private DayOfWeek getDayOfWeek(String day) {
		// "mon,tues,thurs","fri,sat,sun","wed",
		switch (day) {
		case "mon":
			return DayOfWeek.MONDAY;
		case "tues":
			return DayOfWeek.TUESDAY;
		case "wed":
			return DayOfWeek.WEDNESDAY;
		case "thurs":
			return DayOfWeek.THURSDAY;
		case "fri":
			return DayOfWeek.FRIDAY;
		case "sat":
			return DayOfWeek.SATURDAY;
		case "sun":
			return DayOfWeek.SUNDAY;

		}
		return DayOfWeek.SUNDAY;
	}

	public int getPrice() {
		return this.spotRate.getPrice();
	}

	public String getTimes() {
		return this.spotRate.getTimes();
	}

	public String getDays() {
		return this.spotRate.getDays();
	}

	public String getTz() {
		return this.spotRate.getTz();
	}

	public Set<DayOfWeek> getWeekDays() {
		return dayOfWeek;
	}
	
	/**
	 * Check whether a time period is in the time slot. All datatime will be converted
	 * as localtime of the rate timezone before verifying. It needs to meet the following
	 * requirements to return ture.
	 * 
	 *   User input cannot span more than one day
	 *   User input cannot span multiple rates
	 *   Rates will not span multiple days
	 *   
	 * @param startDate Zoned date time
	 * @param endDate Zoned data time
	 * @return
	 */
	public boolean isValid(ZonedDateTime startDate, ZonedDateTime endDate) {
		ZoneId zoneId = ZoneId.of(getTz());
		ZonedDateTime startZonedDate = startDate.withZoneSameInstant(zoneId);
		ZonedDateTime endZonedDate = endDate.withZoneSameInstant(zoneId);
		LocalDateTime startLocalDate = startZonedDate.toLocalDateTime();
		LocalDateTime endLocalDate = endZonedDate.toLocalDateTime();
		logger.debug(
				"Input start date: " + startDate + ", zoned date: " + startZonedDate + ", local date: " + startLocalDate);
		logger.debug(
				"Input end date: " + endDate + ", zoned date: " + endZonedDate + ", local date: " + endLocalDate);
		logger.debug("DayOfWeek: " + startLocalDate.getDayOfWeek() + ", " + endLocalDate.getDayOfWeek());
		if (startLocalDate.getDayOfWeek().getValue() != endLocalDate.getDayOfWeek().getValue()
				|| !this.dayOfWeek.contains(startLocalDate.getDayOfWeek())) {
			logger.debug("wrong day of week");
			return false;
		}
		int timeslotStart = startLocalDate.getHour() * 100 + startLocalDate.getMinute();
		int timeslotEnd = endLocalDate.getHour() * 100 + endLocalDate.getMinute();
		logger.debug("query timeslot: " + timeslotStart + "-" + timeslotEnd + ", current:" + this.startTime + "-"
				+ this.endTime);
		if (timeslotStart >= endTime || timeslotEnd <= startTime) {
			return false;
		}
		return true;
	}

}
