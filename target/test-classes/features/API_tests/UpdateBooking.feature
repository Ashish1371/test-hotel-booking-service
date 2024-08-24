@updateBooking
Feature: update Booking API
  
  @Regression @qa
  Scenario Outline: Test to verify update Booking API works fine
    Given Booking service is up and running
    And Prepare request header and request body
      | key          | value            |
      | Content-Type | application/json |
    When create booking API is called using input "<input>"
    Then validate response code is 200
    When update booking API is called using input "<updated>"
    Then validate response code is 200
    And validate json response for getbookingId API
    |     Key         | value            |
    | firstname       | Jimaaaaa         |
    | lastname        | Brownaaa         |
    | totalprice      | 100              |
    | depositpaid     | true             |
    | additionalneeds | Breakfast        |
    Then validate get booking response body for "<updated>"
    Then delete the bookingid
    Then validate response code is 201
    Examples:
      |input        |updated           |
      |createBooking|UpdateBooking     |


  @Regression @qa
  Scenario Outline: Test to verify update Booking API for invalid payload
    Given Booking service is up and running
    And Prepare request header and request body
      | key          | value            |
      | Content-Type | application/json |
    When create booking API is called using input "<input>"
    Then validate response code is 200
    When update booking API is called using input "<updated>"
    Then validate response code is 500
    Examples:
      |input        |updated           |
      |createBooking|UpdateBookingInvalid     |
    