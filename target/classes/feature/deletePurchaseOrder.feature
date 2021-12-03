Feature: Delete purchase order
  Scenario: Delete purchase order by id
    Given Request data for delete purchase order
    When I send request for delete purchase order
    Then I should get successful response for delete purchase order