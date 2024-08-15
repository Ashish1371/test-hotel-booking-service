package services;

import java.util.Map;

import helpers.ContextData;

import static helpers.RestClient.sendRequest;

public class BookingService {



	public void createBooking(Map<String, Object> body) {
		System.out.println("Header value is set as" + ContextData.getHeader());

		sendRequest(1, ContextData.getHeader(), null, null, null, body, "/booking");

	}

	public void getBookingbyId(Map<String, String> pathparam) {
		System.out.println("pathparam value is set as" + pathparam);

		sendRequest(0, ContextData.getHeader(), pathparam, null, null, null, "/booking/{id}");

	}

	public void deleteBookingbyId(Map<String, String> pathparam, Map<String, String> cookie, String token) {
		System.out.println("pathparam value is set as" + pathparam);

		System.out.println("header value is set as" + ContextData.getHeader());
		sendRequest(2, ContextData.getHeader(), pathparam, null, cookie, null, "/booking/{id}");

	}

	public void getbookingsWithFilters(Map<String, Object> queryparam) {
		System.out.println("queryparam value is set as" + queryparam);

		System.out.println("header value is set as" + ContextData.getHeader());
		sendRequest(0, ContextData.getHeader(), null, queryparam, null, null, "/booking");

	}

	public void updateBooking(Map<String, Object> body, Map<String, String> pathparam, Map<String, String> cookie) {
		System.out.println("Header value is set as" + ContextData.getHeader());

		sendRequest(3, ContextData.getHeader(), pathparam, null, cookie, body, "/booking/{id}");

	}

	public void partialupdateBooking(Map<String, Object> body, Map<String, String> pathparam,
			Map<String, String> cookie) {
		System.out.println("Header value is set as" + ContextData.getHeader());

		sendRequest(4, ContextData.getHeader(), pathparam, null, cookie, body, "/booking/{id}");

	}

	public void getbookings() {
		System.out.println("header value is set as" + ContextData.getHeader());
		sendRequest(0, ContextData.getHeader(), null, null, null, null, "/booking");

	}

}
