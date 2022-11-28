package stepDefinations;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.common.mapper.TypeRef;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ResponseBody;
import services.Authorizations;
import services.booking_service;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Assert;

import helpers.ObjectFactory;
import helpers.contextData;
import helpers.rest_client;
import io.cucumber.datatable.DataTable;

public class createBooking {

	booking_service booking = ObjectFactory.getBookingServiceObject();
	Authorizations auth = ObjectFactory.getAuthorizationObject();
	private Logger logger = Logger.getLogger(createBooking.class);

	@When("create booking API is called with data")
	public void create_booking_api_is_called(DataTable dataTable) {

		Map<String, Object> jsonBodyUsingMap = new HashMap<String, Object>();

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

		logger.info("Request json body" + jsonBodyUsingMap);
		booking.createBooking(jsonBodyUsingMap);
		logger.info("Response json body" + contextData.getResponse().asString());

		ResponseBody responseBody = contextData.getResponse().getBody();
		JsonPath jsnPath = responseBody.jsonPath();
		contextData.setBookingid(jsnPath.get("bookingid").toString());
		logger.info("Set booking id" + jsnPath.get("bookingid").toString());

	}

	@When("create booking API is called")
	public void create_booking_api_is_called() {

		String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());

		Map<String, Object> jsonBodyUsingMap = new HashMap<String, Object>();
		jsonBodyUsingMap.put("firstname", "Jim" + timeStamp);
		jsonBodyUsingMap.put("lastname", "Brown" + timeStamp);
		jsonBodyUsingMap.put("totalprice", 111);
		jsonBodyUsingMap.put("depositpaid", false);

		Map<String, String> bookingDatesMap = new HashMap<String, String>();
		bookingDatesMap.put("checkin", "2021-07-01");
		bookingDatesMap.put("checkout", "2021-07-02");

		jsonBodyUsingMap.put("bookingdates", bookingDatesMap);
		jsonBodyUsingMap.put("additionalneeds", "Breakfast");
		logger.info("Request json body" + jsonBodyUsingMap);
		booking.createBooking(jsonBodyUsingMap);
		logger.info("Response json body" + contextData.getResponse().asString());
		ResponseBody responseBody = contextData.getResponse().getBody();
		JsonPath jsnPath = responseBody.jsonPath();
		contextData.setBookingid(jsnPath.get("bookingid").toString());

	}

	@Then("validate json response")
	public void validate_json_response(io.cucumber.datatable.DataTable dataTable) {

		Map<String, Object> responsebody;
		ResponseBody responseBody = contextData.getResponse().getBody();
		JsonPath jsnPath = responseBody.jsonPath();
		List<Map<String, String>> data = dataTable.asMaps();
		for (Map<String, String> body : data) {

			System.out.println(body.get("Key") + body.get("value"));
			System.out.println(jsnPath.get("booking.firstname"));

			String path = "booking." + body.get("Key");
			System.out.println(path);
			logger.info("Expected" + jsnPath.get(path).toString());
			logger.info("Actual" + body.get("value"));

			Assert.assertEquals(jsnPath.get(path).toString(), body.get("value"));

		}

	}

	@Then("delete the bookingid")
	public void delete_the_bookingid() {
		Map<String, Object> jsonBodyUsingMap = new HashMap<String, Object>();
		Map<String, String> pathparams = new HashMap<String, String>();
		Map<String, String> cookie = new HashMap<String, String>();
		jsonBodyUsingMap.put("username", "admin");
		jsonBodyUsingMap.put("password", "password123");
		auth.createAuthToken(jsonBodyUsingMap);
		pathparams.put("id", contextData.getBookingid().toString());
		String token = contextData.getResponse().getBody().jsonPath().get("token");
		cookie.put("token", token);
		booking.deleteBookingbyId(pathparams, cookie, token);
		logger.info("Delete booking id" + contextData.getBookingid().toString());

	}

}
