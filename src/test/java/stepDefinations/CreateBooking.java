package stepDefinations;

import helpers.Utils;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ResponseBody;
import jdk.jshell.execution.Util;
import org.apache.http.client.utils.URIUtils;
import pojo.response.Booking;
import pojo.response.BookingResponse;
import services.Authorizations;
import services.BookingService;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Assert;

import helpers.ObjectFactory;
import helpers.ContextData;
import io.cucumber.datatable.DataTable;

import static helpers.Utils.getJson;

public class CreateBooking {

    BookingService booking = ObjectFactory.getBookingServiceObject();
    Authorizations auth = ObjectFactory.getAuthorizationObject();
    private Logger logger = Logger.getLogger(CreateBooking.class);

    @When("create booking API is called using input {string}")
    public void create_booking_api_is_called(String input) throws Exception {

        Map<String, Object> payload = new HashMap<String, Object>();
        payload = getJson(input);
        logger.info("Request json body" + payload);
        booking.createBooking(payload);
        logger.info("Response json body" + ContextData.getResponse().asString());
        ResponseBody responseBody = ContextData.getResponse().getBody();
        JsonPath jsnPath = responseBody.jsonPath();
        if (ContextData.getResponse().statusCode() == 200) {
            ContextData.setBookingid(jsnPath.get("bookingid").toString());
            logger.info("Set booking id" + jsnPath.get("bookingid").toString());
        }
    }

    @Then("validate json response")
    public void validate_json_response(io.cucumber.datatable.DataTable dataTable) {

        Map<String, Object> responsebody;
        ResponseBody responseBody = ContextData.getResponse().getBody();
        JsonPath jsnPath = responseBody.jsonPath();
        List<Map<String, String>> data = dataTable.asMaps();
        for (Map<String, String> body : data) {

            String path = "booking." + body.get("Key");
            System.out.println(path);
            logger.info("Expected" + jsnPath.get(path).toString());
            logger.info("Actual" + body.get("value"));

            Assert.assertEquals(jsnPath.get(path).toString(), body.get("value"));
        }
    }


    @Then("validate response body for {string}")
    public void validate_response_body(String input) throws Exception {
        System.out.println("response data" + ContextData.getResponse().getBody().asString());
        BookingResponse booking = ContextData.getResponse().getBody().as(BookingResponse.class);
        logger.info("Deserialize data" + booking.getBooking().getFirstname());
        Map<String, Object> payload = new HashMap<String, Object>();
        payload = getJson(input);
        Assert.assertEquals(payload.get("firstname"), booking.getBooking().getFirstname());
        Assert.assertEquals(payload.get("lastname"), booking.getBooking().getLastname());
        Assert.assertEquals(payload.get("additionalneeds"), booking.getBooking().getAdditionalneeds());
        Assert.assertEquals(payload.get("totalprice"), booking.getBooking().getTotalprice());
        Assert.assertEquals(payload.get("bookingdates").toString().split("=")[1].split(",")[0], booking.getBooking().getBookingdates().getCheckin());
        Assert.assertEquals(payload.get("bookingdates").toString().split("=")[2].split("}")[0], booking.getBooking().getBookingdates().getCheckout());

    }


}
