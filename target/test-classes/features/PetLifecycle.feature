Feature: TC1 - Pet Lifecycle (CRUD and Chaining)

  Background:
    Given the base URL is configured

  Scenario Outline: Create, retrieve, update and delete a pet
    Given I have a pet with name "<petName>" and status "<initialStatus>"
    When I send a POST request to create the pet
    Then the response status code should be 200
    And I extract the pet ID from the response

    When I send a GET request to retrieve the pet by ID
    Then the response status code should be 200
    And the pet name should be "<petName>"
    And the pet status should be "<initialStatus>"

    When I send a PUT request to update the pet status to "<updatedStatus>"
    Then the response status code should be 200

    When I send a DELETE request to delete the pet
    Then the response status code should be 200
    And the deleted pet should not be retrievable

    Examples:
      | petName   | initialStatus | updatedStatus |
      | BuddyTest | available     | sold          |
      | MaxTest   | pending       | sold          |
