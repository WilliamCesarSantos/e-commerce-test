Feature: Customer register

  Scenario: Customer is not registered
    Given customer is unknown
    When customer is registered with success
    Then customer is known

  Scenario: Customer without document
    Given customer without document
    When customer failed to register
    Then notify document must be not null
    And customer is still unknown