package navysu.spothero.services;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import navysu.spothero.entity.MongoDatabaseSequence;

/**
 * Generate sequence number for Rate object in mongoDB
 * 
 * @author navysu
 *
 */
@Service
public class SequenceGenerateServiceMongoImpl implements SequenceGenerateService {

	private static Logger logger = LoggerFactory.getLogger(SequenceGenerateServiceMongoImpl.class);

	/**
	 * Mongo operations to help access mongoDB
	 */
	private MongoOperations mongoOperations;

	/**
	 * Construct instance with MongoOperations
	 * 
	 * @param mongoOperations
	 */
	@Autowired
	public SequenceGenerateServiceMongoImpl(MongoOperations mongoOperations) {
		this.mongoOperations = mongoOperations;
	}

	/**
	 * Generate sequence based on sequence collection name.
	 * 
	 * @param seed - Collection name in MongoDB
	 * @return new sequence number
	 */
	public Long generateSequence(String seed) {
		logger.debug("SEED: " + seed);
		MongoDatabaseSequence counter = mongoOperations.findAndModify(query(where("_id").is(seed)),
				new Update().inc("seq", 1), options().returnNew(true).upsert(true), MongoDatabaseSequence.class);
		logger.debug("new seq: " + counter.getSeq());
		return !Objects.isNull(counter) ? counter.getSeq() : 1;
	}

	@Override
	public void resetSequence(String seed) {
		logger.debug("reset sequence for SEED: " + seed);
		mongoOperations.findAndModify(query(where("_id").is(seed)), new Update().set("seq", 0),
				options().returnNew(true).upsert(true), MongoDatabaseSequence.class);
	}

}
