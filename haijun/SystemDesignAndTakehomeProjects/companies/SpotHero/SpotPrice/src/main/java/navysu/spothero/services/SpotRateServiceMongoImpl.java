package navysu.spothero.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import navysu.spothero.entity.SpotRate;
import navysu.spothero.repositories.SpotRateRepository;

@Service("spotRateServiceMongo")
public class SpotRateServiceMongoImpl implements SpotRateService {
	
	private static Logger logger = LoggerFactory.getLogger(SpotRateServiceMongoImpl.class);

	private SpotRateRepository repository;
	
	@Autowired
	public SpotRateServiceMongoImpl(SpotRateRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public List<SpotRate> listAll() {
		return repository.findAll();
	}

	@Override
	public SpotRate get(Long id) {
		return repository.findById(id).get();
	}


	@Override
	public SpotRate update(Long id, SpotRate rate) {
		Optional<SpotRate> spotRate = repository.findById(id);
		if (spotRate.isPresent()) {
			SpotRate origRate = spotRate.get();
			BeanUtils.copyProperties(rate, origRate);
			origRate.setId(id);
			rate = repository.save(origRate);
			return rate;
		}
		logger.warn("Rate(" + id + ") doesn't exist. Nothing is updated!");
		return null;
	}

	@Override
	public SpotRate save(SpotRate rate) {
		rate = repository.save(rate);
		return rate;
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}

	@Override
	public void deleteAll() {
		repository.deleteAll();
	}

	@Override
	public List<SpotRate> saveAll(List<SpotRate> rates) {
		return repository.saveAll(rates);
	}

}
