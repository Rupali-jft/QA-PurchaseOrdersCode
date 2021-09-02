Feature:PO  Login Functionality

  @1360
  Scenario: Log in to the PO application with a valid username and a valid password with different roles
    Given I log into the Purchase Orders app as an "Initiator"
    And I Click on user icon
    And I click Logout button
    Given I log into the Purchase Orders app as an "Approver"
    And I Click on user icon
    And I click Logout button
    Given I log into the Purchase Orders app as an "Purchaseofficer"
    And I Click on user icon
    And I click Logout button
    Given I log into the Purchase Orders app as an "procurementmanager"
    And I Click on user icon
    And I click Logout button
    Given I log into the Purchase Orders app as an "poguestdevuser"
    And I Click on user icon
    And I click Logout button
