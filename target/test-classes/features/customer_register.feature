Feature: Customer register

  Scenario: Customer is not registered
    Given customer is unknown
    When customer is registered with success
    Then customer is known