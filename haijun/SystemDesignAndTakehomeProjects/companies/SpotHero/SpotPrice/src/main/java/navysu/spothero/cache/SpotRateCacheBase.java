package navysu.spothero.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import navysu.spothero.entity.SpotRate;

/**
 * Cache main functions implemented in BaseCache.The loadCache event is triggered
 * by methods get and getAll if the cache is empty.
 * 
 * @author navysu
 *
 */
public abstract class SpotRateCacheBase implements SpotRateCache {

	/**
	 * Cache rates in a map
	 */
	private Map<Long, SpotRate> cacheMap = new ConcurrentHashMap<>();

	/**
	 * Load all records from database
	 * 
	 * @return
	 */
	protected abstract List<SpotRate> getAllRates();

	/**
	 * load cache from database
	 */
	private void loadCache() {
		synchronized (this) {
			if (cacheMap.isEmpty()) {
				List<SpotRate> allRates = getAllRates();
				for (SpotRate rate : allRates) {
					cacheMap.put(rate.getId(), rate);
				}
			}
		}

	}

	@Override
	public void clear() {
		cacheMap.clear();
	}

	@Override
	public SpotRate put(Long id, SpotRate spotRate) {
		return cacheMap.put(id, spotRate);
	}

	@Override
	public SpotRate remove(Long id) {
		return cacheMap.remove(id);
	}

	@Override
	public SpotRate get(Long id) {
		if (cacheMap.isEmpty() || cacheMap.get(id) == null) {
			loadCache();
		}
		return cacheMap.get(id);
	}

	@Override
	public List<SpotRate> getAll() {
		if (cacheMap.isEmpty()) {
			loadCache();
		}
		return new ArrayList<>(cacheMap.values());
	}
}
