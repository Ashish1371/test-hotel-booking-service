@createBooking
Feature: Delete Booking API 


@Regression @qa
  Scenario Outline: Test to verify delete booking API works fine
  Given Booking service is up and running
  And Prepare request header and request body
    | key          | value            |
    | Content-Type | application/json |
  When create booking API is called using input "<input>"
  Then validate response code is 200
  Then delete the bookingid
  Then validate response code is 201
  Examples:
    |input  |
    |createBooking|



  @Regression @qa
  Scenario Outline: Test to verify delete booking API for non existing Booking ID

    Given Booking service is up and running
    And Prepare request header and request body
      | key          | value            |
      | Content-Type | application/json |
    When create booking API is called using input "<input>"
    Then validate response code is 200
    Then delete the bookingid
    Then validate response code is 201
    Then delete the bookingid
    Then validate response code is 405
    Examples:
      |input  |
      |createBooking|