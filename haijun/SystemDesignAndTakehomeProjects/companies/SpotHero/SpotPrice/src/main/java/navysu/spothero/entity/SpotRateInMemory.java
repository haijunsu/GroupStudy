package navysu.spothero.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * For in memory implementation which hides the id field in response.
 * @author navysu
 *
 */

@Data
@EqualsAndHashCode(callSuper=true)
public class SpotRateInMemory extends SpotRate {
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)    
	private Long id;

}
