package helpers;

import java.util.HashMap;
import java.util.Map;

import io.restassured.response.Response;

public class ContextData {

	static Response response;
	static String bookingid;

	public static String getBookingid() {
		return bookingid;
	}

	public static void setBookingid(String bookingid) {
		ContextData.bookingid = bookingid;
	}

	static String url;
	static Map<String, String> header = new HashMap<String, String>();

	public static Map<String, String> getHeader() {
		return header;
	}

	public static void setHeader(String key, String value) {
		ContextData.header.put(key, value);
	}

	public static String getUrl() {
		return url;
	}

	public static void setUrl(String url) {
		ContextData.url = url;
	}

	public static Response getResponse() {
		return response;
	}

	public static void setResponse(Response response) {
		ContextData.response = response;
	}

}
