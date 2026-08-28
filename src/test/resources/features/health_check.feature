@smoke
Feature: Framework health check

  Scenario: Cucumber is configured successfully
    Given the API automation framework is initialized
    Then the framework should be ready to run tests