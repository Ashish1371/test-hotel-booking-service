package services;

import java.util.Map;

import helpers.ContextData;

import static helpers.RestClient.sendRequest;

public class Authorizations {

    public void createAuthToken(Map<String, Object> body) {
        System.out.println("Header value is set as" + ContextData.getHeader());
        sendRequest(1, ContextData.getHeader(), null, null, null, body, "/auth");
    }
}
