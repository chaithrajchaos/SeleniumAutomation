
@tag
Feature: Purchase order from ecommerce Website

Background:
Given I landed on ecommerce page

  @tag2
  Scenario Outline:Positive test of submitting the order
    Given I logged in with <username> and <Password>
    When I add the <productname> from cart
    And Checkout <productname> and submit the order
    Then verify the confirmation message is displayed

    Examples: 
      | username                         | password      | productname     |
      |  chaithrakala1998@gmail.com      |  1998@20Chai  |  IPHONE 13 PRO  |
      |  chaithrakala1998@gmail.com      |  1998@20Chai  |  IPHONE 13 PRO  |
