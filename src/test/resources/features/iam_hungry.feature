Feature: I'm hungry
  Scenario: Eating pizza
    Given William is hungry
    When William eats pizza
    Then William will be happy

  Scenario: Don't eating anything
    Given William is hungry
    When William will not have dinner
    Then William will be hungry tonight
