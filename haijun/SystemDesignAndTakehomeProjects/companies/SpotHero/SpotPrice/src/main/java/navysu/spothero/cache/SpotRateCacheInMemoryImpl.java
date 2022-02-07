package navysu.spothero.cache;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import navysu.spothero.entity.SpotRate;
import navysu.spothero.services.SpotRateService;

/**
 * Cache will load data from InMemory implementation
 * @author navysu
 *
 */
@Component("spotRateCacheInMemory")
public class SpotRateCacheInMemoryImpl extends SpotRateCacheBase {

	/**
	 * Service to load data
	 */
	private SpotRateService service;

	/**
	 * Initial cache with SpotRateServiceInMemory.
	 * @param service
	 */
	@Autowired
	public SpotRateCacheInMemoryImpl(@Qualifier("spotRateServiceInMemory") SpotRateService service) {
		this.service = service;
	}

	@Override
	protected List<SpotRate> getAllRates() {
		return service.listAll();
	}

	

}
