Feature: RegisterAccount

  @RegisterTest
  Scenario Outline: Validate register new user functionality with valida credentials
    Given user is on login page
    Then user switch to create account tab
    When user enters <emailPrefix> and <password> and clicks create account button
    Then user navigated to the HomePage with <emailPrefix> as profile name

    Examples:
      | emailPrefix | password |
      | vitamojo    | vitap@ss |