@tag
Feature: Order creation on E-commerce application
  I want to create an order

  Background: 
    Given I landed on E-commerce page

  @Regression
  Scenario Outline: Positive scenario of Order creation 
    Given logged in with username <name> and password <password>  
    When I add item <productName> to the cart
    And I checkout <productName> and complete order creation <countryName>
    Then "THANKYOU FOR THE ORDER." message displays  

    Examples: 
      | name                  | password     | productName   | countryName |
      | rkreddy0333@gmail.com | Ravirama12@  | ZARA COAT 3   | India       |