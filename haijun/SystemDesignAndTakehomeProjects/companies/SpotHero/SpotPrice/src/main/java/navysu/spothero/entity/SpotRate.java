package navysu.spothero.entity;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

/**
 * Document of rate
 * @author navysu
 *
 */

@Data
@Document(collection = "spotrate")
public class SpotRate {
	
	@Transient
    public static final String SEQUENCE_NAME = "rate_sequence";

	@Id
	private Long id;

	@NotNull
	private String days;
	
	@NotNull
	private String times;
	
	@NotNull
	private String tz;
	
	@NotNull
	private int price;
}
