Feature: Customer register

  Scenario: Customer is not registered
    Given customer is unknown
    When customer is registered with success
    Then customer is known

  Scenario: Customer without document
    Given customer without document
    When customer is registered with fail
    Then customer has been unknown