Feature: Unhandled error smoke test

  Scenario: Unhandled error smoke test
    When I run "UnhandledExceptionScenario"
    And I wait for 2 seconds
    And the exception "message" starts with "this is a large crisis"
    And the exception "errorClass" equals "RuntimeException"
