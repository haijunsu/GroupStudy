package navysu.spothero.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import navysu.spothero.entity.SpotRate;

@Repository
public interface SpotRateRepository extends MongoRepository<SpotRate, Long> {

}
