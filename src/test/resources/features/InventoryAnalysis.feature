Feature: TC2 - Inventory Analysis (Complex Data Parsing)

  Background:
    Given the base URL is configured

  Scenario: Verify available pet count matches between inventory and findByStatus
    When I send a GET request to fetch the store inventory
    Then the response status code should be 200
    And I extract the available count from inventory

    When I send a GET request to find pets by status "available"
    Then the response status code should be 200
    And the count of pets returned should match the inventory available count