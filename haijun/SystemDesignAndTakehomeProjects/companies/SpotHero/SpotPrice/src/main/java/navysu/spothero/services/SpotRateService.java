package navysu.spothero.services;

import java.util.List;

import org.springframework.stereotype.Service;

import navysu.spothero.entity.SpotRate;

/**
 * CRUD SpotRate object in database
 * @author navysu
 *
 */
@Service
public interface SpotRateService {
	
	List<SpotRate> listAll();
	
	SpotRate get(Long id);
	
	SpotRate save(SpotRate rate);

	SpotRate update(Long id, SpotRate rate);
	
	void delete(Long id);
	
	void deleteAll();
	
	List<SpotRate> saveAll(List<SpotRate> rates);
	
	

}
