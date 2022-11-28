package services;

import java.util.Map;

import helpers.contextData;
import helpers.rest_client;

public class Authorizations extends rest_client {

	public Authorizations() {
		super(contextData.getUrl());

	}

	public void createAuthToken(Map<String, Object> body) {
		System.out.println("Header value is set as" + contextData.getHeader());

		super.sendRequest(1, contextData.getHeader(), null, null, null, body, "/auth");

	}

}
