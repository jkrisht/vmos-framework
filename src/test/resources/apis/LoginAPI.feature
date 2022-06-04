Feature: RegisterAccount

  @RegisterApiTest
  Scenario Outline: Validate login API functionality with valid credentials
    Given user <email> and <password> and make <API> call
    Then user should get <statusCode> with <field> in response

    Examples:
      | email              | password | API      | statusCode | field                                    |
      | vitamojo@gmail.com | vitap@ss | loginApi | 201        | payload.token.refresh,payload.user.email |