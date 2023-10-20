Feature: Authentication in Swaglabs

  Scenario: Successful Login
    Given I open the Swaglab site login page
    When  input a valid username into the Username field
    And  input the correct password into the Password field
    And  click the Login button
    Then should show page products

  Scenario: User Success Logout
    Given I open the Swaglab site login page
    When  input a valid username into the Username field
    And  input the correct password into the Password field
    And click the Login button
    And I click bars on the top left
    And I click button logout
    Then should show login page

  Scenario: Login with Invalid Password
    Given I open the Swaglab site login page
    When input a valid username into the Username field
    And input an invalid password into the Password field
    And click the Login button
    Then should see an error message

