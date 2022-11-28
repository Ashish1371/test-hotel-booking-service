@createBooking
Feature: Create Booking API 
  
  @Regression @qa
  Scenario: Test to verify create Booking API works fine
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
    And validate json response
    |     Key         | value            |
    | firstname       | Jim              |
    | lastname        | Brown            |
    | totalprice      | 111              |
    | depositpaid     | true             |
    | additionalneeds | Breakfast        |
   Then delete the bookingid
   Then validate response code is 201
    
   
   
	
	@Regression @qa
  Scenario: Test to verify create Booking API works fine with data created at run time
    Given Booking service is up and running
    And Prepare request header and request body
    | key          | value            |
    | Content-Type | application/json |       
    When create booking API is called   
    Then validate response code is 200
    Then delete the bookingid
   Then validate response code is 201
    
    


 