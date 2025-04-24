Feature: Handled error smoke test

  Scenario: Handled error smoke test
    When I run "NotifyScenario"
    Then I wait to receive an error
    And the exception "errorClass" ends with "Exception"
    And the exception "message" equals "I expected this"
    And the event "context" equals "running context"
    And the event "user.id" equals "xyz321"
    And the event "user.email" equals "noreply@example.com"
    And the event "user.name" equals "User McUserface"
    And the event "app.releaseStage" equals "notify"
    And the event "metaData.test.scenario" equals "NotifyScenario"
    And the event "metaData.test.config" equals "configuration"
    And the event "metaData.test_data.string" equals "a string"
    And the event "metaData.test_data.number" equals 123
    And the event "metaData.test_data.boolean" equals 1
    And the event "metaData.test_data.array.0" equals "a"
    And the event "metaData.test_data.array.1" equals "b"
    And the event "metaData.test_data.array.2" equals "c"
    And the event "metaData.test_data.map.a" equals "b"
    And the event "metaData.test_data.map.c" equals "d"
    And the event "metaData.test.badValue" is null
    And event 0 contains the feature flag "flag2" with variant "featureFlag2"
    And event 0 does not contain the feature flag "flag1"
