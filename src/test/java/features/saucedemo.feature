Feature: Saucedemo E2E Flow

  Scenario: Verify Saucedemo Title and login to the system
    Given I open the Saucedemo website and see the title as "Swag Labs"
    When I provide the username and password I click the login button
      | fields   | value         |
      | username | standard_user |
      | password | secret_sauce  |
    Then I should successfully log into the Saucedemo website

  Scenario: Add Multiple Items to the Cart
    Given I am logged in
    When I add multiple items to the cart
    Then those items should appear in my cart

  Scenario: Complete the Checkout Process
    Given I have items in my cart
    When I proceed to checkout and provide necessary details
    And I finish the order
    Then I should see the message "Thank you for your order!"
