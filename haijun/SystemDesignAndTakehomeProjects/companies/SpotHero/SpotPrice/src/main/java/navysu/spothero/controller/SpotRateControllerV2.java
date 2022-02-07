package navysu.spothero.controller;
/**
 * Implementation with MongoDB. It is the version v2 which can support get/update a single rate.
 */

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import navysu.spothero.cache.SpotRateCache;
import navysu.spothero.dto.JsonTestData;
import navysu.spothero.dto.ResponsePriceDto;
import navysu.spothero.entity.SpotRate;
import navysu.spothero.services.SpotRateService;

@RestController
@RequestMapping("/v2")
public class SpotRateControllerV2 extends SpotRateBaseController {

	private static Logger logger = LoggerFactory.getLogger(SpotRateController.class);

	@Autowired
	@Qualifier("spotRateServiceMongo")
	private SpotRateService service;

	@Autowired
	@Qualifier("spotRateCacheMongo")
	private SpotRateCache cache;

	@GetMapping("/rates")
	public ResponseEntity<JsonTestData> getAllRates() {
		return getAllRates(cache);
	}

	@GetMapping("/rates/{id}")
	public ResponseEntity<SpotRate> getRate(@PathVariable("id") Long id) {
		try {
			SpotRate rate = cache.get(id);
			if (rate == null) {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(rate, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("getRate error", e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/rates")
	public ResponseEntity<ResponsePriceDto> updateRates(@Valid @RequestBody JsonTestData data) {
		ResponseEntity<Object> response = updateRates(data, service, cache);

		if (response.getStatusCode() == HttpStatus.OK) {
			return new ResponseEntity<>(MESSAGE_SUCCESS, response.getStatusCode());
		}
		return new ResponseEntity<>(MESSAGE_FAIL, response.getStatusCode());

	}

	@PutMapping("/rates/{id}")
	public ResponseEntity<SpotRate> updateRate(@PathVariable("id") Long id, @Valid @RequestBody SpotRate data) {
		try {
			cache.clear();
			SpotRate rate = service.update(id, data);
			if (rate == null) {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(rate, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("updateRate error", e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/price")
	public ResponseEntity<ResponsePriceDto> getPrice(@RequestParam(required = true) String start,
			@RequestParam(required = true) String end) {
		ResponseEntity<Object> response = getPrice(start, end, cache);
		if (response.getBody().equals(STRING_FAIL)) {
			return new ResponseEntity<>(MESSAGE_FAIL, response.getStatusCode());
		} else if (response.getBody().equals(STRING_UNAVAILABLE)) {
			return new ResponseEntity<>(MESSAGE_UNAVAILABLE, HttpStatus.OK);
		} else {
			return new ResponseEntity<>((ResponsePriceDto) response.getBody(), HttpStatus.OK);
		}
	}

}
