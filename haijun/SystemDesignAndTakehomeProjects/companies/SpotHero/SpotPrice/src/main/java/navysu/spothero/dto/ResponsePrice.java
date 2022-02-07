package navysu.spothero.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Object for get price response
 * @author navysu
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class ResponsePrice extends ResponsePriceDto {
	
	private int price;

}
