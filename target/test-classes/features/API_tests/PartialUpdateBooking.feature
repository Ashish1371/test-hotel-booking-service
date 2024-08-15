@PartialupdateBooking
Feature: partial update Booking API
  
  @Regression @qa
  Scenario Outline: Test to verify PATCH partial update booking works fine
    Given Booking service is up and running
    And Prepare request header and request body
      | key          | value            |
      | Content-Type | application/json |
    When create booking API is called using input "<input>"
    Then validate response code is 200
    When partial update booking API is called using input "<updated>"
    Then validate response code is 200
    And validate json response for getbookingId API
      |     Key         | value            |
      | firstname       | Abcd         |
      | lastname        | Brownaaa         |
      | totalprice      | 100              |
      | depositpaid     | true             |
      | additionalneeds | Breakfast        |
    Then delete the bookingid
    Then validate response code is 201
    Examples:
      |input        |updated           |
      |createBooking|PartialUpdateBooking     |


  @Regression @qa @bug
  Scenario Outline: Test to verify PATCH partial update booking for invalid payload
    Given Booking service is up and running
    And Prepare request header and request body
      | key          | value            |
      | Content-Type | application/json |
    When create booking API is called using input "<input>"
    Then validate response code is 200
    When partial update booking API is called using input "<updated>"
    Then validate response code is 500
    Examples:
      |input        |updated           |
      |createBooking|PartialUpdateBookingInvalid     |
    