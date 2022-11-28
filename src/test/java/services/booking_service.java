package services;

import java.util.Map;

import helpers.contextData;
import helpers.rest_client;

public class booking_service extends rest_client {

	public booking_service() {
		super(contextData.getUrl());

	}

	public void createBooking(Map<String, Object> body) {
		System.out.println("Header value is set as" + contextData.getHeader());

		super.sendRequest(1, contextData.getHeader(), null, null, null, body, "/booking");

	}

	public void getBookingbyId(Map<String, String> pathparam) {
		System.out.println("pathparam value is set as" + pathparam);

		super.sendRequest(0, contextData.getHeader(), pathparam, null, null, null, "/booking/{id}");

	}

	public void deleteBookingbyId(Map<String, String> pathparam, Map<String, String> cookie, String token) {
		System.out.println("pathparam value is set as" + pathparam);

		System.out.println("header value is set as" + contextData.getHeader());
		super.sendRequest(2, contextData.getHeader(), pathparam, null, cookie, null, "/booking/{id}");

	}

	public void getbookingsWithFilters(Map<String, String> queryparam) {
		System.out.println("queryparam value is set as" + queryparam);

		System.out.println("header value is set as" + contextData.getHeader());
		super.sendRequest(0, contextData.getHeader(), null, queryparam, null, null, "/booking");

	}

	public void updateBooking(Map<String, Object> body, Map<String, String> pathparam, Map<String, String> cookie) {
		System.out.println("Header value is set as" + contextData.getHeader());

		super.sendRequest(3, contextData.getHeader(), pathparam, null, cookie, body, "/booking/{id}");

	}

	public void partialupdateBooking(Map<String, Object> body, Map<String, String> pathparam,
			Map<String, String> cookie) {
		System.out.println("Header value is set as" + contextData.getHeader());

		super.sendRequest(4, contextData.getHeader(), pathparam, null, cookie, body, "/booking/{id}");

	}

	public void getbookings() {
		System.out.println("header value is set as" + contextData.getHeader());
		super.sendRequest(0, contextData.getHeader(), null, null, null, null, "/booking");

	}

}
