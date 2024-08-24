package helpers;

import services.Authorizations;
import services.BookingService;
import services.HealthCheck;

public class ObjectFactory {

    static BookingService booking = new BookingService();
    static Authorizations auth = new Authorizations();
    static HealthCheck check = new HealthCheck();

    public static BookingService getBookingServiceObject() {
        return booking;
    }

    public static Authorizations getAuthorizationObject() {
        return auth;
    }

    public static HealthCheck gethealthcheckobject() {
        return check;
    }


}
