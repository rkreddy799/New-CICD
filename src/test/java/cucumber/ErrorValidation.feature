
@tag
Feature: Login Error validation
  Login Error validation



  @Regression
  Scenario Outline: Login Error Validation
    Given I landed on E-commerce page
    When logged in with username <name> and password <password> 
    Then verify error message display


Examples: 
      | name                  | password   | 
      | Rkreddy0333@gmail.com | Ravirama12|
