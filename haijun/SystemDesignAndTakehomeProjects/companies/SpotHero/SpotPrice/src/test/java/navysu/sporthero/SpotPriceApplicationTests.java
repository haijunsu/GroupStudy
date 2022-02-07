package navysu.sporthero;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.in;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import navysu.spothero.SpotPriceApplication;
import navysu.spothero.controller.SpotRateBaseController;
import navysu.spothero.dto.JsonTestData;
import navysu.spothero.entity.SpotRate;
import navysu.spothero.services.SequenceGenerateService;
import navysu.spothero.services.SpotRateService;

@SpringBootTest(classes = SpotPriceApplication.class)
@AutoConfigureMockMvc
class SpotPriceApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	@Qualifier("spotRateServiceInMemory")
	private SpotRateService serviceInMem;

	@Autowired
	@Qualifier("spotRateServiceMongo")
	private SpotRateService serviceMongo;

	@Autowired
	private SequenceGenerateService sequenceService;

	/**
	 * Test dataset which is loaded from file data_test.json
	 */
	private JsonTestData testDataset;

	/**
	 * Test data content which is load from file data_test.json.
	 */
	private byte[] jsonContent;

	/**
	 * Price test cases input and expect result. It is hard code. We can load it
	 * from a file in future.
	 */
	private String[][] timeslots = new String[][] {
			{ "2015-07-01T07:00:00-05:00", "2015-07-01T12:00:00-05:00", "true" },
			{ "2015-07-01T12:00:00-05:00", "2015-07-01T07:00:00-05:00", "false" }, // end < start
			{ "2015-07-04T15:00:00+00:00", "2015-07-04T20:00:00+00:00", "true" },
			{ "2015-07-04T07:00:00+05:00", "2015-07-04T20:00:00+05:00", "false" } // in two days
	};

	/**
	 * Initial data on server before running a test case.
	 * 
	 * @throws Exception
	 */
	@BeforeEach
	public void initEach() throws Exception {
		serviceInMem.deleteAll();
		serviceMongo.deleteAll();
		// reset sequence is easy for test
		sequenceService.resetSequence(SpotRate.SEQUENCE_NAME);
		Path path = Paths.get("data_test.json");
		if (Files.notExists(path)) {
			URL dataUrl = this.getClass().getResource("/data_test.json");
			if (dataUrl != null) {
				path = Paths.get(dataUrl.toURI());
			}
		}
		if (Files.notExists(path)) {
			return;
		}
		jsonContent = Files.readAllBytes(path);
		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting();
		Gson gson = builder.create();
		testDataset = gson.fromJson(new String(jsonContent), JsonTestData.class);
		for (SpotRate spotrate : testDataset.getRates()) {
			serviceInMem.save(spotrate);
			serviceMongo.save(spotrate);
		}
	}

	/**
	 * Test get rates endpoint.
	 * 
	 * @throws Exception
	 */
	@DisplayName("Get all rates")
	@Test
	void endpointGetRates() throws Exception {
		// build data for verifying with jsonPath method
		List<String> daysList = new ArrayList<>();
		List<String> timesList = new ArrayList<>();
		List<String> tzList = new ArrayList<>();
		List<Integer> priceList = new ArrayList<>();
		for (SpotRate rate : testDataset.getRates()) {
			daysList.add(rate.getDays());
			timesList.add(rate.getTimes());
			tzList.add(rate.getTz());
			priceList.add(rate.getPrice());
		}

		MvcResult result = this.mockMvc.perform(get("/rates")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith("application/json"))
				.andExpect(jsonPath("$.rates.*", hasSize(testDataset.getRates().size())))
				.andExpect(jsonPath("$.rates.[0].days").value(is(in(daysList))))
				.andExpect(jsonPath("$.rates.[0].times").value(is(in(timesList))))
				.andExpect(jsonPath("$.rates.[0].tz").value(is(in(tzList))))
				.andExpect(jsonPath("$.rates.[0].price").value(is(in(priceList))))
				.andExpect(jsonPath("$.rates.[1].days").value(is(in(daysList))))
				.andExpect(jsonPath("$.rates.[1].times").value(is(in(timesList))))
				.andExpect(jsonPath("$.rates.[1].tz").value(is(in(tzList))))
				.andExpect(jsonPath("$.rates.[1].price").value(is(in(priceList))))
				.andExpect(jsonPath("$.rates.[2].days").value(is(in(daysList))))
				.andExpect(jsonPath("$.rates.[2].times").value(is(in(timesList))))
				.andExpect(jsonPath("$.rates.[2].tz").value(is(in(tzList))))
				.andExpect(jsonPath("$.rates.[2].price").value(is(in(priceList)))).andReturn();

		// rebuild the response json to java object and verify it.
		String resultContent = result.getResponse().getContentAsString();
		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting();
		Gson gson = builder.create();
		JsonTestData resultData = gson.fromJson(new String(resultContent), JsonTestData.class);
		for (SpotRate spotrate : resultData.getRates()) {
			assertNull(spotrate.getId());
			boolean isFound = false;
			for (SpotRate testRate : testDataset.getRates()) {
				// Collection contains method doesn't work. I believe the equals method problem
				// in entity SpotRate
				// Need to do some research.
				if (testRate.getDays().equals(spotrate.getDays()) && testRate.getTimes().equals(spotrate.getTimes())
						&& testRate.getTz().equals(spotrate.getTz()) && testRate.getPrice() == spotrate.getPrice()) {
					isFound = true;
					break;
				}
			}
			assertTrue(isFound);
		}
		assertEquals(resultData.getRates().size(), testDataset.getRates().size());
	}

	@DisplayName("Get all rates V2")
	@Test
	void endpointGetRatesV2() throws Exception {
		// build data for verifying with jsonPath method
		List<String> daysList = new ArrayList<>();
		List<String> timesList = new ArrayList<>();
		List<String> tzList = new ArrayList<>();
		List<Integer> priceList = new ArrayList<>();
		for (SpotRate rate : testDataset.getRates()) {
			daysList.add(rate.getDays());
			timesList.add(rate.getTimes());
			tzList.add(rate.getTz());
			priceList.add(rate.getPrice());
		}
		MvcResult result = this.mockMvc.perform(get("/v2/rates")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith("application/json"))
				.andExpect(jsonPath("$.rates.*", hasSize(testDataset.getRates().size())))
				.andExpect(jsonPath("$.rates.[0].id").value(greaterThanOrEqualTo(0)))
				.andExpect(jsonPath("$.rates.[0].days").value(is(in(daysList))))
				.andExpect(jsonPath("$.rates.[0].times").value(is(in(timesList))))
				.andExpect(jsonPath("$.rates.[0].tz").value(is(in(tzList))))
				.andExpect(jsonPath("$.rates.[0].price").value(is(in(priceList))))
				.andExpect(jsonPath("$.rates.[1].id").value(greaterThanOrEqualTo(0)))
				.andExpect(jsonPath("$.rates.[1].days").value(is(in(daysList))))
				.andExpect(jsonPath("$.rates.[1].times").value(is(in(timesList))))
				.andExpect(jsonPath("$.rates.[1].tz").value(is(in(tzList))))
				.andExpect(jsonPath("$.rates.[1].price").value(is(in(priceList))))
				.andExpect(jsonPath("$.rates.[2].id").value(greaterThanOrEqualTo(0)))
				.andExpect(jsonPath("$.rates.[2].days").value(is(in(daysList))))
				.andExpect(jsonPath("$.rates.[2].times").value(is(in(timesList))))
				.andExpect(jsonPath("$.rates.[2].tz").value(is(in(tzList))))
				.andExpect(jsonPath("$.rates.[2].price").value(is(in(priceList)))).andReturn();
		// rebuild the response json to java object and verify it.
		String resultContent = result.getResponse().getContentAsString();
		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting();
		Gson gson = builder.create();
		JsonTestData resultData = gson.fromJson(new String(resultContent), JsonTestData.class);
		for (SpotRate spotrate : resultData.getRates()) {
			assertNotNull(spotrate.getId());
			boolean isFound = false;
			for (SpotRate testRate : testDataset.getRates()) {
				if (testRate.getDays().equals(spotrate.getDays()) && testRate.getTimes().equals(spotrate.getTimes())
						&& testRate.getTz().equals(spotrate.getTz()) && testRate.getPrice() == spotrate.getPrice()) {
					isFound = true;
					break;
				}
			}
			assertTrue(isFound);
		}
		assertEquals(resultData.getRates().size(), testDataset.getRates().size());
	}

	@DisplayName("Get rates by ID V2")
	@Test
	void endpointGetRatesByIdV2() throws Exception {
		String expectedReturn = "{\"id\":2,\"days\":\"fri,sat,sun\",\"times\":\"0900-2100\",\"tz\":\"America/Chicago\",\"price\":2000}";
		this.mockMvc.perform(get("/v2/rates/2")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith("application/json"))
				.andExpect(content().json(expectedReturn));
		String badUrl = "/v2/rates/bad";
		this.mockMvc.perform(get(badUrl)).andExpect(status().isBadRequest());
		badUrl = "/v2/rates/-1";
		this.mockMvc.perform(get(badUrl)).andExpect(status().isNotFound());
	}
	
	@DisplayName("Put rates")
	@Test
	void endpointPutRates() throws Exception {
		this.mockMvc.perform(put("/rates").contentType("application/json").content(jsonContent)).andDo(print())
				.andExpect(status().isOk()).andExpect(content().string("success"));
		// bad request case
		this.mockMvc.perform(put("/rates")).andExpect(status().isBadRequest());
	}

	@DisplayName("Put rates V2")
	@Test
	void endpointPutRatesV2() throws Exception {
		this.mockMvc.perform(put("/v2/rates").contentType("application/json").content(jsonContent)).andDo(print())
				.andExpect(status().isOk()).andExpect(content().json("{\"message\":\"success\"}"));
		// bad request case
		this.mockMvc.perform(put("/v2/rates").contentType("application/json")).andExpect(status().isBadRequest());
	}

	@DisplayName("Put a rates by ID V2")
	@Test
	void endpointPutRateIDV2() throws Exception {
		// update id = 2 record
		String rateContent = "{\"days\":\"fri,sat,sun\",\"times\":\"0900-2100\",\"tz\":\"America/Chicago\",\"price\":12345}";
		String returnContent = "{\"id\":2,\"days\":\"fri,sat,sun\",\"times\":\"0900-2100\",\"tz\":\"America/Chicago\",\"price\":12345}";
		this.mockMvc.perform(put("/v2/rates/2").contentType("application/json").content(rateContent)).andDo(print())
				.andExpect(status().isOk()).andExpect(content().json(returnContent));
		// bad request case
		String badUrl = "/v2/rates/bad";
		this.mockMvc.perform(put(badUrl).contentType("application/json")).andExpect(status().isBadRequest());
		badUrl = "/v2/rates/-1";
		this.mockMvc.perform(put(badUrl).contentType("application/json").content(rateContent))
				.andExpect(status().isNotFound());
		badUrl = "/v2/rates/" + testDataset.getRates().size() + 10;
		this.mockMvc.perform(put(badUrl).contentType("application/json").content(rateContent))
				.andExpect(status().isNotFound());
	}

	@DisplayName("Get price")
	@Test
	void endpointGetPrice() throws Exception {
		for (String[] priceCase : timeslots) {
			String url = "/price?start=" + priceCase[0] + "&end=" + priceCase[1];
			MvcResult result = this.mockMvc.perform(get(url)).andDo(print()).andExpect(status().isOk()).andReturn();
			String resultContent = result.getResponse().getContentAsString();
			if (priceCase[2].equals("true")) {
				assertTrue(resultContent.startsWith("{\"price\":"));
			} else {
				assertEquals(SpotRateBaseController.STRING_UNAVAILABLE, resultContent);
			}
		}
		// best request
		String badurl = "/price?start=abc";
		this.mockMvc.perform(get(badurl)).andExpect(status().isBadRequest());
		badurl = "/price?start=abc&end=def";
		this.mockMvc.perform(get(badurl)).andExpect(status().is5xxServerError());
	}

	@DisplayName("Get price V2")
	@Test
	void endpointGetPriceV2() throws Exception {
		for (String[] priceCase : timeslots) {
			String url = "/v2/price?start=" + priceCase[0] + "&end=" + priceCase[1];
			MvcResult result = this.mockMvc.perform(get(url)).andDo(print()).andExpect(status().isOk()).andReturn();
			String resultContent = result.getResponse().getContentAsString();
			if (priceCase[2].equals("true")) {
				assertTrue(resultContent.startsWith("{\"price\":"));
			} else {
				assertEquals("{\"message\":\"" + SpotRateBaseController.STRING_UNAVAILABLE + "\"}", resultContent);
			}
		}
		// best request
		String badurl = "/v2/price?start=abc";
		this.mockMvc.perform(get(badurl)).andExpect(status().isBadRequest());
		badurl = "/v2/price?start=abc&end=def";
		this.mockMvc.perform(get(badurl)).andExpect(status().is5xxServerError());
	}
}
