Feature: Test the service layer
  Scenario: Get all the items in the Database
    Given Create record in the db
    When Bring all the records in the db
    Then The status must be OK
    And The size must be 2