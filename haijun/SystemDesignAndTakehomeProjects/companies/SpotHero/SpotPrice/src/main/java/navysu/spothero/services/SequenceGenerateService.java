package navysu.spothero.services;

import org.springframework.stereotype.Service;
/**
 * Service to generate sequence for entity
 * @author navysu
 *
 */
@Service
public interface SequenceGenerateService {

	/**
	 * Generate sequence for application
	 * @param seed - Sequence value is based on the seed
	 * @return new sequence number.
	 */
	Long generateSequence(String seed);
	
	/**
	 * Reset the sequence.
	 */
	void resetSequence(String seed);
}
