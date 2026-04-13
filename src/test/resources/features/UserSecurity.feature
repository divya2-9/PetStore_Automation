Feature: TC3 - User Security and Error Handling (Negative Testing)

  Background:
    Given the base URL is configured

  Scenario Outline: Create a user with an invalid email format
    When I send a POST request to create a user with username "<username>" email "<email>" and password "<password>"
    Then the create user response status code should be 200

    Examples:
      | username      | email         | password  |
      | testUser001   | invalid_email | pass123   |

  Scenario Outline: Fetch a user that does not exist
    When I send a GET request to fetch user "<username>"
    Then the response status code should be 404
    And the response message should contain "User not found"

    Examples:
      | username             |
      | nonExistentUser12345 |

  Scenario Outline: Login with incorrect credentials
    When I send a GET request to login with username "<username>" and password "<password>"
    Then the response status code should be 200
    And the response should not contain a valid session token

    Examples:
      | username    | password      |
      | wrongUser   | wrongPassword |