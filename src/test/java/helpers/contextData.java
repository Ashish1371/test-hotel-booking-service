package helpers;

import java.util.HashMap;
import java.util.Map;

import io.restassured.response.Response;

public class contextData {

	static Response response;
	static String bookingid;

	public static String getBookingid() {
		return bookingid;
	}

	public static void setBookingid(String bookingid) {
		contextData.bookingid = bookingid;
	}

	//static String url = "https://restful-booker.herokuapp.com";
	static String url;
	static Map<String, String> header = new HashMap<String, String>();

	public static Map<String, String> getHeader() {
		return header;
	}

	public static void setHeader(String key, String value) {
		contextData.header.put(key, value);
	}

	public static String getUrl() {
		return url;
	}

	public static void setUrl(String url) {
		contextData.url = url;
	}

	public static Response getResponse() {
		return response;
	}

	public static void setResponse(Response response) {
		contextData.response = response;
	}

}
