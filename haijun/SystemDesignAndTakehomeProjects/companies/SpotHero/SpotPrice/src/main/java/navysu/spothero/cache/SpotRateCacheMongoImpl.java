package navysu.spothero.cache;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import navysu.spothero.entity.SpotRate;
import navysu.spothero.services.SpotRateService;

/**
 * Cache will load data from Mongodb
 * @author navysu
 *
 */
@Component("spotRateCacheMongo")
public class SpotRateCacheMongoImpl extends SpotRateCacheBase {

	/**
	 * Service to load data
	 */
	private SpotRateService service;

	@Autowired
	public SpotRateCacheMongoImpl(@Qualifier("spotRateServiceMongo") SpotRateService service) {
		this.service = service;
	}

	@Override
	protected List<SpotRate> getAllRates() {
		return service.listAll();
	}

	

}
