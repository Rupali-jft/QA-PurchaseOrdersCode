Feature:Patra Shipping and Billing Address

  @14939
  Scenario: Validate "Shipping and Billing" Address for Patra "Bhilai" location.
    Given I log into the Purchase Orders app as an "Initiator"
    And I click the "Add Request" button
    And I enter the following information into the form
      | WO Title   | Test <current date> |
      | Location   | Bhilai              |
      | Department | IT                  |
    And I add the following into the "Item / Service Details" table
      | Item / Service Name | Description              | Quantity |
      | Test                | Test Item <current date> | 1        |
    And I submit the new request
    And I click on "Confirm" and "Yes" button
    And I Click on user icon
    And I click Logout button
    Given I log into the Purchase Orders app as an "Approver"
    And I navigate to the "Pending Approval" tab
    And I open the created WO
    And I approve the request
    And I Click on user icon
    And I click Logout button
    Given I log into the Purchase Orders app as an "Purchaseofficer"
    And I open the created WO
    And Click on Add Quote by Purchase Officer Button
    And I set the quote date in the datepicker
    Then Input values in the Quotes pop-up
      | QuoteTitle | QuoteVendor | QuotedPrice |
      | keyboard   | Apple       | 9000        |
    And I click the "Total Price" box
    And I click on "Submit" button
    And I Click on user icon
    And I click Logout button
    Given I log into the Purchase Orders app as an "Procurementmanager"
    And I open the created WO
    And I click on the "Quotes" link
    And I click the eye icon
    And I click on "Approve" button
    And I Click on user icon
    And I click Logout button
    Given I log into the Purchase Orders app as an "Approver"
    And I open the created WO
    And I click on the "Quotes" link
    And I click the eye icon
    And I click on "Approve" button
    And I Click on user icon
    And I click Logout button
    Given I log into the Purchase Orders app as an "Purchaseofficer"
    And I open the created WO
    And I click the "Add Purchase Order" button
    And I enter "GIP" in the "Vendor Address" text box
    And I click the edit action
    And I enter "10" in the Part No field
    And I click the save action
    And I raise the purchase order
    And I close the purchase order
    And I click the "Print PDF" button
    Then Verify the "Shipping and Billing" Address for Patra "Bhilai" location