package stepDefinations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import helpers.ObjectFactory;
import helpers.ContextData;
import io.cucumber.java.en.When;
import services.Authorizations;
import services.BookingService;

import static helpers.Utils.getJson;

public class PartialBookingUpdate {

	BookingService booking = ObjectFactory.getBookingServiceObject();
	Authorizations auth = ObjectFactory.getAuthorizationObject();
	private Logger logger = Logger.getLogger(CreateBooking.class);


	@When("partial update booking API is called using input {string}")
	public void partial_update_booking_api_is_called_with_data(String input) throws Exception {
		booking.partialupdateBooking(getUpdatePayload(input), getPathParams(), getCookie());
		logger.info("Response json" + ContextData.getResponse().asString());
	}
	Map<String, Object> getUpdatePayload(String input) throws Exception {
		Map<String, Object> payload = new HashMap<String, Object>();
		payload = getJson(input);
		return payload;
	}

	Map<String, String> getCookie() throws Exception {
		Map<String, Object> credentials = new HashMap<String, Object>();
		Map<String, String> cookie = new HashMap<String, String>();
		credentials=getJson("authorization");
		auth.createAuthToken(credentials);
		String token = ContextData.getResponse().getBody().jsonPath().get("token");
		logger.info("Authorization Token generated" + token);
		cookie.put("token", token);
		return cookie;
	}

	Map<String, String> getPathParams() throws Exception {
		Map<String, String> pathparams = new HashMap<String, String>();
		pathparams.put("id", ContextData.getBookingid());
		logger.info("Path parameter set as" + pathparams);
		return pathparams;
	}

}
