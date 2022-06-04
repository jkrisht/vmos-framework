Feature: RegisterAccount

  @RegisterApiTest
  Scenario Outline: Validate register api functionality with new user details
    Given user details <emailPrefix>, <password> and make register <API> call
    Then user should get <statusCode> with <fields> in register api response

    Examples:
      | emailPrefix    | password | API         | statusCode | fields                 |
      | vitamojo | vitap@ss | registerApi | 201        | payload.token.refresh,payload.user.email |