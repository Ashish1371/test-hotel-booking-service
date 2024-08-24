@getBookings
Feature: Get bookings   
  
  
  @Regression @qa
  Scenario: Test to verify get bookings all ids
    Given Booking service is up and running  
    When get bookings API is called
    Then validate response code is 200
    Then validate bookings API
		
		

		
	@Regression @qa
  Scenario Outline: Test to verify get bookings
    Given Booking service is up and running 
    And Prepare request header and request body
    | key          | value            |
    | Content-Type | application/json | 
    | Accept       | application/json |
     When create booking API is called using input "<input>"
    Then validate response code is 200 
    When get bookings API is called using filter "<getinput>"
    Then validate response code is 200
    Then validate booking id is present in response
    Then delete the bookingid
    Then validate response code is 201
      Examples:
        |input           |getinput       |
        |createBooking    |getBookingFilter |
    
    
    
   