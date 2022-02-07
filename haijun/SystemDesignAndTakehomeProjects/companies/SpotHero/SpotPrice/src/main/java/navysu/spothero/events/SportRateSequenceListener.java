package navysu.spothero.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import navysu.spothero.entity.SpotRate;
import navysu.spothero.services.SequenceGenerateService;

/**
 * Generate sequence for a document when save it into database
 * @author navysu
 *
 */
@Component
public class SportRateSequenceListener extends AbstractMongoEventListener<SpotRate> {
	
	private static Logger logger = LoggerFactory.getLogger(SportRateSequenceListener.class);
    
	private SequenceGenerateService sequenceGenerator;

    @Autowired
    public SportRateSequenceListener(SequenceGenerateService sequenceGenerator) {
        this.sequenceGenerator = sequenceGenerator;
    }

    public void onBeforeConvert(BeforeConvertEvent<SpotRate> event) {
        if (event.getSource().getId() == null) {
            event.getSource().setId(sequenceGenerator.generateSequence(SpotRate.SEQUENCE_NAME));
        }
    	logger.debug("Set rate id: " + event.getSource().getId());
    }

}
