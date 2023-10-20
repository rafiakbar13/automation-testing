Feature: Shopping Process

  Scenario: Complete Shopping Process
    Given the user on the product page
    When the user clicks the Add to Cart button
    And the user clicks the cart icon on the top right side
    And the user clicks the Checkout button
    And the user is on the buyer's biodata form
    When the user fills out the biodata form
    And the user clicks the Continue button
    And the user clicks the Finish button
    Then checkout completed

  Scenario: Checkout Without Filling Buyer's Biodata
    Given the user on the product page
    When the user clicks the Add to Cart button
    And the user clicks the cart icon on the top right side
    And the user clicks the Checkout button
    And the user clicks the Continue button
    Then the user should be warned that buyer's biodata is required
