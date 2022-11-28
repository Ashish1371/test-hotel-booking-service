package services;

import java.util.Map;

import helpers.contextData;
import helpers.rest_client;

public class healthcheck extends rest_client {

	public healthcheck() {
		super(contextData.getUrl());

	}

	public void Checkhealth() {

		super.sendRequest(0, contextData.getHeader(), null, null, null, null, "/ping");

	}

}
