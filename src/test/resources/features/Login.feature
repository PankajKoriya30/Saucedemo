Feature: Sauce demo automation

  @smoke
  Scenario: Verify login feature with valid credentials
    Given user is on the sauce demo login page
    When user enters valid credentials
    Then user redirects to products page
