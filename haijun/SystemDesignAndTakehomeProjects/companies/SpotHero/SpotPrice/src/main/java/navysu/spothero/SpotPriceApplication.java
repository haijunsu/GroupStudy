package navysu.spothero;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import navysu.spothero.dto.JsonTestData;
import navysu.spothero.entity.SpotRate;
import navysu.spothero.services.SpotRateService;

@SpringBootApplication
public class SpotPriceApplication implements CommandLineRunner {

	private static Logger logger = LoggerFactory.getLogger(SpotPriceApplication.class);

	@Autowired
	@Qualifier("spotRateServiceMongo")
	private SpotRateService service;

	@Autowired
	@Qualifier("spotRateServiceInMemory")
	private SpotRateService inMemService;

	public static void main(String[] args) {
		SpringApplication.run(SpotPriceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.debug("loading");
		loadData();
	}
	
	/**
	 * Load data from file. File name has been hard code as "data.json". 
	 * The application will search the file in current folder. If not found,
	 * it will try to find the file in classpath.
	 */
	public void loadData() {
		try {
			Path path = Paths.get("data.json");
			if (Files.notExists(path)) {
				logger.debug("File 'data.json' doesn't exist. Try to find in classpath.");
				URL dataUrl = this.getClass().getResource("/data.json");
				if (dataUrl != null) {
					path = Paths.get(dataUrl.toURI());
				}
			}
			if (Files.notExists(path)) {
				logger.error("Cannot find data file (data.json). No data is loaded");
				return;
			}
			
			logger.info("Loading data from " + path.toAbsolutePath());
			byte[] content = Files.readAllBytes(path);
			GsonBuilder builder = new GsonBuilder();
			builder.setPrettyPrinting();
			Gson gson = builder.create();
			JsonTestData data = gson.fromJson(new String(content), JsonTestData.class);
			for (SpotRate spotrate : data.getRates()) {
				service.save(spotrate);
				inMemService.save(spotrate);
			}
			logger.info("Loading data success.");
		} catch (Exception ex) {
			logger.error("load data error", ex);

		}
	}

}
