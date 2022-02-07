package navysu.spothero.dto;

import java.util.List;

import javax.validation.Valid;

import lombok.Data;
import navysu.spothero.entity.SpotRate;

/**
 * Mapping Json data to java POJO
 * @author navysu
 *
 */
@Data
public class JsonTestData {
	
	@Valid
	private List<SpotRate> rates;
	
}
