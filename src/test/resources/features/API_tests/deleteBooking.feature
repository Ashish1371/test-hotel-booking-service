@createBooking
Feature: Delete Booking API 


@Regression @qa
  Scenario: Test to verify delete booking API works fine
    Given Booking service is up and running
    And Prepare request header and request body
    | key          | value            |
    | Content-Type | application/json |       
    When create booking API is called   
    Then validate response code is 200
    Then delete the bookingid
   Then validate response code is 201
   
   
   
   
   
  @Regression @qa
  Scenario: Test to verify delete booking API for non existing Booking ID
  
    Given Booking service is up and running
    And Prepare request header and request body
    | key          | value            |
    | Content-Type | application/json |       
    When create booking API is called   
    Then validate response code is 200
    Then delete the bookingid
    Then validate response code is 201
    Then delete the bookingid
    Then validate response code is 405