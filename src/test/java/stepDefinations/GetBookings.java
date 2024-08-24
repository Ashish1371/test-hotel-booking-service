package stepDefinations;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Assert;

import helpers.ObjectFactory;
import helpers.ContextData;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pojo.response.BookingResponse;
import pojo.response.Bookings;
import services.Authorizations;
import services.BookingService;

import static helpers.Utils.getJson;

public class GetBookings {

    BookingService booking = ObjectFactory.getBookingServiceObject();
    Authorizations auth = ObjectFactory.getAuthorizationObject();
    private Logger logger = Logger.getLogger(GetBookings.class);

    @When("get bookings API is called using filter {string}")
    public void get_bookings_api_is_called_with_filter(String input) throws Exception {

        Map<String, Object> queryParams = new HashMap<String, Object>();
        queryParams = getJson(input);
        System.out.println(queryParams);
        logger.info("queryParams" + queryParams);
        booking.getbookingsWithFilters(queryParams);

        System.out.println("Response of get booking with filter" + ContextData.getResponse().asString());
        logger.info("Response of get booking with filter" + ContextData.getResponse().asString());
    }

    @Then("validate booking id is present in response")
    public void validate_booking_id_is_present_in_response() {

        System.out.println("Before Assertion" + ContextData.getResponse().getBody().asString());
        System.out.println("Before Assertion context" + ContextData.getBookingid().toString());
        Assert.assertTrue(ContextData.getResponse().getBody().asString().contains(ContextData.getBookingid().toString()));

    }

    @Then("validate bookings API")
    public void validate_bookings_api_is_called() {
        System.out.println("response" + ContextData.getResponse().getBody().asString());
        List<Bookings> booking = Arrays.asList(ContextData.getResponse().getBody().as(Bookings[].class));
        Assert.assertEquals(!booking.isEmpty(), true);
        logger.info("Booking id" + booking.get(0).getBookingid());
        System.out.println("Booking id" + booking.get(0).getBookingid());
    }

    @When("get bookings API is called")
    public void get_bookings_api_is_called() {
        booking.getbookings();
        logger.info("Response" + ContextData.getResponse());

    }

}
