@createBooking
Feature: Create Booking API 
  
  @Regression @qa
  Scenario Outline: Test to verify create Booking API works fine
    Given Booking service is up and running
    And Prepare request header and request body
    | key          | value            |
    | Content-Type | application/json |
    When create booking API is called using input "<input>"
    Then validate response code is 200
    And validate json response
    |     Key         | value            |
    | firstname       | Sally              |
    | lastname        | Brown            |
    | totalprice      | 111              |
    | depositpaid     | true             |
    | additionalneeds | Breakfast        |
   Then validate response body for "<input>"
   Then delete the bookingid
   Then validate response code is 201
    Examples:
      |input  |
      |createBooking|


  @Regression @qa
  Scenario Outline: Test to verify create Booking API for invalid data
    Given Booking service is up and running
    And Prepare request header and request body
      | key          | value            |
      | Content-Type | application/json |
    When create booking API is called using input "<input>"
    Then validate response code is 500
    Examples:
      |input  |
      |createBookingInvalid|



   
   
   