package navysu.spothero.cache;

import java.util.List;

import navysu.spothero.entity.SpotRate;

/**
 * Cache can improve the performance. All query methods are getting data from cache instead of database.
 * If there is an update in database, the cache needs be cleared before saving the change to database.
 * @author navysu
 *
 */
public interface SpotRateCache {
	/**
	 * Clear all cache.
	 */
	void clear();
	
	/**
	 * Get SpotRate by id
	 * @param id - SpotRate id
	 * @return a list of SpotRate.
	 */
	SpotRate get(Long id);
	
	/**
	 * Add an object into cache
	 * @param id
	 * @param spotRate
	 */
	SpotRate put(Long id, SpotRate spotRate);

	/**
	 * Remove an object from cache
	 * @param id
	 */
	SpotRate remove(Long id);
	
	/**
	 * Get all values in cache. The order is random.
	 * @return all cached objects
	 */
	List<SpotRate> getAll();
}
