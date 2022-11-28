@getBookings
Feature: Get bookings   
  
  
  @Regression @qa
  Scenario: Test to verify get bookings all ids
    Given Booking service is up and running  
    When get bookings API is called
    Then validate response code is 200
		
		

		
	@Regression @qa
  Scenario: Test to verify get bookings
    Given Booking service is up and running 
    And Prepare request header and request body
    | key          | value            |
    | Content-Type | application/json | 
    | Accept       | application/json |
    When create booking API is called with data
    |     Key         | value            |
    | firstname       | testuser         |
    | lastname        | sur              |
    | totalprice      | 111              |
    | depositpaid     | true             |
    | additionalneeds | Breakfast        |
    Then validate response code is 200 
    When get bookings API is called with filter
    | Key      | value    |
    | firstname | testuser | 
    | lastname  | sur      |
    Then validate response code is 200
    Then validate booking id is present in response
    Then delete the bookingid
    Then validate response code is 201
    
    
    
   