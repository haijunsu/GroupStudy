package navysu.spothero.controller;

/**
 * Implementation with storage in memory.
 */
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import navysu.spothero.cache.SpotRateCache;
import navysu.spothero.dto.JsonTestData;
import navysu.spothero.services.SpotRateService;

@RestController
@RequestMapping("/")
public class SpotRateController extends SpotRateBaseController {

	@Autowired
	@Qualifier("spotRateServiceInMemory")
	private SpotRateService service;

	@Autowired
	@Qualifier("spotRateCacheInMemory")
	private SpotRateCache cache;

	@GetMapping("/rates")
	public ResponseEntity<JsonTestData> getAllRates() {
		return getAllRates(cache);
	}

	@PutMapping("/rates")
	public ResponseEntity<Object> updateRates(@Valid @RequestBody JsonTestData data) {
		return updateRates(data, service, cache);
	}

	@GetMapping("/price")
	public ResponseEntity<Object> getPrice(@RequestParam(required = true) String start,
			@RequestParam(required = true) String end) {
		return getPrice(start, end, cache);
	}

}
