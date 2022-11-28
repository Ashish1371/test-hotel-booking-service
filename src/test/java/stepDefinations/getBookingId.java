package stepDefinations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Assert;

import helpers.ObjectFactory;
import helpers.contextData;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ResponseBody;
import services.Authorizations;
import services.booking_service;

public class getBookingId {

	booking_service booking = ObjectFactory.getBookingServiceObject();
	Authorizations auth = ObjectFactory.getAuthorizationObject();
	private Logger logger = Logger.getLogger(createBooking.class);

	@When("getbookingId API is called")
	public void getbooking_id_api_is_called(io.cucumber.datatable.DataTable dataTable) {
		Map<String, String> pathparams = new HashMap<String, String>();
		List<Map<String, String>> data = dataTable.asMaps();
		for (Map<String, String> header : data) {

			contextData.setHeader(header.get("key"), header.get("value"));
		}

		ResponseBody responseBody = contextData.getResponse().getBody();
		JsonPath jsnPath = responseBody.jsonPath();
		pathparams.put("id", jsnPath.get("bookingid").toString());
		logger.info("Pathparams set as " + pathparams);
		booking.getBookingbyId(pathparams);
		logger.info("Response body getbooking id" + contextData.getResponse().asString());

	}

	@Then("validate json response for getbookingId API")
	public void validate_json_response_for_getbooking_id_api(io.cucumber.datatable.DataTable dataTable) {

		Map<String, Object> responsebody;
		ResponseBody responseBody = contextData.getResponse().getBody();
		JsonPath jsnPath = responseBody.jsonPath();
		List<Map<String, String>> data = dataTable.asMaps();
		for (Map<String, String> body : data) {

			System.out.println(body.get("Key") + body.get("value"));
			logger.info("Expected" + body.get("Key"));
			logger.info("Actual" + body.get("value"));

			Assert.assertEquals(jsnPath.get(body.get("Key")).toString(), body.get("value"));

		}

	}

}
