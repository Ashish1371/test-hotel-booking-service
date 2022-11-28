package stepDefinations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Assert;
import com.google.gson.Gson;

import helpers.ObjectFactory;
import helpers.contextData;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import services.Authorizations;
import services.booking_service;

public class getBookings {

	booking_service booking = ObjectFactory.getBookingServiceObject();
	Authorizations auth = ObjectFactory.getAuthorizationObject();
	private Logger logger = Logger.getLogger(createBooking.class);

	@When("get bookings API is called with filter")
	public void get_bookings_api_is_called_with_filter(io.cucumber.datatable.DataTable dataTable) {

		Map<String, String> queryParams = new HashMap<String, String>();
		List<Map<String, String>> data = dataTable.asMaps();
		for (Map<String, String> body : data) {

			queryParams.put(body.get("Key"), body.get("value"));

		}
		System.out.println(queryParams);
		logger.info("queryParams" + queryParams);
		booking.getbookingsWithFilters(queryParams);

		System.out.println("Response of get booking with filter" + contextData.getResponse().asString());
		logger.info("Response of get booking with filter" + contextData.getResponse().asString());
	}

	@Then("validate booking id is present in response")
	public void validate_booking_id_is_present_in_response() {

		System.out.println("Before Assertion" + contextData.getResponse().getBody().asString());
		System.out.println("Before Assertion context" + contextData.getBookingid().toString());
		Assert.assertTrue(
				contextData.getResponse().getBody().asString().contains(contextData.getBookingid().toString()));

	}

	@When("get bookings API is called")
	public void get_bookings_api_is_called() {
		booking.getbookings();
		logger.info("Response" + contextData.getResponse());

	}

}
