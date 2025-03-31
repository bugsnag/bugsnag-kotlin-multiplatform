Feature: Unhandled error smoke test

  Scenario: Unhandled error smoke test
    When I run "UnhandledExceptionScenario" and relaunch the crashed app
    And I configure Bugsnag for "UnhandledExceptionScenario"
    Then I wait to receive an error
    And the exception "message" starts with "this is a large crisis"
    And the exception "errorClass" equals "kotlin.RuntimeException"
