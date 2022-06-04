Feature: Login

  @LoginTest
  Scenario Outline: Validate login functionality with valida credentials
    Given user is on login page
    When user enters <email> and <password> and clicks login button
    Then user navigated to the HomePage with <username> as profile name

    Examples:
      | email              | password | username |
      | vitamojo@gmail.com | vitap@ss | Test     |