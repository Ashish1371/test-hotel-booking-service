@PartialupdateBooking
Feature: update Booking API
  
  @Regression @qa
  Scenario: Test to verify PATCH partial update booking works fine
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
    
    When partial update booking API is called with data
    |     Key         | value            |
    | firstname       | Jimupdated       |
    | lastname        | Brownupdated     |
    | totalprice      | 100              |
   
   	Then validate response code is 200
    Then delete the bookingid
    Then validate response code is 201
    