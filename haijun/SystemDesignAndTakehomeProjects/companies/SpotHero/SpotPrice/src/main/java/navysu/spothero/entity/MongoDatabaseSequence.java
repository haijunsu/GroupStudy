package navysu.spothero.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

/**
 * Document which helps to generate sequence number
 * @author navysu
 *
 */
@Data
@Document(collection = "dbsequences")
public class MongoDatabaseSequence {
	
	@Id
	private String id;
	private Long seq;

}
