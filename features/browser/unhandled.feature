Feature: Unhandled error smoke test

  Scenario: Unhandled error smoke test
    When I run "UnhandledExceptionScenario" in the browser
    Then I wait to receive an error
    And the exception "message" starts with "this is a large crisis"
    And the exception "errorClass" ends with "RuntimeException"
