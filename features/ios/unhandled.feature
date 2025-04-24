Feature: Unhandled error smoke test

  Scenario: Unhandled error smoke test
    When I run "UnhandledExceptionScenario" and relaunch the crashed app
    And I configure Bugsnag for "UnhandledExceptionScenario"
    Then I wait to receive an error
    And the exception "message" starts with "this is a large crisis"
    And the exception "errorClass" equals "kotlin.RuntimeException"

    And the error payload field "apiKey" equals "f005ba11abacaf005ba11abacaf005ba"
    And the event "app.version" equals "test_app_version"
    And the event "usage.config.launchDurationMillis" equals 1234
    And the event "context" equals "test_context_2"
    And the event "user.id" equals "456"
    And the event "user.name" equals "Amy"
    And the event "user.email" equals "user_amy@example.com"

    And the event "metaData.custom_data_1.key" equals "something_else"
    And the event "metaData.custom_data_1.data" is null

    And the event "metaData.custom_data_1.key" equals "something_else"
    And event 0 does not contain the feature flag "feature_flag_1"
    And event 0 contains the feature flag "feature_flag_2" with variant "2"
    And event 0 contains the feature flag "feature_flag_3" with no variant

    And the event "metaData.custom_data_2.after_start_2" equals "hello"
    And the event "metaData.custom_data_3" is null
    And event 0 contains the feature flag "feature_flag_4" with no variant
    And event 0 contains the feature flag "feature_flag_6" with variant "6"
