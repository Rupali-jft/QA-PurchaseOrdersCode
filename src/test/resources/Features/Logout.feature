Feature: PO Logout Functionality

  @13387
  Scenario:Logout
    Given I log into the Purchase Orders app as an "Initiator"
    And I will be taken to the homepage for that app
    When I Click on user icon
    And I click Logout button
    Then I am redirected to login page