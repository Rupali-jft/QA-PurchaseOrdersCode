Feature: Add Purchase Order Test-Cases

  @1596
  Scenario: Raise PO
    Given I log into the Purchase Orders app as an "Purchaseofficer"
    And I select "Pending Purchase Order" from the "Status" header in the grid
    And I navigate to the "Requests" tab
    And I click on the top work order link
    And I click the "Add Purchase Order" button
    Then Verify that Purchase order page opens
    Then The following elements exist
      | PO Date *               |
      | Vendor                  |
      | Subject                 |
      | Vendor Reference Number |
      | Reference               |
      | Vendor Address          |
    And I click the "Add Term" button
    Then Verify the table headers in "po_termsTable"
      | No                   |
      | Terms And Conditions |
      | Actions              |
    And I click the "Add Item" button
    Then Verify the table headers in "po_details"
      | Item / Service |
      | Part No *      |
      | Description    |
      | Qty            |
      | Unit Price     |
      | Total          |
      | Action         |
    And I enter "GIP" in the "Vendor Address" text box
    And I click the edit action
    And I enter "10" in the Part No field
    And I click the save action
    And I raise the purchase order

  @1597
  Scenario: Cancel Changes
    Given I log into the Purchase Orders app as an "Purchaseofficer"
    And I select "Pending Purchase Order" from the "Status" header in the grid
    And I navigate to the "Requests" tab
    And I click on the top work order link
    And I click the "Add Purchase Order" button
    Then The following elements exist
      | RAISE PO       |
      | Cancel Changes |
      | Close          |
    And I enter "GIP" in the "Vendor Address" text box
    And I click the "Cancel Changes" button
    And I click the "confirm" button
    Then Verify that "Vendor Address" text box is empty

  @1598
  Scenario: Close Button
    Given I log into the Purchase Orders app as an "Purchaseofficer"
    And I select "Pending Purchase Order" from the "Status" header in the grid
    And I navigate to the "Requests" tab
    And I click on the top work order link
    And I click the "Add Purchase Order" button
    Then The following elements exist
      | RAISE PO       |
      | Cancel Changes |
      | Close          |
    And I enter "GIP" in the "Vendor Address" text box
    And I click the "Close" button

  @1860
  Scenario: Print PDF
    Given I log into the Purchase Orders app as an "Purchaseofficer"
    And I navigate to the "Purchase Orders" tab
    And I click on the top work order link
    And I click the "Print PDF" button
    Then Verify that PDF preview opened in new tab
    Then Verify the fields present on the page
    And I click the "Print PDF" button
    Then Verify that PDF is downloading successfully

  @1861
  Scenario: Close PO
    Given I log into the Purchase Orders app as an "Purchaseofficer"
    And I select "Pending Purchase Order" from the "Status" header in the grid
    And I navigate to the "Requests" tab
    And I click on the top work order link
    And I click the "Add Purchase Order" button
    And I enter "GIP" in the "Vendor Address" text box
    And I click the edit action
    And I enter "10" in the Part No field
    And I click the save action
    And I raise the purchase order
    And I close the purchase order
    Then I verify that PO status is changed to "Purchase Order Closed"
    And I get the created Purchase Order Number

  @1906
  Scenario: Add Purchase Order button is not shown
    Given I log into the Purchase Orders app as an "Initiator"
    And I click the "Add Request" button
    And I enter the following information into the form
      | WO Title   | Test <current date> |
      | Location   | Bangalore           |
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
    And I click the "Submit" and "confirm" button
    And I Click on user icon
    And I click Logout button
    Given I log into the Purchase Orders app as an "Procurementmanager"
    And I open the created WO
    And I click on the "Quotes" link
    And I click the eye icon
    And I click the "Approve" button
    And I click the "confirm" button
    And I Click on user icon
    And I click Logout button
    Given I log into the Purchase Orders app as an "Approver"
    And I open the created WO
    And I click on the "Quotes" link
    And I click the eye icon
    And I click the "Approve" button
    And I click the "confirm" button
    And I Click on user icon
    And I click Logout button
    Given I log into the Purchase Orders app as an "Initiator"
    And I select "Pending Purchase Order" from the "Status" header in the grid
    And I navigate to the "Requests" tab
    And I click on the top work order link
    Then I verify that "Add Purchase Order" button "is not" displayed
    And I Click on user icon
    And I click Logout button
    Given I log into the Purchase Orders app as an "Approver"
    And I select "Pending Purchase Order" from the "Status" header in the grid
    And I navigate to the "Requests" tab
    And I click on the top work order link
    Then I verify that "Add Purchase Order" button "is not" displayed
    And I Click on user icon
    And I click Logout button
    Given I log into the Purchase Orders app as an "Procurementmanager"
    And I select "Pending Purchase Order" from the "Status" header in the grid
    And I navigate to the "Requests" tab
    And I click on the top work order link
    Then I verify that "Add Purchase Order" button "is not" displayed
    And I Click on user icon
    And I click Logout button
    Given I log into the Purchase Orders app as an "Purchaseofficer"
    And I select "Pending Purchase Order" from the "Status" header in the grid
    And I navigate to the "Requests" tab
    And I click on the top work order link
    Then I verify that "Add Purchase Order" button "is" displayed
    And I Click on user icon
    And I click Logout button

  @14806
  Scenario: User not allowed to select future date when raising Purchase order
    Given I log into the Purchase Orders app as an "Purchaseofficer"
    And I select "Pending Purchase Order" from the "Status" header in the grid
    And I navigate to the "Requests" tab
    And I click on the top work order link
    And I click the "Add Purchase Order" button
    Then Verify that Purchase order page opens
    Then Verify PO Date Pre-populates the current date
    Then Verify Future dates are disabled

