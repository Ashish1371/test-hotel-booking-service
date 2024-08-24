package stepDefinations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Assert;

import helpers.ObjectFactory;
import helpers.ContextData;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ResponseBody;
import pojo.response.Booking;
import pojo.response.BookingResponse;
import services.Authorizations;
import services.BookingService;

import static helpers.Utils.getJson;

public class GetBookingId {

    BookingService booking = ObjectFactory.getBookingServiceObject();
    Authorizations auth = ObjectFactory.getAuthorizationObject();
    private Logger logger = Logger.getLogger(GetBookingId.class);

    @When("getbookingId API is called")
    public void getbooking_id_api_is_called(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> pathparams = new HashMap<String, String>();
        List<Map<String, String>> data = dataTable.asMaps();
        for (Map<String, String> header : data) {

            ContextData.setHeader(header.get("key"), header.get("value"));
        }

        ResponseBody responseBody = ContextData.getResponse().getBody();
        JsonPath jsnPath = responseBody.jsonPath();
        pathparams.put("id", jsnPath.get("bookingid").toString());
        logger.info("Pathparams set as " + pathparams);
        booking.getBookingbyId(pathparams);
        logger.info("Response body getbooking id" + ContextData.getResponse().asString());

    }


    @Then("validate json response for getbookingId API")
    public void validate_json_response_for_getbooking_id_api(io.cucumber.datatable.DataTable dataTable) {

        Map<String, Object> responsebody;
        ResponseBody responseBody = ContextData.getResponse().getBody();
        JsonPath jsnPath = responseBody.jsonPath();
        List<Map<String, String>> data = dataTable.asMaps();
        for (Map<String, String> body : data) {

            System.out.println(body.get("Key") + body.get("value"));
            logger.info("Expected" + body.get("Key"));
            logger.info("Actual" + body.get("value"));

            Assert.assertEquals(jsnPath.get(body.get("Key")).toString(), body.get("value"));
        }

    }

    @Then("validate get booking response body for {string}")
    public void validate_response_body(String input) throws Exception {
        System.out.println("response data" + ContextData.getResponse().getBody().asString());
        Booking booking = ContextData.getResponse().getBody().as(Booking.class);
        logger.info("Deserialize data" + booking.getFirstname());
        Map<String, Object> payload = new HashMap<String, Object>();
        payload = getJson(input);
        Assert.assertEquals(payload.get("firstname"), booking.getFirstname());
        Assert.assertEquals(payload.get("lastname"), booking.getLastname());
        Assert.assertEquals(payload.get("additionalneeds"), booking.getAdditionalneeds());
        Assert.assertEquals(payload.get("totalprice"), booking.getTotalprice());
        Assert.assertEquals(payload.get("bookingdates").toString().split("=")[1].split(",")[0], booking.getBookingdates().getCheckin());
        Assert.assertEquals(payload.get("bookingdates").toString().split("=")[2].split("}")[0], booking.getBookingdates().getCheckout());

    }

}
