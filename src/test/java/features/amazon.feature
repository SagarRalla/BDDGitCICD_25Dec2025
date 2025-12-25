
Feature: Amazon product search
  Scenario: Search for a product
    Given user is on Amazon homepage
    When user searches for "laptop"
    Then search results for "laptop" should be displayed
