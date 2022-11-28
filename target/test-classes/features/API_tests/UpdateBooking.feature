@updateBooking
Feature: update Booking API
  
  @Regression @qa
  Scenario: Test to verify PUT update booking API works fine
    Given Booking service is up and running
    And Prepare request header and request body
    | key          | value            |
    | Content-Type | application/json |   
    When create booking API is called with data
    |     Key         | value            |
    | firstname       | Jim              |
    | lastname        | Brown            |
    | totalprice      | 111              |
    | depositpaid     | true             |
    | additionalneeds | Breakfast        |
    Then validate response code is 200   
    
    When update booking API is called with data
    |     Key         | value            |
    | firstname       | Jimaaaaa         |
    | lastname        | Brownaaa         |
    | totalprice      | 100              |
    | depositpaid     | true             |
    | additionalneeds | Breakfast        |
    Then validate response code is 200
    And validate json response for getbookingId API
    |     Key         | value            |
    | firstname       | Jimaaaaa         |
    | lastname        | Brownaaa         |
    | totalprice      | 100              |
    | depositpaid     | true             |
    | additionalneeds | Breakfast        |
    Then delete the bookingid
    Then validate response code is 201
    