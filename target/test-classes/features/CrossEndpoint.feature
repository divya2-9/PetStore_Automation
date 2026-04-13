Feature: TC4 - Cross-Endpoint Data Consistency

  Background:
    Given the base URL is configured

  Scenario Outline: Created pet appears in sold list after status update
    Given I create a pet with name "<petName>" category "<categoryName>" and status "<initialStatus>"
    Then the response status code should be 200
    And I extract the pet ID from the response

    When I send a PUT request to update the pet status to "<updatedStatus>"
    Then the response status code should be 200

    When I send a GET request to fetch the store inventory
    Then the response status code should be 200

    When I send a GET request to find pets by status "<updatedStatus>"
    Then the response status code should be 200
    And the created pet ID should be present in the "<updatedStatus>" pets list

    Examples:
      | petName          | categoryName     | initialStatus | updatedStatus |
      | HighValueBulldog | HighValueBulldog | available     | sold          |
