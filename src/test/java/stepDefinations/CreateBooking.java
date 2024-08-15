package stepDefinations;

import helpers.Utils;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ResponseBody;
import jdk.jshell.execution.Util;
import org.apache.http.client.utils.URIUtils;
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
       if(ContextData.getResponse().statusCode()==200) {
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

    @Then("delete the bookingid")
    public void delete_the_bookingid() {
        Map<String, Object> jsonBodyUsingMap = new HashMap<String, Object>();
        Map<String, String> pathparams = new HashMap<String, String>();
        Map<String, String> cookie = new HashMap<String, String>();
        jsonBodyUsingMap.put("username", "admin");
        jsonBodyUsingMap.put("password", "password123");
        auth.createAuthToken(jsonBodyUsingMap);
        pathparams.put("id", ContextData.getBookingid().toString());
        String token = ContextData.getResponse().getBody().jsonPath().get("token");
        cookie.put("token", token);
        booking.deleteBookingbyId(pathparams, cookie, token);
        logger.info("Delete booking id" + ContextData.getBookingid().toString());

    }

    void updateJsonPayload(List<Map<String, String>> data) throws Exception {

    }

}
