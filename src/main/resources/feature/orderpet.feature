Feature: Place order for pet

  Scenario: Placing order for pet
    Given I am sending request data for order pet
    When I am sending order request
    Then I should get successful response for order pet