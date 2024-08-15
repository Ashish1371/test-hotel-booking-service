package services;

import helpers.ContextData;

import static helpers.RestClient.sendRequest;

public class HealthCheck {


	public void Checkhealth() {

		sendRequest(0, ContextData.getHeader(), null, null, null, null, "/ping");

	}

}
