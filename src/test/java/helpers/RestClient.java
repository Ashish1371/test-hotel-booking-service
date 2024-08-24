package helpers;

import java.util.Map;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import stepDefinations.CreateBooking;

import static io.restassured.RestAssured.*;

public class RestClient {

    static RequestSpecBuilder reqSpeBuilder;
    static RequestSpecification reqSpec;
    public static final int GET_REQUEST = 0;
    public static final int POST_REQUEST = 1;
    public static final int DELETE_REQUEST = 2;
    public static final int PUT_REQUEST = 3;
    public static final int PATCH_REQUEST = 4;
    static String url;
    private static Logger logger = Logger.getLogger(CreateBooking.class);

    protected RestClient() {
    }

    protected RestClient(String baseUrl) {
        logger.info("baseurl" + url);
        url = baseUrl;

    }

    public static void sendRequest(int methodId, Map<String, String> headers, Map<String, String> pathParams,
                                   Map<String, Object> queryParams, Map<String, String> cookies, Map<String, Object> body, String basepath) {
        url = ContextData.getUrl();
        reqSpeBuilder = new RequestSpecBuilder();
        reqSpeBuilder.setBaseUri(url);
        Gson gson = new Gson();

        if (headers != null) {
            reqSpeBuilder.addHeaders(headers);

        }

        if (pathParams != null) {
            reqSpeBuilder.addPathParams(pathParams);

        }

        if (queryParams != null) {
            reqSpeBuilder.addQueryParams(queryParams);

        }
        if (cookies != null) {
            reqSpeBuilder.addCookies(cookies);
        }
        reqSpeBuilder.setUrlEncodingEnabled(false);
        reqSpec = reqSpeBuilder.build();

        reqSpeBuilder.setUrlEncodingEnabled(false);
        switch (methodId) {

            case RestClient.GET_REQUEST:
                logger.info("Calling GET API");
                ContextData.setResponse(given().relaxedHTTPSValidation().spec(reqSpec).when().get(basepath));
                logger.info(ContextData.getResponse().asString());

                break;

            case RestClient.POST_REQUEST:

                logger.info("Calling post API");
                ContextData.setResponse(given().relaxedHTTPSValidation().spec(reqSpec).when().body(gson.toJson(body)).post(basepath));
                //logger.info(contextData.getResponse().asString());
                break;

            case RestClient.DELETE_REQUEST:
                ContextData.setResponse(given().relaxedHTTPSValidation().spec(reqSpec).when().delete(basepath));
                logger.info("DeleteData response code" + ContextData.getResponse().statusCode());

                break;

            case RestClient.PUT_REQUEST:
                logger.info("Calling PUT API");

                ContextData.setResponse(given().relaxedHTTPSValidation().spec(reqSpec).when().body(gson.toJson(body)).put(basepath));
                logger.info("updated data" + ContextData.getResponse().asString());
                break;

            case RestClient.PATCH_REQUEST:

                logger.info("Calling PATCH API");
                ContextData.setResponse(given().relaxedHTTPSValidation().spec(reqSpec).when().body(gson.toJson(body)).patch(basepath));
                logger.info("updated data" + ContextData.getResponse().asString());
                break;

            default:
                break;

        }

    }

}
