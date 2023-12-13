Feature: I'm hungry
  Scenario: Eating pizza
    Given William is hungry
    When William eats pizza
    Then William will be happy

  Scenario: Don't eating anythin
    Given William is hungry
    When William eats pizza
    Then William will be hungry tonight