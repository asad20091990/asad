Feature: Find purchase order

  Scenario: Find purchase order by Id
    Given Request data  for finding purchase order
    When I am sending request for purchase order
    Then I should get successful response for purchase order
