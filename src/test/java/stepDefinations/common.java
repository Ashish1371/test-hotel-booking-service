package stepDefinations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;

import helpers.ObjectFactory;
import helpers.contextData;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import services.Authorizations;
import services.booking_service;
import services.healthcheck;

import static org.junit.Assert.*;

public class common {

	healthcheck check = ObjectFactory.gethealthcheckobject();
	booking_service booking = ObjectFactory.getBookingServiceObject();
	Authorizations auth = ObjectFactory.getAuthorizationObject();
	private static Logger logger = Logger.getLogger(createBooking.class);
	
	

	@io.cucumber.java.Before
	public static void setup() {
		try {
			String env = System.getenv("env");
			if (env.equalsIgnoreCase("qa")) {
				contextData.setUrl("https://restful-booker.herokuapp.com/");
				logger.info("Env variable set as qa.Set url to https://restful-booker.herokuapp.com/");
			}

			else if (env.equalsIgnoreCase("uat")) {
				contextData.setUrl("https://restful-booker.herokuapp.com/");
				logger.info("Env variable set as uat.Set url to https://restful-booker.herokuapp.com/");
			}

			else {

				contextData.setUrl("https://restful-booker.herokuapp.com/");
				logger.info(
						"Env variable set to invalid value.Set url to default https://restful-booker.herokuapp.com/");
			}
		} catch (Exception e) {

			contextData.setUrl("https://restful-booker.herokuapp.com/");
			logger.info("Env variable not set.Set url to https://restful-booker.herokuapp.com/");
		}

	}

	@Given("Booking service is up and running")
	public void booking_service_is_up_and_running() {

		check.Checkhealth();
		Assert.assertEquals(contextData.getResponse().getStatusCode(), 201);
		logger.info("Health check successful");
		

	}

	@Given("Prepare request header and request body")
	public void prepare_request_header_and_request_body(io.cucumber.datatable.DataTable dataTable) {
		List<Map<String, String>> data = dataTable.asMaps();
		for (Map<String, String> header : data) {

			contextData.setHeader(header.get("key"), header.get("value"));
		}
		logger.info("Headers set as" + contextData.getHeader());
	}

	@Then("validate response code is {int}")
	public void validate_response_code_is(int statuscode) {

		Assert.assertEquals(contextData.getResponse().getStatusCode(), statuscode);
		logger.info("Response code" + contextData.getResponse().getStatusCode());

	}

}
