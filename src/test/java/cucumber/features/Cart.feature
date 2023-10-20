Feature: Shopping Cart Functionality

  Scenario: Add Items to the Shopping Cart
    * call read('classpath:login.feature@SuccessLogin')
    When I click the Add to Cart button for a product
    Then the product should be added to the shopping cart
    And the shopping cart icon should display the correct item count

  Scenario: Remove Items from the Shopping Cart
    * call read('classpath:login.feature@SuccessLogin')
    When I click the Add to Cart button for a product
    Then the product should be added to the shopping cart
    When I click the Remove button for a product in the shopping cart
    Then the product should be removed from the shopping cart

