Feature: Filter Product

  Scenario: Filter products from high to low price
    Given the user successfully logs in
    When the user clicks on the filter option
    And selects Price High to Low
    Then the product page should display products with the highest price