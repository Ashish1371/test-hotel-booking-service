package helpers;

import services.Authorizations;
import services.booking_service;
import services.healthcheck;

public class ObjectFactory {
	
	static booking_service booking =new booking_service();
	static Authorizations auth =new Authorizations();
	static healthcheck check=new healthcheck();
	
public static booking_service getBookingServiceObject() {
	return booking;
}
public static Authorizations getAuthorizationObject() {
	return auth;
	}
public static healthcheck gethealthcheckobject() {
	return check;
	}	
	
	
}
