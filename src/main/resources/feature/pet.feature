Feature: Pet store api test

  Scenario: Add pet with valid data
    Given Adding pet with valid data
    When Send request for add pet
    Then Get successful response for add pet

