Feature: Handled event smoke test

  Scenario: Handled event smoke test
    When I run "EventScenario"
    Then I wait to receive an error
    And the exception "errorClass" ends with "Exception"
    And the exception "message" equals "I expected this"
    And the event "context" equals "new context"
    And event 0 does not contain the feature flag "flag1"
    And event 0 does not contain the feature flag "flag2"
    And the error "Bugsnag-Api-Key" header equals "99999999999999909999999999999999"
    And event 0 contains the feature flag "flag 2" with variant "new flag 2"
    And the event "metaData.new_2.scenario" equals "EventScenario 2"
    And the event "user.id" equals "test123"
    And the event "user.email" equals "test@example.com"
    And the event "user.name" equals "Jonny User"

    # App data
    And the event "app.binaryArch" equals "arm64"
    And the event "app.id" equals "com.bugsnag.kmp.mazerunner.Mazerunner"
    And the event "app.releaseStage" equals "notify"
    And the event "app.version" equals "1.0"
    And the event "app.type" equals "iOS"
    And the error payload field "events.0.app.duration" is an integer
    And the error payload field "events.0.app.durationInForeground" is an integer
    And the event "app.inForeground" is true
    And the event "app.isLaunching" is true