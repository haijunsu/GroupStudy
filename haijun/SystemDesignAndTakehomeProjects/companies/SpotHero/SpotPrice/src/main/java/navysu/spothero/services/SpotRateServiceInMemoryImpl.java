package navysu.spothero.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import navysu.spothero.dto.JsonTestData;
import navysu.spothero.entity.SpotRate;
import navysu.spothero.entity.SpotRateInMemory;

@Service("spotRateServiceInMemory")
public class SpotRateServiceInMemoryImpl implements SpotRateService {
	
	private JsonTestData data;
	
	private AtomicLong sequenceID = new AtomicLong(0);

	public SpotRateServiceInMemoryImpl() {
		this.data = new JsonTestData();
		List<SpotRate> rates = new ArrayList<>();
		this.data.setRates(rates);
		
	}

	@Override
	public List<SpotRate> listAll() {
		return this.data.getRates();
	}

	@Override
	public SpotRate get(Long id) {
		// not supported
		return null;
	}

	@Override
	public SpotRate save(SpotRate rate) {
		SpotRateInMemory rateInMem = new SpotRateInMemory();
		BeanUtils.copyProperties(rate, rateInMem);
		rateInMem.setId(sequenceID.addAndGet(1));
		this.data.getRates().add(rateInMem);
		return rateInMem;
	}

	@Override
	public SpotRate update(Long id, SpotRate rate) {
		// not supported
		return null;
	}

	@Override
	public void delete(Long id) {
		// not supported		
	}

	@Override
	public void deleteAll() {
		this.data.setRates(new ArrayList<>());
		this.sequenceID = new AtomicLong(0);
	}

	@Override
	public List<SpotRate> saveAll(List<SpotRate> rates) {
		List<SpotRate> results = new ArrayList<>();
		for (SpotRate rate: rates) {
			results.add(save(rate));
		}
		return results;
	}

}
