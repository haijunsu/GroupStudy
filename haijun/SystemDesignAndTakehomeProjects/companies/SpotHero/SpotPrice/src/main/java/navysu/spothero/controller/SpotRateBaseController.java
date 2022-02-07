package navysu.spothero.controller;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import navysu.spothero.cache.SpotRateCache;
import navysu.spothero.dto.JsonTestData;
import navysu.spothero.dto.ResponsePrice;
import navysu.spothero.dto.ResponsePriceDto;
import navysu.spothero.dto.ResponsePriceMessage;
import navysu.spothero.dto.SpotRateDto;
import navysu.spothero.entity.SpotRate;
import navysu.spothero.services.SpotRateService;

/**
 * Base controller can be inherited by other controllers. Some general functions
 * are implemented for children.
 * 
 * @author navysu
 *
 */
public class SpotRateBaseController {

	private static Logger logger = LoggerFactory.getLogger(SpotRateBaseController.class);

	public static final String STRING_UNAVAILABLE = "unavailable";

	public static final String STRING_SUCCESS = "success";

	public static final String STRING_FAIL = "fail";

	public static final ResponsePriceDto MESSAGE_UNAVAILABLE = new ResponsePriceMessage(STRING_UNAVAILABLE);

	public static final ResponsePriceDto MESSAGE_SUCCESS = new ResponsePriceMessage(STRING_SUCCESS);

	public static final ResponsePriceDto MESSAGE_FAIL = new ResponsePriceMessage(STRING_FAIL);

	/**
	 * Get all data from cache
	 * 
	 * @param cache
	 * @return ResponseEntity
	 */
	protected ResponseEntity<JsonTestData> getAllRates(SpotRateCache cache) {
		try {
			JsonTestData data = new JsonTestData();
			data.setRates(cache.getAll());
			return new ResponseEntity<>(data, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("getAllRates error", e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Update rates with provided Json data. Cache will be cleared
	 * @param data - rates data for store
	 * @param service - save data to database 
	 * @param cache - cache data
	 * @return ResponseEntity
	 */
	protected ResponseEntity<Object> updateRates(JsonTestData data, SpotRateService service, SpotRateCache cache) {
		try {
			service.deleteAll();
			cache.clear();
			service.saveAll(data.getRates());
			return new ResponseEntity<>(STRING_SUCCESS, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("getAllRates error", e);
			return new ResponseEntity<>(STRING_FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Get price from cache based on user's input
	 * @param start - timeslot start time
	 * @param end - timeslot end time
	 * @param cache - cache data
	 * @return ResponseEntity which carries price data or error message
	 */
	protected ResponseEntity<Object> getPrice(String start, String end, SpotRateCache cache) {
		try {
			// fix '+' as ' ' in URL
			start = start.replace(' ', '+');
			end = end.replace(' ', '+');
			ZonedDateTime startTime = ZonedDateTime.parse(start, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
			ZonedDateTime endTime = ZonedDateTime.parse(end, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
			if (startTime.compareTo(endTime) > 0) {
				// startTime is greater than endTime. There is no value.
				return new ResponseEntity<>(STRING_UNAVAILABLE, HttpStatus.OK);
			}
			Collection<SpotRate> allRates = cache.getAll();
			boolean isFound = false;
			ResponsePrice price = new ResponsePrice();
			for (SpotRate rate : allRates) {
				// there is no index or other way to search faster.
				// we need to check them one by one.
				logger.debug(rate.toString());
				SpotRateDto dto = new SpotRateDto(rate);
				if (dto.isValid(startTime, endTime)) {
					logger.debug("There is an available price. ");
					if (!isFound) {
						logger.debug("Create response price");
						price.setPrice(dto.getPrice());
						isFound = true;
					} else {
						// more than one record
						logger.debug("There are multiple available prices");
						return new ResponseEntity<>(STRING_UNAVAILABLE, HttpStatus.OK);

					}
				}
			}
			if (isFound) {
				return new ResponseEntity<>(price, HttpStatus.OK);
			}
			return new ResponseEntity<>(STRING_UNAVAILABLE, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("getAllRates error", e);
			return new ResponseEntity<>(STRING_FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
