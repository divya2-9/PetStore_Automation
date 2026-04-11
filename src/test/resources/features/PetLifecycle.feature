Feature: Pet Store API Lifecycle

  Scenario: Create, Retrieve, and Delete a Pet
    Given I have pet details with name "Buddy" and status "available"
    When I perform a POST request to "/pet"
    Then the status code should be 200
    And I extract the pet ID from the response
    When I perform a GET request for that pet ID
    Then the name should be "Buddy" and status "available"
    When I perform a DELETE request for that pet ID
    Then the status code should be 200
    And a GET request for that pet ID should return 404