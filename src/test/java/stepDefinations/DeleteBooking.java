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

public class DeleteBooking {
    BookingService booking = ObjectFactory.getBookingServiceObject();
    Authorizations auth = ObjectFactory.getAuthorizationObject();
    private Logger logger = Logger.getLogger(DeleteBooking.class);

    @Then("delete the bookingid")
    public void delete_the_bookingid() throws Exception {
        Map<String, Object> jsonBodyUsingMap = new HashMap<String, Object>();
        Map<String, String> pathparams = new HashMap<String, String>();
        Map<String, String> cookie = new HashMap<String, String>();
        cookie = getCookie();
        pathparams = getPathParams();
        booking.deleteBookingbyId(pathparams, cookie);
        logger.info("Delete booking id" + ContextData.getBookingid().toString());
    }

    Map<String, String> getCookie() throws Exception {
        Map<String, Object> credentials = new HashMap<String, Object>();
        Map<String, String> cookie = new HashMap<String, String>();
        credentials = getJson("authorization");
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
