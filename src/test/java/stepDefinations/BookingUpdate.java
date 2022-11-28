package stepDefinations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import helpers.ObjectFactory;
import helpers.contextData;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ResponseBody;
import services.Authorizations;
import services.booking_service;

public class BookingUpdate {

	booking_service booking = ObjectFactory.getBookingServiceObject();
	Authorizations auth = ObjectFactory.getAuthorizationObject();
	private Logger logger = Logger.getLogger(createBooking.class);

	@When("update booking API is called with data")
	public void update_booking_api_is_called_with_data(io.cucumber.datatable.DataTable dataTable) {
		Map<String, Object> jsonBodyUsingMap = new HashMap<String, Object>();
		Map<String, Object> cred = new HashMap<String, Object>();
		Map<String, String> cookie = new HashMap<String, String>();

		List<Map<String, String>> data = dataTable.asMaps();
		for (Map<String, String> body : data) {

			if (body.get("Key").equalsIgnoreCase("depositpaid")) {
				jsonBodyUsingMap.put(body.get("Key"), Boolean.parseBoolean(body.get("value")));
			}

			else {

				jsonBodyUsingMap.put(body.get("Key"), body.get("value"));

			}
		}

		Map<String, String> bookingDatesMap = new HashMap<String, String>();
		bookingDatesMap.put("checkin", "2021-07-01");
		bookingDatesMap.put("checkout", "2021-07-02");
		jsonBodyUsingMap.put("bookingdates", bookingDatesMap);
		logger.info("Json body set as" + jsonBodyUsingMap);
		Map<String, String> pathparams = new HashMap<String, String>();
		pathparams.put("id", contextData.getBookingid());
		logger.info("Path parameter set as" + pathparams);

		cred.put("username", "admin");
		cred.put("password", "password123");
		auth.createAuthToken(cred);
		String token = contextData.getResponse().getBody().jsonPath().get("token");
		logger.info("Authorization Token generated" + token);
		cookie.put("token", token);
		booking.updateBooking(jsonBodyUsingMap, pathparams, cookie);
		logger.info("Response" + contextData.getResponse().asString());

	}

}
