Feature: Purchase Order from E commerce website
  As a user, I want to log into the application purchase the order.

Background:
 Given I landed on E commerce page


  Scenario Outline: Submit Order
    Given the user logged in <username> with <password>
    When Select the <product>
    And Checkout <product> and submit the order
    Then "Thankyou for the order."  validate the confirmation

  Examples:
  |username         |password         |product|
  |sravank@gmail.com| Kumar@520        | ZARA COAT 3|
