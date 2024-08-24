@getBooking
Feature: Get bookingAPI 
  
  @Regression @qa
  Scenario Outline: Test to verify get booking by id works fine
    Given Booking service is up and running
    And Prepare request header and request body
      | key          | value            |
      | Content-Type | application/json |
    When create booking API is called using input "<input>"
    Then validate response code is 200
    When getbookingId API is called
    | key          | value            |
    | Accept       | application/json | 
    Then validate response code is 200
    And validate json response for getbookingId API
    |     Key         | value            |
    | firstname       | Sally            |
    | lastname        | Brown            |
    | totalprice      | 111              |
    | depositpaid     | true             |
    | additionalneeds | Breakfast        |
    Then validate get booking response body for "<input>"
   Then delete the bookingid
   Then validate response code is 201
    Examples:
      |input  |
      |createBooking|


  @Regression @qa
  Scenario Outline: Test to verify get booking ids works fine
    Given Booking service is up and running
    And Prepare request header and request body
      | key          | value            |
      | Content-Type | application/json |
    When create booking API is called using input "<input>"
    Then validate response code is 200
    When getbookingId API is called
      | key          | value            |
      | Accept       | application/json |
    Then validate response code is 200
    And validate json response for getbookingId API
      |     Key         | value            |
      | firstname       | Sally            |
      | lastname        | Brown            |
      | totalprice      | 111              |
      | depositpaid     | true             |
      | additionalneeds | Breakfast        |
    Then validate get booking response body for "<input>"
    Then delete the bookingid
    Then validate response code is 201
    Examples:
      |input  |
      |createBooking|



 